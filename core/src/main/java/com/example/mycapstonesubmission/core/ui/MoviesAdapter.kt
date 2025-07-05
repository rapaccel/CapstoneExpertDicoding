package com.example.mycapstonesubmission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycapstonesubmission.core.databinding.ListMoviesBinding
import com.example.mycapstonesubmission.core.domain.model.Movies

class MoviesAdapter : ListAdapter<Movies, MoviesAdapter.ListViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Movies) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.ListViewHolder {
       return ListViewHolder(
            ListMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ListViewHolder, position: Int) {
        val data= getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ListMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(getItem(position))
                }
            }
        }

        fun bind(data: Movies) {
            Glide.with(itemView.context)
                .load("https://media.themoviedb.org/t/p/w300_and_h450_bestv2${data.posterPath}")
                .into(binding.ivPoster)
            binding.tvTitle.text = data.title
            binding.tvOverview.text=data.overview
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movies> =
            object : DiffUtil.ItemCallback<Movies>() {
                override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

                override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                    return oldItem == newItem
                }
            }
    }
}