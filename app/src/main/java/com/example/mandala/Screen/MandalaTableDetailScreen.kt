import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mandala.Card.MandalaTableCard
import com.example.mandala.Dataclass.MandalaItem

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalaTableDetailScreen() {
    // Ví dụ danh sách dữ liệu (thay bằng dữ liệu thật khi có)
    val sampleData = listOf(
        MandalaItem(1, "Mục tiêu 1", "A", "Việc 1", "Người A", "Hành động 1"),
        MandalaItem(2, "Mục tiêu 2", "B", "Việc 2", "Người B", "Hành động 2"),
        MandalaItem(3, "Mục tiêu 3", "C", "Việc 3", "Người C", "Hành động 3")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Chi tiết Mandala") },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý sự kiện back */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Bọc toàn bộ LazyColumn trong Box có horizontalScroll nếu cần cuộn ngang
        Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Header bảng
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        TableHeaderCell(text = "STT", width = 40.dp)
                        TableHeaderCell(text = "Mục tiêu", width = 80.dp)
                        TableHeaderCell(text = "Ô", width = 40.dp)
                        TableHeaderCell(text = "Việc cần làm", width = 100.dp)
                        TableHeaderCell(text = "Người thực hiện", width = 100.dp)
                        TableHeaderCell(text = "Hành động", width = 100.dp)
                    }
                    Divider()
                }

                // Các dòng dữ liệu được hiển thị trong Card
                items(sampleData) { item ->
                    MandalaTableCard(item,{})
                }
            }
        }
    }
}

@Composable
fun TableHeaderCell(text: String, width: androidx.compose.ui.unit.Dp) {
    Text(
        text = text,
        modifier = Modifier.width(width),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary
    )
}
