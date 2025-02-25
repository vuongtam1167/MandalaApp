package com.example.mandala.Screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
fun MandalaDetailChartScreen() {
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
                            fontWeight = FontWeight.Bold,
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
                // Thông tin Người thực hiện và Lớp
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Mục tiêu chính: ",
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

                Spacer(modifier = Modifier.height(25.dp))

                // Biểu đồ 3x3 dạng chart
                MandalaChart(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Nút FAB bên trái (chuyển đổi trạng thái chart)
        FloatingActionButton(
            onClick = { /* Xử lý chuyển đổi trạng thái bảng */ },
            containerColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.check_box_outline_blank_24dp_000000_fill0_wght400_grad0_opsz24),
                contentDescription = "Toggle State"
            )
        }

        // Nút FAB bên phải (thêm mới)
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
fun MandalaChart(modifier: Modifier = Modifier) {
    // Danh sách 9 trạng thái, tương ứng 9 ô
    val cellTexts = remember {
        mutableStateListOf("F", "C", "G", "B", "Mục tiêu chính", "D", "E", "A", "H")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(580.dp)  // Set a fixed height to make cells taller
    ) {
        // 1) Canvas to draw the grid lines
        Canvas(modifier = Modifier.fillMaxSize()) {
            val lineColor = Color.Black
            val strokeWidth = 4f

            val w = size.width
            val h = size.height

            // Draw two horizontal lines
            drawLine(
                color = lineColor,
                start = Offset(0f, h / 3f),
                end = Offset(w, h / 3f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = lineColor,
                start = Offset(0f, 2 * h / 3f),
                end = Offset(w, 2 * h / 3f),
                strokeWidth = strokeWidth
            )

            // Draw two vertical lines
            drawLine(
                color = lineColor,
                start = Offset(w / 3f, 0f),
                end = Offset(w / 3f, h),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = lineColor,
                start = Offset(2 * w / 3f, 0f),
                end = Offset(2 * w / 3f, h),
                strokeWidth = strokeWidth
            )

            // Draw outer border lines
            drawLine(
                color = lineColor,
                start = Offset(0f, 0f),
                end = Offset(w, 0f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = lineColor,
                start = Offset(0f, h),
                end = Offset(w, h),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = lineColor,
                start = Offset(0f, 0f),
                end = Offset(0f, h),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = lineColor,
                start = Offset(w, 0f),
                end = Offset(w, h),
                strokeWidth = strokeWidth
            )
        }

        // 2) 9 TextFields (3 rows x 3 columns) overlaying the Canvas
        Column(modifier = Modifier.fillMaxSize()) {
            // Row 1: indices 0,1,2
            Row(modifier = Modifier.weight(1f)) {
                ChartCellTextField(
                    text = cellTexts[0],
                    onValueChange = { cellTexts[0] = it },
                    modifier = Modifier.weight(1f)
                )
                ChartCellTextField(
                    text = cellTexts[1],
                    onValueChange = { cellTexts[1] = it },
                    modifier = Modifier.weight(1f)
                )
                ChartCellTextField(
                    text = cellTexts[2],
                    onValueChange = { cellTexts[2] = it },
                    modifier = Modifier.weight(1f)
                )
            }
            // Row 2: indices 3,4,5
            Row(modifier = Modifier.weight(1f)) {
                ChartCellTextField(
                    text = cellTexts[3],
                    onValueChange = { cellTexts[3] = it },
                    modifier = Modifier.weight(1f)
                )
                ChartCellTextField(
                    text = cellTexts[4],
                    onValueChange = { cellTexts[4] = it },
                    modifier = Modifier.weight(1f)
                )
                ChartCellTextField(
                    text = cellTexts[5],
                    onValueChange = { cellTexts[5] = it },
                    modifier = Modifier.weight(1f)
                )
            }
            // Row 3: indices 6,7,8
            Row(modifier = Modifier.weight(1f)) {
                ChartCellTextField(
                    text = cellTexts[6],
                    onValueChange = { cellTexts[6] = it },
                    modifier = Modifier.weight(1f)
                )
                ChartCellTextField(
                    text = cellTexts[7],
                    onValueChange = { cellTexts[7] = it },
                    modifier = Modifier.weight(1f)
                )
                ChartCellTextField(
                    text = cellTexts[8],
                    onValueChange = { cellTexts[8] = it },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartCellTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = false,
            minLines = 1,
            maxLines = 8,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}
