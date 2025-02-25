package com.example.mandala.Card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mandala.Dataclass.MandalaItem

@Composable
fun MandalaTableCard(Item:MandalaItem, onClickCard :()->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClickCard
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            TableBodyCell(text = Item.stt.toString(), width = 40.dp)
            TableBodyCell(text = Item.mucTieu, width = 80.dp)
            TableBodyCell(text = Item.o, width = 40.dp)
            TableBodyCell(text = Item.viecCanLam, width = 100.dp)
            TableBodyCell(text = Item.nguoiThucHien, width = 100.dp)
            TableBodyCell(text = Item.hanhDong, width = 100.dp)
        }
    }
}

@Composable
fun TableBodyCell(text: String, width: androidx.compose.ui.unit.Dp) {
    Text(
        text = text,
        modifier = Modifier.width(width),
        style = MaterialTheme.typography.bodyMedium
    )
}