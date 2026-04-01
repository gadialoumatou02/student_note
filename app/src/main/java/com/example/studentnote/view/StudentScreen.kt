package com.example.studentnote.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentnote.data.Student
import com.example.studentnote.R


// Function to determine course
fun courseStudent(s: String): String {
    return if (s == "Mathématiques") "M" else "F"
}

// Filter
fun filterNote(liste: List<Student>, fr: Boolean, math: Boolean): List<Student> {
    return when {
        fr && !math -> {
            liste.filter { it.discipline == "Français" }
        }

        !fr && math -> {
            liste.filter { it.discipline == "Mathématiques" }
        }

        fr && math -> {
            liste.filter { it.discipline == "Français" || it.discipline == "Mathématiques" }
        }

        else -> liste
    }
}

@Composable
fun StudentScreen(
    modifier: Modifier = Modifier,
    studentListViewModel: StudentNoteListViewModel = viewModel()
) {
    var frChoice by remember { mutableStateOf(false) }
    var mathChoice by remember { mutableStateOf(false) }

    val students by studentListViewModel.studentList.observeAsState(emptyList())

    val filteredStudents = remember(students, frChoice, mathChoice) {
        filterNote(students, frChoice, mathChoice)
    }

    LaunchedEffect(Unit) {
        studentListViewModel.updateStudentNote()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = studentListViewModel::updateStudentNote
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_autorenew_24),
                    contentDescription = stringResource(R.string.refresh_button_description)
                )
            }
        },

        // ✅ CORRECT : bottomBar ici
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                MultiChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {

                    val buttonColors = SegmentedButtonDefaults.colors(
                        activeContainerColor = colorResource( R.color.purple_500),
                        activeContentColor = colorResource( R.color.white),

                        inactiveContainerColor = colorResource( R.color.purple_200), // purple clair
                        inactiveContentColor = colorResource( R.color.black)
                    )

                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
                        checked = frChoice,
                        onCheckedChange = { frChoice = it },
                        modifier = Modifier.weight(1f),
                        colors = buttonColors,
                        label = {
                            Text(text = stringResource(R.string.fr_choice))
                        }
                    )

                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
                        checked = mathChoice,
                        onCheckedChange = { mathChoice = it },
                        modifier = Modifier.weight(1f),
                        colors = buttonColors,
                        label = {
                            Text(text = stringResource(R.string.math_choice))
                        }
                    )
                }
            }
        }

    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(filteredStudents) { student ->
                StudentRow(student)
            }
        }
    }
}

@Composable
fun StudentRow(student: Student, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = student.academie,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(0.35f)
        )

        Text(
            text = "(${courseStudent(student.discipline)})",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            modifier = Modifier
                .weight(0.10f)
                .padding(horizontal = 8.dp)
        )

        Text(
            text = student.competences,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(0.40f)
        )

        Text(
            text = "${student.taux.toInt()} %",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier
                .weight(0.15f)
                .padding(start = 8.dp)
        )
    }
}
