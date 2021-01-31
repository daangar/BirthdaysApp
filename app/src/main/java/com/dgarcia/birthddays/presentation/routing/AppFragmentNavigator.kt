package com.dgarcia.birthddays.presentation.routing

import androidx.fragment.app.FragmentActivity
import com.dgarcia.birthddays.R
import com.dgarcia.birthddays.presentation.common.extensions.showFragment

class AppFragmentNavigator(private val activity: FragmentActivity) : FragmentNavigator {

    override fun showWeatherDetails() = activity.showFragment(ContactDetailFragment.newInstance(), R.id.fragmentContainer, true)
}
