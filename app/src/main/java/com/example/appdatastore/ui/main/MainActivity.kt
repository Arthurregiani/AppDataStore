package com.example.appdatastore.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.appdatastore.data.repository.UserRepository
import com.example.appdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Instanciamento da ViewModel usando Factory
        viewModel = ViewModelProvider(this, MainViewModelFactory(UserRepository(applicationContext))).get(MainViewModel::class.java)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.save.setOnClickListener{
            viewModel.save(binding.editName.text.toString())
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch{
            viewModel.username.collect{
                if (it.isNullOrBlank()){
                    binding.textMessage.text = "Bem vindo, usuário"
                    binding.save.text = "Salvar"
                }else{
                    binding.textMessage.text = "Bem vindo de volta, $it!"
                    binding.save.text = "Alterar"
                }
            }
        }
    }

}