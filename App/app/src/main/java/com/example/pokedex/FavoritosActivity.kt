package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.dataBases.Entities.PokemonEntity
import com.example.pokedex.dataBases.viewsModels.PokemonFavoritos


class FavoritosActivity : ComponentActivity() {
    private val favoritosViewModel: PokemonFavoritos by lazy {
        PokemonFavoritos(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoritosScreen(favoritosViewModel)
        }
    }

    private fun Volver() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FavoritosScreen(viewModel: PokemonFavoritos) {
        val favoritos = remember { mutableStateOf<List<PokemonEntity>>(emptyList()) }

        var showDeleteModal by remember { mutableStateOf(false) }
        var selectedPokemon by remember { mutableStateOf<PokemonEntity?>(null) }

        LaunchedEffect(Unit) {
            viewModel.loadPokemons { pokemons ->
                favoritos.value = pokemons
            }
        }

        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Pokémons Favoritos", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimary)},
                    navigationIcon = {
                        IconButton(onClick = { Volver() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = MaterialTheme.colorScheme.onPrimary)
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(favoritos.value) { pokemon ->
                    FavoritoItem(
                        pokemon = pokemon,
                        onDelete = {
                            selectedPokemon = pokemon // Seleccionar el Pokémon
                            showDeleteModal = true // Mostrar el modal
                        }
                    )
                }
            }
            if (showDeleteModal && selectedPokemon != null) {
                AlertDialog(
                    onDismissRequest = { showDeleteModal = false },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.deletePokemonFavorito(selectedPokemon!!.id) // Eliminar el Pokémon
                            favoritos.value = favoritos.value.filter { it.id != selectedPokemon!!.id } // Actualizar la lista
                            showDeleteModal = false // Cerrar el modal
                        }) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDeleteModal = false }) {
                            Text("Cancelar")
                        }
                    },
                    title = { Text("Confirmar eliminación") },
                    text = { Text("¿Estás seguro de que quieres eliminar a ${selectedPokemon!!.name} de la lista de favoritos?") }
                )
            }
        }
    }

    @Composable
    fun FavoritoItem(pokemon: PokemonEntity, onDelete: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ){
                    Image(
                        painter = rememberAsyncImagePainter(pokemon.imgUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(240.dp)
                            .padding(1.dp)
                    )
                }
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Column(
                modifier = Modifier.padding(1.dp).align(Alignment.CenterHorizontally)
            ) {

                Text(
                    text = "Altura: ${pokemon.height}, Peso: ${pokemon.weight}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Button(onClick = { onDelete() },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Eliminar", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
