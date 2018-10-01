package com.example.eduardo.eva1_examenu1_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Principal extends AppCompatActivity
implements View.OnClickListener{
    //DECLARACION
    TextView cLetras, intentos, palabra;
    EditText respuesta;
    Button ok, intNuevo;
    RadioGroup niveles;
    RadioButton dificil, medio, facil;
    CheckBox xtream;

    int intAletorio1;
    int intAletorio2;
    char [] letras;
    int bandera1 = 0;
    int bandera2 = 0;
     int intentosRest = 6;


    String[] palabras = {"sistemas", "adivinar", "arreglos", "android", "clases", "principal", "entero", "string", "programacion", "celular" };   //Array de 10 elementos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //ENCONTRARLOS
        cLetras = findViewById(R.id.textViewcLetras);
        intentos = findViewById(R.id.textViewIntRest);
        palabra = findViewById(R.id.textViewPalabra);
        respuesta = findViewById(R.id.editTextRespuesta);
        ok = findViewById(R.id.buttonOK);
        intNuevo = findViewById(R.id.buttonIntNuevo);
        niveles = findViewById(R.id.rdGroupNiveles);
        dificil = findViewById(R.id.radioButtonDificil);
        medio = findViewById(R.id.radioButtonMedio);
        facil = findViewById(R.id.radioButtonFacil);
        xtream = findViewById(R.id.checkBoxXTREAM);

        palabraMostrar();
        //COLOCAR INTENTOS RESTANTES



        niveles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonDificil:

                         intentosRest = (2);
                        intentos.setText(Integer.toString(intentosRest));
                        break;
                    case R.id.radioButtonMedio:

                        intentosRest = (4);
                        intentos.setText(Integer.toString(intentosRest));
                        break;
                    case R.id.radioButtonFacil:
                        intentosRest = (6);
                        intentos.setText(Integer.toString(intentosRest));
                        break;
                }
            }
        });


        ok.setOnClickListener(this);
        intNuevo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Intentando de nuevo", Toast.LENGTH_SHORT).show();

                //VERIFICA SI QUE NIVEL DE DIFICULTAD SELECCIONO
                if (dificil.isChecked()) {
                    intentosRest = 2;
                    intentos.setText(Integer.toString(intentosRest));
                } else {
                    if (medio.isChecked()) {
                        intentosRest = 4;
                        intentos.setText(Integer.toString(intentosRest));
                    } else {
                        intentosRest = 6;
                        intentos.setText(Integer.toString(intentosRest));
                    }

                }


            }
        });
    }

    @Override
    public void onClick(View v) {

        if((niveles.getCheckedRadioButtonId() == -1)){
            Toast.makeText(getApplicationContext(), "Seleeciona un nivel", Toast.LENGTH_SHORT).show();


        }else{
           // Toast.makeText(getApplicationContext(), " nivel seleccionado", Toast.LENGTH_SHORT).show();
            compararPalabra();
        }

    }

    public void palabraMostrar () {

        intentos.setText(Integer.toString(intentosRest));
        //ESCOJER LA PALABRA ALEATORIAMENTE
        int palabraAleatoria;
        int n2 = 10;
        palabraAleatoria = (int) (Math.random() * n2) + 1;//se crea el numero iniciaL random
        //ESCOJER LETRAS ALEATORIAS QUE SE VAN A COULTAR
        int tamañoPalabra = palabras[palabraAleatoria].length();
        Random aleatorio = new Random(System.currentTimeMillis());

        intAletorio1 = aleatorio.nextInt(tamañoPalabra);
        intAletorio2 = aleatorio.nextInt(tamañoPalabra);
        //CANTIDAD DE LETRAS
        int tamañoPalabras = (int) (palabras[palabraAleatoria].length());
        cLetras.setText(Integer.toString(tamañoPalabras));
        //Toast.makeText(getApplicationContext(), "" + intAletorio1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "" + intAletorio2, Toast.LENGTH_SHORT).show();
        //MOSTAR PALABRA EN TEXTVIEW
        String palabraMostrar = palabras[palabraAleatoria];
        letras = palabraMostrar.toCharArray();//SE DIVIDE LA PALABRA EN LETRAS
        //Toast.makeText(getApplicationContext(), "palabra. " + palabraMostrar, Toast.LENGTH_SHORT).show();

        for (int i = 0; i < letras.length; i++) {//SE LEE LA PALABRA DIVIDIDA


            if ((intAletorio1 == i)) {
                palabra.append("_ ");
            } else {
                if (intAletorio2 == i) {
                    //  Toast.makeText(getApplicationContext(), "_", Toast.LENGTH_SHORT).show();
                    palabra.append("_ ");
                } else {
                    //Toast.makeText(getApplicationContext(), "letras " + letras[i], Toast.LENGTH_SHORT).show();
                    palabra.append(Character.toString(letras[i]));
                }


            }


        }
    }

    public void compararPalabra () {


        //RESPUESTA
        String adivPalabra = respuesta.getText().toString();
        //String letraEnAleatorio1 = Character.toString(letras[intAletorio1]);
        //String letraEnAleatorio2 = Character.toString(letras[intAletorio2]);
        //Toast.makeText(getApplicationContext(), "resp " + adivPalabra , Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "letra en array " + letraEnAleatorio1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "letra en array " + letraEnAleatorio2, Toast.LENGTH_SHORT).show();

        if (adivPalabra.equals(Character.toString(letras[intAletorio1])) && adivPalabra.equals(Character.toString(letras[intAletorio2]))) {//SI SON LA MISMA LETRA GANA JUEGO
            Toast.makeText(getApplicationContext(), "Palabras iguales Ganaste juego", Toast.LENGTH_SHORT).show();
        } else {

            if (adivPalabra.equals(Character.toString(letras[intAletorio1]))) {//VERIFICA SI SELECCIONO PIRMERO LA SEGUNDA LETRA

                palabra.setText("");
                bandera1 = 1;
                if (bandera2 == 1){//IMPRIME
                    for (int i = 0; i < letras.length; i++ ){//SE LEE LA PALABRA DIVIDIDA

                            //Toast.makeText(getApplicationContext(), "letras " + letras[i], Toast.LENGTH_SHORT).show();
                            palabra.append(Character.toString(letras[i]));


                    }
                    Toast.makeText(getApplicationContext(), "Ganaste", Toast.LENGTH_SHORT).show();
                }else{//IMPRIME SOLO LA QUE SELECCIONO
                    for (int i = 0; i < letras.length; i++ ){//SE LEE LA PALABRA DIVIDIDA



                        if((intAletorio2 == i) ){
                            //  Toast.makeText(getApplicationContext(), "_", Toast.LENGTH_SHORT).show();
                            palabra.append("_ ");
                        }else {
                            //Toast.makeText(getApplicationContext(), "letras " + letras[i], Toast.LENGTH_SHORT).show();
                            palabra.append(Character.toString(letras[i]));
                        }

                    }
                    Toast.makeText(getApplicationContext(), "Bien echo Solo una mas", Toast.LENGTH_SHORT).show();
                }


            } else {
                if (adivPalabra.equals(Character.toString(letras[intAletorio2]))) {
                   // Toast.makeText(getApplicationContext(), "Bien echo", Toast.LENGTH_SHORT).show();
                    palabra.setText("");
                    bandera2 = 1;
                    if (bandera1 == 1){//IMPRIME
                        for (int i = 0; i < letras.length; i++ ){//SE LEE LA PALABRA DIVIDIDA

                            //Toast.makeText(getApplicationContext(), "letras " + letras[i], Toast.LENGTH_SHORT).show();
                            palabra.append(Character.toString(letras[i]));


                        }
                        Toast.makeText(getApplicationContext(), "Ganaste", Toast.LENGTH_SHORT).show();
                    }else{//IMPRIME SOLO LA QUE SELECCIONO
                        for (int i = 0; i < letras.length; i++ ){//SE LEE LA PALABRA DIVIDIDA



                            if((intAletorio1 == i) ){
                                //  Toast.makeText(getApplicationContext(), "_", Toast.LENGTH_SHORT).show();
                                palabra.append("_ ");
                            }else {
                                //Toast.makeText(getApplicationContext(), "letras " + letras[i], Toast.LENGTH_SHORT).show();
                                palabra.append(Character.toString(letras[i]));
                            }

                        }
                        Toast.makeText(getApplicationContext(), "Bien echo Solo una mas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (intentosRest == 1){
                        Toast.makeText(getApplicationContext(), "Perdiste", Toast.LENGTH_SHORT).show();
                        intentos.setText("Perdiste");
                    }else {
                        Toast.makeText(getApplicationContext(), "Mal Asierto", Toast.LENGTH_SHORT).show();
                        intentosRest--;
                        intentos.setText(Integer.toString(intentosRest));
                    }

                }
            }
        }
    }
}
