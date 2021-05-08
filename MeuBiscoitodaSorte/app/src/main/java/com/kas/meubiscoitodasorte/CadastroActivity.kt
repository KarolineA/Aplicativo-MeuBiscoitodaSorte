package com.kas.meubiscoitodasorte
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kas.meubiscoitodasorte.databinding.ActivityCadastroBinding


class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaElementos = arrayListOf("Elementos", "Fogo", "Terra", "Ar", "Agua",
            "Não Sabe")

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaElementos
        )

        binding.spnCadastroElementos.adapter = spinnerAdapter


        binding.btnCadastroCadastrar.setOnClickListener {

            val nome = binding.edtCadastroNome.text.toString().trim()
            val email = binding.edtCadastroEmail.text.toString().trim()
            val senha = binding.edtCadastroSenha.text.toString().trim()
            val elem = binding.spnCadastroElementos.selectedItem.toString()


            if(nome.isEmpty() ||  email.isEmpty() || senha.isEmpty() ||
                elem == listaElementos[0] ){

                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            } else {

                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )

                val editPrefs = sharedPrefs.edit()
                editPrefs.putString("NOME", nome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("ELEMENTOS", elem)
                editPrefs.apply()

                Toast.makeText(this, "Usúario Cadastrado", Toast.LENGTH_SHORT).show()

                val mIntent = Intent(this, MainActivity::class.java)

                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                finishAffinity()
            }
        }
    }
}