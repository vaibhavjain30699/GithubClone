package com.vaibhav.githubclone.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vaibhav.githubclone.fragment.OverViewFragment
import com.vaibhav.githubclone.fragment.RepositoriesFragment
import org.jetbrains.annotations.NotNull

class ViewPagerAdapter constructor(@NotNull fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        if (position == 0)
            fragment = OverViewFragment.newInstance()
        else if (position == 1)
            fragment = RepositoriesFragment.newInstance()
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title: String = ""
        if (position == 0)
            title = "Overview"
        else if (position == 1)
            title = "Repositories"
        return title
    }

}