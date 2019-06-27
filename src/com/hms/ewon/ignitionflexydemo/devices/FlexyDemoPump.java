package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple industrial pump with simulated data for pump pressure and power
 * consumption.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoPump extends FlexyDemoFlexy {

    /**
     * The lower bound for simulated pressure values
     */
    private final int pressureLowPSI;

    /**
     * The upper bound for simulated pressure values
     */
    private final int pressureHighPSI;

    /**
     * The ideal value for simulated pressure values
     */
    private final int pressureIdealPSI;

    /**
     * The lower bound for simulated power consumption values
     */
    private final int powerLowVolt;

    /**
     * The upper bound for simulated power consumption values
     */
    private final int powerHighVolt;

    /**
     * The ideal value for simulated power consumption values
     */
    private final int powerIdealVolt;


    /**
     * Basic <code>FlexyDemoPump</code> constructor. Create and initialize a basic industrial pump with simulated
     * values for pressure and power consumption.
     *
     * @param name name of this <code>FlexyDemoPump</code>
     */
    public FlexyDemoPump( String name, int pressureLow, int pressureHigh, int pressureIdeal, int powerLow, int powerHigh, int powerIdeal ) {
        super( name );
        this.pressureLowPSI = pressureLow;
        this.pressureHighPSI = pressureHigh;
        this.pressureIdealPSI = pressureIdeal;
        this.powerLowVolt = powerLow;
        this.powerHighVolt = powerHigh;
        this.powerIdealVolt = powerIdeal;
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        setTag( "PRESSURE", new Integer( FlexyDemo.randomIntLowWeight( pressureLowPSI, pressureHighPSI, pressureIdealPSI ) ) );
        setTag( "POWER", new Integer( FlexyDemo.randomIntHighWeight( powerLowVolt, powerHighVolt, powerIdealVolt ) ) );
    }

}
