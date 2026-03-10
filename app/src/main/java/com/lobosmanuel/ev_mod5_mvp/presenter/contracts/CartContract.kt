package com.lobosmanuel.ev_mod5_mvp.presenter.contracts

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
        // Solicita los datos al CartManager
        fun loadCartItems()

        // Lógica para eliminar un producto (opcional por ahora)
        fun removeFromCart(shoe: Shoes)

        // Calcula el total sumando los precios
        fun calculateTotal()
    }
}