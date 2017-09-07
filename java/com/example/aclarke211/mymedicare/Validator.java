//=========================================================================================================================================\\
package com.example.aclarke211.mymedicare;
//=========================================================================================================================================\\


//=========================================================================================================================================\\
public class Validator {

    //variables
    public String temperature, lowBloodPressure, highBloodPressure, heartRate;
    public int intLowBloodPressure, intHighBloodPressure, intHeartRate;
    public float fltTemperature;

//=========================================================================================================================================\\
    //for users with an age less than 49
    public String checkTemperature(String receivedTemperature) {

        //the string
        this.temperature = receivedTemperature;
        fltTemperature = Float.parseFloat(temperature);
        String message = "No Risk";

        if (fltTemperature <= 36.0 || fltTemperature >= 38.0) {

            message = "High Risk";

        } else if(fltTemperature <= 36.4 || fltTemperature >= 37.6) {

            message = "Low Risk";

        }

        return message;

    }

//-----------------------------------------------------------------------
    //for users with an age 50 or over
    public String checkOldTemperature(String receivedTemperature) {

        this.temperature = receivedTemperature;
        fltTemperature = Float.parseFloat(temperature);
        String message = "No Risk";

        if (fltTemperature <= 35.0 || fltTemperature >= 37.5) {

            message = "High Risk";

        } else if(fltTemperature <= 35.8 || fltTemperature >= 36.9) {

            message = "Low Risk";

        }

        return message;

    }

//=========================================================================================================================================\\
    public String checkLowBloodPressure(String receivedLowBloodPressure) {

        this.lowBloodPressure = receivedLowBloodPressure;
        intLowBloodPressure = Integer.parseInt(receivedLowBloodPressure);
        String message = "No Risk";

        if(intLowBloodPressure <= 71 || intLowBloodPressure >= 91) {

            message = "High Risk";


        } else if (intLowBloodPressure <= 76 || intLowBloodPressure >= 84) {

            message = "Low Risk";

        }

        return message;
    }

//-----------------------------------------------------------------------
    public String checkOldLowBloodPressure(String receivedLowBloodPressure) {

        this.lowBloodPressure = receivedLowBloodPressure;
        intLowBloodPressure = Integer.parseInt(receivedLowBloodPressure);
        String message = "No Risk";

        if(intLowBloodPressure <= 74 || intLowBloodPressure >= 95) {

            message = "High Risk";


        } else if (intLowBloodPressure <= 81 || intLowBloodPressure >= 89) {

            message = "Low Risk";

        }

        return message;
    }

    //=========================================================================================================================================\\
    public String checkHighBloodPressure(String receivedHighBloodPressure) {

        this.highBloodPressure = receivedHighBloodPressure;
        intHighBloodPressure = Integer.parseInt(receivedHighBloodPressure);
        String message = "No Risk";

        if(intHighBloodPressure <= 102 || intHighBloodPressure >= 140) {

            message = "High Risk";


        } else if (intHighBloodPressure <= 109 || intHighBloodPressure >= 133) {

            message = "Low Risk";

        }

        return message;
    }

    //-----------------------------------------------------------------------
    public String checkOldHighBloodPressure(String receivedHighBloodPressure) {

        this.highBloodPressure = receivedHighBloodPressure;
        intHighBloodPressure = Integer.parseInt(receivedHighBloodPressure);
        String message = "No Risk";

        if(intHighBloodPressure <= 110 || intHighBloodPressure >= 148) {

            message = "High Risk";


        } else if (intHighBloodPressure <= 116 || intHighBloodPressure >= 142) {

            message = "Low Risk";

        }

        return message;
    }

    //=========================================================================================================================================\\
    public String checkHeartRate(String receivedHeartRate) {

        this.heartRate = receivedHeartRate;
        intHeartRate = Integer.parseInt(receivedHeartRate);
        String message = "No Risk";

        if(intHeartRate <= 30 || intHeartRate >= 180) {

            message = "High Risk";


        } else if (intHeartRate <= 50 || intHeartRate >= 140) {

            message = "Low Risk";

        }

        return message;
    }

    //-----------------------------------------------------------------------
    public String checkOldHeartRate(String receivedHeartRate) {

        this.heartRate = receivedHeartRate;
        intHeartRate = Integer.parseInt(receivedHeartRate);
        String message = "No Risk";

        if(intHeartRate <= 50 || intHeartRate >= 160) {

            message = "High Risk";


        } else if (intHeartRate <= 70 || intHeartRate >= 120) {

            message = "Low Risk";

        }

        return message;
    }





//=========================================================================================================================================\\
}
//=========================================================================================================================================\\