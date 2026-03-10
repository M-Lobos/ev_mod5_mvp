package com.lobosmanuel.ev_mod5_mvp.model

// revisar esto luego por que está saltando mucho error
// import android.os.Parcelable
// import kotlinx.parcelize.Parcelize

/**
 * SHOES
 * Es la caja donde están los datos del producto - es el modelo; el molde del cual se crean los
 * objetos.
 *
 * DATA CLASS
 * Tipo de clase especializada en almacernar estados y modelar datos por sobre
 * la gestión de operaciones complejas y repetitivas.
 *
 */


data class Shoes(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imgUrl: String
)