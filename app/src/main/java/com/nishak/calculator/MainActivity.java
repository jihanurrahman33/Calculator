package com.nishak.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv,solution_tv;
    MaterialButton btn_c,btn_brack_open,btn_brack_close,btn_divide,btn_7,btn_8,btn_9,btn_multi,btn_4,btn_5,btn_6,btn_add,btn_1,btn_2,btn_3,btn_sub,btn_ac,btn_0,btn_dot,btn_equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);

        assignID(btn_c,R.id.button_c);
        assignID(btn_brack_open,R.id.open_bracket);
        assignID(btn_brack_close,R.id.close_bracket);
        assignID(btn_divide,R.id.divide_symbol);
        assignID(btn_7,R.id.button_7);
        assignID(btn_8,R.id.button_8);
        assignID(btn_9,R.id.button_9);
        assignID(btn_multi,R.id.button_multiply);
        assignID(btn_4,R.id.button_4);
        assignID(btn_5,R.id.button_5);
        assignID(btn_6,R.id.button_6);
        assignID(btn_add,R.id.button_addition);
        assignID(btn_1,R.id.button_1);
        assignID(btn_2,R.id.button_2);
        assignID(btn_3,R.id.button_3);
        assignID(btn_sub,R.id.button_subtraction);
        assignID(btn_ac,R.id.button_ac);
        assignID(btn_0,R.id.button_0);
        assignID(btn_dot,R.id.button_dot);
        assignID(btn_equal,R.id.button_equal);
    }
    void assignID(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String button_Text = button.getText().toString();
        String data_calc = solution_tv.getText().toString();
        if (button_Text.equals("AC")) {
            solution_tv.setText("0");
            result_tv.setText("0");
            return;
        }
        if (button_Text.equals("=")) {
            solution_tv.setText(result_tv.getText());
            return;
        }
        if (button_Text.equals("C")) {
            data_calc = data_calc.substring(0, data_calc.length() - 1);
        } else {
            data_calc = data_calc + button_Text;
        }

        solution_tv.setText(data_calc);
        String finalResult=getResult(data_calc);
        if (!finalResult.equals("Err")) {
            result_tv.setText(finalResult);
        }
    }
        String getResult(String data)
        {
            try{
                Context context=Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable=context.initSafeStandardObjects();
               String finalResult= context.evaluateString(scriptable,data, "JavaScript",1,null).toString();
               if(finalResult.endsWith(".0")) {
                   finalResult=finalResult.replace("0","");
               }
               return finalResult;

            }catch (Exception e)
            {
                return "Err";
            }
        }




    }
