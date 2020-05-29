package com.example.master.cuciin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Cart extends AppCompatActivity {

    Intent intent;
    int counter1, counter2, total1, total2;
    TextView qty1, qty2, price1, price2, sub_total, total_price;

    HomeActivity home = new HomeActivity();

    public void makeOrder(View view){
        intent = new Intent(getApplicationContext(), OrderComplete.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        home.setFlag(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        counter1 = 1;
        counter2 = 1;
        TextView min_button1 = findViewById(R.id.minButton1);
        TextView plus_button1 = findViewById(R.id.plusButton1);
        TextView min_button2 = findViewById(R.id.minButton2);
        TextView plus_button2 = findViewById(R.id.plusButton2);
        Button add_item_button = findViewById(R.id.addItemButton);
        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        qty1 = findViewById(R.id.qty1);
        qty2 = findViewById(R.id.qty2);
        sub_total = findViewById(R.id.subTotal);
        total_price = findViewById(R.id.totalPrice);

        min_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter1 > 1)
                    counter1--;
                qty1.setText(Integer.toString(counter1));
                price1.setText(Integer.toString(40000*counter1));
                total1 = 40000*counter1;
            }
        });
        plus_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter1++;
                qty1.setText(Integer.toString(counter1));
                price1.setText(Integer.toString(40000*counter1));
                total1 = 40000*counter1;
            }
        });

        min_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter2 > 1)
                    counter2--;
                qty2.setText(Integer.toString(counter2));
                price2.setText(Integer.toString(50000*counter2));
                total2 = 50000*counter2;
            }
        });
        plus_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter2++;
                qty2.setText(Integer.toString(counter2));
                price2.setText(Integer.toString(50000*counter2));
                total2 = 50000*counter2;
            }
        });

        add_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub_total.setText(Integer.toString(total1+total2));
                total_price.setText(Integer.toString(total1+total2));
            }
        });
    }
}
