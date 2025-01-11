package com.example.journal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.R.attr.padding
import android.hardware.lights.Light
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journal.ui.theme.JournalTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JournalTheme {
                Home()
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(){
    var showBottomSheet by rememberSaveable {mutableStateOf(false)}
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFBEBAAC)

    ){
        Scaffold(
            containerColor = Color(0xFFBEBAAC),
            topBar = {
                TopAppBar(
                    title = { Text("Journal") },
                    colors = topAppBarColors(
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    actions = {
                        IconButton(onClick = { /* To search for an entry */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            },
            floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            showBottomSheet = true
                        },
                        shape = CircleShape,
                        containerColor = Color.Black,
                        contentColor = Color.White,
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Item")
                    }
            },
            floatingActionButtonPosition = FabPosition.Center
        ){
            innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                Text(
                    text = "Hi, anjelica",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp),
                )

            }

        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            InsideSheet()
        }
    }
}
@Preview
@Composable
fun BasicTextFieldExample() {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        TextField(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            value = text,
            onValueChange = {
                text = it
            },
            textStyle = TextStyle(
                color = if (text.isEmpty()) Color.LightGray else MaterialTheme.colorScheme.primary
            ),
            placeholder = {
                Text("Type here", color = Color.LightGray)
            }
        )
    }
}

@Composable
fun FilledButtonExample(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Save")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsideSheet(){
    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("NEW JOURNAL") }
                )
            },
            floatingActionButton = {
                FilledButtonExample ( onClick = {} )
            },
            floatingActionButtonPosition = FabPosition.EndOverlay
        ){
                innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                BasicTextFieldExample()
            }

        }
    }
}

