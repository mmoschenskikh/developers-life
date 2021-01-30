package ru.maxultra.developerslife.ui.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.maxultra.developerslife.ui.gifpage.PageFragment

private const val PAGE_COUNT = 4

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = PAGE_COUNT

    override fun createFragment(position: Int): Fragment = PageFragment()
}
