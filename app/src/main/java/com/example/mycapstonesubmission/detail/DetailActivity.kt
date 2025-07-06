package com.example.mycapstonesubmission.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mycapstonesubmission.R
import com.example.mycapstonesubmission.core.domain.model.Movies
import com.example.mycapstonesubmission.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val detailData=intent.getParcelableExtra<Movies>(EXTRA_DATA)
        binding.tvoverviewDetail.text=detailData?.overview
        Glide.with(this)
            .load("https://media.themoviedb.org/t/p/w533_and_h300_bestv2${detailData?.posterPath}")
            .into(binding.ivDetail)
        binding.tvTitleDetail.text=detailData?.title
        if (detailData != null) {
            statusFavorite(detailData.isFavorite)
        }
        binding.ivFavorite.setOnClickListener {
            detailData?.let {movie ->
                val newFavoriteStatus = !(movie.isFavorite)
                movie.isFavorite = newFavoriteStatus

                viewModel.setFavoriteMovies(movie, newFavoriteStatus)
                statusFavorite(movie.isFavorite)

                Log.d("isFavorite", newFavoriteStatus.toString())
            }
        }
    }

    private fun statusFavorite(isFavorite : Boolean){
        val iconRes = if (isFavorite) {
            R.drawable.favorite_red
        } else {
            R.drawable.outline_favorite
        }
        binding.ivFavorite.setImageResource(iconRes)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}