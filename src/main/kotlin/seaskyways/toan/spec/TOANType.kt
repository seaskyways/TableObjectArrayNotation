package seaskyways.toan.spec

import seaskyways.toan.TOANMapper
import seaskyways.toan.spec.TOANConstants.nullValue

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
interface TOANType<T> {
    val typeTag: String

    fun fromString(mapper: TOANMapper, string: String): T
    fun toString(mapper: TOANMapper, value: Any?): String = (value ?: nullValue).toString()
}