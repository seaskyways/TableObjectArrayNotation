package seaskyways.toan.spec

import seaskyways.toan.TOANMapper
import seaskyways.toan.spec.TOANConstants.nullValue

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 * TOANType is the base interface any object that has to be part of another should implement.
 * e.g:
 * for
 * class Person(val id:Int, val name: String)
 *
 *
 */
interface TOANType<T> {
    val typeTag: String
    val isArray: Boolean get() = false
    val isNullable: Boolean get() = false
    fun fromString(mapper: TOANMapper, string: String): T
    fun toString(mapper: TOANMapper, value: Any?): String = (value ?: nullValue).toString()
}