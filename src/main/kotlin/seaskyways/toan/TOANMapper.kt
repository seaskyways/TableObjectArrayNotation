package seaskyways.toan

import seaskyways.toan.spec.TOANConstants
import seaskyways.toan.spec.TOANConstants.arrayEnd
import seaskyways.toan.spec.TOANConstants.arrayStart
import seaskyways.toan.spec.TOANConstants.newLine
import seaskyways.toan.spec.TOANConstants.next
import seaskyways.toan.spec.TOANConstants.nullableIdentifier
import seaskyways.toan.spec.TOANConstants.separator
import seaskyways.toan.spec.TOANConstants.typeOperator
import seaskyways.toan.spec.TOANConstants.typePropertiesEnd
import seaskyways.toan.spec.TOANConstants.typePropertiesStart
import seaskyways.toan.spec.TOANObjectArchitecture
import seaskyways.toan.spec.TOANSerializable

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */

class TOANMapper {

    fun architectureToHeader(toanArchitecture: TOANObjectArchitecture): String {
        return buildString {
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
    }

    fun objectToRow(toanSerializable: TOANSerializable): String = buildString {
        toanSerializable.valueList.zip(toanSerializable.architecture)
                .forEach { (any, toan) ->
                    append(toan.toString(this@TOANMapper, any))
                    append(separator)
                }
        deleteCharAt(lastIndex)
    }

    fun writeValueAsString(
            baseTOANArchitecture: TOANObjectArchitecture,
            architecture: Array<TOANObjectArchitecture>,
            toans: Array<TOANSerializable>
    ) = buildString {
        val lastIndex = architecture.lastIndex
        architecture.forEachIndexed { i, toanObjectArchitecture ->
            append(architectureToHeader(toanObjectArchitecture))
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
        toans.forEach {
            append(objectToRow(it))
            append(TOANConstants.newLine)
        }
    }

//    private fun validateTOANSerializable(toanSerializable: TOANSerializable){
//        if (toanSerializable.architecture.size != toanSerializable.valueList)
//    }
}