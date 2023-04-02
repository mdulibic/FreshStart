package com.example.freshstart.navigation.destination

import androidx.annotation.MainThread
import androidx.collection.ArrayMap
import androidx.lifecycle.SavedStateHandle
import com.example.freshstart.viewModels.SavedStateHandleViewModel
import java.lang.reflect.Constructor
import kotlin.reflect.KClass

private val constructorMap =
    ArrayMap<KClass<out DestinationArgument>, Constructor<out DestinationArgument>>()

@MainThread
inline fun <reified Args : DestinationArgument> SavedStateHandleViewModel.destArgs() =
    DestinationArgsLazy(Args::class) { savedStateHandle }

class DestinationArgsLazy<Args : DestinationArgument>(
    private val navArgsClass: KClass<Args>,
    private val savedStateHandleProducer: () -> SavedStateHandle
) {
    private var args: Args? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): Args {
        if (args == null) {
            args = (
                (
                    constructorMap[navArgsClass]
                        ?: navArgsClass.java.getConstructor(
                            SavedStateHandle::class.java
                        ).also { constructor ->
                            constructorMap[navArgsClass] = constructor
                        }
                    )
                ).newInstance(savedStateHandleProducer()) as Args
        }

        return args!!
    }
}
