package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of an air compressor
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoAirCompressor extends FlexyDemoFlexy {

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
     * Basic <code>FlexyDemoAirCompressor</code> constructor. Create and initialize a basic air compressor with
     * simulated data.
     *
     * @param name name of this <code>FlexyDemoAirCompressor</code>
     * @param pressureLowPSI lower bound for pressure value
     * @param pressureHighPSI upper bound for pressure value
     * @param pressureIdealPSI ideal value for pressure
     */
    public FlexyDemoAirCompressor( String name, int pressureLowPSI, int pressureHighPSI, int pressureIdealPSI ) {
        super( name );
        this.pressureLowPSI = pressureLowPSI;
        this.pressureHighPSI = pressureHighPSI;
        this.pressureIdealPSI = pressureIdealPSI;
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        try {
            if ( getTag( "PWR" ) == PWR_ON ) {
                setTag( "PRESSURE",
                        new Integer( FlexyDemo.randomIntHighWeight( pressureLowPSI, pressureHighPSI, pressureIdealPSI ) ) );
            } else {
                setTag( "PRESSURE", new Integer( PWR_OFF ) );
            }
        } catch ( EWException e ) {
            System.out.println("[FlexyDemo] An error occurred while updating AirCompressor simulated data.");
        }
    }

}
