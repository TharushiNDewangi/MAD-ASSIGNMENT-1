package com.example.elearningsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class markscalculation extends AppCompatActivity {

    EditText e1,e2,e3,e4;

    Button b1,b2;
    String mark,outoumark,numofmod,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markscalculation);

        e1=findViewById(R.id.mark);
        e2=findViewById(R.id.outofmark);
        e3=findViewById(R.id.name);
        e4=findViewById(R.id.numofmodules);



        b1=findViewById(R.id.calculate);
        b2=findViewById(R.id.cancel);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mark = e1.getText().toString();
                outoumark =e2.getText().toString();
                name =e3.getText().toString();
                numofmod =e4.getText().toString();

                float castedmark=Integer.parseInt(mark);
                float castedoutofmark=Integer.parseInt(outoumark);
                float numofmodule = Integer.parseInt(numofmod);


                float result =  calcResult(castedoutofmark,castedmark);
                float avgres = calcAvgmark(castedmark,numofmodule);
                String convertresult = String.valueOf(result);
                String convertavg = String.valueOf(avgres);

                Intent intent = new Intent(markscalculation.this, showresult.class);
                //
                intent.putExtra("res", convertresult);
                intent.putExtra("avg", convertavg);
                intent.putExtra("name", name);
                Toast.makeText(getApplicationContext(), "RTTFRR"+castedoutofmark, Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }
    public static float calcResult(float castedoutofmark,float castedmark)
    {
        return ((castedmark / castedoutofmark) * 100);
    }
    public static float calcAvgmark(float castedmark,float numofmodule)
    {
        return (castedmark / numofmodule);
    }

    private void clearControls()
    {
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");

    }
}