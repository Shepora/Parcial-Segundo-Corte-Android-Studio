package com.example.parcialas2.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import viewmodel.ProductoViewModel

@Composable
fun CarritoScreen(navController: NavController, viewModel: ProductoViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(34.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.carrito) { producto ->
                Text("- ${producto.nombre}: $${producto.precio}")
            }
        }

        Text("Total: $${viewModel.totalCarrito()}")

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                viewModel.limpiarCarrito()
                navController.popBackStack()
            }) {
                Text("Finalizar Compra")
            }
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}
