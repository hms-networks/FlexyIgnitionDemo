package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple industrial pump with simulated data for pump flow.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoPump extends FlexyDemoFlexy {

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
     * Basic <code>FlexyDemoPump</code> constructor. Create and initialize a basic industrial pump with simulated values
     * for pump flow.
     *
     * @param name name of this <code>FlexyDemoPump</code>
     * @param flowLowGPM lower bound for flow value
     * @param flowHighGPM upper bound for flow value
     * @param flowIdealGPM ideal value for flow
     */
    public FlexyDemoPump( String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM ) {
        super( name );
        this.flowLowGPM = flowLowGPM;
        this.flowHighGPM = flowHighGPM;
        this.flowIdealGPM = flowIdealGPM;
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
            } else {
                setTag( "FLOW", new Integer( PWR_OFF ) );
            }
        } catch ( EWException e ) {
            System.out.println("[FlexyDemo] An error occurred while updating Pump simulated data.");
        }
    }

}
