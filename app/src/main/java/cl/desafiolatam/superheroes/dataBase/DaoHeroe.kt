package cl.desafiolatam.superheroes.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.desafiolatam.superheroes.model.HeroMini

@Dao
interface DaoHeroe {
    @Query("SELECT * FROM hero_table")
    fun getAllHeroes() : LiveData<List<EntityHeroe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroeList : List<EntityHeroe>)

    @Query("SELECT id, name, images_sm FROM hero_table")
    fun getMinimalHeroes() : LiveData<List<HeroMini>>

    @Query("SELECT * FROM hero_table WHERE id = :id")
    fun getHeroe(id: Int) : LiveData<EntityHeroe>
}
