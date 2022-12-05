package com.example.listofsports

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listofsports.databinding.ItemLayoutBinding

class BallAdapter(
    val itemClicked: (Ball) -> Unit
) : ListAdapter<Ball, BallAdapter.ViewHolder>(BallDiffCallback()) {

    class BallDiffCallback : DiffUtil.ItemCallback<Ball>() {
        override fun areItemsTheSame(oldItem: Ball, newItem: Ball): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ball, newItem: Ball): Boolean {
            return oldItem == newItem
        }
    }

    // inner class for holding view items (viewholder)
    class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) { // apply view binding
        // assign the data content to the view holder

        fun bind(ball: Ball) {
//            binding.imageView.setImageResource(ball.imageId)
//            binding.textView.text = ball.name
            // apply data binding
            binding.ball = ball
            binding.executePendingBindings()
        }

    }

    // create a new viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }


    // get a viewholder and populate the data content
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ball = getItem(position)
        holder.bind(ball)
        // handle the item selection event
        holder.itemView.setOnClickListener {
            if (ball.name == "Baseball")
            {
                it.findNavController().navigate(R.id.baseballFragment)
            }
            else if (ball.name == "Basketball")
            {
                it.findNavController().navigate(R.id.basketballFragment)
            }
            else if (ball.name == "Football")
            {
                it.findNavController().navigate(R.id.footballFragment)
            }
            else if (ball.name == "Volleyball")
            {
                it.findNavController().navigate(R.id.volleyballFragment)
            }
        }
    }
}

@BindingAdapter("ballImage")
//function name can be arbitrary
fun ImageView.setballImage(item: Ball?) {
    item?.let {
        setImageResource(item.imageId)
    }
}
