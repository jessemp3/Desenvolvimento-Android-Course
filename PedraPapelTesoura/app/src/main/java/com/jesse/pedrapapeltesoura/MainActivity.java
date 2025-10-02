package com.jesse.pedrapapeltesoura;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selecionarPedra(View view) {
        verificarGanhador("pedra");
    }

    public void selecionarPapel(View view) {
        verificarGanhador("papel");
    }

    public void selecionarTesoura(View view) {
        verificarGanhador("tesoura");
    }

    private void verificarGanhador(String escolhaUsuario) {
        String escolhaComputador = gerarEscolhaComputador();
        TextView text = findViewById(R.id.resultado);

        if (
            (escolhaComputador == "pedra" && escolhaUsuario == "tesoura") ||
            (escolhaComputador == "papel" && escolhaUsuario == "pedra") ||
            (escolhaComputador == "tesoura" && escolhaUsuario == "papel")
        ) { // app ganha
            text.setText("Você perdeu! :(");
        } else if (
            (escolhaUsuario == "pedra" && escolhaComputador == "tesoura") ||
            (escolhaUsuario == "papel" && escolhaComputador == "pedra") ||
            (escolhaUsuario == "tesoura" && escolhaComputador == "papel")
        ) { // user ganha
            text.setText("Você ganhou! :)");
        } else { // empate
            text.setText("Empatamos! :|");
        }
    }

    private String gerarEscolhaComputador() {
        String[] opcoes = {"pedra", "papel", "tesoura"};
        int numero = (int) (Math.random() * 3);

        String escolhaComputador = opcoes[numero];

        ImageView view = findViewById(R.id.padrao);
        switch (escolhaComputador) {
            case "pedra":
                view.setImageResource(R.drawable.pedra);
                Log.d("escolhaComputador", "pedra");
                break;
            case "papel":
                view.setImageResource(R.drawable.papel);
                Log.d("escolhaComputador", "papel");
                break;
            case "tesoura":
                view.setImageResource(R.drawable.tesoura);
                Log.d("escolhaComputador", "tesoura");
                break;

        }

        return escolhaComputador;
    }


}