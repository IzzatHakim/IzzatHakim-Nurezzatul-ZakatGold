package com.example.zakatgold;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CalculatorFragment extends Fragment {


    private EditText weight, type, value;
    private Button count;
    private TextView result;
    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;
    float gv,gw;



    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        weight = v.findViewById(R.id.weight);
        type = v.findViewById(R.id.type);
        value = v.findViewById(R.id.value);
        result = v.findViewById(R.id.result);
        count = (Button) v.findViewById(R.id.button);

        if(type.length() == 0){
            type.setError("Enter type in lower case form");
        }

        SharedPreferences sh1 = getActivity().getSharedPreferences("weight", Context.MODE_PRIVATE);
        SharedPreferences sh2 = getActivity().getSharedPreferences("value", Context.MODE_PRIVATE);

        gw = sh1.getFloat("weight",0.0F);
        gv = sh2.getFloat("value", 0.0F);

        weight.setText(""+gw);
        value.setText(""+gv);

        String keep = "keep";
        String wear = "wear";


        count.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                        gw = Float.parseFloat(weight.getText().toString());
                        String t = type.getText().toString();
                        gv = Float.parseFloat(value.getText().toString());

                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Please enter value", Toast.LENGTH_SHORT).show();
                }catch (Exception exp) {
                    Toast.makeText(getActivity(), "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();

                    Log.d("Exception", exp.getMessage());
                }
                SharedPreferences.Editor editor = sh1.edit();
                SharedPreferences.Editor editor1 = sh2.edit();

                editor.putFloat("weight", gw);
                editor.putFloat("value", gv);

                editor.apply();
                editor1.apply();

                int x;
                if(keep.equalsIgnoreCase(type.getText().toString())){
                     x = 85;
                }
                else {
                    x = 200;
                }


                double totValue = (gw * gv);
                double totGoldValue = ((gw - x) * gv );
                double totZ = (0.025 * totGoldValue);

                result.setText("i.\tThe total value of the gold:" + Double.toString(totValue) + "\n"+
                        "\nii. \tTotal gold value that is zakat payable:" + Double.toString(totGoldValue) + "\n"+
                        "\niii. \tThe total zakat:" + Double.toString(totZ));



            }

        });

        return v;
    }


}