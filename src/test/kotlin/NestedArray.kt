import seaskyways.toan.TOANMapper
import kotlin.system.measureTimeMillis

/**
 * Created by Ahmad Al-Sharif on 23/05 - May/17.
 */

fun main(args: Array<String>) {
    val mapper = TOANMapper()
    val cities = (1..10).map {
        City(
                name = "City $it",
                id = if (it % 2 == 0) it else null,
                people = (1..10).map {
                    DetailPerson(
                            Person(
                                    name = "Person $it",
                                    description = "Desc$it",
                                    age = it,
                                    height = it * 1.5f
                            ),
                            it,
                            null
                    )
                }
        )
    }
    var toan: String = ""

    val time = measureTimeMillis {
        toan = mapper.writeValueAsString(
                City,
                arrayOf(City, Person.toanArchitecture, DetailPerson),
                cities.toTypedArray()
        )
    }
    print("\n\n")
    println(toan)
    print("\nParsing took $time millis")
}