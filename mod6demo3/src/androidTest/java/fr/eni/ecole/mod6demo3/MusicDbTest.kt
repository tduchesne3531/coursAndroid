package fr.eni.ecole.mod6demo3

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.eni.ecole.mod6demo3.bo.Music
import fr.eni.ecole.mod6demo3.room.AppDatabase
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MusicDbTest {

    private lateinit var db : AppDatabase

    @Before
    fun createDb(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java).build()
    }

    @After
    fun closeDb(){
        db.close()
    }


    @Test
    fun testInsertMusic() = runTest {
        val music = Music(
            0L,
            "Gimme Chocolate",
            "Baby Metal",
            120
        )
        val id = db.musicDao().insert(music)
        assertTrue(id > 0)
    }

    @Test
    fun testFindById() = runTest {
        val music = Music(
            0L,
            "Gimme Chocolate",
            "Baby Metal",
            120)
        val id = db.musicDao().insert(music)
        assertTrue(id > 0)

        val musicFound = db.musicDao().findById(id)
        assertNotNull(musicFound)
        assertNotNull(musicFound.title)
        Assert.assertEquals(musicFound.title, music.title)

    }
}