package com.example.sql.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sql.R
import com.example.sql.data.Heroi
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var itemSelecionado: Heroi? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        carregarDados()

        binding.buttonAdd.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    private fun inputCheck(nome: String, identidade: String): Boolean{
        return !(nome == "" || identidade == "")
    }

    private fun inserirNoBanco(){
        val nome = binding.editNome.text.toString()
        val identidade = binding.editIdentidade.text.toString()

        if(inputCheck(nome, identidade)){
            val salvar: String
            if(itemSelecionado != null){
                salvar = "Heroi Atualizado"
                val heroi = Heroi(itemSelecionado?.id!!, nome, identidade)
                mainViewModel.updateUser(heroi)
            }else{
                salvar = "Heroi Criado"
                val heroi = Heroi(0, nome, identidade)
                mainViewModel.addUser(heroi)
            }
            Toast.makeText(
                context, salvar,
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listaFragment)
        }else{
            Toast.makeText(
                context, "Preencha todos os campos!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun carregarDados(){
        itemSelecionado = mainViewModel.itemSelecionado
        if(itemSelecionado != null){
            binding.editNome.setText(itemSelecionado?.nome)
            binding.editIdentidade.setText(itemSelecionado?.identidade)
        }
    }

}