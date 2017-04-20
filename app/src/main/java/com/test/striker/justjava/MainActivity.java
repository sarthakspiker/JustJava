package com.test.striker.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int basePrice = 4;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream) ;
        boolean hasWhippedCream=whippedCream.isChecked();
        if (hasWhippedCream){
            basePrice = basePrice + 1;
        }
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate) ;
        boolean hasChocolate = chocolate.isChecked();
        if(hasChocolate){
            basePrice = basePrice + 2;
        }
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        String orderSummary ="Quantity : "+quantity+"\nTotal $ "+basePrice*quantity+"\nThank you !";
        orderSummary = "Whipped Cream Added ? "+hasWhippedCream+"\n"+orderSummary;
        orderSummary = "Chocolate Added ? "+hasChocolate+"\n"+orderSummary;
        orderSummary = "Name : "+name+"\n"+orderSummary;
        composeEmail(name,orderSummary);
    }
    public void increment(View view){
        if (quantity==100) {
            Toast.makeText(this, "Quantity is already maximum !", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }
    public void decrement(View view){
        if (quantity==1) {
            Toast.makeText(this, "Quantity is already minimum !", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(NumberFormat.getInstance().format(number));
    }
    /**
     * This method displays the given price on the screen.
     */
//    private void displaySummary(String summary) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(summary);
//    }

    public void composeEmail(String name ,String body){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}