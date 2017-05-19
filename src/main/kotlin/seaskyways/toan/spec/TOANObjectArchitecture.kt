package seaskyways.toan.spec

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
interface TOANObjectArchitecture {
    val tag: String
    val nullable: Boolean get() = false
    val architecture: Set<TypedKey<*>>
}