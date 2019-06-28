package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple industrial tank with simulated data for fill and pressure.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoTank extends FlexyDemoFlexy {

    /**
     * Lowest Simulated Value for Intake Side Pressure
     */
    private final int inPressureLowPSI;

    /**
     * Highest Simulated Value for Intake Side Pressure
     */
    private final int inPressureHighPSI;

    /**
     * Ideal Simulated Value for Intake Side Pressure
     */
    private final int inPressureIdealPSI;

    /**
     * Lowest Simulated Value for Output Side Pressure
     */
    private final int outPressureLowPSI;

    /**
     * Highest Simulated Value for Output Side Pressure
     */
    private final int outPressureHighPSI;

    /**
     * Ideal Simulated Value for Output Side Pressure
     */
    private final int outPressureIdealPSI;

    /**
     * Lowest Simulated Value for Tank Fill
     */
    private final int fillLow;

    /**
     * Highest Simulated Value for Tank Fill
     */
    private final int fillHigh;

    /**
     * Ideal Simulated Value for Tank Fill
     */
    private final int fillIdeal;

    /**
     * Time it Takes Tank to Process
     */
    private final int tankTimeSecs;

    /**
     * Internal Integer for Time in Tank Count
     */
    private int timeInTankSecs = 0;

    /**
     * Basic <code>FlexyDemoTank</code> constructor. Create and initialize a basic industrial tank with simulated
     * values for fill and pressure.
     *
     * @param name name of this <code>FlexyDemoTank</code>
     */
    public FlexyDemoTank( String name, int inPressureLow, int inPressureHigh, int inPressureIdeal, int outPressureLow, int outPressureHigh, int outPressureIdeal, int fillLow, int fillHigh, int fillIdeal, int tankTime ) {
        super( name );
        this.inPressureLowPSI = inPressureLow;
        this.inPressureHighPSI = inPressureHigh;
        this.inPressureIdealPSI = inPressureIdeal;
        this.outPressureLowPSI = outPressureLow;
        this.outPressureHighPSI = outPressureHigh;
        this.outPressureIdealPSI = outPressureIdeal;
        this.fillLow = fillLow;
        this.fillHigh = fillHigh;
        this.fillIdeal = fillIdeal;
        this.tankTimeSecs = tankTime;
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        setTag( "IN-PRESSURE", new Integer( FlexyDemo.randomIntLowWeight( inPressureLowPSI + timeInTankSecs, inPressureHighPSI + timeInTankSecs, inPressureIdealPSI + timeInTankSecs ) ) );
        setTag( "OUT-PRESSURE", new Integer( FlexyDemo.randomIntMidWeight( outPressureLowPSI - timeInTankSecs, outPressureHighPSI - timeInTankSecs, outPressureIdealPSI + timeInTankSecs ) ) );

        // Simulate change in tank fill value -- if and only if tank is not already "processing"
        // Tank is "processing" when timeInTankSecs > 0
        if ( timeInTankSecs == 0 )
            setTag( "FILL", new Integer( FlexyDemo.randomIntHighWeight( fillLow, fillHigh, fillIdeal ) ) );
        setTag( "PROCESSINGTIME", new Integer( timeInTankSecs ) );

        timeInTankSecs++;
        if ( timeInTankSecs > tankTimeSecs ) timeInTankSecs = 0;
    }
}
