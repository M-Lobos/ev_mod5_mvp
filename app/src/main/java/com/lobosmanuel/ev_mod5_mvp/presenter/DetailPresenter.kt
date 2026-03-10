package com.lobosmanuel.ev_mod5_mvp.presenter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
        val name = arguments?.getString("shoeName")
        val price = arguments?.getDouble("shoePrice")
        val imageUrl = arguments?.getString("shoeImage")

        if (name != null && price != null && imageUrl != null) {

            lastLoadedShoe = Shoes(
                id = 0,                    // Un ID genérico ya que el Bundle no lo trae
                name = name,               // 'name' es String, encaja perfecto
                price = price,             // 'price' es Double, encaja perfecto
                description = "Sin descripción disponible", // Campo obligatorio que faltaba
                imgUrl = imageUrl          // 'imageUrl' es String, encaja perfecto
            )

            view.showProductDetail(name, price, imageUrl)
        } else {
            view.showError("Error al recuperar la información del producto")
        }
    }

    /**
     * Gestiona la acción de agregar al carrito.
     * (Pendiente de conectar con el CartManager en el paso 2 del sumario).
     */
    override fun addToCart() {
        val currentShoe = lastLoadedShoe

        if (currentShoe != null) {
            // En lugar de intentar castear la vista a Fragment (que es arriesgado),
            // lo ideal sería que addToCart recibiera el Contexto, pero si quieres
            // probar rápido, usa el contexto del Fragment de esta forma:
            val context = (view as? Fragment)?.requireContext()

            if (context != null) {
                CartManager.addToCart(context, currentShoe)
                // Opcional: Avisa al usuario que funcionó
                Toast.makeText(context, "Añadido: ${currentShoe.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}