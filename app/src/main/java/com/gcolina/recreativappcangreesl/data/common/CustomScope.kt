package com.gcolina.recreativappcangreesl.data.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CustomScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO
}
