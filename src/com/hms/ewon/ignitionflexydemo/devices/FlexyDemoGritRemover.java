package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a grit remover.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoGritRemover extends FlexyDemoFlexy {

    /**
     * The lowest value for simulated flow
     */
    private final int flowLowGPM;

    /**
     * The highest value for simulated flow
     */
    private final int flowHighGPM;

    /**
     * The ideal value for simulated flow
     */
    private final int flowIdealGPM;

    /**
     * The lowest value for simulated motor rpm
     */
    private final int motorLowRPM;

    /**
     * The highest value for simulated motor rpm
     */
    private final int motorHighRPM;

    /**
     * The ideal value for simulated motor rpm
     */
    private final int motorIdealRPM;

    /**
     * Basic <code>FlexyDemoGritRemover</code> constructor. Create and initialize a grit remover with simulated
     * data for flow.
     *
     * @param name name of this <code>FlexyDemoGritRemover</code>
     * @param flowLowGPM lower bound for flow value
     * @param flowHighGPM upper bound for flow value
     * @param flowIdealGPM ideal value for flow
     * @param motorLowRPM lower bound for motor rpm value
     * @param motorHighRPM upper bound for motor rpm value
     * @param motorIdealRPM ideal value for motor rpm
     * @param initPowerStatus initial power status at device creation
     */
    public FlexyDemoGritRemover( String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM, int motorLowRPM,
                                 int motorHighRPM, int motorIdealRPM, int initPowerStatus ) {
        super( name );
        this.flowLowGPM = flowLowGPM;
        this.flowHighGPM = flowHighGPM;
        this.flowIdealGPM = flowIdealGPM;
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
        setTag( "FLOW", new Integer( PWR_ON ) );
        setTag( "PWR", new Integer( initPowerStatus ) );
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        try {
            if ( getTag( "PWR" ) == PWR_ON ) {
                setTag( "FLOW",
                        new Integer( FlexyDemo.randomIntMidWeight( flowLowGPM, flowHighGPM,
                                flowIdealGPM ) ) );
                setTag( "MOTOR1-RPM", new Integer( FlexyDemo.randomIntHighWeight( motorLowRPM, motorHighRPM, motorIdealRPM ) ) );
            } else {
                setTag( "FLOW", new Integer( PWR_OFF ) );
                setTag( "MOTOR1-RPM", new Integer( PWR_OFF ) );
            }
        } catch ( EWException e ) {
            System.out.println("[FlexyDemo] An error occurred while updating GritRemover simulated data.");
        }
    }

}
