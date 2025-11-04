package com.jesse.aulaframgent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.jesse.aulaframgent.databinding.ActivityMainBinding
import com.jesse.aulaframgent.fragments.ChamadasFragment
import com.jesse.aulaframgent.fragments.ConversasFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        // começo da transação de fragment
//        val fragmentManager = supportFragmentManager.beginTransaction()
//
//        //alteraões no fragment
//        fragmentManager.add(
//            R.id.fragmentContainerView, // binding não funciona aqui
//            ConversasFragment()
//        )
//
//        fragmentManager.commit()


        binding.button.setOnClickListener {
//            val conversasFragment = ConversasFragment()

            // passando parametros de uma activity para um fragment
//          val bundle = conversasFragment.arguments = Bundle().apply {
//                putString("categoria", "Conversas do jesse")
//            }

            //forma simplificada e normal
//            supportFragmentManager
//                .beginTransaction()
//                .replace(
//                    R.id.fragmentContainerView,
//                    conversasFragment
//                ) // o metodo add simplismente colocar na tela , se já tiver aberto um fragment ele sobrepõe, o melhor é usar o replace que substitui
//                .commit()

            val bundle = bundleOf(
                "categoria" to "Conversas do Jesse"
            )

            // usando o fragment KTX
            supportFragmentManager.commit {
                replace<ConversasFragment>(
                    R.id.fragmentContainerView,
                    args = bundle,

                )
            }
        }

        binding.button2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainerView,
                    ChamadasFragment()
                )
                .commit()
        }

    }
}

