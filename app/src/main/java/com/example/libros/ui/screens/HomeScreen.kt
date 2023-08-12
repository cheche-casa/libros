package com.example.libros.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
        is LibrosUiState.Success -> AnfibiosGridScreen(librosUiState.libros, modifier.fillMaxSize())
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
private fun AnfibiosGridScreen(libro: Libros, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Non Erros")
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
