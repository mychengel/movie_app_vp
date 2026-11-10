package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.data.remote.dto.MovieListResponseDto
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.model.MoviePage

private const val IMAGE_BASE = "https://image.tmdb.org/t/p/"

private fun poster(path: String?) = path?.let { "${IMAGE_BASE}w500$it" }
private fun backdrop(path: String?) = path?.let { "${IMAGE_BASE}w780$it" }

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview.orEmpty(),
    posterUrl = poster(posterPath),
    backdropUrl = backdrop(backdropPath),
    releaseYear = releaseDate?.take(4).orEmpty(),
    rating = voteAverage,
)

fun MovieListResponseDto.toDomain() = MoviePage(
    movies = results.map { it.toDomain() },
    page = page,
    totalPages = totalPages,
)

fun MovieDetailDto.toDomain() = MovieDetail(
    id = id,
    title = title,
    tagline = tagline.orEmpty(),
    overview = overview.orEmpty(),
    runtimeMinutes = runtime?.takeIf { it > 0 },
    genres = genres.map { it.name },
    posterUrl = poster(posterPath),
    backdropUrl = backdrop(backdropPath),
    releaseDate = releaseDate.orEmpty(),
    rating = voteAverage,
    voteCount = voteCount,
)
