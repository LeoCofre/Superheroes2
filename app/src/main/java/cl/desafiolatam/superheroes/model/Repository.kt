package cl.desafiolatam.superheroes.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.desafiolatam.superheroes.dataBase.EntityHeroe
import cl.desafiolatam.superheroes.dataBase.HeroeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository  (context: Context) {

    var heroDatabase = HeroeDatabase.getDatabase(context)
    var heroList = heroDatabase.getHeroeDao().getMinimalHeroes()

    fun loadApiData() {
        val call = RetrofitClient.retrofitInstance().allHeroes()

        call.enqueue(object : Callback<List<Hero>> {
            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Log.d("Adapter", "Error al cargar heroes")
            }

            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                Log.d("Adapter", "${response.code()}")
                Log.d("Adapter", "${response.body()}")
                saveDatabase(heroConverter(response.body()!!))
            }
        })
    }
    fun heroConverter(listHero : List<Hero>) : List<EntityHeroe> {
        return listHero.map { hero -> EntityHeroe(hero.id, hero.name, hero.powerstats, hero.slug, hero.images) }
    }

    fun saveDatabase (listHeroeEntity: List<EntityHeroe>) {
        CoroutineScope(Dispatchers.IO).launch {
            heroDatabase.getHeroeDao().insertHeroes(listHeroeEntity)
        }
    }
    fun getDetails(param1: String): LiveData<EntityHeroe> {
        return heroDatabase.getHeroeDao().getHeroe(param1.toInt())
    }
}