package com.vlc.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vlc.sample.databinding.ItemVideoBinding

class VideoListAdapter(
    var context: Context,
    var items: List<Video>,
    var listener: VideoClickListener
): RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder>() {

    interface VideoClickListener {
        fun onVideoSelected(video: Video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = ItemVideoBinding.inflate(inflater, parent, false)
        return VideoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class VideoListViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: Video) {
            binding.apply {
                tvName.text = "Name: ${item.guideName}"
                tvNumber.text = "Number: ${item.guideNumber}"
                tvUrl.text = "URL: ${item.uRL}"
            }
        }

        override fun onClick(item: View?) {
            listener.onVideoSelected(items[adapterPosition])
        }

    }
}