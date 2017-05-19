package seaskyways.toan.spec

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
interface TOANType<out T> {
    val typeTag: String

    fun fromString(string: String): T
}