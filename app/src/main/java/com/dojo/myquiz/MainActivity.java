package com.dojo.myquiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    public RadioGroup radioGroup;
    public TextView textoPergunta;
    public RadioButton opcaoA;
    public RadioButton opcaoB;
    public RadioButton opcaoC;
    public Button botaoOk;
    public Button botaoReseta;

    String[] Perguntas = {"Primeira pergunta?",
            "Segunda  pergunta?",
            "Terceira pergunta?",
            "Quarta pergunta?",
            "Quinta pergunta?"};

    String[] OpcaoA = {"Resposta A primeira pergunta.",
            "Resposta A segunda pergunta.",
            "Resposta A terceira pergunta.",
            "Resposta A quarta pergunta.",
            "Resposta A quinta pergunta."};

    String[] OpcaoB = {"Resposta B primeira pergunta.",
            "Resposta B segunda pergunta.",
            "Resposta B terceira pergunta.",
            "Resposta B quarta pergunta.",
            "Resposta B quinta pergunta."};

    String[] OpcaoC = {"Resposta C primeira pergunta.",
            "Resposta C segunda pergunta.",
            "Resposta C terceira pergunta.",
            "Resposta C quarta pergunta.",
            "Resposta C quinta pergunta."};

    int[] listaRespostas = new int[Perguntas.length];
    int[] listaGabarito = {1,2,3,1,2};

    int respostasCorretas = 0;
    int numeroPergunta = 0;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoOk = findViewById(R.id.botaoOk);
        botaoOk.setEnabled(false);
        botaoReseta = findViewById(R.id.botaoReseta);
        botaoReseta.setEnabled(false);
        textoPergunta = findViewById(R.id.campoTexto);
        opcaoA = findViewById(R.id.opcaoA);
        opcaoB = findViewById(R.id.opcaoB);
        opcaoC = findViewById(R.id.opcaoC);
        radioGroup = findViewById(R.id.grupoRadio);

        atualizaPerguntas(botaoOk);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.opcaoA:
                    Log.d("s","Opcao n1!");
                    listaRespostas[numeroPergunta - 1] = 1;
                    break;
                case R.id.opcaoB:
                    Log.d("s","Opcao n2!");
                    listaRespostas[numeroPergunta - 1] = 2;
                    break;
                case R.id.opcaoC:
                    Log.d("s","Opcao n3!");
                    listaRespostas[numeroPergunta - 1] = 3;
                    break;
            }
            botaoOk.setEnabled(true);
        });
    }

    public void atualizaPerguntas(View view){

        if(numeroPergunta == Perguntas.length){
            opcaoA.setEnabled(false);
            opcaoB.setEnabled(false);
            opcaoC.setEnabled(false);
            radioGroup.clearCheck();
            confereResultado();
        }else {
            textoPergunta.setText(Perguntas[numeroPergunta]);
            opcaoA.setText(OpcaoA[numeroPergunta]);
            opcaoB.setText(OpcaoB[numeroPergunta]);
            opcaoC.setText(OpcaoC[numeroPergunta]);
            numeroPergunta++;
            botaoOk.setEnabled(false);
            radioGroup.clearCheck();
        }
    }

    public void confereResultado(){
        int contadorLista= 0;
        for(int numero : listaRespostas){
            System.out.println(numero);
            if(numero == listaGabarito[contadorLista]){
                respostasCorretas++;
                System.out.println("Resposta Correta!");
            }else{
                System.out.println("Resposta Errada!");
            }
            contadorLista++;
        }
        alertaResultado(botaoOk);
    }

    public void alertaResultado(View view){
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Resultado!");
        alertDialog.setMessage("Voce acertou " + respostasCorretas + " questoes!");
        alertDialog.show();
        botaoOk.setEnabled(false);
        botaoReseta.setEnabled(true);
    }

    public void resetaPergunta(View view){
        numeroPergunta=0;
        respostasCorretas=0;
        textoPergunta.setText(Perguntas[numeroPergunta]);
        opcaoA.setText(OpcaoA[numeroPergunta]);
        opcaoB.setText(OpcaoB[numeroPergunta]);
        opcaoC.setText(OpcaoC[numeroPergunta]);
        opcaoA.setEnabled(true);
        opcaoB.setEnabled(true);
        opcaoC.setEnabled(true);
        numeroPergunta++;
        botaoOk.setEnabled(false);
        botaoReseta.setEnabled(false);
        radioGroup.clearCheck();
    }
}