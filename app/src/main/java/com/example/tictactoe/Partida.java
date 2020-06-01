package com.example.tictactoe;

import android.widget.TextView;

import java.util.Random;

public class Partida {
    private final int dificultad;
    public int jugador;//usado en table
    private int[] casillasestado;
    private final int[] [] CONBINACIONES={  {0,1,2},{3,4,5},{6,7,8},{0,3,6},
                                            {1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //CONSTRUCTOR
    public Partida(int dificultad){

        this.dificultad= dificultad;
        jugador= 1;

        //INICIALIZO EL ARRAY DEL ESTADO A 0
        casillasestado = new int[9];
        for(int i= 0; i < 9; i++){
            casillasestado[i]= 0;
        }
    }
    //COMPROBACION DE CASILLA OCUPADA
    public boolean comprueba_casilla(int casilla){
        if(casillasestado[casilla] != 0){
            return false;
        }else{
            casillasestado[casilla]= jugador;
        }return true;
    }
    //LA INTELIGENCIA ARTIFICIAL
    public int ia(){
        int casilla;//DEVUELVE
        casilla=dosEnRaya(2);//La maquina mira si puede hacer tres en raya y devuelve casilla
        if (casilla!= -1){
            return casilla;//CASILLA CLAVE
        }
        if (dificultad>0){//si es normal o dificil mira si el jugador puede hacer tres en raya
            casilla=dosEnRaya(1);
            if (casilla!= -1){
                return casilla;//CASILLA CLAVE
            }
        }
        if (dificultad==2){
            //si no hay posibilidad de tres en raya(si lo hay habra hecho return antes
            if (casillasestado[4]== 0) return 4;
            //ke marke una eskina libre 0,2,6,8
            if (casillasestado[0]== 0) return 0;
            if (casillasestado[2]== 0) return 2;
            if (casillasestado[6]== 0) return 6;
            if (casillasestado[8]== 0) return 8;
        }
        //si no hay return antes lo hace random
        Random casilla_azar= new Random();
        casilla= casilla_azar.nextInt(9);
        return casilla;
    }
    //devuelve la CASILLA CLAVE

    public int dosEnRaya(int jugador_en_turno){
        //devuelve la posicion ke la maquina debe poner para evitar tres en raya
        int casilla = -1;//posicion que no existe SERA DEVUELTA
        int cuantas_lleva= 0;
        for (int i= 0; i<CONBINACIONES.length;i++){
            for(int pos:CONBINACIONES[i]){
               if (casillasestado[pos]== jugador_en_turno){
                   cuantas_lleva++;
               }
               if (casillasestado[pos]== 0){
                   casilla=pos;//esta casilla esta vacia, si las otras dos estan con el mismo jugador
                   //esta es la casilla CLAVE
               }
            }
            if (cuantas_lleva==2 && casilla!=-1){
               return casilla;
            }
            casilla=-1;
            cuantas_lleva= 0;
        }
        //Si no secumple el if del AND resetea los valores para el siguiente trio
        //Si en ningun trio se cumple devuelve una casilla que no existe
        return -1;
    }

    //EL TURNO y devuelve el valor del ESTADO del juego
    public int turno(){
        boolean empate=true;
        boolean ult_movimiento= true;
        //Recorre el array 3d y pone el numero de jugador ke
        // ha marcado en las posiciones que le corresponden
        for (int i= 0; i<CONBINACIONES.length;i++){
            for(int pos:CONBINACIONES[i]){
                if (casillasestado[pos]!=jugador){
                    ult_movimiento= false;
                    //evalua si el numero de jugador esta en las tres posiciones
                    //si en alguna no esta devuelve false
                    //si los tres son iguales al jugador ult_mov es true y ha ganado
                }
                if (casillasestado[pos]==0){
                    empate= false;
                }

            }
            if (ult_movimiento){
                return  jugador;
                //si ult_movimiento es true termina la ejecucion
            }
            ult_movimiento= true;
        }
        //comprueba si hay empate, si ha encontrado un 0 en el array casillaestado
        if (empate){
            return 3;
        }
        jugador++;
        if (jugador>2){
            jugador= 1;
        }
        return 0;
    }
}
        //ESTADO del juego
//EMPATE                     3
//GANA JUG1                  1
//GANA JUG2                  2
//NINGUNA, LA PARTIDA SIGUE  0