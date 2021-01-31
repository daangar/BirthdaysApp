package com.dgarcia.birthddays.presentation.routing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.dgarcia.birthddays.presentation.ui.base.BaseActivity
import java.io.Serializable

class AppNavigator(private val activity: AppCompatActivity): Navigator {
    companion object {
        const val SCREEN_TYPE = "screen_type"
    }

    override fun showContactsActivity() = navigateTo(getIntent<ContactsActivity>().apply {
        putExtra(SCREEN_TYPE, ScreenType.CONTACTS)
    })

    private fun navigateTo(intent: Intent) = activity.startActivity(intent)

    private inline fun <reified T : BaseActivity> getIntent() = Intent(activity, T::class.java)
}

enum class ScreenType : Serializable {
    CONTACTS
}
