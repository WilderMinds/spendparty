package com.samdev.core.main.arch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @author Sam
 * Created 01/01/2024 at 10:24 pm
 */
class Store<State, Action>(
    initialState: State,
    coroutineScope: CoroutineScope,
    private val reducer: Reducer<State, Action>,
    private val sideEffects: List<SideEffect<State, Action>>,
) {

    private val dispatchedActionsChannel: Channel<Action> = Channel(Channel.UNLIMITED)

    val dispatch: Dispatch<Action> = { action: Action -> dispatchedActionsChannel.trySend(action) }

    val state = dispatchedActionsChannel
        .receiveAsFlow()
        .scan(initialState) { state, action ->
            reducer(state, action).also { updatedState ->
                sideEffects.forEach { sideEffect ->
                    coroutineScope.launch {
                        sideEffect(dispatch, updatedState, action)
                    }
                }
            }
        }.stateIn(coroutineScope, SharingStarted.Eagerly, initialState)
}
