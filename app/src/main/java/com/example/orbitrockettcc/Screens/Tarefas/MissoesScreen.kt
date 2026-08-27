package com.example.orbitrockettcc.Screens.Tarefas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orbitrockettcc.Models.Tarefa
import com.example.orbitrockettcc.R
import com.example.orbitrockettcc.Screens.fundoEstrela

@Composable
fun MissaoScreen() {

    // ========================================================
    // ESTADOS
    // ========================================================

    var mostrarCard by remember {
        mutableStateOf(false)
    }

    var nomeTarefa by remember {
        mutableStateOf("")
    }

    var duracao by remember {
        mutableStateOf("")
    }

    var planetaSelecionado by remember {
        mutableStateOf("")
    }

    var tarefaSelecionada by remember {
        mutableStateOf<Tarefa?>(null)
    }

    val tarefas = remember {
        mutableStateListOf<Tarefa>()
    }


    // ========================================================
    // FUNÇÃO PARA FECHAR O FORMULÁRIO
    // ========================================================

    fun fecharFormulario() {
        nomeTarefa = ""
        duracao = ""
        planetaSelecionado = ""
        mostrarCard = false
    }


    // ========================================================
    // SE TIVER UMA TAREFA SELECIONADA
    // ========================================================

    if (tarefaSelecionada != null) {

        TempoScreen(
            tarefa = tarefaSelecionada!!,
            onVoltar = {
                tarefaSelecionada = null
            }
        )

        return
    }


    // ========================================================
    // TELA PRINCIPAL
    // ========================================================

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Fundo
        fundoEstrela()


        // ====================================================
        // TÍTULO
        // ====================================================

        Text(
            text = "Minhas Missões",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.offset(
                x = 30.dp,
                y = 50.dp
            )
        )


        // ====================================================
        // LISTA DE MISSÕES
        // ====================================================

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 100.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {

            items(tarefas) { tarefa ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(vertical = 8.dp)
                        .clickable {
                            tarefaSelecionada = tarefa
                        },

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2B195E)
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = tarefa.nome,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(
                                modifier = Modifier.height(5.dp)
                            )

                            Text(
                                text = "Duração: ${tarefa.duracao} minutos",
                                color = Color(0xFFC8C5FF)
                            )
                        }


                        Button(
                            onClick = {
                                tarefas.remove(tarefa)
                            }
                        ) {

                            Text(
                                text = "Excluir",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }


        // ====================================================
        // FORMULÁRIO
        // ====================================================

        if (mostrarCard) {

            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(12.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF36266C)
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    // ----------------------------------------
                    // TÍTULO
                    // ----------------------------------------

                    Text(
                        text = "Nova Missão",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )


                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )


                    // ----------------------------------------
                    // NOME
                    // ----------------------------------------

                    OutlinedTextField(

                        value = nomeTarefa,

                        onValueChange = {
                            nomeTarefa = it
                        },

                        label = {
                            Text(
                                "Nome da missão",
                                color = Color.White
                            )
                        },

                        modifier = Modifier.fillMaxWidth(),

                        colors = OutlinedTextFieldDefaults.colors(

                            focusedTextColor =
                                Color.White,

                            unfocusedTextColor =
                                Color.White,

                            focusedLabelColor =
                                Color.White,

                            unfocusedLabelColor =
                                Color.White,

                            cursorColor =
                                Color.White,

                            focusedBorderColor =
                                Color.White,

                            unfocusedBorderColor =
                                Color.White
                        )
                    )


                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )


                    // ====================================================
                    // PLANETAS
                    // ====================================================

                    Text(
                        text = "Escolha o seu destino:",
                        color = Color(0xFFC8C5FF),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )


                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    LazyVerticalGrid(

                        columns = GridCells.Fixed(3),

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp),

                        horizontalArrangement =
                            Arrangement.spacedBy(7.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(7.dp)
                    ) {

                        // LUA
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Lua"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Lua"

                                        duracao =
                                            "15"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.lua
                                        ),

                                    contentDescription =
                                        "Lua",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Lua",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "10–20 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // VÊNUS
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Vênus"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Vênus"

                                        duracao =
                                            "30"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.venus
                                        ),

                                    contentDescription =
                                        "Vênus",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Vênus",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "20–35 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // MARTE
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Marte"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Marte"

                                        duracao =
                                            "45"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.marte
                                        ),

                                    contentDescription =
                                        "Marte",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Marte",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "30–50 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // MERCÚRIO
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Mercúrio"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Mercúrio"

                                        duracao =
                                            "60"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.mercurio
                                        ),

                                    contentDescription =
                                        "Mercúrio",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Mercúrio",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "45–60 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // JÚPITER
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Júpiter"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Júpiter"

                                        duracao =
                                            "75"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.jupiter
                                        ),

                                    contentDescription =
                                        "Júpiter",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Júpiter",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "60–90 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // SATURNO
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Saturno"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Saturno"

                                        duracao =
                                            "105"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.saturno
                                        ),

                                    contentDescription =
                                        "Saturno",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Saturno",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "90–120 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // URANO
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Urano"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Urano"

                                        duracao =
                                            "135"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.urano
                                        ),

                                    contentDescription =
                                        "Urano",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Urano",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "120–150 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // NETUNO
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Netuno"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Netuno"

                                        duracao =
                                            "165"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.netuno
                                        ),

                                    contentDescription =
                                        "Netuno",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Netuno",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "150–180 min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }


                        // PLUTÃO
                        item {

                            Column(
                                horizontalAlignment =
                                    Alignment.CenterHorizontally,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        if (
                                            planetaSelecionado ==
                                            "Plutão"
                                        ) {
                                            Color(0xFF5A4BA8)
                                        } else {
                                            Color(0xFF2B1B54)
                                        }
                                    )
                                    .clickable {

                                        planetaSelecionado =
                                            "Plutão"

                                        duracao =
                                            "180"
                                    }
                                    .padding(7.dp)
                            ) {

                                Image(
                                    painter =
                                        painterResource(
                                            R.drawable.plutao
                                        ),

                                    contentDescription =
                                        "Plutão",

                                    modifier =
                                        Modifier.size(45.dp)
                                )

                                Text(
                                    "Plutão",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    "180+ min",
                                    color =
                                        Color(0xFFAAA4E8),
                                    fontSize = 8.sp
                                )
                            }
                        }
                    }


                    // ====================================================
                    // DURAÇÃO
                    // ====================================================

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text =
                            if (planetaSelecionado.isEmpty()) {
                                "Nenhum destino escolhido"
                            } else {
                                "Destino: $planetaSelecionado\n" +
                                        "Duração: $duracao minutos"
                            },

                        color = Color.White,

                        fontSize = 13.sp
                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )


                    // ====================================================
                    // BOTÕES
                    // ====================================================

                    Row {

                        Button(

                            onClick = {

                                if (
                                    nomeTarefa.isNotBlank() &&
                                    duracao.isNotBlank()
                                ) {

                                    tarefas.add(
                                        Tarefa(
                                            nome = nomeTarefa,
                                            duracao = duracao
                                        )
                                    )

                                    fecharFormulario()
                                }
                            },

                            enabled =
                                nomeTarefa.isNotBlank() &&
                                        duracao.isNotBlank()
                        ) {

                            Text(
                                "Salvar",
                                color = Color.White
                            )
                        }


                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )


                        OutlinedButton(

                            onClick = {
                                fecharFormulario()
                            }
                        ) {

                            Text(
                                "Cancelar",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }


        // ====================================================
        // BOTÃO +
        // ====================================================

        FloatingActionButton(

            onClick = {
                mostrarCard = true
            },

            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .offset(y = 25.dp),

            containerColor =
                Color(0xFF2B195E)
        ) {

            Icon(
                imageVector = Icons.Default.Add,

                contentDescription =
                    "Adicionar",

                tint = Color.White
            )
        }
    }
}