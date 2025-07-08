package com.alexeybondarenko.picsearch.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexeybondarenko.picsearch.ui.utils.common.PicSearchErrorDialog
import org.koin.androidx.compose.koinViewModel



@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState,
    onApiSelected: (selectedOption: ApiSelection) -> Unit,
    onThemeSelected: (selectedOption: ThemeSelection) -> Unit,
    onLanguageSelected: (selectedOption: LanguageSelection) -> Unit,
    onDeleteAllImagesClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        uiState as SettingsUiState.SettingsLoaded

        RadioButtonSelection(
            label = "Select API", // todo move to res
            options = ApiSelection.entries,
            selectedOption = uiState.currentApi,
            onOptionSelected = { newSelectedApi ->
                onApiSelected.invoke(newSelectedApi as ApiSelection)
            }
        )

        RadioButtonSelection(
            label = "Select Theme", // todo move to res
            options = ThemeSelection.entries,
            selectedOption = uiState.currentTheme,
            onOptionSelected = { newSelectedTheme ->
                onThemeSelected.invoke(newSelectedTheme as ThemeSelection)
            }
        )

        RadioButtonSelection(
            label = "Select Language", // todo move to res
            options = LanguageSelection.entries,
            selectedOption = uiState.currentLanguage,
            onOptionSelected = { newSelectedLanguage ->
                onLanguageSelected.invoke(newSelectedLanguage as LanguageSelection)
            }
        )

        LabelText(label = "Сохраненные изображения") // todo move to res

        val openAlertDialog = remember { mutableStateOf(false) }
        Button(
            onClick = { openAlertDialog.value = true },
            modifier = Modifier
                .padding(start = 16.dp),
            enabled = uiState.isDeleteAllImagesPossible
        ) {
            Text("Удалить все") // todo move to res
        }

        if (openAlertDialog.value) {
            DeleteConfirmationAlertDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    onDeleteAllImagesClick.invoke()
                },
            )
        }

        uiState.operationErrorMessage?.let { error ->
            PicSearchErrorDialog(error = error)
        }
    }
}

@Composable
fun DeleteConfirmationAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = "Вы уверены?") // todo move to res
        },
        text = {
            Text(text = "Реальноо??")// todo move to res
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Подтвердить")// todo move to res
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Отмена")// todo move to res
            }
        }
    )
}

@Composable
fun RadioButtonSelection(
    modifier: Modifier = Modifier,
    label: String,
    options: List<Selection>,
    selectedOption: Selection,
    onOptionSelected: (selectedOption: Selection) -> Unit,
) {
    Column(modifier.selectableGroup()) {
        Spacer(Modifier.height(32.dp))

        LabelText(label = label)

        options.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = null
                )
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    label: String,
) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelLarge,
        fontSize = 20.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        uiState = SettingsUiState.SettingsLoaded(
            currentApi = ApiSelection.UNSPLASH,
            currentTheme = ThemeSelection.SYSTEM,
            currentLanguage = LanguageSelection.RU,
            isDeleteAllImagesPossible = true,
            operationErrorMessage = null,
        ),
        onApiSelected = {},
        onThemeSelected = {},
        onLanguageSelected = {},
        onDeleteAllImagesClick = {},
    )
}