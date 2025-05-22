package com.example.mymovielist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InsertEditMovieScreen(
    modifier: Modifier = Modifier,
    uiState: InsertEditMovieScreenUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onCategoryIconChange: (Int) -> Unit,
    onCancel: () -> Unit
) {
    BackHandler { onCancel() }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = uiState.name,
            onValueChange = onNameChange,
            label = { Text("Nome do Filme") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.description,
            onValueChange = onDescriptionChange,
            label = { Text("Descrição do Filme") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Ícone do Filme")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            listOf(
                R.drawable.acao,
                R.drawable.romance,
                R.drawable.terror,
                R.drawable.comedia,
                R.drawable.drama
            ).forEach { iconRes ->
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onCategoryIconChange(iconRes) }
                        .border(
                            width = if (uiState.icon == iconRes) 2.dp else 0.dp,
                            color = if (uiState.icon == iconRes) Color.Blue else Color.Transparent,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInsertEditMovieScreen() {
    InsertEditMovieScreen(
        uiState = InsertEditMovieScreenUiState(
            name = "Interestelar",
            description = "Um épico espacial emocionante.",
            icon = R.drawable.acao
        ),
        onNameChange = {},
        onDescriptionChange = {},
        onCategoryIconChange = {},
        onCancel = {}
    )
}
