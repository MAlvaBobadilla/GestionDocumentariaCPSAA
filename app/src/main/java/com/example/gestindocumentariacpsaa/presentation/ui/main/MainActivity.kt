package com.example.gestindocumentariacpsaa.presentation.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gestindocumentariacpsaa.R
import com.example.gestindocumentariacpsaa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
//        initIntents()
        val colaborador = intent.getStringExtra("usuario")

        val envio = Bundle().apply {
            putString("usuario", colaborador)
        }
        initNavigation(envio)
        setUpToolBar()
    }
    private fun initNavigation(envio: Bundle) {
        //Almacenador de Pantallas
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        //Navegador de Pantallas
        navController = navHost.navController
        //Asignar el navegador al menú
        binding.navView.setupWithNavController(navController)
        navController.navigate(R.id.homeFragment, envio)
    }
    private fun setUpToolBar() {
        // Configuracion del iconoMenu
        val iconoMenu = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.open_nav, R.string.close_nav
        )
        binding.drawerLayout.addDrawerListener(iconoMenu)
        iconoMenu.syncState()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.myqrFragment,
                R.id.asistenciaFragment,
                R.id.descansosMedicosFragment,
                R.id.ticketsFragment,
                R.id.reportesFragment,
                R.id.profileFragment,
            ),
            binding.drawerLayout
        )

        // Configura la Toolbar con el NavController
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configura el título según el fragmento
        navController.addOnDestinationChangedListener { _, fragments, _ ->
            supportActionBar?.title = when (fragments.id) {
                R.id.homeFragment -> "Configurar Zona de Trabajo"
                R.id.myqrFragment -> "Mi Codigo Identificador"
                R.id.asistenciaFragment -> "Registrar Asistencia"
                R.id.descansosMedicosFragment -> "Descansos Médicos"
                R.id.ticketsFragment -> "Tickets de Cumpleaños"
                R.id.reportesFragment -> "Reportes Varios"
                R.id.profileFragment -> "Cerrar Sesión"
                else -> "Other"
            }
        }

    }

    //Abrir el menu de navegacion
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}