package com.lobosmanuel.ev_mod5_mvp.presenter.contracts

import android.os.Bundle


// Este contrato define qué hace la vista, y qué ordenes recibirá el Presentador
interface DetailContract {
    interface View {
        fun showProductDetail(name: String, price: Double, imageUrl: String)
        fun showError(message: String)
    }

    interface Presenter {
        fun loadProductData(arguments: Bundle?)
        fun addToCart()
    }
}