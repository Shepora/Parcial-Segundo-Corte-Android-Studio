package com.example.parcialas2.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import viewmodel.ProductoViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun DetalleProductoScreen(navController: NavController, viewModel: ProductoViewModel, id: Int?) {
    val producto = id?.let { viewModel.obtenerProductoPorId(it) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (producto == null) {
            Text("Producto no encontrado")
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(producto.imagenUrl)
                    .crossfade(true)
                    .error(android.R.drawable.ic_dialog_alert)
                    .build(),
                contentDescription = producto.nombre,
                modifier = Modifier.height(200.dp).fillMaxWidth()
            )
            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
            Text("Precio: $${producto.precio}")
            Text("Descripción: ${producto.descripcion}")

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    viewModel.agregarAlCarrito(producto)
                    navController.popBackStack()
                }) {
                    Text("Agregar al Carrito")
                }
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        }
    }
}