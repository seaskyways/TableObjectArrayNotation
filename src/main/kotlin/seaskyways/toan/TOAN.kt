package seaskyways.toan

import seaskyways.toan.spec.TOANType

/**
 * Created by Ahmad Al-Sharif on 23/05 - May/17.
 */

inline fun <reified T> TOANType<T>.arrayTOAN() = object : TOANType<T> {
    override val typeTag: String = this@arrayTOAN.typeTag

    override fun fromString(mapper: TOANMapper, string: String): T = this@arrayTOAN.fromString(mapper, string)

    override fun toString(mapper: TOANMapper, value: Any?) = this@arrayTOAN.toString(mapper, value)

    override val isArray = true
}

inline fun <reified T> TOANType<T>.nullableTOAN() = object : TOANType<T> {
    override val typeTag: String = this@nullableTOAN.typeTag

    override fun fromString(mapper: TOANMapper, string: String): T = this@nullableTOAN.fromString(mapper, string)

    override fun toString(mapper: TOANMapper, value: Any?) = this@nullableTOAN.toString(mapper, value)

    override val isNullable = true
}