import seaskyways.toan.arrayTOAN
import seaskyways.toan.nullableTOAN
import seaskyways.toan.spec.TOANObjectArchitecture
import seaskyways.toan.spec.TOANSerializable
import seaskyways.toan.spec.TOANType
import seaskyways.toan.spec.TypedKey
import seaskyways.toan.stdlib.primitive.IntTOAN
import seaskyways.toan.stdlib.primitive.StringTOAN

/**
 * Created by Ahmad Al-Sharif on 23/05 - May/17.
 */
data class City(val name: String, val id: Int?, val people: List<DetailPerson>) : TOANSerializable {
    companion object : TOANObjectArchitecture {
        override val tag: String = "City"
        override val architecture: Set<TypedKey<*>> = setOf(
                TypedKey("name", StringTOAN),
                TypedKey("id", IntTOAN),
                TypedKey("people", DetailPerson.arrayTOAN())
        )
    }

    override val architecture: List<TOANType<*>> = listOf(StringTOAN, IntTOAN.nullableTOAN(), DetailPerson.arrayTOAN())
    override val valueList: List<*> = listOf(name, id, people)
}
