package com.lobosmanuel.ev_mod5_mvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lobosmanuel.ev_mod5_mvp.R
import com.lobosmanuel.ev_mod5_mvp.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Extraer los datos del bundle que enviamos en HomeFragment
        val shoeName = arguments?.getString("shoeName") ?: "Producto"
        val shoePrice = arguments?.getDouble("shoePrice") ?: 0.0

        // 2. Inyectar los datos en los TextViews del XML
        // Asumiendo que estos son tus IDs en fragment_detail.xml
        binding.txtDetailName.text = shoeName
        binding.txtDetailPrice.text = "$$shoePrice CLP"

        // 3. Corregir la navegación al carrito
        binding.btnGoToCart.setOnClickListener {
            // Usa el ID de la ACCIÓN del nav_graph, no el ID del botón
            findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}