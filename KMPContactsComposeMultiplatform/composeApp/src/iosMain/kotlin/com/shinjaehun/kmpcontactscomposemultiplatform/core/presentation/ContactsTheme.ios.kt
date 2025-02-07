package com.shinjaehun.kmpcontactscomposemultiplatform.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.shinjaehun.kmpcontactscomposemultiplatform.ui.theme.DarkColorScheme
import com.shinjaehun.kmpcontactscomposemultiplatform.ui.theme.LightColorScheme
import com.shinjaehun.kmpcontactscomposemultiplatform.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}