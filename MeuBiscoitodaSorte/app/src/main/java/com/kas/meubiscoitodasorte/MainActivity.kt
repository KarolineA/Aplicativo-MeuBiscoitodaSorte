package com.kas.meubiscoitodasorte

import androidx.core.content.ContextCompat
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kas.meubiscoitodasorte.databinding.ActivityMainBinding
import kotlin.random.Random
import android.content.Intent


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("INTENT_EMAIL")

        val sharedPrefs = getSharedPreferences(
                "cadastro_$email",
                Context.MODE_PRIVATE
        )

        val nome = sharedPrefs.getString("NOME", "")
        val elemento = sharedPrefs.getString("ELEMENTOS", "")


        binding.txvMainNome.text = "$nome "

        binding.txvMain.text = elemento


        val sortelista = arrayListOf(
                "Se a sua vida for a melhor coisa que já te aconteceu, acredite,você tem mais sorte do que pode imaginar.     Daniel Godri",
                "Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado.  Roberto Shinyashiki",
                " A descoberta consiste em ver o que todos viram e em pensar no que ninguém pensou. A. Szent-Gyorgyi",
                "O silêncio é de ouro e muitas vezes é resposta.",
                "O saber se aprende com os mestres. A sabedoria, só com o corriqueiro da vida.   Cora Coralina",
                "O que sabemos é uma gota, o que ignoramos é um oceano. Isaac Newton",
                "Não existe um caminho para a felicidade. A felicidade é o caminho. Thich Nhat Hanh",
                "A jornada é o que nos traz a felicidade e não o destino -Poder Alem da Vida",
                "A persistência é o caminho do êxito. Charles Chaplin"
        )






        binding.btnMainSair.setOnClickListener {

        val alert = AlertDialog.Builder(this)

        alert.setTitle("Atenção")

        alert.setMessage("Deseja mesmo sair?")

        alert.setPositiveButton("Sair") {dialog, wich ->
            val mIntent = Intent(this, LoginActivity::class.java)
            startActivity(mIntent)

            finishAffinity()
        }

        alert.setNeutralButton("Não") { dialog, wich -> }


        alert.setCancelable(false)


        alert.show()
    }

        val button: Button = findViewById(R.id.btnMainSorte)
        button.setOnClickListener {
            val randomLuck = Random.nextInt(0, sortelista.lastIndex)
            val sorteDoDia = sortelista.get(randomLuck)
            AlertDialog.Builder(this@MainActivity)
                    .setTitle("Sua frase:")
                    .setMessage("$sorteDoDia")
                    .setPositiveButton("OK") { _, _ ->

                    }
                    .setCancelable(false)
                    .create()
                    .show()





        }


    }
}