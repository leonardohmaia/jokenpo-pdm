package com.example.jogojokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jogojokenpo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var placarPlayer: Int = 0
    private var placarCpu: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configuraInicio()
    }

    private fun configuraInicio() {
        binding.tesouraBtn.setOnClickListener { escolhaJogador("Tesoura") }
        binding.pedraBtn.setOnClickListener { escolhaJogador("Pedra") }
        binding.papelBtn.setOnClickListener { escolhaJogador("Papel") }
        binding.novoJogoBtn.setOnClickListener { novoJogo() }
    }

    private fun sorteiaCPU(): String {
        val listaOpcoes: List<String> = listOf("Tesoura", "Pedra", "Papel")
        return listaOpcoes.random()
    }

    private fun escolhaJogador(jogadorEscolha: String) {
        val cpuEscolha = sorteiaCPU()

        binding.escolhaPlayerTxt.text = jogadorEscolha
        binding.opcaoCpuTxt.text = cpuEscolha

        val resultado = determinarResultado(jogadorEscolha, cpuEscolha)

        if (resultado == Resultado.EMPATE) {
            // Empate
        } else if (resultado == Resultado.CPU_VENCE) {
            placarCpu++
        } else {
            placarPlayer++
        }

        binding.resultadoPlayerTxt.text = placarPlayer.toString()
        binding.resultadoCpuTxt.text = placarCpu.toString()
    }

    private fun determinarResultado(jogadorEscolha: String, cpuEscolha: String): Resultado {
        return when {
            jogadorEscolha == cpuEscolha -> Resultado.EMPATE
            (jogadorEscolha == "Tesoura" && cpuEscolha == "Papel") ||
                    (jogadorEscolha == "Papel" && cpuEscolha == "Pedra") ||
                    (jogadorEscolha == "Pedra" && cpuEscolha == "Tesoura") -> Resultado.JOGADOR_VENCE
            else -> Resultado.CPU_VENCE
        }
    }

    private fun novoJogo() {
        placarPlayer = 0
        placarCpu = 0

        binding.resultadoPlayerTxt.text = "0"
        binding.resultadoCpuTxt.text = "0"
        binding.escolhaPlayerTxt.text = "-"
        binding.opcaoCpuTxt.text = "-"
    }

    enum class Resultado {
        JOGADOR_VENCE, CPU_VENCE, EMPATE
    }
}
