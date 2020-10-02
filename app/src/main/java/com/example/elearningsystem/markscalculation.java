package com.example.elearningsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class markscalculation extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    TextView t1,t2;
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

        t1=findViewById(R.id.result);
        t2=findViewById(R.id.resultavg);

        b1=findViewById(R.id.calculate);
        b2=findViewById(R.id.cancel);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation();
            }
        });
    }
    public void calculation()
    {
        mark = e1.getText().toString();
        outoumark =e2.getText().toString();
        name =e3.getText().toString();
        numofmod =e4.getText().toString();

        int castedmark=Integer.parseInt(mark);
        int castedoutofmark=Integer.parseInt(outoumark);
        int numofmodule = Integer.parseInt(numofmod);

        int result = (castedoutofmark / castedmark) * 100;
        int avgres = castedmark / numofmodule;
        String convertresult = String.valueOf(result);
        String convertavg = String.valueOf(avgres);

        t1.setText("mark percetage : "+convertresult);
        t2.setText("Average mark : "+convertavg);

        if(avgres >= 95)
        {

        }
        else if(avgres >= 75)
        {

        }
        else if(avgres >= 65)
        {

        }
        else if(avgres >= 55)
        {

        }
        else if(avgres >= 45)
        {

        }
        else
        {

        }


    }

    private void clearControls()
    {
        e1.setText("");
        e2.setText("");

    }
}