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
     * @param initPowerStatus initial power status at device creation
     */
    public FlexyDemoAirCompressor( String name, int pressureLowPSI, int pressureHighPSI, int pressureIdealPSI,
                                   int initPowerStatus ) {
        super( name );
        this.pressureLowPSI = pressureLowPSI;
        this.pressureHighPSI = pressureHighPSI;
        this.pressureIdealPSI = pressureIdealPSI;
        this.initPowerStatus = initPowerStatus;
    }

    /**
     * Method to handle creation and default value of applicable tags
     */
    protected void initTags() {
        setTag( "PRESSURE", new Integer( PWR_ON ) );
        setTag( "PWR", new Integer( initPowerStatus ) );
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        try {
            if ( getTagValueAsLong( "PWR" ) == PWR_ON ) {
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
