package pe.edu.idat.applasazon.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import pe.edu.idat.applasazon.MainActivity
import pe.edu.idat.applasazon.R
import pe.edu.idat.applasazon.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.btniniciarsesion.setOnClickListener{
            val email = binding.etcorreo.text.toString()
            val password = binding.etpassword.text.toString()
            if (email.isNotEmpty()&& password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else{
                Toast.makeText(this, "No pueden haber casillas vacías", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnirregistro.setOnClickListener{
            val signupIntent = Intent(this, RegistroActivity::class.java)
            startActivity(signupIntent)
        }

    }
}