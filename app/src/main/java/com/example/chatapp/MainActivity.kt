package com.example.chatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.chatapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.layout_tabs.view.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        val adapter = SwipeAdapter(supportFragmentManager, getItems())
        binding.viewPager.adapter = adapter

//            binding.layoutTabs.tv_users.setOnClickListener {
//
//            }
//
//        binding.layoutTabs.tv_chats.setOnClickListener {
//
//        }
    }

    private fun getItems() = arrayListOf(
        ChatsFragment(),
        UsersFragment()
    )

    class SwipeAdapter(fragmentManager: FragmentManager, val fragments: List<Fragment>) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size
    }
}