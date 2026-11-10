package com.example.movieapp.data.repository

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.TmdbApi
import com.example.movieapp.domain.model.MovieCategory
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.model.MoviePage
import com.example.movieapp.util.safeCall

/**
 * Satu-satunya pintu data untuk ViewModel.
 * ViewModel tidak tahu apakah data berasal dari Retrofit, database, atau lainnya.
 */
class MovieRepository(private val api: TmdbApi) {

    /** Movie untuk carousel horizontal (Popular / Now Playing). */
    suspend fun getFeatured(category: MovieCategory): Result<MoviePage> = guarded {
        when (category) {
            MovieCategory.POPULAR -> api.getPopular()
            MovieCategory.NOW_PLAYING -> api.getNowPlaying()
        }.toDomain()
    }

    /** Seluruh movie (list vertikal), per halaman. */
    suspend fun getAllMovies(page: Int): Result<MoviePage> = guarded {
        api.discoverMovies(page).toDomain()
    }

    suspend fun getMovieDetail(id: Int): Result<MovieDetail> = guarded {
        api.getMovieDetail(id).toDomain()
    }

    private suspend fun <T> guarded(block: suspend () -> T): Result<T> {
        if (BuildConfig.TMDB_API_KEY.isBlank()) {
            return Result.failure(
                IllegalStateException("TMDB_API_KEY belum diisi. Tambahkan di local.properties lalu sync Gradle.")
            )
        }
        return safeCall(block)
    }
}
