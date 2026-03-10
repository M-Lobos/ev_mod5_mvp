package com.lobosmanuel.ev_mod5_mvp.view.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lobosmanuel.ev_mod5_mvp.databinding.ItemCartBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes

/**
 * CartAdapter: Gestiona la visualización de los productos en el carrito.
 * Recibe lambdas (onDelete) para mantener la lógica fuera del adaptador.
 */
class CartAdapter(
    private val items: List<Shoes>,
    private val onDeleteClick: (Shoes) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val shoe = items[position]

        with(holder.binding) {
            txtCartName.text = shoe.name
            // Mostramos el precio (asumiendo que es Int en tu modelo)
            // Si quieres el total por item (precio * cantidad), se ajustaría aquí
            txtQuantity.text = "1"

            // Carga de imagen con Glide
            Glide.with(imgCartItem.context)
                .load(shoe.imgUrl)
                .into(imgCartItem)

            // Configuración del botón de eliminar
            btnRemoveItem.setOnClickListener {
                onDeleteClick(shoe)
            }

            // Los botones +/- están en tu XML, por ahora los dejamos como cascarones
            // o podrías implementar una lógica simple de UI aquí.
            btnPlus.setOnClickListener { /* Lógica de cantidad */ }
            btnMinus.setOnClickListener { /* Lógica de cantidad */ }
        }
    }

    override fun getItemCount(): Int = items.size
}