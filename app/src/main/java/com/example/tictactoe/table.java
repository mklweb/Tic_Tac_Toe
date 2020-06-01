package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class table extends AppCompatActivity {
    private TextView tv_jugador;
    private int numberPlayers;
    private int player;
    private int level;//0,1,2

    private int[] CASILLAS;
    private Partida partida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);



        Intent intent = getIntent();
        numberPlayers= intent.getIntExtra(MainActivity.EXTRA_MESSAGE1,0);
        level= intent.getIntExtra(MainActivity.EXTRA_MESSAGE2,1);

        //Iniciamos el array casillas que identifica cada casilla
        //antes de pulsar, hay que identificar en onCreate
        CASILLAS = new int[9];
        CASILLAS[0]= R.id.a1;
        CASILLAS[1]= R.id.a2;
        CASILLAS[2]= R.id.a3;
        CASILLAS[3]= R.id.b1;
        CASILLAS[4]= R.id.b2;
        CASILLAS[5]= R.id.b3;
        CASILLAS[6]= R.id.c1;
        CASILLAS[7]= R.id.c2;
        CASILLAS[8]= R.id.c3;
    }

    //METODO BOTONES
    public void aJugar(View view){
        tv_jugador = findViewById(R.id.tv_jugador);
        player=1;
        tv_jugador.setText(String.valueOf(player));
        ImageView imagen;
        //PONER EN BLANCO CASILLAS
        for (int cadacasilla:CASILLAS){
            imagen=(ImageView)findViewById(cadacasilla);
            imagen.setImageResource(R.drawable.casillab);
        }

        //COMIENZA la partida y pasa dificultad como parametro
        partida= new Partida(level);

        //DESACTIVAR BOTONE
        ((Button)findViewById(R.id.bt_start)).setEnabled(false);
    }
    //METODO DETECTA CASILLA PULSADA

    public void toque (View view){
        if (partida==null){
            return;
        }
        int casilla= 0;
        //introduce el indice de la casilla que hemos pulsado en la variable casilla
        for (int i= 0; i < 9; i++){
            if (CASILLAS[i]== view.getId()){
                casilla=i;
                break;
            }
        }
        //Si la casilla que se ha tocado esta ocupada termina la ejecucion del metodo
        if(partida.comprueba_casilla(casilla)==false){

            return;
        }
        //llamada al metodo marca
        marca(casilla);
        int resultado=partida.turno();
        player++;
        if (player>2){
            player= 1;
        }
        tv_jugador.setText(String.valueOf(player));
        if (resultado>0){
            termina(resultado);
            return; //Por si gana el jugador 1 ke se acabe la ejecucion
        }
        if(numberPlayers==1) { // COMPRUEBA SI HAY DOS JUGAORES O UNO
            //llamada al metodo ia del objeto partida y luego llama a marcar para que dibuje
            casilla = partida.ia();
            while (partida.comprueba_casilla(casilla) != true) {
                //Si esta ocupada elige otra casilla hasta que encuentre una libre
                casilla = partida.ia();
            }

            marca(casilla);
            resultado = partida.turno();
            //cambio jugador e imprimo en el ET
            player++;
            if (player>2){
                player= 1;
            }
            tv_jugador.setText(String.valueOf(player));

            if (resultado > 0) {
                termina(resultado);
            }
        }
    }

    private void termina (int resultado){
        String mensaje;
        if (resultado == 1) mensaje="Ganan los Circulos";
        else if (resultado== 2) mensaje="Ganan las cruces";
        else mensaje="empate";
        //VENTANA EMERGENTE
        Toast toast=Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
        //https://developer.android.com/training/snackbar/showing#java OTRA FORMA MAS MODERNA CREO
        partida= null;//terminamos la partida
        //HABILITAMOS BOTONES
        ((Button)findViewById(R.id.bt_start)).setEnabled(true);
    }


    //METODO QUE MARCA LAS CASILLAS
    private void marca(int casilla){
        ImageView imagen;
        imagen=(ImageView)findViewById(CASILLAS[casilla]);
        //llama a la variable jugador de la clase partida
        if(partida.jugador==1){
            imagen.setImageResource(R.drawable.casillao);
        }else{
            imagen.setImageResource(R.drawable.casillax);
        }
    }
}
