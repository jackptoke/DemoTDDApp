package dev.agitek.demotddapp

import dev.agitek.demotddapp.models.Engine
import dev.agitek.demotddapp.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val engine = Engine(temperature = 15, isTurnedOn = false)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raiseTheTemperatureGraduallyWhenTurnOn(): Unit = runTest(dispatcher) {
        val flow: Flow<Int> = engine.turnOn()
        val temperatures: List<Int> = flow.toList()
        assertEquals(listOf(37,52,95), temperatures)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun engineTurnsOff(): Unit = runTest(dispatcher) {
        engine.turnOn()
        engine.turnOff()
        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }


}