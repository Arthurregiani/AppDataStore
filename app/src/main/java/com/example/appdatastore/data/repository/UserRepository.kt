package com.example.appdatastore.data.repository
//padrão projeto Factory



import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException


private val Context.dataStore by preferencesDataStore(UserRepository.FILE_NAME)

class UserRepository(context: Context) {
    //Constantes e atributos estáticos
    companion object{
        const val FILE_NAME = "file_datastore"
        val ATTR_USERNAME = stringPreferencesKey("username")
    }

    private val datastore = context.dataStore

    //Atributo flow
    //Possui username salvo no DataStore
    val usernameFlow: Flow<String?> = datastore.data.catch {exception ->
        if(exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map{ it.get(ATTR_USERNAME) }


    suspend fun saveUsername(username: String){
        datastore.edit { it[ATTR_USERNAME]=username }
    }

}