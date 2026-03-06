package com.lobosmanuel.ev_mod5_mvp.presenter

import com.google.android.filament.View
import com.lobosmanuel.ev_mod5_mvp.model.Shoes

/**
 * 2) Extender la interface de HomeContract a la clase del presentador HomePresenter
 *
 * Los Presenter son clases intermediarias de lógica de presentación.
 * Reciben eventos de la vista, solicitan datos al modelo, y administran
 * la respuesta de vuelta a la vista.
 *
 * */

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter{

    override fun start(){
        //Sim de una lista de datos
        val dummyList = listOf(
            Shoes(  1,
                    "Zapatilla Runner",
                    80.0,
                    "Ideal para asfalto",
                    "https://media.falabella.com/falabellaCL/50349557_1/w=1500,h=1500,fit=cover"),

            Shoes(  2,
                    "Zapatilla Urban",
                    120.0,
                    "Estilo diario",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034")
        )

        // Le ordenamos a la vista que los muestre
        view.showProducts(dummyList)
    }
}


