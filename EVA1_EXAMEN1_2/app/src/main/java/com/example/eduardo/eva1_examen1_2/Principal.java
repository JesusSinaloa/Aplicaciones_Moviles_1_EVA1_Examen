package com.example.eduardo.eva1_examen1_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    //DECLARACION
    EditText numFaltante ;
    TextView num1, num2, num3, num4, num5, resultado ;
    Button  verificar;
    RadioGroup serie;
    RadioButton algebra, aritmetica;
    int contador = 1;
    int posicionOculta;


    int[] arraySerie = new int[6];//ARREGLO QUE GUARDARA LA serie


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //ASOCIAR

        numFaltante = findViewById(R.id.editTextNumeroFaltante);
        num1 = findViewById(R.id.textViewN1);
        num2 = findViewById(R.id.textViewN2);
        num3 = findViewById(R.id.textViewN3);
        num4 = findViewById(R.id.textViewN4);
        num5 = findViewById(R.id.textViewN5);
        verificar = findViewById(R.id.buttonVerificar);
        serie = findViewById(R.id.rdGroupSerie);
        algebra = findViewById(R.id.radioButtonAlgebraica);
        aritmetica = findViewById(R.id.radioButtonAritmetica);
        resultado = findViewById(R.id.textViewResultado);

        //DECLARACION DE EVENTOS
        //EVENTO RADIO GROUP
        serie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.radioButtonAritmetica:
                            generarSerieAritmetica();

                        break;
                    case R.id.radioButtonAlgebraica:
                        generarSerieAlgebraica();
                        break;
                }

            }
        });

        //EVENTO CLICK PARA BOTON
        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((algebra.isChecked() || aritmetica.isChecked()) && (!numFaltante.getText().toString().isEmpty())) {


                    compararNumero();
                }else{
                    Toast.makeText(getApplicationContext(), "Selecciona un operacion y/o Introduce una respuesta", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




    public void mostrarSerie (){//metodo para llenar etiquetas
        posicionOculta = (int) (Math.random() * 5) + 1;//numero aleatorio para saber cual se va a ocultar


        switch (posicionOculta){
            case 1:
                Toast.makeText(getApplicationContext(), "oculto:" +  posicionOculta  , Toast.LENGTH_SHORT).show();
               num1.setText("");
                num2.setText(Integer.toString(arraySerie[2]));
                num3.setText(Integer.toString(arraySerie[3]));
                num4.setText(Integer.toString(arraySerie[4]));
                num5.setText(Integer.toString(arraySerie[5]));

                break;
            case 2:
                Toast.makeText(getApplicationContext(), "oculto:" + posicionOculta , Toast.LENGTH_SHORT).show();
                 num1.setText(Integer.toString(arraySerie[1]));
                num2.setText("");
                num3.setText(Integer.toString(arraySerie[3]));
                num4.setText(Integer.toString(arraySerie[4]));
                num5.setText(Integer.toString(arraySerie[5]));
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "oculto:" + posicionOculta, Toast.LENGTH_SHORT).show();
                 num1.setText(Integer.toString(arraySerie[1]));
                num2.setText(Integer.toString(arraySerie[2]));
                num3.setText("");
                num4.setText(Integer.toString(arraySerie[4]));
                num5.setText(Integer.toString(arraySerie[5]));
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "oculto:" +  posicionOculta, Toast.LENGTH_SHORT).show();
                num1.setText(Integer.toString(arraySerie[1]));
                num2.setText(Integer.toString(arraySerie[2]));
                num3.setText(Integer.toString(arraySerie[3]));
                num4.setText("");
                num5.setText(Integer.toString(arraySerie[5]));
                break;
            case 5:
                Toast.makeText(getApplicationContext(), "oculto:" + posicionOculta , Toast.LENGTH_SHORT).show();
                num1.setText(Integer.toString(arraySerie[1]));
                num2.setText(Integer.toString(arraySerie[2]));
                num3.setText(Integer.toString(arraySerie[3]));
                num4.setText(Integer.toString(arraySerie[4]));
                num5.setText("");
                break;
        }


       /* num1.setText(Integer.toString(arraySerie[1]));
        num2.setText(Integer.toString(arraySerie[2]));
        num3.setText(Integer.toString(arraySerie[3]));
        num4.setText(Integer.toString(arraySerie[4]));
        num5.setText(Integer.toString(arraySerie[5]));*/

    }

    public void compararNumero (){
        int faltante = posicionOculta;
        //Toast.makeText(getApplicationContext(), "posicion:" + faltante , Toast.LENGTH_SHORT).show();
        int respuesta = (Integer.parseInt(numFaltante.getText().toString()));
        boolean acumulador = false;





            if (arraySerie[faltante] == respuesta){
                Toast.makeText(getApplicationContext(), "Bien echo", Toast.LENGTH_SHORT).show();

                contador = 1;
                Toast.makeText(getApplicationContext(), "Juega de nuevo", Toast.LENGTH_SHORT).show();
                resultado.setText("intento" + contador);
                if(algebra.isChecked()){
                    generarSerieAlgebraica();
                }else{
                    generarSerieAritmetica();
                }

            }else {
                if(contador >= 3){
                    Toast.makeText(getApplicationContext(), "Perdiste", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "empieza de nuevo", Toast.LENGTH_SHORT).show();

                    contador = 1;
                    resultado.setText("intento" + contador);
                    if(algebra.isChecked()){
                        generarSerieAlgebraica();
                    }else{
                        generarSerieAritmetica();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "incorrecto"  , Toast.LENGTH_SHORT).show();
                    contador++;
                    resultado.setText("intento: " + contador);

                }
            }




    }

    public void generarSerieAritmetica () {
        int numAleatorioInicial;
        int distancia;
        int n = 100;
        int n1 = 25;
        numAleatorioInicial = (int) (Math.random() * n) + 1;//se crea el numero iniciaL random
        distancia = (int) (Math.random() * n1) + 1;//se crea la distancia random
        for (int i=1 ; i < 6; i++){// se llena el arreglo con la formula
            arraySerie[i] = numAleatorioInicial +(i - 1) * distancia;
            // Toast.makeText(getApplicationContext(), "i:" + i , Toast.LENGTH_SHORT).show();

        }

        mostrarSerie();// se imprime el arreglo en las etiquetas
        //resultado.setText(Integer.toString(numAleatorioInicial));
        resultado.setText("Intento: " + Integer.toString(contador));
    }

    public void generarSerieAlgebraica (){
        int numAleatorioInicialAlg;



        int n2 = 10;

        numAleatorioInicialAlg = (int) (Math.random() * n2) + 1;//se crea el numero iniciaL random
        for (int i=1 ; i < 6; i++){// se llena el arreglo con la formula
            int r = (int)(Math.pow(numAleatorioInicialAlg,(i-1)));

            arraySerie[i] =((numAleatorioInicialAlg) * (r));
            // Toast.makeText(getApplicationContext(), "i:" + i , Toast.LENGTH_SHORT).show();

        }
        mostrarSerie();// se imprime el arreglo en las etiquetas
        //resultado.setText(Integer.toString(numAleatorioInicial));
        resultado.setText("Intento: " + Integer.toString(contador));
    }
}
