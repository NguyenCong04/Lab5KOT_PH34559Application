package com.congntph34559.fpoly.lab5kot_ph34559application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ExerciseThreeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetLayoutExerciseThree()
        }
    }
}

@Composable
fun GetLayoutExerciseThree() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CategoryApp()
    }
}

@Composable
fun CategoryApp() {
    val categories = listOf(
        "Fiction",
        "Mystery",
        "Science",
        "Fiction",
        "Fantasy",
        "Adventure",
        "Historical",
        "Horror",
        "Romance"
    )
    val suggestions = listOf(
        "Biography",
        "Cookbook",
        "Poetry",
        "Self-help",
        "Thriller"
    )
    var selectedCategories by remember {
        mutableStateOf(setOf<String>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Choose a category:", style =
            MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(5.dp))
        AssistChip(
            onClick = { },
            label = { Text("Need help?") }
        )
        Spacer(modifier = Modifier.height(5.dp))

        CategoryChip(
            category = categories,
            selecetedCategory = selectedCategories,
            onSelectedCategory = {
                selectedCategories = if (selectedCategories.contains(it))
                    selectedCategories - it
                else
                    selectedCategories + it
            }
        )
        SuggestionChip(
            suggestion = suggestions,
            selecetedCategory = selectedCategories,
            onSuggestionSeleceted = {
                selectedCategories = selectedCategories + it
            }
        )
        Spacer(modifier = Modifier.height(5.dp))

        if (selectedCategories.isNotEmpty()) {

            SelectedCategoriesChips(
                selectedCategories = selectedCategories,
                onCategoryRemoved = {
                    selectedCategories = selectedCategories - it
                }
            )
            AssistChip(
                onClick = { selectedCategories = setOf() },
                label = {
                    Text(
                        "Clear selections",

                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color.DarkGray
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = Color.Black
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChip(
    category: List<String>,
    selecetedCategory: Set<String>,
    onSelectedCategory: (String) -> Unit
) {

    Text(
        text = "Choose category: ",
        fontSize = 16.sp,
        fontWeight = FontWeight(500)
    )
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        category.forEach {
            FilterChip(
                selected = selecetedCategory.contains(it),
                onClick = { onSelectedCategory(it) },
                label = { Text(text = it) },
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }


}


@Composable
fun SuggestionChip(
    suggestion: List<String>,
    selecetedCategory: Set<String>,
    onSuggestionSeleceted: (String) -> Unit
) {

    Text(
        text = "Suggestion: ",
        fontSize = 16.sp,
        fontWeight = FontWeight(500)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        suggestion.forEach {
            val isSelected = selecetedCategory.contains(it)
            val chipColors =
                if (isSelected) {
                    SuggestionChipDefaults.suggestionChipColors(
                        containerColor = Color.LightGray
                    )
                } else {
                    SuggestionChipDefaults.suggestionChipColors()
                }
            androidx.compose.material3.SuggestionChip(
                onClick = { onSuggestionSeleceted(it) },
                label = { Text(text = it) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCategoriesChips(
    selectedCategories: Set<String>,
    onCategoryRemoved: (String) -> Unit
) {
    Text(
        "Selected categories:", style =
        MaterialTheme.typography.titleMedium
    )
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        selectedCategories.forEach { selectedCategory ->
            InputChip(
                selected = true,
                onClick = { },
                label = { Text(selectedCategory) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Deselect",
                        modifier = Modifier
                            .clickable {
                                onCategoryRemoved(selectedCategory)
                            }
                            .size(18.dp)
                    )
                },
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    GetLayoutExerciseThree()
}