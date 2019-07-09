package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a pH sensor
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoPHSensor extends FlexyDemoFlexy
{

   /**
    * The lowest value for pH
    */
   private final double phLow;

   /**
    * The highest value for pH
    */
   private final double phHigh;

   /**
    * The ideal value for pH
    */
   private final double phIdeal;

   /**
    * Basic <code>FlexyDemoPHSensor</code> constructor. Create and initialize a settling tank with simulated data for
    * fill.
    *
    * @param name    name of this <code>FlexyDemoPHSensor</code>
    * @param phLow   lower bound for pH value
    * @param phHigh  upper bound for pH value
    * @param phIdeal ideal value for pH
    */
   public FlexyDemoPHSensor( String name, double phLow, double phHigh, double phIdeal )
   {
      super( name );
      this.phLow = phLow;
      this.phHigh = phHigh;
      this.phIdeal = phIdeal;
   }

   /**
    * Method to handle creation and default value of applicable tags
    */
   protected void initTags()
   {
      double midPH = 7.0;
      setTag( "PH", new Double( midPH ) );
   }

   /**
    * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
    */
   protected void runCycleUpdate()
   {
      setTag( "PH", new Double( FlexyDemo.randomDoubleHighWeight( phLow, phHigh, phIdeal ) ) );
   }

}
