package com.lobosmanuel.ev_mod5_mvp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lobosmanuel.ev_mod5_mvp.R
import com.lobosmanuel.ev_mod5_mvp.databinding.FragmentHomeBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes
import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.HomeContract
import com.lobosmanuel.ev_mod5_mvp.presenter.HomePresenter

/**
 * HomeFragment: Representa la vista principal de la aplicación.
 * Implementa [HomeContract.View] para recibir datos desde el Presentador.
 * * Responsabilidades:
 * 1. Inflar la interfaz de usuario.
 * 2. Iniciar el Presentador.
 * 3. Renderizar la lista de productos en el RecyclerView.
 * 4. Gestionar la navegación hacia el Detalle y el Carrito.
 */
class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomePresenter
    private var _binding: FragmentHomeBinding? = null

    /**
     * Propiedad de acceso seguro al binding.
     * Solo es válida entre [onCreateView] y [onDestroyView].
     */
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializamos el presentador pasando 'this' como la referencia de la Vista
        presenter = HomePresenter(this)

        // Iniciamos la carga de datos (Lógica de negocio delegada al Presentador)
        presenter.start()
    }

    /**
     * Implementación de la interfaz HomeContract.View.
     * Recibe la lista de modelos [Shoes] y configura el adaptador del RecyclerView.
     * * @param products Lista de objetos tipo Shoes provenientes del Presentador.
     */
    override fun showProducts(products: List<Shoes>) {
        Log.d("MVP_TEST", "Renderizando ${products.size} productos en pantalla")

        // Configuración del adaptador con Lambdas para los clics
        val adapter = ShoeAdapter(
            shoes = products,
            onDetailClick = { shoe ->
                /**
                 * Paso de datos entre fragmentos:
                 * Creamos un Bundle con la información necesaria para el Detalle.
                 * Incluimos la URL de la imagen para que el Detalle pueda cargarla.
                 */
                val bundle = Bundle().apply {
                    putString("shoeName", shoe.name)
                    putDouble("shoePrice", shoe.price.toDouble())
                    putString("shoeImage", shoe.imgUrl) // Aseguramos que viaje la URL
                }

                // Ejecutamos la navegación usando la acción definida en nav_graph
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            },
            onAddClick = { shoe ->
                // Evento para añadir directamente al carrito desde la lista principal
                Log.d("MVP_TEST", "Añadiendo al carrito: ${shoe.name}")
            }
        )

        // Asignamos el adaptador al RecyclerView
        binding.rvShoes.adapter = adapter
    }

    /**
     * Importante: Limpiar el binding al destruir la vista para evitar Memory Leaks,
     * especialmente necesario en fragmentos.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}