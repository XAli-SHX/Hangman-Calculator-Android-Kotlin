package ir.alishayanpoor.hangmancalculator.exception

open class AppException(private val localizedMessage: String? = "") : Exception() {

    override fun getLocalizedMessage(): String {
        return localizedMessage ?: super.getLocalizedMessage().orEmpty()
    }
}