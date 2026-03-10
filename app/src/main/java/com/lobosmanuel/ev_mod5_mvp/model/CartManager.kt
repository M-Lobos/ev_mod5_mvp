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

        // 1. Buscamos si el zapato ya existe en la lista (por ID o Nombre)
        val existingIndex = currentCart.indexOfFirst { it.name == product.name }

            //esta función considera el caso de "añadir" uno para el botón de "+"
        if (existingIndex != -1) {
            // 2. Si existe, lo reemplazamos por una COPIA con quantity + 1
            val existingItem = currentCart[existingIndex]
            currentCart[existingIndex] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            // 3. Si no existe, lo agregamos tal cual (viene con quantity = 1 por defecto)
            currentCart.add(product)
        }

        // 4. Guardamos el JSON actualizado
        saveCart(context, currentCart)
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
        return getCartContents(context).sumOf { it.price * it.quantity }
    }

    /**
     * Elimina todos los elementos del carrito (Limpia la persistencia).
     */
    fun clearCart(context: Context) {
        getPrefs(context).edit().remove(CART_KEY).apply()
    }

    fun removeFromCart(context: Context, shoe: Shoes) {
        val currentCart = getCartContents(context).toMutableList()
        currentCart.removeAll { it.name == shoe.name }
        saveCart(context, currentCart) // Usamos la función auxiliar
    }

    /**
     * Disminuye en 1 la cantidad. La validación de "no menor a 1"
     * se hace en el Presenter/Adapter, pero aquí aseguramos la persistencia.
     */
    fun removeOne(context: Context, product: Shoes) {
        val currentCart = getCartContents(context).toMutableList()
        val index = currentCart.indexOfFirst { it.name == product.name }

        if (index != -1 && currentCart[index].quantity > 1) {
            val item = currentCart[index]
            currentCart[index] = item.copy(quantity = item.quantity - 1)
            saveCart(context, currentCart)
        }
    }

    /**
     * Función auxiliar para centralizar el guardado en SharedPreferences.
     * Convierte la lista a JSON y la guarda.
     */
    private fun saveCart(context: Context, cart: List<Shoes>) {
        val json = gson.toJson(cart)
        getPrefs(context).edit().putString(CART_KEY, json).apply()
    }
}
