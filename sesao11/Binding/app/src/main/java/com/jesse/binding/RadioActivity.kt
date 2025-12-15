package com.jesse.binding

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.jesse.binding.databinding.ActivityRadioBinding

class RadioActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRadioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        with(binding){

            spinnerExibir(this@RadioActivity)
            button5.setOnClickListener {
//                radioButton()
//                switchButton()
//                exibirSnackBar(this@RadioActivity)
//                caixaDialog(this@RadioActivity)
                spinerItemSelecionado()
            }

            toggleButton.setOnClickListener {
                if(toggleButton.isChecked){
                    Toast.makeText(this@RadioActivity , "Toggle selecionado" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

private fun ActivityRadioBinding.spinerItemSelecionado() {
    val itemSelecionado = spinner.selectedItem
    val itemPosicao = spinner.selectedItemPosition


    if(itemPosicao == 0){
        textViewResultado.text = "Selecione um item"
    }else{
        textViewResultado.text = "$itemSelecionado : $itemPosicao"

    }
}

private fun ActivityRadioBinding.spinnerExibir(context: Context) {
    val names = listOf<String>("Escolher Nomes", "kaique", "alice " , "jesse" , "cice")

    // pra poder setar valores no spinner , tem quer criar um adapter nele kkk
    spinner.adapter = ArrayAdapter<String>(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        names
    )
}

private fun ActivityRadioBinding.caixaDialog(context: Context) {
    val alertBuilder = AlertDialog.Builder(context)
    alertBuilder.setTitle("Confirmar Exlusão do item?")
    alertBuilder.setMessage("Confirmar?")

    alertBuilder.setNegativeButton("Cancelar" , {
        dialog , posicao ->
        Toast.makeText(context , "Cancelado ${posicao}" , Toast.LENGTH_SHORT).show()
        dialog.cancel()
    })

    alertBuilder.setPositiveButton("Remover" , {
        //dialog(colocar _ se não for usar , e a posição do botão
        dialog , posicao ->
        Toast.makeText(context , "Removido ${posicao}" , Toast.LENGTH_SHORT).show()
    })

    alertBuilder.setCancelable(false)
    alertBuilder.setIcon(R.drawable.ic_perfil)

    val alertDialog = alertBuilder.create()
    alertDialog.show()
}


private fun ActivityRadioBinding.exibirSnackBar(context: Context) {
 val snackbar = Snackbar.make(
     main,
     "SnackBar" ,
     Snackbar.LENGTH_LONG
 )

    snackbar.setAction("Desfazer"){
        Toast.makeText(context , "Desfeito" ,  Toast.LENGTH_SHORT).show()
    }

//    snackbar.setTextColor(getColor(R.color.blue , ))

    snackbar.show()
}

private fun ActivityRadioBinding.switchButton() {
    val selecionado = switch1.isChecked
    val toogleSelecionado = toggleButton.isChecked


    textViewResultado.text = "Switch: ${selecionado} \nToggle: ${toogleSelecionado}"
}

private fun ActivityRadioBinding.radioButton() {
   val selecionadoMas = radioButtonMas.isChecked
    if(selecionadoMas){
        textViewResultado.text = "Masculino"
    }else{
        textViewResultado.text = "Feminino"
    }
}
