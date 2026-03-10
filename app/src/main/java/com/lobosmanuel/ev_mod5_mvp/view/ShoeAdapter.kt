package com.lobosmanuel.ev_mod5_mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lobosmanuel.ev_mod5_mvp.databinding.ShoeCardLayoutBinding
import com.lobosmanuel.ev_mod5_mvp.model.Shoes

//La clase herada de RecyclerView.Adapter
//ViewHolder se define más abajo

/**
 * Adaptador encargado de gestionar y mostrar la lista de calzado en el RecyclerView.
 * @property shoes          Lista de objetos [Shoes] que se van a mostrar inicialmente.
 * @property onDetailClick  Función de callback para navegar al detalle del producto.
 * @property onAddClick     Función de callback (lambda) que se ejecuta al pulsar el botón "Agregar".
 */
class ShoeAdapter(
    private var shoes: List<Shoes>, // Usa 'var' para que updateList pueda funcionar
    private val onDetailClick: (Shoes) -> Unit, // Definición completa del lambda para navegación
    private val onAddClick: (Shoes) -> Unit    // Callback para la acción de agregar al carrito
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    /**
     * Las inner class pueden acceder directamente a los miembros de la clase externa, adiferencia  de una
     * nested class que no puede (de hecho siquiera tiene referencia a esta)
     *
     * ViewHolder es una clase interna que actúa como un contenedor para la vista de cada ítem.
     * Su función principal es mantener las referencias a las vistas (Views) mediante ViewBinding
     * para evitar llamadas costosas a findViewById() cada vez que se hace scroll.
     * @param binding       Proporciona acceso directo a todos los IDs definidos en shoe_card_layout.xml.
     */
    inner class ShoeViewHolder(val binding: ShoeCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Este método se llama cuando el RecyclerView necesita crear una nueva "caja" (ViewHolder).
     * Aquí se infla el diseño XML y se envuelve en la clase ShoeViewHolder.
     * @return Una nueva instancia de [ShoeViewHolder] con el layout inflado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ShoeCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    /**
     * Vincula los datos de un objeto específico de la lista con las vistas del ViewHolder.
     * Se ejecuta cada vez que un elemento entra en pantalla.
     * @param holder El contenedor que debe ser actualizado con nuevos datos.
     * @param position La posición del elemento dentro de la lista de datos.
     */
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoe = shoes[position]

        // holder.binding nos permite acceder a los elementos del XML por su ID
        holder.binding.apply {
            // Asigna el nombre del zapato al TextView correspondiente
            txtShoeName.text = shoe.name

            // Asigna el precio formateado con el símbolo de moneda
            txtShoePrice.text = "$${shoe.price}"

            // Carga una imagen temporal del sistema mientras no haya URLs reales
            // Usamos Shoe_img que es el ID que definiste en tu XML
            ShoeImg.setImageResource(android.R.drawable.ic_menu_gallery)

            // El click se pone en la raíz de la tarjeta (root) para ir al detalle
            root.setOnClickListener {
                onDetailClick(shoe)
            }

            // Define el comportamiento del botón "Agregar" enviando el objeto 'shoe' al Fragment
            btnAgregar.setOnClickListener {
                onAddClick(shoe)
            }
        }
    }

    /**
     * Informa al RecyclerView cuántos elementos hay en total en la fuente de datos.
     * @return El tamaño de la lista de zapatos.
     */
    override fun getItemCount(): Int = shoes.size

    /**
     * Método de utilidad para refrescar el contenido del adaptador desde el Presenter.
     * Reemplaza la lista actual por una nueva y notifica al RecyclerView que debe redibujarse.
     * @param newList La nueva lista de [Shoes] a mostrar.
     */
    fun updateList(newList: List<Shoes>) {
        this.shoes = newList
        // Notifica al adaptador que los datos han cambiado para que refresque la UI
        notifyDataSetChanged()
    }
}