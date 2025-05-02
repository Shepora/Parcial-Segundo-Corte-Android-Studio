package com.example.parcialas2.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import viewmodel.ProductoViewModel

@Composable
fun CatalogoScreen(navController: NavController, viewModel: ProductoViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Catálogo de Productos", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.productos) { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable { navController.navigate("detalle/${producto.id}") }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(producto.imagenUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = producto.nombre,
                        modifier = Modifier.size(90.dp)
                    )
                    Spacer(Modifier.width(23.dp))
                    Column {
                        Text(producto.nombre)
                        Text("$${producto.precio}")
                    }
                }
            }
        }

        Text("Total Carrito: $${viewModel.totalCarrito()}", style = MaterialTheme.typography.bodyLarge)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate("registro") }) {
                Text("Agregar Producto")
            }
            Button(onClick = { navController.navigate("carrito") }) {
                Text("Ir al Carrito")
            }
        }
    }
}