package com.lobosmanuel.ev_mod5_mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide                     //meter Glide en las deps en build.gradle(module:app)
import com.lobosmanuel.ev_mod5_mvp.R
import com.lobosmanuel.ev_mod5_mvp.databinding.ShoeCardLayoutBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes

/**
 * Adaptador encargado de gestionar y mostrar la lista de calzado en el RecyclerView.
 * @property shoes          Lista de objetos [Shoes] que se van a mostrar inicialmente.
 * @property onDetailClick  Función de callback para navegar al detalle del producto.
 * @property onAddClick     Función de callback (lambda) que se ejecuta al pulsar el botón "Agregar".
 */
class ShoeAdapter(
    private var shoes: List<Shoes>,
    private val onDetailClick: (Shoes) -> Unit,
    private val onAddClick: (Shoes) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    /**
     * ViewHolder es una clase interna que actúa como un contenedor para la vista de cada ítem.
     * Mantiene las referencias a las vistas mediante ViewBinding.
     */
    inner class ShoeViewHolder(val binding: ShoeCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ShoeCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    /**
     * Vincula los datos de un objeto [Shoes] con las vistas del ViewHolder.
     * Implementa la carga de imágenes dinámicas mediante Glide.
     */
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoe = shoes[position]

        holder.binding.apply {
            txtShoeName.text = shoe.name
            txtShoePrice.text = "$${shoe.price}"

            // === INTEGRACIÓN DE GLIDE ===
            // Reemplazamos el recurso estático por la carga desde la URL (imgUrl)
            Glide.with(holder.itemView.context)
                .load(shoe.imgUrl) // Usamos el nombre corregido del modelo
                .placeholder(R.drawable.shoe_test_img) // Imagen mientras carga
                .error(R.drawable.shoe_test_img)      // Imagen en caso de error de red
                .centerCrop()                         // Ajusta la imagen al contenedor
                .into(ShoeImg)                        // ID de tu ImageView en shoe_card_layout.xml

            // Gestión de eventos de clic
            root.setOnClickListener {
                onDetailClick(shoe)
            }

            btnAgregar.setOnClickListener {
                onAddClick(shoe)
            }
        }
    }

    override fun getItemCount(): Int = shoes.size

    /**
     * Actualiza la lista de datos y notifica al RecyclerView para refrescar la interfaz.
     */
    fun updateList(newList: List<Shoes>) {
        this.shoes = newList
        notifyDataSetChanged()
    }
}