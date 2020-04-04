/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp.data



import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.architecture.blueprints.todoapp.MainApplication
import com.example.android.architecture.blueprints.todoapp.data.source.WeatherResponse
import com.example.android.architecture.blueprints.todoapp.data.source.WeatherService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


/**
 * Immutable model class for a Task. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param title title of the task
 * @param description description of the task
 * @param isCompleted whether or not this task is completed
 * @param id id of the task
 */
@Entity(tableName = "cidades")
data class Cidades @JvmOverloads constructor(
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "description") var description: String = "",
        @ColumnInfo(name = "temperatura") var temperatura: String = "",
        @ColumnInfo(name = "tempmax") var tempmax: String = "",
        @ColumnInfo(name = "tempmin") var tempmin: String = "",
        @ColumnInfo(name = "probchuvahum") var probchuvahum: String = "",
        @ColumnInfo(name = "previsao") var previsao: String = "",
        @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString()
) {



    init {
        this.getCurrentData();

    }

    var tempki: String = ""
        get() = probchuvahum

    var gettempMax: String = ""
        get() = tempmax

    var gettempMin: String = ""
        get() = tempmin

    var gettemperatura: String = ""
        get() = temperatura

    var getprevisao: String = ""
        get() = previsao



    val titleForList: String
        get() = if (title.isNotEmpty()) title else description



    val isEmpty
        get() = title.isEmpty()


    companion object {
        var BaseUrl = "https://api.openweathermap.org/"
        var AppId = "31f835fafe8e52b246f01ab96effeab4"
        var units = "metric"
        var lang= "pt_br"
    }



    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(if (title.isNotEmpty()) title else "João Pessoa", lang, units, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    tempmax = "" + weatherResponse.main!!.temp_max +"º max"
                    tempmin = "" + weatherResponse.main!!.temp_min +"º min"
                    temperatura = "Temp: " + weatherResponse.main!!.temp +"º " + weatherResponse.weather[0].description
                    previsao = "" + weatherResponse.weather[0].description


                    probchuvahum = "Probabilidade de chuva: "+ weatherResponse.clouds!!.all+"% - umidade: "+ weatherResponse.main!!.humidity+"%"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

            }
        })
    }






}

