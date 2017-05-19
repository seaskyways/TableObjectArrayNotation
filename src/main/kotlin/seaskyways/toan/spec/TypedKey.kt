package seaskyways.toan.spec

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
data class TypedKey<out T>(val key: String, val type: TOANType<T>, val nullable: Boolean = false)