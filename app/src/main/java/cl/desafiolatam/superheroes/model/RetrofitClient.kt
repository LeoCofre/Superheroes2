package cl.desafiolatam.superheroes.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/"

class RetrofitClient {
    companion object {

        fun retrofitInstance(): ApiHeroe {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiHeroe::class.java)
        }
    }
}