package com.lobosmanuel.ev_mod5_mvp.view

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lobosmanuel.ev_mod5_mvp.R
import com.lobosmanuel.ev_mod5_mvp.databinding.FragmentHomeBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes
import com.lobosmanuel.ev_mod5_mvp.presenter.HomeContract
import com.lobosmanuel.ev_mod5_mvp.presenter.HomePresenter


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 *
 * 3) Implementar la interfaz de la vista en el fragment
 *      No olvidar importar la interfaz del contrac HomeContract.View
 */
class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter : HomePresenter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        // 4) declarar y definir el presenter  para la vista
        presenter = HomePresenter(this)

        // 5) iniciar el presentador desde el fragment
        presenter.start()


        //boton que navega del fragmento 1 al 2, actualizar a home y detail
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showProducts(products: List<Shoes>){
        d("MVP_TEST", "Se recibieron ${products.size} productos en la vista")
    }
}