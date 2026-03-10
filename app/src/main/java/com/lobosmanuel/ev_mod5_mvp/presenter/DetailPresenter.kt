package com.lobosmanuel.ev_mod5_mvp.presenter

import android.os.Bundle
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
        // Aquí se procesarán las selecciones de talla y color en el futuro
    }
}