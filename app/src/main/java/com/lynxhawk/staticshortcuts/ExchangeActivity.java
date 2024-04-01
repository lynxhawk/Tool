package com.lynxhawk.staticshortcuts;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ExchangeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    EditText money;
    Button cal;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        money = (EditText)findViewById(R.id.money);
        cal = (Button)findViewById(R.id.cal);
        result = (TextView)findViewById(R.id.result);

        final RadioGroup radioGroup1 = (RadioGroup)findViewById(R.id.group1);
        final RadioGroup radioGroup2 = (RadioGroup)findViewById(R.id.group2);
        RadioButton cny1 = (RadioButton)findViewById(R.id.cny1);
        RadioButton usd1 = (RadioButton)findViewById(R.id.usd1);
        RadioButton jpy1 = (RadioButton)findViewById(R.id.jpy1);
        RadioButton cny2 = (RadioButton)findViewById(R.id.cny2);
        RadioButton usd2 = (RadioButton)findViewById(R.id.usd2);
        RadioButton jpy2 = (RadioButton)findViewById(R.id.jpy2);

        radioGroup1.setOnCheckedChangeListener(this);
        radioGroup2.setOnCheckedChangeListener(this);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(radioGroup1.getCheckedRadioButtonId()==R.id.cny1&&radioGroup2.getCheckedRadioButtonId()==R.id.usd2)
               {
                   Double x = Double.parseDouble(money.getText().toString())/6.8;
                   result.setText(x.toString());
               }
                if(radioGroup1.getCheckedRadioButtonId()==R.id.usd1&&radioGroup2.getCheckedRadioButtonId()==R.id.cny2)
                {
                    Double x = Double.parseDouble(money.getText().toString())*6.8;
                    result.setText(x.toString());
                }
                if(radioGroup1.getCheckedRadioButtonId()==R.id.cny1&&radioGroup2.getCheckedRadioButtonId()==R.id.jpy2)
                {
                    Double x = Double.parseDouble(money.getText().toString())*16;
                    result.setText(x.toString());
                }
                if(radioGroup1.getCheckedRadioButtonId()==R.id.jpy1&&radioGroup2.getCheckedRadioButtonId()==R.id.cny2)
                {
                    Double x = Double.parseDouble(money.getText().toString())/16;
                    result.setText(x.toString());
                }
                if(radioGroup1.getCheckedRadioButtonId()==R.id.jpy1&&radioGroup2.getCheckedRadioButtonId()==R.id.usd2)
                {
                    Double x = Double.parseDouble(money.getText().toString())/108;
                    result.setText(x.toString());
                }
                if(radioGroup1.getCheckedRadioButtonId()==R.id.usd1&&radioGroup2.getCheckedRadioButtonId()==R.id.jpy2)
                {
                    Double x = Double.parseDouble(money.getText().toString())*108;
                    result.setText(x.toString());
                }
                //    MainActivity.this.finish();
            }
        });


    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
