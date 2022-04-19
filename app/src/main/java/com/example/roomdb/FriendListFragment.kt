package com.example.roomdb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.roomdb.databinding.FragmentAddFriendBinding
import com.example.roomdb.databinding.FragmentFriendListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.google.gson.Gson

class FriendListFragment : Fragment(), FriendAdapter.ItemClickListener {

    lateinit var binding: FragmentFriendListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ((activity as MainActivity)).database.myDao().getActor().observe(requireActivity(), {
            Log.d("DATA", it.toString())
            val users: List<FriendModel> = it
            val friendAdapter = FriendAdapter(context, users, this)
            binding.rvFriend.adapter = friendAdapter
        })


        binding.addFriend.setOnClickListener {
            val homeFragment: Fragment = AddFriendFragment()
            ((activity as MainActivity)).replaceFragmentMethod(
                homeFragment,
                AddFriendFragment::class.java.simpleName, true, false, requireActivity()
            )
        }
    }

    override fun delete(friendModel: FriendModel) {
        GlobalScope.launch {
            ((activity as MainActivity)).database.myDao().deleteFriend(friendModel.id)
        }
    }

    override fun showLocation(friendModel: FriendModel) {
        val intent =Intent(context , MapsActivity::class.java)
        val gson = Gson()
        val myJson = gson.toJson(friendModel)
        intent.putExtra("FRIEND_MODEL", myJson);
        startActivity(intent)
    }

}