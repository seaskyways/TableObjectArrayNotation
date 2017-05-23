import seaskyways.toan.TOANMapper
import kotlin.system.measureTimeMillis

/**
 * Created by Ahmad on 19/05 May/2017.
 */

fun main(args: Array<String>) {
    val people = (1..10).map { Person("Person $it", "Desc$it", it, it * 1.5f) }
    val detailedPeople = people.mapIndexed { i, it -> DetailPerson(it, i, "Long Long Long Address") }
    var toanResult: String = ""
    val time = measureTimeMillis {
        toanResult = TOANMapper().writeValueAsString(
                DetailPerson,
                arrayOf(DetailPerson, Person.toanArchitecture),
                detailedPeople.toTypedArray()
        )
    }
    println()
    println(toanResult)
    println("Parsing ${people.size} DetailPerson took $time milliseconds")
}