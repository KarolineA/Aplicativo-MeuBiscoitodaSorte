package com.kas.meubiscoitodasorte

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kas.meubiscoitodasorte.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = "Seja Bem Vindx!"
        val duration = Toast.LENGTH_LONG

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()


        binding.btnLoginEntrar.setOnClickListener {


            val email = binding.edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = binding.edtLoginSenha.text.toString().trim()



            if(email.isEmpty()){
                binding.edtLoginEmail.error = "Campo obrigatório"
                binding.edtLoginEmail.requestFocus()
            } else if(senha.isEmpty()){
                binding.edtLoginSenha.error = "Campo obrigatório"
                binding.edtLoginSenha.requestFocus()
            } else {

                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )

                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")


                if(email == emailPrefs && senha == senhaPrefs){


                    Toast.makeText(this,"Usuário Logado", Toast.LENGTH_SHORT).show()


                    val mIntent = Intent(this, MainActivity::class.java)

                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)



                    finish()

                } else {

                    Toast.makeText(this,"Usuário ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnLoginCadastrar.setOnClickListener {

            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }
    }
}