package cl.desafiolatam.superheroes.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.desafiolatam.superheroes.dataBase.EntityHeroe
import cl.desafiolatam.superheroes.model.Repository

class ViewModelHeroe(application: Application) : AndroidViewModel(application) {

    private var repository : Repository = Repository(application)
    var listHero = repository.heroList

    init {
        repository = Repository(application)

        repository.loadApiData()

    }
    fun getDetails(param1: String): LiveData<EntityHeroe> {
        return repository.getDetails(param1)
    }
}