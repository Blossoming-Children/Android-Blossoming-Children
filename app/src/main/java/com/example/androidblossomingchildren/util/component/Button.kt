package com.example.androidblossomingchildren.util.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.androidblossomingchildren.util.ThemePreviews
import com.example.androidblossomingchildren.util.theme.TionTheme

@Composable
fun TionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .height(75.dp)
        .fillMaxWidth()
        .padding(horizontal = 56.dp),
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    shape: Shape = RoundedCornerShape(10.dp),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@ThemePreviews
@Composable
private fun TionButtonPreview() {
    TionTheme {
        TionBackground(
            modifier = Modifier.size(200.dp),
        ) {
            Column {
                TionButton(
                    onClick = { },
                    enabled = false,
                    content = { Text("Test Button") },
                )

                TionButton(
                    onClick = { },
                    enabled = true,
                    content = { Text("Test Button") },
                )
            }
        }
    }
}
