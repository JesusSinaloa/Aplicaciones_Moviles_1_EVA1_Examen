package com.example.eduardo.eva1_examen1_1_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity

{

 //Declaracion de objetos
    TextView Pi, Resultado, TxtViewgrados, Unidades;
    EditText Radio, Grados;
    RadioGroup rdGroupCalculos;
    RadioButton Volumen, Area, Esfera;
    Button btnCalcular, btnCerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Pi = findViewById(R.id.textViewPiValor);
        Resultado = findViewById(R.id.textViewResultado);
        Radio = findViewById(R.id.editTextRadio);
        Grados = findViewById(R.id.editTextGrados);
        rdGroupCalculos = findViewById(R.id.RdGroupCalculos);
        Volumen = findViewById(R.id.radioButtonVolumen);
        Area = findViewById(R.id.radioButtonArea);
        Esfera = findViewById(R.id.radioButtonVCE);
        btnCalcular = findViewById(R.id.buttonCalcular);
        btnCerrar = findViewById(R.id.buttonCerrar);
        TxtViewgrados = findViewById(R.id.textViewGrados);
        Unidades = findViewById(R.id.textViewUnidades);


        Pi.setText("3.14");

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((rdGroupCalculos.getCheckedRadioButtonId() == -1) || (Radio.getText().toString().isEmpty()) ) {
                    Toast.makeText(getApplicationContext(), "SELECCIONE UN ELEMENTO DEL RADIO GROUP O INTRODUSCA UAN RADIO", Toast.LENGTH_SHORT).show();

                }else {

                    double pi = 3.14;
                    double radio = Double.parseDouble(Radio.getText().toString());
                    if(Volumen.isChecked()){

                        double volumen = (((4 * pi) * (radio * radio *radio)) / (3));
                        Resultado.setText(Double.toString(volumen));

                    }else{
                        if (Area.isChecked()){
                            double area = (4 * (pi) * (radio *radio));
                            Resultado.setText(Double.toString(area));
                        }else{
                            if (Esfera.isChecked() && ((!Grados.getText().toString().isEmpty()))) {
                                double grados = Double.parseDouble((Grados.getText().toString()));
                                double Vesfera = ((1.3333) * (((pi)* (radio * radio * radio)) / (360)) * (grados));
                                Resultado.setText(Double.toString(Vesfera));
                            }else{
                                Toast.makeText(getApplicationContext(), "INTRODUSCA UN GRADO", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });


        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CERRANDO", Toast.LENGTH_SHORT).show();

                finish(); // La cerramos.
            }
        });


        rdGroupCalculos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButtonVolumen:
                        Grados.setEnabled(false);
                        TxtViewgrados.setEnabled(false);
                        Unidades.setText("m^3");

                        break;
                    case R.id.radioButtonArea:
                        Grados.setEnabled(false);
                        TxtViewgrados.setEnabled(false);
                        Unidades.setText("m^2");
                        break;
                    case R.id.radioButtonVCE:
                     Grados.setEnabled(true);
                        TxtViewgrados.setEnabled(true);
                        Unidades.setText("m^3");
                        break;


                }
            }
        });
    }



}
