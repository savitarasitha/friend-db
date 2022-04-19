package com.example.roomdb

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.databinding.FriendDesignBinding

class FriendAdapter(
    val context: Context?,
    var itemList: List<FriendModel>,
    var mListener: ItemClickListener
) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {



    var binding: FriendDesignBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            FriendDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding!!.tvFriendName.text = itemList.get(position).friendName
        binding!!.tvLatitude.text = "Lat: "+itemList.get(position).latitude.toString()
        binding!!.tvLongitude.text = "Lgt: "+itemList.get(position).longitude.toString()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: FriendDesignBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {
        init {
            binding!!.ivDelete.setOnClickListener {
                mListener.delete(itemList[adapterPosition])
            }

            binding!!.clFriend.setOnClickListener {
                mListener.showLocation(itemList[adapterPosition])
            }
        }
    }

    interface ItemClickListener{
        fun delete(friendModel: FriendModel)
        fun showLocation(friendModel: FriendModel)
    }
}