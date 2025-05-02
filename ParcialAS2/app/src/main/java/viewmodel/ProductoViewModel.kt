package viewmodel

import Model.Producto
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ProductoViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>()
    val carrito = mutableStateListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun agregarAlCarrito(producto: Producto) {
        carrito.add(producto)
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun totalCarrito(): Double {
        return carrito.sumOf { it.precio }
    }

    fun limpiarCarrito() {
        carrito.clear()
    }
}
