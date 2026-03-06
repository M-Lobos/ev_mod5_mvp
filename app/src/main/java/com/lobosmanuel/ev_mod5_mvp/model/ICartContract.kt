package com.lobosmanuel.ev_mod5_mvp.model

/**
 * Las buenas prácticas de la industria y las detendencias actuales sugieren la separación
 * de responsabildiades, así que el contrato como interfaz para el CartManager se ha establecido
 * en un archivo aparte
 *
 * Este contrato define qué hace la clase, no cómo, básicamente dice a al ManagerCart, qué debe
 * hacer para actuar como "repositorio" de las cajas (datos de shoes) que ha selecionado el usuario
 * desde las vistas
 * */


interface ICartContract {
    fun addToCart(product: Shoes)
    fun getCartContents(): List<Shoes>
    fun clearCart()

}