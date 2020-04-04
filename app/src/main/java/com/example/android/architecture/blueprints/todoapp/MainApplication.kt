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

package com.example.android.architecture.blueprints.todoapp

import android.app.Application


import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.Cidades
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.DebugTree


import kotlinx.coroutines.GlobalScope



/**
 * An application that lazily provides a repository. Note that this Service Locator pattern is
 * used to simplify the sample. Consider a Dependency Injection framework.
 *
 * Also, sets up Timber in the DEBUG BuildConfig. Read Timber's documentation for production setups.
 */
class MainApplication : Application()  {



    // Depends on the flavor,
    val taskRepository: TasksRepository
        get() = ServiceLocator.provideTasksRepository(this)


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())

        instance = this
        database = Room.databaseBuilder(this,
                ToDoDatabase::class.java, "database").build()

        var city1 = Cidades(title = "Barcelona", description = "Outra Cidade" )
        createTask(city1)
        var city2 = Cidades(title = "Dublin", description = "Outra Cidade" )
        createTask(city2)
        var city3 = Cidades(title = "London", description = "Outra Cidade" )
        createTask(city3)
        var city4= Cidades(title = "New York", description = "Outra Cidade" )
        createTask(city4)
        var city5 = Cidades(title = "João Pessoa", description = "Minha Cidade" )
        createTask(city5)


    }


    private fun createTask(newCidades: Cidades) = GlobalScope.launch {
        taskRepository.saveTask(newCidades)

    }

    companion object {
        lateinit var instance: MainApplication
        lateinit var database: ToDoDatabase

    }









}
