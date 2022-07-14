package com.sirius.test_app.recycler.util

import java.lang.ref.WeakReference

internal fun<T> T.weak(): WeakReference<T> = WeakReference(this)
