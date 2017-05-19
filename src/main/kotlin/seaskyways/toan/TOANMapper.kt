package seaskyways.toan

import seaskyways.toan.spec.TOANConstants.nullableIdentifier
import seaskyways.toan.spec.TOANObjectArchitecture
import seaskyways.toan.spec.TOANSerializable

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */

class TOANMapper {

    fun architectureToHeader(toanArchitecture: TOANObjectArchitecture): String {
        return buildString {
            append(toanArchitecture.tag)
            if (toanArchitecture.nullable) append("?")
            append("(")
            if (!toanArchitecture.architecture.isEmpty()) {
                val lastIndex = toanArchitecture.architecture.size - 1
                toanArchitecture.architecture.forEachIndexed { i, (key, value, nullable) ->
                    append(key)
                    append("=")
                    append(value.typeTag)
                    if (nullable) append(nullableIdentifier)
                    if (i != lastIndex) append(",")
                }
            }
            append(")")
        }
    }

    fun objectToRow(toanSerializable: TOANSerializable): String = buildString {

    }
}