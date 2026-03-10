package com.lobosmanuel.ev_mod5_mvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.lobosmanuel.ev_mod5_mvp.R
import com.lobosmanuel.ev_mod5_mvp.databinding.FragmentDetailBinding
import com.lobosmanuel.ev_mod5_mvp.presenter.DetailPresenter
import com.lobosmanuel.ev_mod5_mvp.presenter.contracts.DetailContract

/**
 * Fragmento encargado de mostrar el detalle de un producto seleccionado.
 * Implementa [DetailContract.View] para delegar la lógica de datos al Presenter.
 */
class DetailFragment : Fragment(), DetailContract.View {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    // Referencia al presentador definida por el contrato
    private lateinit var presenter: DetailContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializamos el presentador pasándole 'this' como la vista
        presenter = DetailPresenter(this)

        // 1. Delegar la extracción y validación de datos al Presenter
        // Ya no lo hacemos manualmente aquí; el presenter nos dirá qué mostrar
        presenter.loadProductData(arguments)

        // 2. Corregir la navegación al carrito
        binding.btnGoToCart.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
        }

        // 3. Listener para agregar al carrito (Paso 2 del proyecto)
        binding.btnAddToCartDetail.setOnClickListener {
            presenter.addToCart()
        }
    }

    /**
     * MODO MVP: El presentador llama a este método cuando los datos están listos.
     * Inyecta los datos en los componentes de la interfaz.
     */
    override fun showProductDetail(name: String, price: Double, imageUrl: String) {
        binding.txtDetailName.text = name
        binding.txtDetailPrice.text = "$$price CLP"

        // Carga de imagen con Glide (ahora que ya tienes la dependencia corregida)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.shoe_test_img)
            .into(binding.imgDetailShoe)
    }

    /**
     * Muestra un error si los datos no pudieron recuperarse del Bundle.
     */
    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}