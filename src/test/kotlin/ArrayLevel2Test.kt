import seaskyways.toan.TOANMapper

/**
 * Created by Ahmad on 19/05 May/2017.
 */

fun main(args: Array<String>) {
    val people = (1..10).map { Person("Person $it", "Desc$it", it, it * 1.5f) }
    val detailedPeople = people.mapIndexed { i, it -> DetailPerson(it, i, "Long Long Long Address") }
    val toanResult = TOANMapper().writeValueAsString(
            DetailPerson,
            arrayOf(DetailPerson, Person.toanArchitecture),
            detailedPeople.toTypedArray()
    )
    print("\n\n")
    println(toanResult)
}