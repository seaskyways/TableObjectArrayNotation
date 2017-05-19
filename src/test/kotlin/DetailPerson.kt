import seaskyways.toan.spec.TOANObjectArchitecture
import seaskyways.toan.spec.TOANSerializable
import seaskyways.toan.spec.TOANType
import seaskyways.toan.spec.TypedKey
import seaskyways.toan.stdlib.primitive.IntTOAN
import seaskyways.toan.stdlib.primitive.StringTOAN

/**
 * Created by Ahmad on 19/05 May/2017.
 */
data class DetailPerson(val person: Person, val id: Int, val address: String?) : TOANSerializable {

    companion object : TOANObjectArchitecture {
        override val tag: String = "DetailPerson"
        override val architecture: Set<TypedKey<*>> = setOf(TypedKey("person", Person), TypedKey("id", IntTOAN), TypedKey("address", StringTOAN, true))
    }

    override val architecture: List<TOANType<*>> = listOf(Person, IntTOAN, StringTOAN)
    override val valueList: List<*> = listOf(person, id, address)
}