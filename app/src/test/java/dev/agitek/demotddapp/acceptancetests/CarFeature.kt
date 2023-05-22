package dev.agitek.demotddapp.acceptancetests
import dev.agitek.demotddapp.models.Car
import dev.agitek.demotddapp.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule

import org.junit.Test

class CarFeature {
    val car = Car(6.0)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun carIsLoosingFuelWhenItTurnsOn(): Unit = runTest(dispatcher)  {
        car.turnOn()
        assertEquals(5.5, car.fuel)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperature(): Unit = runTest(dispatcher)  {
        car.turnOn()

        coroutinesTestRule.dispatcher.scheduler.advanceTimeBy(2001)
        assertEquals(37, car.engine.temperature)

        coroutinesTestRule.dispatcher.scheduler.advanceTimeBy(2001)
        assertEquals(52, car.engine.temperature)

        coroutinesTestRule.dispatcher.scheduler.advanceTimeBy(2001)
        assertEquals(95, car.engine.temperature)

        assertEquals(true, car.engine.isTurnedOn)
    }
}