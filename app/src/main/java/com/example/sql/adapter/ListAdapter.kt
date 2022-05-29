package com.example.sql.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.data.OnItemSelectedListener
import com.example.sql.data.Heroi
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.CustomRowBinding

class ListAdapter (
    private val onItemSelectedListener: OnItemSelectedListener,
    private val mainViewModel: MainViewModel,
    private val context: Context
        ): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var heroiList = emptyList<Heroi>()

    class MyViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = heroiList[position]

        holder.binding.textNome.text = "Nome: ${user.nome}"
        holder.binding.textIdentidade.text = "Identidade: ${user.identidade}"

        holder.binding.buttonEdit.setOnClickListener {
            onItemSelectedListener.onItemSelectedListener(user)
        }

        holder.binding.buttonDelete.setOnClickListener {
            showAlertDialog(user)
        }

    }

    override fun getItemCount(): Int {
        return heroiList.size
    }

    fun setData(heroi: List<Heroi>){
        this.heroiList = heroi
        notifyDataSetChanged()
    }

    private fun showAlertDialog(heroi: Heroi){
        AlertDialog.Builder(context)
            .setTitle("Deletar Personagem")
            .setMessage("Você realmente deseja deletar este personagem?")
            .setPositiveButton("Sim"){
                _,_ -> mainViewModel.deleteUser(heroi)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }

}