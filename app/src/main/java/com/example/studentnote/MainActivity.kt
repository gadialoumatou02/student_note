package com.example.studentnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentnote.ui.theme.StudentNoteTheme
import com.example.studentnote.view.StudentScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentNoteTheme {
                StudentScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudentNoteTheme {
        StudentScreen(modifier = Modifier.fillMaxSize())
    }
}