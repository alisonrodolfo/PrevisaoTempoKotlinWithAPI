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

package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.lifecycle.LiveData
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Result.Error
import com.example.android.architecture.blueprints.todoapp.data.Result.Success
import com.example.android.architecture.blueprints.todoapp.data.Cidades

class FakeDataSource(var cidades: MutableList<Cidades>? = mutableListOf()) : TasksDataSource {
    override suspend fun getTasks(): Result<List<Cidades>> {
        cidades?.let { return Success(ArrayList(it)) }
        return Error(
            Exception("Tasks not found")
        )
    }

    override suspend fun getTask(taskId: String): Result<Cidades> {
        cidades?.firstOrNull { it.id == taskId }?.let { return Success(it) }
        return Error(
            Exception("Task not found")
        )
    }

    override suspend fun saveTask(cidades: Cidades) {
        cidades?.add(cidades)
    }

    override suspend fun completeTask(cidades: Cidades) {
        cidades?.firstOrNull { it.id == cidades.id }?.let { it.isCompleted = true }
    }

    override suspend fun completeTask(taskId: String) {
        cidades?.firstOrNull { it.id == taskId }?.let { it.isCompleted = true }
    }

    override suspend fun activateTask(cidades: Cidades) {
        cidades?.firstOrNull { it.id == cidades.id }?.let { it.isCompleted = false }
    }

    override suspend fun activateTask(taskId: String) {
        cidades?.firstOrNull { it.id == taskId }?.let { it.isCompleted = false }
    }

    override suspend fun clearCompletedTasks() {
        cidades?.removeIf { it.isCompleted }
    }

    override suspend fun deleteAllTasks() {
        cidades?.clear()
    }

    override suspend fun deleteTask(taskId: String) {
        cidades?.removeIf { it.id == taskId }
    }

    override fun observeTasks(): LiveData<Result<List<Cidades>>> {
        TODO("not implemented")
    }

    override suspend fun refreshTasks() {
        TODO("not implemented")
    }

    override fun observeTask(taskId: String): LiveData<Result<Cidades>> {
        TODO("not implemented")
    }

    override suspend fun refreshTask(taskId: String) {
        TODO("not implemented")
    }
}
