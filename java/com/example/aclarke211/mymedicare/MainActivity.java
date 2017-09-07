//==================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//==================================================================================================================================\\
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//==================================================================================================================================\\
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

//==================================================================================================================================\\
    public void onButtonClick(View v){

        //getting the id of the view that has been clicked
        switch (v.getId()) {
            //acts like an 'if' statement, code in here in run when the corresponding ID is clicked
            //if login button is pressed
            case R.id.btnLogin_mainActivity:
                //open the login screen
                startActivity(new Intent(this, Login.class));
                break;

            //if register button is pressed
            case R.id.btnRegister_mainActivity:
                //go to the register screen
                startActivity(new Intent(this, Register1.class));
                break;

        }
    }

//==================================================================================================================================\\
}
//==================================================================================================================================\\












