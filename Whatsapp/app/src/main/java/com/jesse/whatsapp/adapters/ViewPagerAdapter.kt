package com.jesse.whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jesse.whatsapp.fragments.ContatosFragment
import com.jesse.whatsapp.fragments.ConversasFragment

class ViewPagerAdapter(
    val abas: List<String>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(p0: Int): Fragment {
        when (p0) {
            1 -> return ContatosFragment()
        }
        return ConversasFragment()
    }

    override fun getItemCount(): Int {
        return abas.size
    }
}