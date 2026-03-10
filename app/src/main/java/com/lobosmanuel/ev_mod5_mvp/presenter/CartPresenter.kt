package com.lobosmanuel.ev_mod5_mvp.presenter

import android.content.Context
import com.lobosmanuel.ev_mod5_mvp.model.CartManager

import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.CartContract

/**
 * Son clases intermediarias de lógica de presentación.
 * Reciben eventos de la vista, solicitan datos al modelo, y administran
 * la respuesta de vuelta a la vista.
 *
 * */

class CartPresenter(private val view: CartContract.View) : CartContract.Presenter {

    override fun loadCartItems(context: Context) {
        val items = CartManager.getCartContents(context)
        if (items.isEmpty()) {
            view.showEmptyState()
        } else {
            view.showCartList(items)
            calculateTotal(context)
        }
    }

    override fun calculateTotal(context: Context) {
        val items = CartManager.getCartContents(context)
        val total = items.sumOf { it.price.toDouble() }
        view.updateTotal(total)
    }

    override fun removeFromCart(context: Context, position: Int) {
        // Lógica para el futuro
    }
}