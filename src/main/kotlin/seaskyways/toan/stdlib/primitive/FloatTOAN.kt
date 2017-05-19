package seaskyways.toan.stdlib.primitive

import seaskyways.toan.TOANMapper
import seaskyways.toan.spec.TOANType

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
object FloatTOAN : TOANType<Float> {
    override val typeTag: String = "F"
    override fun fromString(mapper: TOANMapper, string: String) = string.toFloat()
}