package seaskyways.toan.stdlib.primitive

import seaskyways.toan.TOANMapper
import seaskyways.toan.spec.TOANConstants.nullValue
import seaskyways.toan.spec.TOANType

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
object StringTOAN : TOANType<String> {
    override val typeTag: String = "S"
    override fun fromString(mapper: TOANMapper, string: String): String = string
    override fun toString(mapper: TOANMapper, value: Any?) = (value as? String ?: nullValue.toString())
}