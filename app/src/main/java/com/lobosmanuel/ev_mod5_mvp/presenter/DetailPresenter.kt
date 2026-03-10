package com.lobosmanuel.ev_mod5_mvp.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lobosmanuel.ev_mod5_mvp.model.CartManager
import com.lobosmanuel.ev_mod5_mvp.model.Shoes
import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.DetailContract

/**
 * Son clases intermediarias de lógica de presentación.
 * Reciben eventos de la vista, solicitan datos al modelo, y administran
 * la respuesta de vuelta a la vista.
 *
 * */

/**
 * DetailPresenter: Intermediario entre los datos (Model) y la interfaz (View) del detalle.
 * Implementa la lógica de extracción de argumentos y validación de datos.
 * * @property view Referencia a la interfaz del contrato para actualizar la UI.
 */
class DetailPresenter(private val view: DetailContract.View) : DetailContract.Presenter {

    // 1. Declaramos la variable para "recordar" el zapato actual
    private var lastLoadedShoe: Shoes? = null

    /**
     * Procesa el [Bundle] recibido desde el fragmento.
     * Extrae los datos y decide qué método de la vista debe ejecutarse.
     * * @param arguments El conjunto de datos enviados desde el HomeFragment.
     */
    override fun loadProductData(arguments: Bundle?) {
        // Extracción segura de datos
        val name = arguments?.getString("shoeName")
        val price = arguments?.getDouble("shoePrice")
        val imageUrl = arguments?.getString("shoeImage")

        // Lógica de validación: Solo mostramos el detalle si los datos críticos existen
        if (name != null && price != null && imageUrl != null) {
            view.showProductDetail(name, price, imageUrl)
        } else {
            // En caso de error en el paso de datos, notificamos a la vista
            view.showError("Error al recuperar la información del producto")
        }
    }

    /**
     * Gestiona la acción de agregar al carrito.
     * (Pendiente de conectar con el CartManager en el paso 2 del sumario).
     */
    override fun addToCart() {
        // 1. Necesitamos el objeto Shoes.
        // Podrías guardarlo en una variable de clase cuando se carga en loadProductData
        val currentShoe = lastLoadedShoe

        if (currentShoe != null) {
            // 2. Usamos el contexto de la vista (Fragment) para guardar
            val context = (view as? Fragment)?.context
            if (context != null) {
                CartManager.addToCart(context, currentShoe)
            }
        }
    }
}