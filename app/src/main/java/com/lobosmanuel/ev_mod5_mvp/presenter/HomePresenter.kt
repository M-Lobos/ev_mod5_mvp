package com.lobosmanuel.ev_mod5_mvp.presenter

import com.google.android.filament.View
import com.lobosmanuel.ev_mod5_mvp.model.Shoes
import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.HomeContract

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
                    "Ideal para correr en  asfalto",
                    "https://media.falabella.com/falabellaCL/50349557_1/w=1500,h=1500,fit=cover"),

            Shoes(  2,
                    "Zapatilla Urban",
                    120.0,
                    "Estilo diario y casual",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034"),

            Shoes(  3,
                    "Zapatilla trecking",
                    110.0,
                    "Excelente opción para sendereismo",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034"),

            Shoes(  4,
                    "Zapatilla formal",
                    150.0,
                    "Estilo elegante y sobrio",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034"),

            Shoes(  5,
                    "Zapatilla jogging",
                    90.0,
                    "Para largas caminatas",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034"),

            Shoes(  6,
                    "Zapatilla deportiva",
                    90.0,
                    "Para cualquier deporte que quieras",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034"),

            Shoes(  2,
                    "Zapatilla gym",
                    180.0,
                    "Ligeras y cómodas para tu rutina",
                    "https://www.bonzer.cl/cdn/shop/files/zapatilla-adidas-originals-sambae-778949.jpg?v=1719631034")

        )

        // Le ordenamos a la vista que los muestre
        view.showProducts(dummyList)
    }
}


