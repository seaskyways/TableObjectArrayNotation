package seaskyways.toan

import com.sun.corba.se.impl.oa.toa.TOA
import seaskyways.toan.spec.TOANConstants
import seaskyways.toan.spec.TOANConstants.arrayEnd
import seaskyways.toan.spec.TOANConstants.arrayStart
import seaskyways.toan.spec.TOANConstants.newLine
import seaskyways.toan.spec.TOANConstants.next
import seaskyways.toan.spec.TOANConstants.nullValue
import seaskyways.toan.spec.TOANConstants.nullableIdentifier
import seaskyways.toan.spec.TOANConstants.separator
import seaskyways.toan.spec.TOANConstants.typeOperator
import seaskyways.toan.spec.TOANConstants.typePropertiesEnd
import seaskyways.toan.spec.TOANConstants.typePropertiesStart
import seaskyways.toan.spec.TOANObjectArchitecture
import seaskyways.toan.spec.TOANSerializable
import seaskyways.toan.spec.TOANType
import java.util.concurrent.*
import kotlin.concurrent.thread

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */

class TOANMapper {

    fun StringBuilder.architectureToHeader(toanArchitecture: TOANObjectArchitecture) = apply {
        append(toanArchitecture.tag)
        if (toanArchitecture.nullable) append(nullableIdentifier)
        append(typePropertiesStart)
        if (!toanArchitecture.architecture.isEmpty()) {
            val lastIndex = toanArchitecture.architecture.size - 1
            toanArchitecture.architecture.forEachIndexed { i, (key, value, nullable) ->
                append(key)
                append(typeOperator)
                append(value.typeTag)
                if (nullable) append(nullableIdentifier)
                if (i != lastIndex) append(separator)
            }
        }
        append(typePropertiesEnd)
    }

    fun architectureToHeader(toanArchitecture: TOANObjectArchitecture) = buildString { this.architectureToHeader(toanArchitecture) }

    fun objectToRow(toanSerializable: TOANSerializable): String = buildString { objectToRow(toanSerializable, this) }
    fun objectToRow(toanSerializable: TOANSerializable, stringBuilder: StringBuilder, lastChar: Char? = null) = stringBuilder.apply {
        toanSerializable.valueList.zip(toanSerializable.architecture)
                .forEach { (any, toan) ->
                    when {
                        toan.isNullable && any == null -> append(nullValue)
                        toan.isArray -> arrayToRow(any as List<*>, stringBuilder)
                        else -> append(toan.toString(this@TOANMapper, any))
                    }
                    this.append(separator)
                }
        this.deleteCharAt(this.lastIndex)
        lastChar?.let { append(lastChar) }
    }

    fun arrayToRow(value: List<*>, builder: StringBuilder): Unit = with(builder) {
        append("[${value.size}]>")
        value.map { it as TOANSerializable }
                .forEach { objectToRow(it, this, ',') }
        deleteCharAt(lastIndex)
    }

    private fun StringBuilder.appendToanList(toans: Array<TOANSerializable>, parallelism: Int = 1) {
        if (parallelism == 1) {
            toans.forEach {
                objectToRow(it, this)
                append(TOANConstants.newLine)
            }
        } else {
            val threadPool = Executors.newFixedThreadPool(parallelism)
            val missAmount = toans.size % parallelism
            val amountPerList = (toans.size - missAmount) / parallelism
            val toanArrays = Array<Array<TOANSerializable>?>(parallelism) { null }
            for (i in toanArrays.indices) {
                val additional = if (i == toanArrays.lastIndex) missAmount else 0
                toanArrays[i] = toans.sliceArray((i * amountPerList) until ((i * amountPerList) + amountPerList + additional))
            }
            List(parallelism) {
                threadPool.submit {
                    val arr = toanArrays[it]
                    buildString {
                        println("start working with index $it")
                        arr?.forEach {
                            objectToRow(it, this@buildString)
                            append(TOANConstants.newLine)
                        }
                    }
                } to it
            }.parallelStream()
                    .forEach { (it, index) ->
                        append(it.get())
                        println("done with $index")
                    }
            threadPool.shutdown()
        }
    }

    fun writeValueAsString(
            baseTOANArchitecture: TOANObjectArchitecture,
            architecture: Array<TOANObjectArchitecture>,
            toans: Array<TOANSerializable>,
            parallelism: Int = 1
    ) = buildString {
        val lastIndex = architecture.lastIndex
        architecture.forEachIndexed { i, toanObjectArchitecture ->
            architectureToHeader(toanObjectArchitecture)
            if (i != lastIndex) append(newLine)
        }
        append(next)
        append(newLine)
        append(baseTOANArchitecture.tag)
        append(arrayStart)
        append(toans.size)
        append(arrayEnd)
        append(next)
        append(newLine)
        appendToanList(toans, parallelism)
    }

//    private fun validateTOANSerializable(toanSerializable: TOANSerializable){
//        if (toanSerializable.architecture.size != toanSerializable.valueList)
//    }
}