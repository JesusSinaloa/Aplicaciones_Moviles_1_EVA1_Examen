package wuwuwu.subnet.ejemplo.subnet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText[] ipEditTexts = new EditText[4];
    private EditText[] maskEditTexts = new EditText[4];

    private RadioButton radio_decimal;
    private RadioButton radio_binary;

    private TextView textViewIp;
    private TextView textViewMask;

    private TextView textViewSubnet;
    private TextView textViewPrefix;

    private int[] ip = new int[4];
    private int[] mask = new int[4];
    private int[] subnet = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditTexts[0] = findViewById(R.id.edit_ip0);
        ipEditTexts[1] = findViewById(R.id.edit_ip1);
        ipEditTexts[2] = findViewById(R.id.edit_ip2);
        ipEditTexts[3] = findViewById(R.id.edit_ip3);

        maskEditTexts[0] = findViewById(R.id.edit_mask0);
        maskEditTexts[1] = findViewById(R.id.edit_mask1);
        maskEditTexts[2] = findViewById(R.id.edit_mask2);
        maskEditTexts[3] = findViewById(R.id.edit_mask3);

        radio_decimal = findViewById(R.id.radio_decimal);
        radio_binary = findViewById(R.id.radio_binary);

        textViewIp = findViewById(R.id.text_view_ip);
        textViewMask = findViewById(R.id.text_view_mask);

        textViewSubnet = findViewById(R.id.text_view_subnet);
        textViewPrefix = findViewById(R.id.text_view_prefix);
    }

    public void clear (View view) {
        for (int i = 0; i < 4; i++) {
            ipEditTexts[i].setText("");
            maskEditTexts[i].setText("");
        }
    }

    public void calculate (View view) {
        for (int i = 0; i < 4; i++) {
            this.ip[i] = ipEditTexts[i].getText().toString().matches("") ? 0 : Integer.parseInt(ipEditTexts[i].getText().toString());
            this.mask[i] = maskEditTexts[i].getText().toString().matches("") ? 0 : Integer.parseInt(maskEditTexts[i].getText().toString());
        }

        calculateSubnet();

        if (radio_binary.isChecked()) {
            Toast.makeText(this, "Binary", Toast.LENGTH_SHORT).show();
            textViewIp.setText(arrayToBinaryString(this.ip));
            textViewMask.setText(arrayToBinaryString(this.mask));
        }
        if (radio_decimal.isChecked()) {
            Toast.makeText(this, "Decimal", Toast.LENGTH_SHORT).show();
            textViewIp.setText(arrayToString(this.ip));
            textViewMask.setText(arrayToString(this.mask));
        }

        textViewSubnet.setText(arrayToString(this.subnet));
        textViewPrefix.setText(String.valueOf(calculatePrefix()));
    }

    private void calculateSubnet () {
        for (int i = 0; i < 4; i++) {
            this.subnet[i] = this.ip[i] & this.mask[i];
        }
    }

    private String arrayToString (int[] array) {
        String s = "";

        for (int i = 0; i < array.length; i++) {
            s += String.valueOf(array[i]);
            if (!(i == array.length - 1)) {
                s += " - ";
            }
        }
        return s;
    }

    private String arrayToBinaryString (int[] array) {
        String s = "";

        for (int i = 0; i < array.length; i++) {
            s += Integer.toBinaryString(array[i]);
            if (!(i == array.length - 1)) {
                s += " - ";
            }
        }
        return s;
    }

    private int calculatePrefix () {
        int prefix = 0;
        String maskBinary = arrayToBinaryString(this.mask);

        for (char c : maskBinary.toCharArray()) {
            if (c == '1') {
                prefix++;
            }
        }
        return prefix;
    }

}
