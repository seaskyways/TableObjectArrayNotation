package seaskyways.toan.spec

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
data class TypedKey<T>(val key: String, val type: TOANType<T>, val nullable: Boolean = false)