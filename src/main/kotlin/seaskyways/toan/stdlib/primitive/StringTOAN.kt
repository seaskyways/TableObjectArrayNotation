package seaskyways.toan.stdlib.primitive

import seaskyways.toan.spec.TOANType

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
object StringTOAN : TOANType<String> {
    override val typeTag: String = "S"
    override fun fromString(string: String) = string
}