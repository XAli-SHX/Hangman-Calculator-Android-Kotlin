package ir.alishayanpoor.hangmancalculator.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import ir.alishayanpoor.hangmancalculator.exception.AppException

object Navigator {
    fun launchActivity(
        context: Context,
        cls: Class<*>,
        extraKey: String? = null,
        extraData: String? = null,
    ) {
        Intent(context, cls).also {
            if (extraKey != null)
                it.putExtra(extraKey, extraData)
            context.startActivity(it)
        }
    }

    @Throws(NavigationDataException::class)
    fun getNavigationStringData(activity: Activity, key: String): String {
        val data = activity.intent.getStringExtra(key)
        return data ?: throw NavigationDataException("null data")
    }

    class NavigationDataException(localizedMessage: String = "") : AppException(localizedMessage)
}