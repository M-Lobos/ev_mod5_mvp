package com.lobosmanuel.ev_mod5_mvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lobosmanuel.ev_mod5_mvp.databinding.FragmentCartBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes
import com.lobosmanuel.ev_mod5_mvp.presenter.CartPresenter
import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.CartContract
import com.lobosmanuel.ev_mod5_mvp.view.Adapters.CartAdapter

/**
 * CartFragment: Representa la Vista (View) en el patrón MVP para el carrito de compras.
 * Su única responsabilidad es mostrar la información que el Presenter le entrega
 * y notificar eventos de usuario.
 */
class CartFragment : Fragment(), CartContract.View {

    // ViewBinding: _binding es nullable para liberar la memoria en onDestroyView
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    // Referencia al contrato del Presenter
    private lateinit var presenter: CartContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inicialización del Presenter pasando 'this' como la vista
        presenter = CartPresenter(this)

        // 2. Configuración del RecyclerView con un LayoutManager lineal
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())

        // 3. Listener para el botón de vaciar carrito
        binding.btnEmptyCart.setOnClickListener {
            presenter.emptyCart(requireContext())
        }

        // 4. Solicitud inicial de datos al Presenter
        presenter.loadCartItems(requireContext())
    }

    /**
     * Implementación de CartContract.View:
     * Recibe la lista y configura el adaptador con los listeners de acción.
     */
    override fun showCartList(items: List<Shoes>) {
        // Al crear el adaptador, le pasamos funciones lambda para manejar los clics
        val adapter = CartAdapter(
            items,
            onDeleteClick = { shoe ->
                presenter.removeFromCart(requireContext(), shoe)
            }
            // Borra onQuantityChange de aquí si el Adapter no lo tiene en su constructor
        )

        binding.rvCart.adapter = adapter

        // Gestión de visibilidad
        binding.rvCart.visibility = View.VISIBLE
        binding.txtEmptyMessage.visibility = View.GONE
    }

    /**
     * Implementación de CartContract.View: Actualiza el texto del monto total.
     */
    override fun updateTotal(total: Double) {
        // Formateo simple a dos decimales para el precio
        binding.txtTotalAmount.text = "Total: $${String.format("%.2f", total)} CLP"
    }

    /**
     * Implementación de CartContract.View: Gestiona el estado visual cuando no hay productos.
     */
    override fun showEmptyState() {
        binding.rvCart.visibility = View.GONE
        binding.txtEmptyMessage.visibility = View.VISIBLE
    }

    /**
     * Limpieza de referencias para evitar Memory Leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}