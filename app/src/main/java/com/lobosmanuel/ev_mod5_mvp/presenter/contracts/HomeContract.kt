package com.lobosmanuel.ev_mod5_mvp.presenter.contracts

import com.lobosmanuel.ev_mod5_mvp.model.Shoes

/**
 * 1) Se defeine el contrato de la vista par ahombepresenter, el qué debe hacer
 * Contrato de la vista para homepresenter
 * */

interface HomeContract {

    interface View {
        fun showProducts(products: List<Shoes>)
    }

    interface Presenter {
        // Aquí es donde el Presenter pedirá los datos
        fun start()
    }
}

/**
 * Diferencias con el Detail
 *
 * DetailContract: Se enfoca en recibir datos de un solo producto a través de un Bundle y mostrarlos.
 *
 * CartContract: Se enfoca en gestionar una colección de datos (la lista de lo que el usuario eligió).
 * */