package com.lobosmanuel.ev_mod5_mvp.view.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lobosmanuel.ev_mod5_mvp.databinding.ItemCartBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes

/**
 * CartAdapter: Gestiona la visualización de los productos en el carrito.
 * Ahora maneja incremento, decremento y eliminación total.
 */
class CartAdapter(
    private val items: List<Shoes>,
    private val onDeleteClick: (Shoes) -> Unit,
    private val onPlusClick: (Shoes) -> Unit,
    private val onMinusClick: (Shoes) -> Unit
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

            // 1. Ahora mostramos la cantidad real desde el objeto Shoes
            txtQuantity.text = shoe.quantity.toString()

            // 2. Opcional: Mostrar el subtotal del ítem (precio * cantidad)
            // txtItemTotal.text = "$${shoe.price * shoe.quantity}"

            Glide.with(imgCartItem.context)
                .load(shoe.imgUrl)
                .into(imgCartItem)

            // Configuración del botón de eliminar (basurero)
            btnRemoveItem.setOnClickListener {
                onDeleteClick(shoe)
            }

            // Lógica de incremento
            btnPlus.setOnClickListener {
                onPlusClick(shoe)
            }

            // Lógica de decremento con validación de Toast
            btnMinus.setOnClickListener {
                if (shoe.quantity > 1) {
                    onMinusClick(shoe)
                } else {
                    Toast.makeText(
                        root.context,
                        "Usa el basurero para quitar el producto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size
}