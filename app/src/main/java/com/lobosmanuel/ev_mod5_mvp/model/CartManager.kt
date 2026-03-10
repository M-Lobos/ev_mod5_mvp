package com.lobosmanuel.ev_mod5_mvp.model

import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.CartContract
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * CARTmANAGER
 * Funciona como la cinta de transporte (lógica) de las cajas donde están los datos (shoes),
 * Sólo aquí se necesitan instrucciones sobre el qué hacer, el contrato  (no el cómo)
 *
 *
 * SharedPreferences
 * Ojetos que implementan una interfaz  (android.content.SharedPreferences)
 * Son como contenedores que almacenan key-value paris de un XML,
 * gestionan acceso mediante el context de una app.
 * En resumen, definen (como interfaz) el qué (contrato) del objeto, serán
 * implementados por la clase del objeto para cumplir su contrato.
 * */

/**
 * CartManager (Modelo / Repositorio)
 * * Actúa como la "cinta de transporte" de la lógica de negocio para el carrito.
 * Utiliza el patrón Singleton para asegurar una única fuente de verdad.
 * Persiste los datos en formato JSON dentro de SharedPreferences.
 */
object CartManager {
    private const val PREFS_NAME = "cart_prefs"
    private const val CART_KEY = "cart_items"
    private val gson = Gson()

    // Una única lista en memoria para toda la app
    private val memoryCart = mutableListOf<Shoes>()

    /**
     * Obtiene la instancia de SharedPreferences de forma privada.
     */
    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Agrega un producto al almacenamiento persistente.
     * 1. Recupera la lista actual.
     * 2. Añade el nuevo objeto [Shoes].
     * 3. Serializa la lista completa a JSON y guarda.
     */
    fun addToCart(context: Context, product: Shoes) {
        val currentCart = getCartContents(context).toMutableList()
        currentCart.add(product)

        val json = gson.toJson(currentCart)
        getPrefs(context).edit().putString(CART_KEY, json).apply()
    }

    /**
     * Recupera todos los productos almacenados en el carrito.
     * Convierte la cadena JSON de SharedPreferences de vuelta a una [List] de [Shoes].
     * Retorna una lista vacía si no hay datos.
     */
    fun getCartContents(context: Context): List<Shoes> {
        val json = getPrefs(context).getString(CART_KEY, null) ?: return emptyList()

        // TypeToken es necesario para que GSON reconstruya la estructura de la lista genérica
        val type = object : TypeToken<List<Shoes>>() {}.type
        return gson.fromJson(json, type)
    }

    /**
     * Calcula la suma total de los precios de los productos en el carrito.
     */
    fun getTotalPrice(context: Context): Double {
        return getCartContents(context).sumOf { it.price.toDouble() }
    }

    /**
     * Elimina todos los elementos del carrito (Limpia la persistencia).
     */
    fun clearCart(context: Context) {
        getPrefs(context).edit().remove(CART_KEY).apply()
    }

    fun removeFromCart(context: Context, shoe: Shoes) {
        // Borramos directamente de la lista que vive en memoria
        memoryCart.removeAll { it.name == shoe.name }
    }
}
