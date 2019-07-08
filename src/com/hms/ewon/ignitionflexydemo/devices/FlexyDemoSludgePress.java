package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a sludge press
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSludgePress extends FlexyDemoFlexy {

    /**
     * Number of motors associated with a sludge press
     */
    private static int NUM_MOTORS = 10;

    /**
     * The lower bound for simulated air movement rate (in feet^3/cubic feet)
     */
    private final int motorLowRPM;

    /**
     * The upper bound for simulated air movement rate (in feet^3/cubic feet)
     */
    private final int motorHighRPM;

    /**
     * The ideal value for simulated air movement rate (in feet^3/cubic feet)
     */
    private final int motorIdealRPM;


    /**
     * Basic <code>FlexyDemoSludgePress</code> constructor. Create and initialize a basic press to squeeze liquid out
     * of sludge.
     *
     * @param name name of this <code>FlexyDemoSludgePress</code>
     * @param motorLowRPM lower bound for motor rpm value
     * @param motorHighRPM upper bound for motor rpm value
     * @param motorIdealRPM ideal value for motor rpm
     */
    public FlexyDemoSludgePress( String name, int motorLowRPM, int motorHighRPM, int motorIdealRPM ) {
        super( name );
        this.motorLowRPM = motorLowRPM;
        this.motorHighRPM = motorHighRPM;
        this.motorIdealRPM = motorIdealRPM;
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        try {
            for ( int x = 1; x <= NUM_MOTORS; x++ ) {
                if ( getTag( "PWR" ) == PWR_ON ) {
                    setTag( "MOTOR"+x+"-RPM", new Integer( FlexyDemo.randomIntHighWeight( motorLowRPM, motorHighRPM, motorIdealRPM ) ) );
                } else {
                    setTag( "MOTOR"+x+"-RPM", new Integer( PWR_OFF ) );
                }
            }
        } catch ( EWException e ) {
            System.out.println("[FlexyDemo] An error occurred while updating SludgePress simulated data.");
        }
    }

}
