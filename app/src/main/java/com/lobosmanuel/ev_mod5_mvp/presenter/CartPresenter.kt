package com.lobosmanuel.ev_mod5_mvp.presenter

import android.content.Context
import com.lobosmanuel.ev_mod5_mvp.model.CartManager
import com.lobosmanuel.ev_mod5_mvp.model.Shoes
import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.CartContract

/**
 * CartPresenter: Orquestador de la lógica para la pantalla del carrito.
 * * Actúa como mediador entre la persistencia (CartManager) y la interfaz
 * de usuario (CartFragment), asegurando que la vista permanezca "pasiva".
 */
class CartPresenter(private val view: CartContract.View) : CartContract.Presenter {

    /**
     * Carga los elementos del carrito y decide qué estado debe mostrar la vista.
     */
    override fun loadCartItems(context: Context) {
        // 1. Solicitamos los datos al Modelo
        val items = CartManager.getCartContents(context)

        // 2. Lógica de presentación: ¿Hay items o está vacío?
        if (items.isEmpty()) {
            view.showEmptyState()
        } else {
            // Entregamos la lista a la vista para que la infle en el RecyclerView
            view.showCartList(items)

            // Gatillamos el cálculo del total automáticamente al cargar
            calculateTotal(context)
        }
    }

    /**
     * Solicita al modelo el cálculo del total y formatea la respuesta para la vista.
     */
    override fun calculateTotal(context: Context) {
        val total = CartManager.getTotalPrice(context)

        // Notificamos a la vista el nuevo monto para actualizar el TextView
        view.updateTotal(total)
    }

    override fun emptyCart(context: Context) {
        // Se le ordena al modelo limpiar la persistencia
        CartManager.clearCart(context)

        // 2. Notifica a la vista que refresque la interfaz
        // Al estar vacío, loadCartItems detectará la lista vacía y mostrará showEmptyState()
        loadCartItems(context)
    }

    /**
     * Gestiona la eliminación de un producto específico.
     * Nota: En una fase avanzada, aquí se llamaría a CartManager.remove(shoe),
     * y luego se ejecutaría loadCartItems(context) para refrescar la lista.
     */
    override fun removeFromCart(context: Context, shoe: Shoes) {
        // 1. Borra de la lista única del Manager
        CartManager.removeFromCart(context, shoe)

        // 2. RE-CARGA: Esto le envía la lista actualizada al Fragment

        loadCartItems(context)
    }
}