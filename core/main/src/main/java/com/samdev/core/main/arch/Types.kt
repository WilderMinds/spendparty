package com.samdev.core.main.arch

/**
 * @author Sam
 * Created 01/01/2024 at 8:36 pm
 */

typealias Reducer<State, Action> = (state: State, action: Action) -> State

typealias Dispatch<Action> = (Action) -> Unit

typealias SideEffect<State, Action> = suspend Dispatch<Action>.(state: State, action: Action) -> Unit
