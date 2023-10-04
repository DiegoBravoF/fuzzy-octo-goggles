package com.diego.marvel.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.diego.marvel.ui.loading.Loading

@Composable
fun ListScreen(onNavigateToDetail: (Int) -> Unit, viewModel: ListViewModel = viewModel()) {
    Scaffold { paddingValues ->
        val state = viewModel.uiState.collectAsState()
        if (state.value.loading) {
            Loading()
        }
        if (state.value.list.isNotEmpty()) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState)
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                state.value.list.forEach {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier =
                    Modifier.clickable {
                        onNavigateToDetail(it.id)
                    }) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .width(100.dp)
                                .height(100.dp)
                                .clip(CircleShape),
                            model = it.image,
                            contentDescription = it.name,
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text(text = it.name)
                            Text(text = it.description, maxLines = 2)
                        }
                    }
                }
            }
        }
    }
}