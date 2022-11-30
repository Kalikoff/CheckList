// Калмыков 303
package com.example.checklist.kalmykov303;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int N = 4; // Количество продуктов

    CheckBox[] checkBoxesProduct = new CheckBox[N]; // Товары
    EditText[] editTextsAmount = new EditText[N]; // Количество каждого товаров
    EditText[] editTextsPrice = new EditText[N]; // Цена каждого товара

    RadioButton radioButtonDialogWindow; // Диалоговое окно
    RadioButton radioButtonClassToast; // Диалог классом Toast

    int[] productsAmount = new int[N]; // Количество товаров
    double[] productsPrice = new double[N]; // Цены на продукты

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxesProduct[0] = findViewById(R.id.checkBoxProduct1);
        checkBoxesProduct[1] = findViewById(R.id.checkBoxProduct2);
        checkBoxesProduct[2] = findViewById(R.id.checkBoxProduct3);
        checkBoxesProduct[3] = findViewById(R.id.checkBoxProduct4);

        editTextsAmount[0] = findViewById(R.id.editTextAmountProduct1);
        editTextsAmount[1] = findViewById(R.id.editTextAmountProduct2);
        editTextsAmount[2] = findViewById(R.id.editTextAmountProduct3);
        editTextsAmount[3] = findViewById(R.id.editTextAmountProduct4);

        editTextsPrice[0] = findViewById(R.id.editTextPriceProduct1);
        editTextsPrice[1] = findViewById(R.id.editTextPriceProduct2);
        editTextsPrice[2] = findViewById(R.id.editTextPriceProduct3);
        editTextsPrice[3] = findViewById(R.id.editTextPriceProduct4);

        radioButtonDialogWindow = findViewById(R.id.radioButtonDialogWindow);
        radioButtonClassToast = findViewById(R.id.radioButtonClassToast);
    }


    // Рассчитать
    @SuppressLint("DefaultLocale")
    public void Calculate_onClick(View v) {
        String str = "";
        double sum = 0;

        if (!GetProductsPrice())
            return;

        for (int i = 0; i < N; i++) {
            if (checkBoxesProduct[i].isChecked()) {
                int amount = Integer.parseInt(editTextsAmount[i].getText().toString());
                double price = productsPrice[i];
                String string = checkBoxesProduct[i].getText().toString();
                double sumProduct = amount * price;

                sum += (amount * price);

                str += String.format("%s: %d*%.2f = %.2f рублей\n", string, amount, price, sumProduct);
            }
        }

        String str2 = String.format("\nСумма: %.2f рублей\n", sum);
        str += str2;

        SelectDialog(str, str2);
    }


    // Получить цену продуктов
    private boolean GetProductsPrice() {
        int i = 0;
        int type = 0;

        try {
            for (; i < N; i++) {
                type = 1;
                productsAmount[i] = Integer.parseInt(editTextsAmount[i].getText().toString());

                type = 2;
                productsPrice[i] = Double.parseDouble(editTextsPrice[i].getText().toString());

                type = 0;

                if (productsAmount[i] <= 0 || productsPrice[i] <= 0)
                    return false;
            }
        }
        catch (Exception e) {
            if (type == 1) {
                editTextsAmount[i].setError("Введите значение!");
            }
            else if (type == 2) {
                editTextsPrice[i].setError("Введите значение!");
            }

            return false;
        }

        return true;
    }


    // Выбор типа диалога
    private void SelectDialog(String str, String str2) {
        if (radioButtonDialogWindow.isChecked()) {
            AlertDialog dialog = CreateDialog(str);
            dialog.show();
        } else if (radioButtonClassToast.isChecked()) {
            Toast.makeText(this, str2, Toast.LENGTH_SHORT).show();
        }
    }


    // Создание диалога
    private AlertDialog CreateDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ваши товары").setIcon(R.drawable.icon).setMessage(message).setPositiveButton("ОК", (dialog, id) -> dialog.cancel());

        return builder.create();
    }
}
// Калмыков 303