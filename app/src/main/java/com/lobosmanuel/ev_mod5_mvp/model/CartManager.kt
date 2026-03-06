package com.lobosmanuel.ev_mod5_mvp.model
/**
 * CARTmANAGER
 * Funciona como la cinta de transporte (lógica) de las cajas donde están los datos (shoes),
 * Sólo aquí se necesitan instrucciones sobre el qué hacer, el contrato  (no el cómo)
 *
 * IcARTcONTRACT
 * Extiende a CartManager para que sepa qué debe hacer con als cajas (datos de shoes). Nota, con el
 * SharedPreferences el sólo le dice qué hacer con estas cajas; el que decide qué mostrar y cuando
 * es el PRESENTER, que le solicita al CartManager
 *
 * SharedPreferences
 * Ojetos que implementan una interfaz  (android.content.SharedPreferences)
 * Son como contenedores que almacenan key-value paris de un XML,
 * gestionan acceso mediante el context de una app.
 * En resumen, definen (como interfaz) el qué (contrato) del objeto, serán
 * implementados por la clase del objeto para cumplir su contrato.
 * */


object CartManager : ICartContract {
    private const val CART_KEY = "cart_items"

    // Implementa lógica con SharedPreferences
    // product es el PARÁMETRO de la función, Shoes es el tipo que recibe

    //Recupera lista actual de SharedPrefernces -> Agrega nuevo Shoe -> Convierte a JSON -> Guarda.
    override fun addToCart(product: Shoes)    { /* Lógica con GSON */ }

    //Recupera String de SharedPrefernces -> Convierte JSON a Lista -> Retorna Lista.
    override fun getCartContents(): List<Shoes> { /* Lógica con GSON */ return emptyList() }

    //Borra la clave específica en SharedPreferences.
    override fun clearCart() { /* Lógica */ }
}


