package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple industrial lift station with simulated data.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoLiftStation extends FlexyDemoFlexy {

    /**
     * The lower bound for simulated flow values
     */
    private final int flowLowGPM;

    /**
     * The upper bound for simulated flow values
     */
    private final int flowHighGPM;

    /**
     * The ideal value for simulated flow values
     */
    private final int flowIdealGPM;


    /**
     * Basic <code>FlexyDemoLiftStation</code> constructor. Create and initialize a basic industrial lift station with
     * simulated data.
     *
     * @param name name of this <code>FlexyDemoLiftStation</code>
     * @param flowLowGPM lower bound for flow value
     * @param flowHighGPM upper bound for flow value
     * @param flowIdealGPM ideal value for flow
     * @param initPowerStatus initial power status at device creation
     */
    public FlexyDemoLiftStation( String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM, int initPowerStatus ) {
        super( name );
        this.flowLowGPM = flowLowGPM;
        this.flowHighGPM = flowHighGPM;
        this.flowIdealGPM = flowIdealGPM;
        this.initPowerStatus = initPowerStatus;
    }

    /**
     * Method to handle creation and default value of applicable tags
     */
    protected void initTags() {
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
                        new Integer( FlexyDemo.randomIntLowWeight( flowLowGPM, flowHighGPM,
                                flowIdealGPM ) ) );
            } else {
                setTag( "FLOW", new Integer( 0 ) );
            }
        } catch ( EWException e ) {
            System.out.println("[FlexyDemo] An error occurred while updating LiftStation simulated data.");
        }
    }

}
