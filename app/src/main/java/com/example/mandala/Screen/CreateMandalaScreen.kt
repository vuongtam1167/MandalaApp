package com.example.mandala.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CreateMandalaScreen() {
    val boardTypes = listOf("Bảng chính", "Bảng phụ")
    val mainBoardList = listOf("Bảng chính A", "Bảng chính B", "Bảng chính C")
    val classList = listOf("Lớp 1", "Lớp 2", "Lớp 3")
    val cellList = listOf("A", "B", "C", "D", "E", "F", "G", "H")

    var selectedBoardType by remember { mutableStateOf(boardTypes[0]) }
    var boardName by remember { mutableStateOf("") }
    var selectedMainBoard by remember { mutableStateOf(mainBoardList[0]) }
    var selectedClass by remember { mutableStateOf(classList[0]) }
    var selectedCell by remember { mutableStateOf(cellList[0]) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "TẠO MANDALA",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý back */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (selectedBoardType == "Bảng chính") {
                        // Xử lý với boardName
                    } else {
                        // Xử lý với selectedMainBoard, selectedClass, selectedCell
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DropdownSetting(
                label = "Loại bảng",
                selected = selectedBoardType,
                items = boardTypes,
                onItemSelected = {
                    selectedBoardType = it
                    // Reset các trường khi đổi loại bảng
                    boardName = ""
                    selectedMainBoard = mainBoardList[0]
                    selectedClass = classList[0]
                    selectedCell = cellList[0]
                }
            )
            if (selectedBoardType == "Bảng chính") {
                SettingTextField(
                    label = "Tên bảng chính",
                    value = boardName,
                    onValueChange = { boardName = it }
                )
            } else {
                DropdownSetting(
                    label = "Tên bảng chính",
                    selected = selectedMainBoard,
                    items = mainBoardList,
                    onItemSelected = { selectedMainBoard = it }
                )
                DropdownSetting(
                    label = "Lớp bảng",
                    selected = selectedClass,
                    items = classList,
                    onItemSelected = { selectedClass = it }
                )
                DropdownSetting(
                    label = "Ô",
                    selected = selectedCell,
                    items = cellList,
                    onItemSelected = { selectedCell = it }
                )
            }
        }
    }
}

@Composable
fun DropdownSetting(
    label: String,
    selected: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .clickable { expanded = true }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = selected)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}
