package org.sleepapp.data.repository

import cache.Database
import cache.DatabaseDriverFactory
import org.sleepapp.data.model.Note

class NoteRepository(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)



}