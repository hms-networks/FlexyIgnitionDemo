package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a settling tank with variable drain
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSettlingDrainTank extends FlexyDemoFlexy
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
    * The percentage that drain is open
    */
   private final int drainOpenPerc;

   /**
    * Basic <code>FlexyDemoSettlingDrainTank</code> constructor. Create and initialize a settling tank with drain with
    * simulated data for fill.
    *
    * @param name          name of this <code>FlexyDemoSettlingDrainTank</code>
    * @param fillLowPerc   lower bound for fill value
    * @param fillHighPerc  upper bound for fill value
    * @param fillIdealPerc ideal value for fill
    * @param drainOpenPerc percentage that wall drain is lowered
    */
   public FlexyDemoSettlingDrainTank( String name, int fillLowPerc, int fillHighPerc, int fillIdealPerc,
                                      int drainOpenPerc )
   {
      super( name );
      this.fillLowPerc = fillLowPerc;
      this.fillHighPerc = fillHighPerc;
      this.fillIdealPerc = fillIdealPerc;
      this.drainOpenPerc = drainOpenPerc;
   }

   /**
    * Method to handle creation and default value of applicable tags
    */
   protected void initTagConfigs()
   {
      addTagConfig( new FlexyDemoTagConfig( getTagFullName( "DRAINOPENPERC" ), FlexyDemoTagConfig.TYPE_INTEGER ) );
      addTagConfig( new FlexyDemoTagConfig( getTagFullName( "FILL" ), FlexyDemoTagConfig.TYPE_INTEGER ) );
   }

   /**
    * Method to set tags to default values, if necessary
    */
   protected void tagDefaults()
   {
      FlexyDemoTagManager.setTagAsInt( getTagFullName( "DRAINOPENPERC" ), drainOpenPerc );
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
