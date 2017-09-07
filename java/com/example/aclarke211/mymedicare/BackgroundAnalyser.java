package com.example.aclarke211.mymedicare;

import android.widget.LinearLayout;

//==================================================================================================================================\\
public class BackgroundAnalyser {
    //constructor
    public BackgroundAnalyser() {

    }

//==================================================================================================================================\\
    public void changeBackground(LinearLayout bgLayout, String bgColour) {
        //check the passed string to find which colour to change to
        switch (bgColour) {

            case "Blue":

                //change the background image to an XML file
                bgLayout.setBackgroundResource(R.drawable.blue_background);

                break;

            case "Yellow":

                //change the background image to an XML file
                bgLayout.setBackgroundResource(R.drawable.yellow_background);

                break;

            case "White":

                //change the background image to an XML file
                bgLayout.setBackgroundResource(R.drawable.white_background);

                break;

        }

    }

}
