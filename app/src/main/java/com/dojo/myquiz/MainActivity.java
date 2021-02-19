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

    String[] Perguntas = {"Quem descobriu as américas?",
            "Conhece-te a ti mesmo, é uma pergunta de que filosofo?",
            "Quem defendeu a ideia que o Sol era o centro do nosso sistema solar?",
            "Quem é o verdadeiro inventor da lâmpada?",
            "Quem foi responsavel pela descoberta da radioatividade?"};

    String[] OpcaoA = {"Cristóvão Colombo.",
            "Aristóteles.",
            "Galileu Galilei.",
            "Leonardo da Vinci.",
            "Marie Curie."};

    String[] OpcaoB = {"Pedro Álvares Cabral.",
            "Sócrates.",
            "Isaac Newton.",
            "Nikola Tesla.",
            "Louis Pasteur."};

    String[] OpcaoC = {"Vasco da Gama.",
            "Platão.",
            "Albert Einstein.",
            "Thomas Edison.",
            "Alexander Fleming."};

    int[] listaRespostas = new int[Perguntas.length];
    int[] listaGabarito = {1,2,1,3,1};

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