package com.lobosmanuel.ev_mod5_mvp.presenter.contracts

import android.content.Context
import com.lobosmanuel.ev_mod5_mvp.model.Shoes

/**
 * Contrato para la gestión del Carrito de Compras.
 */
interface CartContract {

    interface View {
        // Muestra la lista de productos en el RecyclerView del carrito
        fun showCartList(items: List<Shoes>)

        // Actualiza el TextView del total de la compra
        fun updateTotal(total: Double)

        // Muestra un mensaje si el carrito está vacío
        fun showEmptyState()
    }

    interface Presenter {
        // Se agrega el parámetro Context para que coincida con lo que necesita el CartManager

        // Solicita los datos al CartManager
        fun loadCartItems(context: Context)

        // Lógica para eliminar un producto (opcional por ahora)
        fun removeFromCart(context: Context, shoe: Shoes)

        // Calcula el total sumando los precios
        fun calculateTotal(context: Context)

        // Función para vaciar todo el carrito
        fun emptyCart(context: Context)

        //Función para incrementar una unidad de un producto que ya está en el carrito
        fun increaseQuantity(context: Context, shoe: Shoes)

        //Función para decrementar una unidad de un producto que ya está en el carrito
        fun decreaseQuantity(context: Context, shoe: Shoes)
    }
}