package com.example.parcialas2.Screens

import Model.Producto
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import viewmodel.ProductoViewModel

@Composable
fun RegistroProductoScreen(navController: NavController, viewModel: ProductoViewModel) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Agregar Producto", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
        OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
        OutlinedTextField(value = imagenUrl, onValueChange = { imagenUrl = it }, label = { Text("URL Imagen") })

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                val precioDouble = precio.toDoubleOrNull()

                if (
                    nombre.isNotBlank() &&
                    descripcion.isNotBlank() &&
                    imagenUrl.isNotBlank() &&
                    precioDouble != null
                ) {
                    viewModel.agregarProducto(
                        Producto(
                            id = viewModel.productos.size + 1,
                            nombre = nombre,
                            precio = precioDouble,
                            descripcion = descripcion,
                            imagenUrl = imagenUrl
                        )
                    )
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Todos los campos deben estar completos y el precio debe ser válido.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Guardar")
            }

            Button(onClick = { navController.popBackStack() }) {
                Text("Cancelar")
            }
        }
    }
}