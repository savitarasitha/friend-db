package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.roomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        supportActionBar!!.hide()

        database = Room.databaseBuilder(applicationContext,
        MyDatabase::class.java, "friend.db").build()

        val friendListFragment: Fragment = FriendListFragment()
        replaceFragmentMethod(friendListFragment,
            FriendListFragment::class.java.simpleName, false, true, this@MainActivity
        )
    }

    fun replaceFragmentMethod(
        frag: Fragment?, tag: String?, addToStack: Boolean, clearStack: Boolean,
        fa: FragmentActivity
    ) {
        if (clearStack) fa.supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        val ft = fa.supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, frag!!, tag)
        if (addToStack) ft.addToBackStack(tag)
        ft.commit()
    }
}