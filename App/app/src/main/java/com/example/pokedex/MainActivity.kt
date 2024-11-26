package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationwebservice.R
import com.example.pokedex.services.driverAdapters.RegionesDiverAdapter
import com.example.pokedex.services.models.Region
import com.example.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {

    val regionesDiverAdapter by lazy { RegionesDiverAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var regiones by remember { mutableStateOf<List<Region>>(emptyList()) }
            var LoadRegiones by remember { mutableStateOf<Boolean>(false) }
            if (!LoadRegiones) {
                this.regionesDiverAdapter.allRegiones(
                    loadData = {
                        regiones = it
                        LoadRegiones = true
                    },
                    errorData = {
                        println("Error en el servicio")
                        LoadRegiones = true
                    }
                )
            }
            RegionesScreen(regiones = regiones,
                onClickRegion = { regionName, regionID -> goToPokemons(regionName, regionID) },
                irFavoritos = {goToFavoritos()})
        }
    }

    fun goToFavoritos(){
        val intent = Intent( this, FavoritosActivity::class.java)
        startActivity(intent)
    }
    fun goToPokemons(regionName: String, regionID: Int) {
        println("regionName = ${regionName} regionID = ${regionID}")
        val intent = Intent(this, RegionActivity::class.java).apply {
            putExtra("region_name", regionName)}.apply { putExtra("regionID", regionID)}
        startActivity(intent)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegionesScreen(
        regiones: List<Region>,
        onClickRegion: (String, Int) -> Unit,
        irFavoritos: () -> Unit
    ) {
        PokedexTheme {
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = { Text(text = stringResource(id = R.string.title_regiones)) },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(5.dp) // Espaciado interno adicional
                ){
                    Button(
                        onClick = irFavoritos,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("Favoritos")
                    }
                    LazyColumn(
                        modifier = Modifier
                    ) {
                        items(
                            items = regiones,
                            key = { it.name }
                        ) { region ->
                            RegionItem(region = region, onClickRegion = onClickRegion)
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun RegionItem(
        region: Region,
        onClickRegion: (String, Int) -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onClickRegion(region.name, region.url.split("/").dropLast(1).last().toInt()) },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = region.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.go_to_Region),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }


    @Preview(showBackground = true, widthDp = 360)


    @Composable
    fun RegionesScreenPreview() {
        val fakeRegions = listOf(
            Region(name = "kanto", url = "https://pokeapi.co/api/v2/region/1/"),
            Region(name = "johto", url = "https://pokeapi.co/api/v2/region/2/")
        )
        RegionesScreen(
            regiones = fakeRegions,
            onClickRegion = { name, id -> println("Clicked region: $name, ID: $id") },
            irFavoritos = {}

        )
    }
}