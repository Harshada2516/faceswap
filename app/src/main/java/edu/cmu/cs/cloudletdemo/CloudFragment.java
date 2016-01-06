package edu.cmu.cs.cloudletdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.cmu.cs.gabriel.Const;
import edu.cmu.cs.gabriel.GabrielClientActivity;
import edu.cmu.cs.gabriel.R;

public class CloudFragment extends Fragment {
    private TextView titleTextView;
    private TextView ipTextView;
    private Button resetIPButton;
    private EditText ipEditText;
    private Button runDemoButton;
    private static final String
            IPV4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String IPV6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";

    public CloudFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view=inflater.inflate(R.layout.cloudlet_fragment,container,false);

        titleTextView=(TextView)view.findViewById(R.id.titleView);
        titleTextView.setText("cloud ip");
        ipTextView=(TextView)view.findViewById(R.id.cloudletIPView);
        ipTextView.setText(Const.CLOUD_GABRIEL_IP);
        resetIPButton=(Button)view.findViewById(R.id.resetIPButton);
        ipEditText=(EditText)view.findViewById(R.id.cloudletIPEditTextView);
        resetIPButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = String.valueOf(ipEditText.getText());
                if(isIpAddress(input)) {
                    ipTextView.setText(input);
                    Const.CLOUD_GABRIEL_IP= input;
                } else {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Invalid")
                            .setMessage("Please Enter a Valid IP Address")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        runDemoButton=(Button)view.findViewById(R.id.cloudletRunDemoButton);
        runDemoButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Const.GABRIEL_IP = Const.CLOUD_GABRIEL_IP;
                Intent intent = new Intent(getContext(), GabrielClientActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "initializing demo", Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public boolean isIpAddress(String ipAddress) {
        if (ipAddress.matches(IPV4Pattern) || ipAddress.matches(IPV6Pattern)) {
            return true;
        }
        return false;
    }

}
