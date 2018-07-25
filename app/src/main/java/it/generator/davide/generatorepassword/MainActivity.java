package it.generator.davide.generatorepassword;

import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void genera(View v){
        EditText e=findViewById(R.id.seed);

        String hash="";
        try {
            MessageDigest digest= MessageDigest.getInstance("SHA-256");
            digest.update(e.getText().toString().getBytes());

            hash = bytesToHexString(digest.digest());


        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }


        int n;
        String pw="";
        for(int i=0; i<8;i++){
            n=new Random().nextInt(64);
            pw+=hash.charAt(n);
        }

        ((TextView)findViewById(R.id.textView2)).setText(pw);
    }
    private static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
