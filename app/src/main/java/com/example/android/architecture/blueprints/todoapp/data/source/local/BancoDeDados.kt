package com.example.android.architecture.blueprints.todoapp.data.source.local

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteOpenHelper


class BancoDeDados : ToDoDatabase() {
    override fun taskDao(): TasksDao {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}