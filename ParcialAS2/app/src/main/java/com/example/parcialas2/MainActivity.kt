package com.example.parcialas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcialas2.Screens.CarritoScreen
import com.example.parcialas2.Screens.CatalogoScreen
import com.example.parcialas2.Screens.DetalleProductoScreen
import com.example.parcialas2.Screens.RegistroProductoScreen
import com.example.parcialas2.ui.theme.LicoreriaTheme
import viewmodel.ProductoViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LicoreriaTheme {
                val navController = rememberNavController()
                val viewModel: ProductoViewModel = viewModel()

                NavHost(navController = navController, startDestination = "catalogo") {
                    composable("catalogo") {
                        CatalogoScreen(navController, viewModel)
                    }
                    composable("registro") {
                        RegistroProductoScreen(navController, viewModel)
                    }
                    composable("detalle/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                        DetalleProductoScreen(navController, viewModel, id)
                    }
                    composable("carrito") {
                        CarritoScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}