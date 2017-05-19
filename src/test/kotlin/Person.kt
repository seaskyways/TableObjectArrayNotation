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
    companion object {
        val toanArchitecture = object : TOANObjectArchitecture {
            override val tag: String
                get() = "Person"

            override val architecture: Set<TypedKey<*>> = setOf(
                    TypedKey("name", StringTOAN),
                    TypedKey("desc", StringTOAN, true),
                    TypedKey("age", IntTOAN),
                    TypedKey("name", FloatTOAN)
            )
        }
    }

    override val architecture: Set<TOANType<*>> = setOf(StringTOAN, StringTOAN, IntTOAN, FloatTOAN)
    override val toTOANList: List<String> = listOf(name, description, age.toString(), height.toString())
}