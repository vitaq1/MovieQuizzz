package com.incite.moviequiz.domain.model

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import com.incite.moviequiz.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataLoader @Inject constructor(
    var repo: MovieRepositoryImpl
) {

    var packs: MutableList<FilmPack> = mutableListOf()
    var films: MutableList<Film> = mutableListOf()


    var isLoaded = false


    fun loadData(context: Context) {

        /** If db is empty **/
        val filmPacks = repo.getFilmPacks()
        val filmsDb = repo.getFilms()

        println("9999" + filmPacks)
        println(filmPacks)
        println(filmsDb)
        if (filmPacks.isEmpty() and filmsDb.isEmpty()) {
            println("REPO IS EMPTY")
            repeat(30) { pack_num ->
                val filmPack = FilmPack(id = pack_num)
                repeat(20) {
                    val filmId: Int = pack_num * 20 + it
                    val film = Film(
                        id = filmId,
                        pack_id = pack_num,
                        name = getStringByName("s${filmId + 1}", context),
                        image = context.resourceUri("f${filmId + 1}").toString()
                    )
                    filmPack.films.add(film)
                    films.add(film)
                    repo.addFilm(film)
                }
                packs.add(filmPack)
                repo.addFilmPack(filmPack)
            }
            /** If db is filled **/
        } else {
            println("REPO IS FILLED")
            packs = filmPacks.toMutableList()
            films = filmsDb.toMutableList()
        }
        isLoaded = true
    }


    private fun getStringByName(idName: String, context: Context): String {
        val res: Resources = context.resources
        return res.getString(res.getIdentifier(idName, "string", context.packageName))
    }

    private fun Context.resourceUri(idName: String): Uri = with(resources) {
        val resId: Int = resources.getIdentifier(idName, "drawable", packageName)
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(getResourcePackageName(resId))
            .appendPath(getResourceTypeName(resId))
            .appendPath(getResourceEntryName(resId))
            .build()
    }


}
