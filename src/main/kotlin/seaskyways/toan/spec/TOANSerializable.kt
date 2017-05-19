package seaskyways.toan.spec

/**
 * Created by Ahmad Al-Sharif on 19/05 - May/17.
 */
interface TOANSerializable {
    val architecture: List<TOANType<*>>
    val valueList: List<*>
}