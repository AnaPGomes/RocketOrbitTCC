package com.example.orbitrockettcc.Screens.Rodape

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.orbitrockettcc.Screens.Perfil.HomeScreenRoot
import com.example.orbitrockettcc.Screens.Tarefas.MissaoScreen


@Composable
fun AplicarRodape() {

    var abaAtual by remember { mutableStateOf(Aba.MISSOES) }

    // Guarda o planeta selecionado
    var planetaSelecionadoNome by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        bottomBar = {
            RodapeNavegacao(
                abaAtual = abaAtual,
                onAbaSelecionada = { abaAtual = it }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when (abaAtual) {



                Aba.MISSOES -> {
                    MissaoScreen()
                }

                Aba.PERFIL -> {
                    HomeScreenRoot()
                }
            }
        }
    }
}