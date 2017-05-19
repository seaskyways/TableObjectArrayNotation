package seaskyways.toan.stdlib.primitive

import seaskyways.toan.TOANMapper
import seaskyways.toan.spec.TOANType

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
object IntTOAN : TOANType<Int> {
    override val typeTag: String = "I"
    override fun fromString(mapper: TOANMapper, string: String): Int = string.toInt()
}