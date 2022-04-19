package com.example.roomdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.roomdb.databinding.FragmentAddFriendBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFriendFragment : Fragment() {

    lateinit var binding: FragmentAddFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFriendBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAddFrined.setOnClickListener {
            val friendName: String = binding.etFriendName.text.toString()
            val latitude: String = binding.etLatitude.text.toString()
            val longitude: String = binding.etLongitude.text.toString()
                GlobalScope.launch {
                    ((activity as MainActivity)).database.myDao().addFriend(FriendModel(0, friendName, latitude.toDouble(), longitude.toDouble()))
                    binding.etFriendName.setText("")
                    binding.etLatitude.setText("")
                    binding.etLongitude.setText("")
                }
        }
    }

}