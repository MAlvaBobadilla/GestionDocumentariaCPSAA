package com.example.gestindocumentariacpsaa.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.gestindocumentariacpsaa.R
import com.example.gestindocumentariacpsaa.databinding.ActivityMainBinding
import com.example.gestindocumentariacpsaa.presentation.ui.home.HomeFragment

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
        initNavigation()
        setUpToolBar()
    }

    private fun initNavigation() {
        //Almacenador de Pantallas
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        //Navegador de Pantallas
        navController = navHost.navController
        //Asignar el navegador al menú
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragment -> {
                    cerrarSesion()
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(it, navController)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }
        binding.navView.setCheckedItem(R.id.homeFragment)
    }
    //Abrir el menu de navegacion
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun cerrarSesion() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar Sesion")
        builder.setMessage("¿Desea cerrar sesion?")

        builder.setPositiveButton("Cerrar Sesión") { dialog, _ ->
            dialog.dismiss()
            navController.navigate(R.id.loginActivity)
            finish()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun setUpToolBar() {
        // Configura la Toolbar primero
        setSupportActionBar(binding.toolbar)

        // Configuración del DrawerToggle
        val iconoMenu = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.open_nav, R.string.close_nav
        )

        // Sincroniza el estado del DrawerToggle con el DrawerLayout
        binding.drawerLayout.addDrawerListener(iconoMenu)
        iconoMenu.syncState()

        // Configuración del NavController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.myqrFragment,
                R.id.asistenciaFragment,
                R.id.descansosMedicosFragment,
                R.id.ticketsFragment,
                R.id.reportesFragment
            ),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Cambiar el título según el fragmento
        navController.addOnDestinationChangedListener { _, fragments, _ ->
            supportActionBar?.title = when (fragments.id) {
                R.id.homeFragment -> "Configurar Zona de Trabajo"
                R.id.myqrFragment -> "Mi Código Identificador"
                R.id.asistenciaFragment -> "Registrar Asistencia"
                R.id.descansosMedicosFragment -> "Descansos Médicos"
                R.id.ticketsFragment -> "Tickets de Cumpleaños"
                R.id.reportesFragment -> "Reportes Varios"
                else -> "Other"
            }
        }
    }

}