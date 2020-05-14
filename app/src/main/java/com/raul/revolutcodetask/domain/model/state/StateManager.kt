package com.raul.revolutcodetask.domain.model.state

import androidx.lifecycle.LiveData
import com.raul.revolutcodetask.domain.model.ErrorEntity
import com.raul.revolutcodetask.domain.model.state.ScreenState.*
import javax.inject.Inject

class StateManager<T> @Inject constructor() : LiveData<ScreenState<T>>() {

    internal fun set(categories: T) {
        postValue(Success(categories))
    }

    internal fun loading() {
        postValue(Loading())
    }

    internal fun error(t: ErrorEntity) {
        postValue(Error(t))
    }
}