package com.example.mandala.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mandala.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MandalaDetailScreen() {
    val nameState = remember { mutableStateOf("Mục tiêu chính") }
    val classState = remember { mutableStateOf("Lớp Mandala") }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "TÊN BẢNG MANDALA",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Xử lý back */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Mục tiêu chính:",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = nameState.value,
                            onValueChange = { nameState.value = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            )
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Lớp:",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = classState.value,
                            onValueChange = { classState.value = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

                MandalaButtonGrid(modifier = Modifier.fillMaxWidth())
            }
        }

        FloatingActionButton(
            onClick = { /* Xử lý chuyển đổi trạng thái bảng */ },
            containerColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Icon(painter = painterResource(R.drawable.space_dashboard_24dp_000000_fill0_wght400_grad0_opsz24), contentDescription = "Toggle State")
        }

        FloatingActionButton(
            onClick = { /* Xử lý khi nhấn thêm */ },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun MandalaButtonGrid(modifier: Modifier = Modifier) {
    val pink = Color(0xFFFFC0CB)
    val lightBlue = Color(0xFFADD8E6)
    
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MandalaButton("F", pink, Modifier.weight(1f))
                MandalaButton("C", pink, Modifier.weight(1f))
                MandalaButton("G", pink, Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MandalaButton("B", pink, Modifier.weight(1f))
                MandalaButton("Mục tiêu chính", lightBlue, Modifier.weight(1f))
                MandalaButton("D", pink, Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MandalaButton("E", pink, Modifier.weight(1f))
                MandalaButton("A", pink, Modifier.weight(1f))
                MandalaButton("H", pink, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun MandalaButton(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /* Xử lý khi bấm nút trong bảng */ },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = text, color = Color.Black)
    }
}