package cl.desafiolatam.superheroes.model


import retrofit2.Call
import retrofit2.http.GET

interface ApiHeroe {
    @GET("all.json")
    fun allHeroes() : Call<List<Hero>>
}