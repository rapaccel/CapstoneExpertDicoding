package com.example.mycapstonesubmission.core.utils

import com.example.mycapstonesubmission.core.data.source.local.entity.MoviesEntity
import com.example.mycapstonesubmission.core.data.source.remote.response.ResultsItem
import com.example.mycapstonesubmission.core.domain.model.Movies

object DataMapper {

    fun mapResponsetoEntities(input: List<ResultsItem>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies =
                MoviesEntity(
                    movieId = it.id ?: 0,
                    overview = it.overview,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    video = it.video,
                    title = it.title,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    popularity = it.popularity.toString().toDouble(),
                    voteAverage = it.voteAverage.toString().toDouble(),
                    adult = it.adult,
                    voteCount = it.voteCount,
                    isFavorite = false
                )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input : List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                movieId = it.movieId,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntities(input: Movies)=
        MoviesEntity(
            movieId = input.movieId,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            video = input.video,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            adult = input.adult,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
}