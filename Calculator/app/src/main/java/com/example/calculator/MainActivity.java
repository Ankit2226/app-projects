package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
//import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     TextView resultTv,solutiontv;
     MaterialButton buttonc,buttonBrackOpen,BrackClose;
     MaterialButton buttonDivide,buttonMultiply,buttonplus,getButtonMinus,buttonEquals;
     MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
     MaterialButton buttonAC, buttonDot;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.resultv);
        solutiontv=findViewById(R.id.solutiontv);

        assignID(buttonc,R.id.button_c);
        assignID(buttonBrackOpen,R.id.open_bracket);
        assignID(BrackClose,R.id.close_bracket);
        assignID(buttonDivide,R.id.divide);
        assignID(buttonMultiply,R.id.multiply);
        assignID(getButtonMinus,R.id.sub);
        assignID(buttonplus,R.id.add);
        assignID(buttonEquals,R.id.equalto);
        assignID(button0,R.id.zero);
        assignID(button1,R.id.one);
        assignID(button2,R.id.two);
        assignID(button3,R.id.three);
        assignID(button4,R.id.four);
        assignID(button5,R.id.five);
        assignID(button6,R.id.six);
        assignID(button7,R.id.seven);
        assignID(button8,R.id.eight);
        assignID(button9,R.id.nine);
        assignID(buttonAC,R.id.ac);
        assignID(buttonDot,R.id.dot);


    }

    void assignID(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        MaterialButton button =(MaterialButton) view;
        String buttonText =button.getText().toString();
      //  solutiontv.setText(buttonText);
        String dataTocalculate = solutiontv.getText().toString();
        if(buttonText.equals("Ac")){
            solutiontv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutiontv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataTocalculate=dataTocalculate.substring(0,dataTocalculate.length()-1);
        }else{
            dataTocalculate=dataTocalculate+buttonText;

        }

        solutiontv.setText(dataTocalculate);

        String finalResult = getResult(dataTocalculate);

        if (!finalResult.equals(("Error"))){
            resultTv.setText((finalResult));
        }

    }
    String getResult(String data){
        return "calculated";
        try {
            Context context = Context.enter();
            context.getApplicationInfo(-1);
            Scriptable Scriptable =context.sendBroadcast();
           context.toString(Scriptable,data,"JavaScript",1,null).tostring();
            String finalResult;
            finalResult = null;
            if (finalResult.endsWith(".0)")){
               finalResult=finalResult.replace(".0","");
           }
           return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}