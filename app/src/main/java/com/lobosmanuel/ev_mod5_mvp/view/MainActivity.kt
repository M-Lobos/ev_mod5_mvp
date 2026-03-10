package com.lobosmanuel.ev_mod5_mvp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.lobosmanuel.ev_mod5_mvp.R
import com.lobosmanuel.ev_mod5_mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // llama mi toolbar del XML: my_toolbar
        setSupportActionBar(binding.myToolbar)

        // NavHostFragment está dentro de content_main (el include)
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Se configura la barra para que trabaje con el NavGraph
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
//        }
    //Gestion del fab (FloatingActionButton)
        binding.fab.setOnClickListener {
            // Obtiene  el controlador de navegación desde el Host Fragment
            val navController = findNavController(R.id.nav_host_fragment_content_main)

            // Navega al carrito usando acción creada desde el nav_graph
            navController.navigate(R.id.action_homeFragment_to_cartFragment)
        }

        // Se escuchan los cambios de destino de navegación
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                // Solo mostramos el FAB si estamos en el Home
                R.id.homeFragment -> binding.fab.show()

                // Se oculta en cualquier otro fragmento (Detalle, Carrito, etc.)
                else -> binding.fab.hide()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}