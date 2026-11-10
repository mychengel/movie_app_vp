# MovieApp – Jetpack Compose + MVVM + Navigation + Retrofit + Coroutines (TMDB)

## Cara menjalankan
1. Buka folder ini di Android Studio, tunggu Gradle sync.
2. Buka (atau buat) file `local.properties` di root project, tambahkan:
   ```
   TMDB_API_KEY=api_key_tmdb_anda
   ```
   (API key v3 dari https://www.themoviedb.org/settings/api). File ini sudah masuk `.gitignore`.
3. Sync Gradle ulang, lalu Run. Jika key kosong, aplikasi menampilkan pesan error + tombol Retry.

## Halaman
- **Dashboard**: carousel horizontal (Popular / Now Playing, ganti lewat chip) di atas, lalu daftar **All Movies** (`discover/movie`) yang bisa di-scroll dengan infinite scroll.
- **Detail**: backdrop, poster, rating, durasi, genre, overview. Dibuka dengan mengklik movie mana pun.

## Struktur
```
data/remote      TmdbApi (Retrofit), ApiKeyInterceptor, dto/ (bentuk JSON)
data/repository  MovieRepository + Mappers (DTO -> model domain)
domain/model     Movie, MovieDetail, MoviePage, MovieCategory
di               ServiceLocator (Retrofit & Repository singleton)
ui/navigation    Routes (@Serializable), AppNavHost (satu-satunya pemegang NavController)
ui/dashboard     DashboardViewModel + UiState, DashboardScreen/Content
ui/detail        DetailViewModel + UiState, DetailScreen/Content
ui/components    RemoteImage (Coil), MovieCard, MovieListItem, LoadingBox, ErrorBox
util             safeCall (rethrow CancellationException), pesan error
```

## Catatan
- Pola: Screen (stateful) + Content (stateless), 1 layar = 1 ViewModel + UiState (StateFlow), navigasi hanya mengirim `movieId`.
- Versi library ada di `gradle/libs.versions.toml`; cek versi terbaru bila Gradle sync gagal.
- API key disisipkan oleh `ApiKeyInterceptor`; logging OkHttp memakai level BASIC agar key tidak tercetak di Logcat.
- Ide pengembangan: pencarian (`search/movie`), cast (`append_to_response=credits`), cache dengan Room, Hilt.
