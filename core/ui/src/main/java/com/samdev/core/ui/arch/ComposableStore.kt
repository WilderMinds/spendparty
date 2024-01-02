package com.samdev.core.ui.arch

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import com.samdev.core.main.arch.Store

/**
 * @author Sam
 * Created 01/01/2024 at 11:00 pm
 */

@Composable
inline fun <reified State, Action> composableStore(
    crossinline save: (State) -> Parcelable? = { state -> state as? Parcelable },
    crossinline restore: (Parcelable) -> State? = { parcelable -> parcelable as? State },
    initialAction: Action? = null,
    crossinline init: (State?) -> Store<State, Action>
): Store<State, Action> {
    return rememberSaveable(
        saver = Saver(
            save = { store ->
                if (store.state.value is Parcelable) {
                    save(store.state.value)
                } else null
            },
            restore = { restoredParcelable ->
                restore(restoredParcelable).let(init)
            }
        ),
        init = {
            init(null).also { store ->
                initialAction?.let(store.dispatch)
            }
        }
    )
}
