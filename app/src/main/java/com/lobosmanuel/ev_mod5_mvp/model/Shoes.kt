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
 * Los DATA CLASS de kotlin posee una función copy(), Esto genera un objeto nuevo con todos los
 * datos idénticos, excepto el que tú definas. Esto genera un objeto nuevo con todos los datos
 * idénticos, excepto el que tú definas
 */


data class Shoes(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imgUrl: String,
    val quantity: Int = 1         //<-- valor por defecto que luego haremos cambiar
)

//Se prefiere que los datos sean inmutables.
// En lugar de "cambiarle un valor" al objeto (lo que podría causar errores si otro componente
// está leyendo ese mismo objeto al mismo tiempo), lo que hacemos es crear una copia nueva con
// el dato actualizado.