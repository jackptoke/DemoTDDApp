package dev.agitek.demotddapp.models

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Engine(
    var temperature: Int = 27,
    var isTurnedOn: Boolean = false
) {
    suspend fun turnOn(): Flow<Int> {
        isTurnedOn = true
        return flow {
            delay(2000)
            temperature = 37
            emit(temperature)

            delay(2000)
            temperature = 52
            emit(temperature)

            delay(2000)
            temperature = 95
            emit(temperature)
        }
    }

    fun turnOff() {
        isTurnedOn = false
        temperature = 15
    }
}
