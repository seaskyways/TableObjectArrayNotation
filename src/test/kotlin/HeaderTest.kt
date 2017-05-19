import seaskyways.toan.TOANMapper

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
class HeaderTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val p = Person("Ahmad", "Student", 19, 18.5f)
            val mapper = TOANMapper()
            val pToanHeader = mapper.architectureToHeader(Person.toanArchitecture)
            println(pToanHeader)
        }
    }
}