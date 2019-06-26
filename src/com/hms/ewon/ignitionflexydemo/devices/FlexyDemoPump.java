package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple industrial pump with simulated data for pump pressure and power
 * consumption.
 *
 * @author Alexander Hawk
 * @version 06/21/2019
 * @see FlexyDemoFlexy
 */
public class FlexyDemoPump extends FlexyDemoFlexy {

    /**
     * The lower bound for simulated pressure values
     */
    private final int pressureLow;

    /**
     * The upper bound for simulated pressure values
     */
    private final int pressureHigh;

    /**
     * The ideal value for simulated pressure values
     */
    private final int pressureIdeal;

    /**
     * The lower bound for simulated power consumption values
     */
    private final int powerLow;

    /**
     * The upper bound for simulated power consumption values
     */
    private final int powerHigh;

    /**
     * The ideal value for simulated power consumption values
     */
    private final int powerIdeal;


    /**
     * Basic <code>FlexyDemoPump</code> constructor. Create and initialize a basic industrial pump with simulated
     * values for pressure and power consumption.
     *
     * @param name name of this <code>FlexyDemoPump</code>
     */
    public FlexyDemoPump( String name, int pressureLow, int pressureHigh, int pressureIdeal, int powerLow, int powerHigh, int powerIdeal ) {
        super( name );
        this.pressureLow = pressureLow;
        this.pressureHigh = pressureHigh;
        this.pressureIdeal = pressureIdeal;
        this.powerLow = powerLow;
        this.powerHigh = powerHigh;
        this.powerIdeal = powerIdeal;
    }

    /**
     * Handle tag and data simulation updates. This method is called every 1 second cycle.
     */
    protected void runCycleUpdate() {
        setTag( "PRESSURE", new Integer( FlexyDemo.randomIntLowWeight( pressureLow, pressureHigh, pressureIdeal ) ) );
        setTag( "POWER", new Integer( FlexyDemo.randomIntHighWeight( powerLow, powerHigh, powerIdeal ) ) );
    }

}
