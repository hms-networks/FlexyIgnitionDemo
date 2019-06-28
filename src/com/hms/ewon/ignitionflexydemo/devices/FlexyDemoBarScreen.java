package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple metal bar screen.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoBarScreen extends FlexyDemoFlexy {

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
     * Basic <code>FlexyDemoBarScreen</code> constructor. Create and initialize a simple metal bar screen with simulated
     * data for flow.
     *
     * @param name name of this <code>FlexyDemoBarScreen</code>
     */
    public FlexyDemoBarScreen( String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM ) {
        super( name );
        this.flowLowGPM = flowLowGPM;
        this.flowHighGPM = flowHighGPM;
        this.flowIdealGPM = flowIdealGPM;
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        setTag( "IN-FLOW", new Integer( FlexyDemo.randomIntMidWeight( flowLowGPM, flowHighGPM, flowIdealGPM ) ) );

        // Parameters: low, high, ideal
        int randomOutOffset = FlexyDemo.randomIntLowWeight( 0, 3, 1 );
        setTag( "OUT-FLOW",
                new Integer( FlexyDemo.randomIntMidWeight( flowLowGPM - randomOutOffset, flowHighGPM - randomOutOffset,
                        flowIdealGPM - randomOutOffset ) ) );
    }

}
