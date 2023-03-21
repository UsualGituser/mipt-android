package com.aleksandrov.mfti_1

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalVIPClass = compositionLocalOf<VIPClass> { VIPClass("Default value") }