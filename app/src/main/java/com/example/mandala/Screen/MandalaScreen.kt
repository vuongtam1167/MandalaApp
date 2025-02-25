package com.example.mandala.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mandala.Card.MandalaCard
import com.example.mandala.Dataclass.Mandala
import com.example.mandala.Dataclass.MandalaChild
import com.example.mandala.Dataclass.MandalaDiaChi

@Preview(showBackground = true)
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalaScreen() {
    // Dữ liệu mẫu để test
    val sampleData = remember {
        listOf(
            Mandala(
                IsChidren = false,
                bangCha = MandalaChild(tenBang = "", o = ""),
                tenBang = "Bảng Chính 1",
                soBang = 1,
                capLop = "Lớp 1",
                diaChis = listOf(
                    MandalaDiaChi(diaChi = "A", noiDung = "Nội dung A"),
                    MandalaDiaChi(diaChi = "B", noiDung = "Nội dung B")
                )
            ),
            Mandala(
                IsChidren = true,
                bangCha = MandalaChild(tenBang = "Bảng Chính 1", o = "A"),
                tenBang = "Bảng Phụ 1",
                soBang = 2,
                capLop = "Lớp 2",
                diaChis = listOf(
                    MandalaDiaChi(diaChi = "C", noiDung = "Nội dung C"),
                    MandalaDiaChi(diaChi = "D", noiDung = "Nội dung D")
                )
            )
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "MANDALA",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleData.filter { !it.IsChidren }) { mandala ->
                MandalaCard(mandala = mandala,{})
            }
        }
    }
}