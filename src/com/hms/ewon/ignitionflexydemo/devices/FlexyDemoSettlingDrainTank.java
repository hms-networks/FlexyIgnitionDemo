package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a settling tank with variable drain
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSettlingDrainTank extends FlexyDemoFlexy {

    /**
     * The lowest value for simulated fill
     */
    private final int fillLowPerc;

    /**
     * The highest value for simulated fill
     */
    private final int fillHighPerc;

    /**
     * The ideal value for simulated fill
     */
    private final int fillIdealPerc;

    /**
     * Basic <code>FlexyDemoSettlingDrainTank</code> constructor. Create and initialize a settling tank with drain with
     * simulated data for fill.
     *
     * @param name name of this <code>FlexyDemoSettlingDrainTank</code>
     * @param fillLowPerc lower bound for fill value
     * @param fillHighPerc upper bound for fill value
     * @param fillIdealPerc ideal value for fill
     * @param drainOpenPerc percentage that wall drain is lowered
     */
    public FlexyDemoSettlingDrainTank( String name, int fillLowPerc, int fillHighPerc, int fillIdealPerc,
                                       int drainOpenPerc ) {
        super( name );
        this.fillLowPerc = fillLowPerc;
        this.fillHighPerc = fillHighPerc;
        this.fillIdealPerc = fillIdealPerc;

        setTag( "DRAINOPENPERC", new Integer( drainOpenPerc ) );
    }

    /**
     * Method to handle creation and default value of applicable tags
     */
    protected void initTags() {
        setTag( "FILL", new Integer( PWR_ON ) );
    }

    /**
     * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
     */
    protected void runCycleUpdate() {
        setTag( "FILL", new Integer( FlexyDemo.randomIntMidWeight( fillLowPerc, fillHighPerc, fillIdealPerc ) ) );
    }

}
