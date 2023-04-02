package com.example.freshstart.navigation.destination


sealed class ArgsDestination<T : DestinationArgument>(route: String, inputArgs: Array<String>) :
    Destination(route, *inputArgs) {
    abstract fun toArgs(args: T): String

    operator fun invoke(args: T): String = toArgs(args)



    protected fun String.appendParams(params: Array<String>): String =
        params.joinToString(prefix = "$this/", separator = "/")
}
