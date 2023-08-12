package com.example.libros.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.libros.R
import com.example.libros.model.Libro
import com.example.libros.model.Libros
import com.example.libros.ui.theme.LibrosTheme

@Composable
fun HomeScreen(
    librosUiState: LibrosUiState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (librosUiState) {
        is LibrosUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is LibrosUiState.Success -> LibrosGridScreen(librosUiState.libros.items, modifier.fillMaxSize())
        is LibrosUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
private fun LibrosGridScreen(libros: List<Libro>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ){
        items(items = libros, key = { libro -> libro.id }) {
                libro -> LibroCard(libro)
        }
    }
}

@Composable
fun LibroCard(libro: Libro, modifier: Modifier = Modifier) {
    val url = libro.volumeInfo.imageLinks?.thumbnail?.replace("http","https")
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation =  CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.libro),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LibrosTheme {
        LoadingScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    LibrosTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AnfibiosGridScreenPreview() {
//    LibrosTheme {
//        val mockData = List(10) { Libros("$it", "", "", "") }
//        AnfibiosGridScreen(mockData) //simula datos baleiros
//    }
//}
