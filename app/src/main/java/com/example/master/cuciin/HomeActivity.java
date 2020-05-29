package com.example.master.cuciin;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Intent intent;
    BottomSheetDialog bottom_sheet;
    EditText jumlah;
    private int counter, flag = 0;

    public void taskClicked(View view){
        intent = new Intent(getApplicationContext(), TaskActivity.class);
        startActivity(intent);
    }
    public void helpClicked(View view){
        intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }
    public void profileClicked(View view) {
        intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }

    public void checkOut(View view){
        if(flag != 0){
            intent = new Intent(getApplicationContext(), Cart.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please make order first!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportActionBar().hide();

        ImageButton qc_button = findViewById(R.id.qcButton);
        qc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1;
                //create
                bottom_sheet = new BottomSheetDialog(HomeActivity.this);
                bottom_sheet.setContentView(R.layout.activity_quick_cleaning);
                bottom_sheet.setCanceledOnTouchOutside(true);
                //initialize and assign
                jumlah = bottom_sheet.findViewById(R.id.jumlah);
                ImageButton min_button = bottom_sheet.findViewById(R.id.minButton);
                ImageButton plus_button = bottom_sheet.findViewById(R.id.plusButton);
                Button add_button = bottom_sheet.findViewById(R.id.addButton);

                min_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //decrease qty
                        if(counter > 1)
                            counter--;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                plus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //increase qty
                        counter++;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottom_sheet.dismiss();
                        flag++;
                    }
                });
                bottom_sheet.show();
            }
        });

        ImageButton dc_button = findViewById(R.id.dcButton);
        dc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1;
                //create
                bottom_sheet = new BottomSheetDialog(HomeActivity.this);
                bottom_sheet.setContentView(R.layout.activity_deep_cleaning);
                bottom_sheet.setCanceledOnTouchOutside(true);
                //initialize and assign
                jumlah = bottom_sheet.findViewById(R.id.jumlah);
                ImageButton min_button = bottom_sheet.findViewById(R.id.minButton);
                ImageButton plus_button = bottom_sheet.findViewById(R.id.plusButton);
                Button add_button = bottom_sheet.findViewById(R.id.addButton);

                min_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //decrease qty
                        if(counter > 1)
                            counter--;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                plus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //increase qty
                        counter++;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottom_sheet.dismiss();
                        flag++;
                    }
                });
                bottom_sheet.show();
            }
        });

        ImageButton lt_button = findViewById(R.id.ltButton);
        lt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1;
                //create
                bottom_sheet = new BottomSheetDialog(HomeActivity.this);
                bottom_sheet.setContentView(R.layout.activity_leather_treatment);
                bottom_sheet.setCanceledOnTouchOutside(true);
                //initialize and assign
                jumlah = bottom_sheet.findViewById(R.id.jumlah);
                ImageButton min_button = bottom_sheet.findViewById(R.id.minButton);
                ImageButton plus_button = bottom_sheet.findViewById(R.id.plusButton);
                Button add_button = bottom_sheet.findViewById(R.id.addButton);

                min_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //decrease qty
                        if(counter > 1)
                            counter--;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                plus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //increase qty
                        counter++;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottom_sheet.dismiss();
                        flag++;
                    }
                });
                bottom_sheet.show();
            }
        });

        ImageButton uy_button = findViewById(R.id.uyButton);
        uy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1;
                //create
                bottom_sheet = new BottomSheetDialog(HomeActivity.this);
                bottom_sheet.setContentView(R.layout.activity_unyellowing);
                bottom_sheet.setCanceledOnTouchOutside(true);
                //initialize and assign
                jumlah = bottom_sheet.findViewById(R.id.jumlah);
                ImageButton min_button = bottom_sheet.findViewById(R.id.minButton);
                ImageButton plus_button = bottom_sheet.findViewById(R.id.plusButton);
                Button add_button = bottom_sheet.findViewById(R.id.addButton);

                min_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //decrease qty
                        if(counter > 1)
                            counter--;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                plus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //increase qty
                        counter++;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottom_sheet.dismiss();
                        flag++;
                    }
                });
                bottom_sheet.show();
            }
        });

        ImageButton rp_button = findViewById(R.id.rpButton);
        rp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1;
                //create
                bottom_sheet = new BottomSheetDialog(HomeActivity.this);
                bottom_sheet.setContentView(R.layout.activity_repaint);
                bottom_sheet.setCanceledOnTouchOutside(true);
                //initialize and assign
                jumlah = bottom_sheet.findViewById(R.id.jumlah);
                ImageButton min_button = bottom_sheet.findViewById(R.id.minButton);
                ImageButton plus_button = bottom_sheet.findViewById(R.id.plusButton);
                Button add_button = bottom_sheet.findViewById(R.id.addButton);

                min_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //decrease qty
                        if(counter > 1)
                            counter--;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                plus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //increase qty
                        counter++;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottom_sheet.dismiss();
                        flag++;
                    }
                });
                bottom_sheet.show();
            }
        });

        ImageButton rm_button = findViewById(R.id.rmButton);
        rm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1;
                //create
                bottom_sheet = new BottomSheetDialog(HomeActivity.this);
                bottom_sheet.setContentView(R.layout.activity_repaint_midsole);
                bottom_sheet.setCanceledOnTouchOutside(true);
                //initialize and assign
                jumlah = bottom_sheet.findViewById(R.id.jumlah);
                ImageButton min_button = bottom_sheet.findViewById(R.id.minButton);
                ImageButton plus_button = bottom_sheet.findViewById(R.id.plusButton);
                Button add_button = bottom_sheet.findViewById(R.id.addButton);

                min_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //decrease qty
                        if(counter > 1)
                            counter--;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                plus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //increase qty
                        counter++;
                        jumlah.setText(Integer.toString(counter));
                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottom_sheet.dismiss();
                        flag++;
                    }
                });
                bottom_sheet.show();
            }
        });
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
