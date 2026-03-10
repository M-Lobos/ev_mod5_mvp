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
                    "https://www.merrell.cl/cdn/shop/files/J038219_2FM_1.jpg?v=1770215017&width=1400"),

            Shoes(  4,
                    "Zapatilla formal",
                    150.0,
                    "Estilo elegante y sobrio",
                    "https://www.hushpuppies.cl/cdn/shop/files/HP11001111271_111_1.jpg?v=1771247793&width=1400"),

            Shoes(  5,
                    "Zapatilla jogging",
                    90.0,
                    "Para largas caminatas",
                    "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSYftZDD0JOKVMzC6427EZvwsmj6mYLYDS7dKztVhfYogga_PFCcNxoTUM4CdjCHrHA4BK4oGTWMajKnS17oPIMf7Y4UT5vPeYaqCsq3AfpoaNG4pkH7uiOtg"),

            Shoes(  6,
                    "Zapatilla deportiva",
                    90.0,
                    "Para cualquier deporte que quieras",
                    "https://i5.walmartimages.cl/asr/114b652e-24c6-4993-af5b-a2138b44d9d8.0033b54a99f6b25aabca3c9d35776529.jpeg?odnHeight=500&odnWidth=500&odnBg=ffffff"),

            Shoes(  2,
                    "Zapatilla gym",
                    180.0,
                    "Ligeras y cómodas para tu rutina",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2yG5dNdlkPWI0Tm3otSnSSNhpjC6ywIbb7A&s")

        )

        // Le ordenamos a la vista que los muestre
        view.showProducts(dummyList)
    }
}


