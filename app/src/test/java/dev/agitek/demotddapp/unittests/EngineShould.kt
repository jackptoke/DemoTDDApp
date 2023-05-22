package dev.agitek.demotddapp.unittests

import dev.agitek.demotddapp.models.Engine
import dev.agitek.demotddapp.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class EngineShould {
    val engine = Engine(temperature=35, isTurnedOn = false)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun turnOn(): Unit = runTest(UnconfinedTestDispatcher())  {
        engine.turnOn()
        assertTrue(engine.isTurnedOn)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun riseTheTemperatureWhenItTurnsOn(): Unit = runTest(UnconfinedTestDispatcher())  {
        val flow = engine.turnOn()
        val temperatures = flow.toList()
        assertEquals(listOf(37,52,95), temperatures)
    }
}