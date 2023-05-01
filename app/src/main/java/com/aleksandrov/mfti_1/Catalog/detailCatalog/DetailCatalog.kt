package com.aleksandrov.mfti_1.Catalog.detailCatalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailCatalog(name: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(name, fontWeight = FontWeight.Medium, fontSize = 24.sp)
    }
}