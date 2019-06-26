package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple metal bar screen.
 *
 * @author Alexander Hawk
 * @version 06/25/2019
 * @see FlexyDemoFlexy
 */
public class FlexyDemoBarScreen extends FlexyDemoFlexy {

    /**
     * The lowest value for simulated flow
     */
    private final int flowLow;

    /**
     * The highest value for simulated flow
     */
    private final int flowHigh;

    /**
     * The ideal value for simulated flow
     */
    private final int flowIdeal;

    /**
     * Basic <code>FlexyDemoBarScreen</code> constructor. Create and initialize a simple metal bar screen with simulated
     * data for flow.
     *
     * @param name name of this <code>FlexyDemoBarScreen</code>
     */
    public FlexyDemoBarScreen( String name, int flowLow, int flowHigh, int flowIdeal ) {
        super( name );
        this.flowLow = flowLow;
        this.flowHigh = flowHigh;
        this.flowIdeal = flowIdeal;
    }

    /**
     * Handle tag and data simulation updates. This method is called every 1 second cycle.
     */
    protected void runCycleUpdate() {
        setTag( "IN-FLOW", new Integer( FlexyDemo.randomIntMidWeight( flowLow, flowHigh, flowIdeal ) ) );
        int randomOutOffset = FlexyDemo.randomIntLowWeight( 0, 3, 1 );
        setTag( "OUT-FLOW",
                new Integer( FlexyDemo.randomIntMidWeight( flowLow - randomOutOffset, flowHigh - randomOutOffset,
                        flowIdeal - randomOutOffset ) ) );
    }

}
