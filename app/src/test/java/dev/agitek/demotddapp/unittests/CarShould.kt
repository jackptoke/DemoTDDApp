package dev.agitek.demotddapp.unittests

import dev.agitek.demotddapp.models.Car
import dev.agitek.demotddapp.models.Engine
import dev.agitek.demotddapp.utils.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CarShould {
    private val engine: Engine = mock()
    private val car: Car

    init {
        runTest(UnconfinedTestDispatcher()) {
            whenever(engine.turnOn()).thenReturn(flow {
                delay(2000)
                emit(37)
                delay(2000)
                emit(52)
                delay(2000)
                emit(95)
            })
        }
        car = Car(5.0, engine)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun looseFuelWhenItTurnsOn(): Unit = runTest(UnconfinedTestDispatcher()) {
        car.turnOn()
        advanceUntilIdle()
        assertEquals(4.5, car.fuel)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun turnOnItsEngine(): Unit = runTest(UnconfinedTestDispatcher())  {
        car.turnOn()
        car.turnOn()
        advanceUntilIdle()
        verify(engine, times(2)).turnOn()
    }
}