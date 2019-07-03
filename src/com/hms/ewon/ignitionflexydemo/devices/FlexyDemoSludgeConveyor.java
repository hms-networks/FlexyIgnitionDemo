package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a conveyor belt for sludge.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSludgeConveyor extends FlexyDemoFlexy {

    /**
     * The lower bound for simulated motor rpm values
     */
    private final int motorLowRPM;

    /**
     * The upper bound for simulated motor rpm values
     */
    private final int motorHighRPM;

    /**
     * The ideal value for simulated motor rpm values
     */
    private final int motorIdealRPM;


    /**
     * Basic <code>FlexyDemoSludgeConveyor</code> constructor. Create and initialize a basic conveyor belt for sludge
     * with simulated data.
     *
     * @param name name of this <code>FlexyDemoSludgeConveyor</code>
     * @param motorLowRPM lower bound for motor rpm value
     * @param motorHighRPM upper bound for motor rpm value
     * @param motorIdealRPM ideal value for motor rpm
     * @param initPowerStatus initial power status at device creation
     */
    public FlexyDemoSludgeConveyor( String name, int motorLowRPM, int motorHighRPM, int motorIdealRPM,
                                    int initPowerStatus ) {
        super( name );
        this.motorLowRPM = motorLowRPM;
        this.motorHighRPM = motorHighRPM;
        this.motorIdealRPM = motorIdealRPM;
        this.initPowerStatus = initPowerStatus;
    }

    /**
     * Method to handle creation and default value of applicable tags
     */
    protected void initTags() {
        setTag( "MOTOR1-RPM", new Integer( PWR_ON ) );
        setTag( "PWR", new Integer( initPowerStatus ) );
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        try {
            if ( getTag( "PWR" ) == PWR_ON ) {
                setTag( "MOTOR1-RPM",
                        new Integer( FlexyDemo.randomIntHighWeight( motorLowRPM, motorHighRPM, motorIdealRPM ) ) );
            } else {
                setTag( "MOTOR1-RPM", new Integer( PWR_OFF ) );
            }
        } catch ( EWException e ) {
            System.out.println("[FlexyDemo] An error occurred while updating SludgeConveyor simulated data.");
        }
    }

}
