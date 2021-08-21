package com.example.justjava;

import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view) {
        EditText nameField =  (EditText) findViewById(R.id.editTextTextPersonName2);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkBox2);
        boolean haschocolate = chocolateCheckBox.isChecked();
    int price = calculatePrice(hasWhippedCream , haschocolate);
    String priceMessage = OrderSummeary(name , price , hasWhippedCream , haschocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order summeray for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);
 }

    public void incriment(View view) {
        if(quantity==100){
    Toast.makeText(this, getString(R.string.you_cannot_order_coffie_more_then_100) , Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity +1;
        displayQuantity(quantity);
    }
    public void decriment(View view) {

     if(quantity ==1){
         Toast.makeText(this, getString(R.string.you_cannot_coffie_less_then_1) , Toast.LENGTH_SHORT).show();
         return;
     }
        quantity =quantity-1;

        displayQuantity(quantity);
    }
    private int calculatePrice(boolean hasWhippedCream, boolean haschocolate){
    int baseprice = 5;

    if(haschocolate){
        baseprice = baseprice +2;
    }
    if(hasWhippedCream){
        baseprice = baseprice +1;
    }
        return  quantity*baseprice;
    }
    private String OrderSummeary(String name , int price , boolean addwhippedcream , boolean addchocolate ){
        String priceMessage = " name = stranger  " + name ;
        priceMessage +="\n add whipped cream ?" + addwhippedcream;
        priceMessage += "\n add chocolate ?" + addchocolate;
        priceMessage = priceMessage + "\n quantity "+ quantity;
        priceMessage = priceMessage + "\n Total Price "+ price;
        priceMessage= priceMessage +"\n " + getString(R.string.thank_you);
        return priceMessage;
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    private void displayPrice(String number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}