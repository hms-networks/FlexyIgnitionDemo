package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a simple industrial tank with simulated data for fill and pressure.
 *
 * @author Alexander Hawk
 * @version 06/25/2019
 * @see FlexyDemoFlexy
 */
public class FlexyDemoTank extends FlexyDemoFlexy {

    /**
     * Lowest Simulated Value for Intake Side Pressure
     */
    private final int inPressureLow;

    /**
     * Highest Simulated Value for Intake Side Pressure
     */
    private final int inPressureHigh;

    /**
     * Ideal Simulated Value for Intake Side Pressure
     */
    private final int inPressureIdeal;

    /**
     * Lowest Simulated Value for Output Side Pressure
     */
    private final int outPressureLow;

    /**
     * Highest Simulated Value for Output Side Pressure
     */
    private final int outPressureHigh;

    /**
     * Ideal Simulated Value for Output Side Pressure
     */
    private final int outPressureIdeal;

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
    private final int tankTime;

    /**
     * Internal Integer for Time in Tank Count
     */
    private int timeInTank = 0;

    /**
     * Basic <code>FlexyDemoTank</code> constructor. Create and initialize a basic industrial tank with simulated
     * values for fill and pressure.
     *
     * @param name name of this <code>FlexyDemoTank</code>
     */
    public FlexyDemoTank( String name, int inPressureLow, int inPressureHigh, int inPressureIdeal, int outPressureLow, int outPressureHigh, int outPressureIdeal, int fillLow, int fillHigh, int fillIdeal, int tankTime ) {
        super( name );
        this.inPressureLow = inPressureLow;
        this.inPressureHigh = inPressureHigh;
        this.inPressureIdeal = inPressureIdeal;
        this.outPressureLow = outPressureLow;
        this.outPressureHigh = outPressureHigh;
        this.outPressureIdeal = outPressureIdeal;
        this.fillLow = fillLow;
        this.fillHigh = fillHigh;
        this.fillIdeal = fillIdeal;
        this.tankTime = tankTime;
    }

    /**
     * Handle tag and data simulation updates. This method is called every 1 second cycle.
     */
    protected void runCycleUpdate() {
        setTag( "IN-PRESSURE", new Integer( FlexyDemo.randomIntLowWeight( inPressureLow + timeInTank, inPressureHigh + timeInTank, inPressureIdeal + timeInTank) ) );
        setTag( "OUT-PRESSURE", new Integer( FlexyDemo.randomIntMidWeight( outPressureLow - timeInTank, outPressureHigh - timeInTank, outPressureIdeal + timeInTank ) ) );
        if ( timeInTank < 1 )
            setTag( "FILL", new Integer( FlexyDemo.randomIntHighWeight( fillLow, fillHigh, fillIdeal ) ) );
        setTag( "PROCESSINGTIME", new Integer( timeInTank ) );

        timeInTank++;
        if ( timeInTank > tankTime ) timeInTank = 0;
    }
}
