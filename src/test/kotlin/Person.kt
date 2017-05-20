import seaskyways.toan.TOANMapper
import seaskyways.toan.spec.TOANObjectArchitecture
import seaskyways.toan.spec.TOANSerializable
import seaskyways.toan.spec.TOANType
import seaskyways.toan.spec.TypedKey
import seaskyways.toan.stdlib.primitive.FloatTOAN
import seaskyways.toan.stdlib.primitive.IntTOAN
import seaskyways.toan.stdlib.primitive.StringTOAN

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
data class Person(val name: String, val description: String, val age: Int, val height: Float) : TOANSerializable {
    companion object : TOANType<Person> {
        val toanArchitecture = object : TOANObjectArchitecture {
            override val tag: String
                get() = "Person"

            override val architecture: Set<TypedKey<*>> = setOf(
                    TypedKey("name", StringTOAN),
                    TypedKey("desc", StringTOAN, true),
                    TypedKey("age", IntTOAN),
                    TypedKey("height", FloatTOAN)
            )
        }

        override val typeTag: String = toanArchitecture.tag

        override fun fromString(mapper: TOANMapper, string: String): Person {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun toString(mapper: TOANMapper, value: Any?): String {
            return mapper.objectToRow(value as Person)
        }
    }

    override val architecture: List<TOANType<*>> = listOf(StringTOAN, StringTOAN, IntTOAN, FloatTOAN)
    override val valueList: List<*> = listOf(name, description, age, height)
}