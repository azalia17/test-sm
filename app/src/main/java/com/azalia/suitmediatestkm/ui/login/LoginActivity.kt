package com.azalia.suitmediatestkm.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.azalia.suitmediatestkm.databinding.ActivityLoginBinding
import com.azalia.suitmediatestkm.ui.ViewModelFactory
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.azalia.suitmediatestkm.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding


    private var name: Editable? = null
    private var palindrome: Editable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels {
            factory
        }

        setupView()

        name = loginBinding.etName.text
        palindrome = loginBinding.etPalindrome.text
//        loginBinding.btnNext.isEnabled = name != null && palindrome.toString().isNotEmpty() && palindrome != null && name.toString().isNotEmpty()
        loginBinding.btnNext.setOnClickListener {
            loginViewModel.userLogin(name.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        loginBinding.btnCheck.isEnabled = palindrome.toString().isNotEmpty() && palindrome != null
        loginBinding.btnCheck.setOnClickListener {
            if (isPalindrome(palindrome.toString())) {
                Toast.makeText(this, "The sentence is palindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "The sentence is not palindrom", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setNextButtonEnable() {

    }

    private fun setCheckButtonEnable() {
        palindrome = loginBinding.etPalindrome.text

    }

    fun isPalindrome(sentence: String): Boolean {
        val cleanedSentence = sentence.replace(Regex("[^A-Za-z0-9]"), "").toLowerCase()
        return cleanedSentence == cleanedSentence.reversed()
    }
}