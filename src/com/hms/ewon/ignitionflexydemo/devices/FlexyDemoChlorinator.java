package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a chlorinator with simulated data
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoChlorinator extends FlexyDemoFlexy
{

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
    * True/False is drain is open
    */
   private final boolean drainOpen;

   /**
    * Basic <code>FlexyDemoChlorinator</code> constructor. Create and initialize a settling tank with simulated data for
    * fill.
    *
    * @param name          name of this <code>FlexyDemoChlorinator</code>
    * @param fillLowPerc   lower bound for fill value
    * @param fillHighPerc  upper bound for fill value
    * @param fillIdealPerc ideal fill value
    * @param drainOpen     true if drain is open
    */
   public FlexyDemoChlorinator( String name, int fillLowPerc, int fillHighPerc, int fillIdealPerc, boolean drainOpen )
   {
      super( name );
      this.fillLowPerc = fillLowPerc;
      this.fillHighPerc = fillHighPerc;
      this.fillIdealPerc = fillIdealPerc;
      this.drainOpen = drainOpen;
   }

   /**
    * Method to handle creation and default value of applicable tags
    */
   protected void initTagConfigs()
   {
      addTagConfig( new FlexyDemoTagConfig( getTagFullName( "DRAINOPEN" ), FlexyDemoTagConfig.TYPE_BOOLEAN ) );
      addTagConfig( new FlexyDemoTagConfig( getTagFullName( "FILL" ), FlexyDemoTagConfig.TYPE_INTEGER ) );
   }

   /**
    * Method to set tags to default values, if necessary
    */
   protected void tagDefaults()
   {
      FlexyDemoTagManager.setTagAsBoolean( getTagFullName( "DRAINOPEN" ), drainOpen );
   }

   /**
    * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
    */
   protected void runCycleUpdate()
   {
      int newFill = FlexyDemo.randomIntMidWeight( fillLowPerc, fillHighPerc, fillIdealPerc );
      FlexyDemoTagManager.setTagAsInt( getTagFullName( "FILL" ), newFill );
   }

}
