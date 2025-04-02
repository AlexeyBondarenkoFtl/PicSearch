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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onApiSelected = viewModel::selectApi,
        onThemeSelected = viewModel::selectTheme,
        onLanguageSelected = viewModel::selectLanguage,
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState,
    onApiSelected: (selectedOption: ApiSelection) -> Unit,
    onThemeSelected: (selectedOption: ThemeSelection) -> Unit,
    onLanguageSelected: (selectedOption: LanguageSelection) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
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
    }
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

        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
        )

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

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        uiState = SettingsUiState.SettingsLoaded(
            currentApi = ApiSelection.UNSPLASH,
            currentTheme = ThemeSelection.SYSTEM,
            currentLanguage = LanguageSelection.RU
        ),
        onApiSelected = {},
        onThemeSelected = {},
        onLanguageSelected = {},
    )
}