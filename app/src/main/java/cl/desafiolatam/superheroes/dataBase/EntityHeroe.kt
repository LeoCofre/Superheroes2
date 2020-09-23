package cl.desafiolatam.superheroes.dataBase

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import cl.desafiolatam.superheroes.model.HeroImages
import cl.desafiolatam.superheroes.model.Powerstats

@Entity(tableName = "hero_table")
data class EntityHeroe (
    @PrimaryKey val id : Int,
    val name : String,
    @Embedded(prefix = "ps_") val powerstats: Powerstats,
    val slug : String,
    @Embedded(prefix = "images_") val images : HeroImages
)