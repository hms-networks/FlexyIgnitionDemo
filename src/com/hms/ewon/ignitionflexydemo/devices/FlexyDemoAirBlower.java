package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of an air blower
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoAirBlower extends FlexyDemoFlexy
{

   /**
    * The lower bound for simulated air movement rate (in feet^3/cubic feet)
    */
   private final int airRateLowFT3;

   /**
    * The upper bound for simulated air movement rate (in feet^3/cubic feet)
    */
   private final int airRateHighFT3;

   /**
    * The ideal value for simulated air movement rate (in feet^3/cubic feet)
    */
   private final int airRateIdealFT3;


   /**
    * Basic <code>FlexyDemoAirBlower</code> constructor. Create and initialize an air blower with simulated data.
    *
    * @param name            name of this <code>FlexyDemoAirBlower</code>
    * @param airRateLowFT3   lower bound for air flow rate value
    * @param airRateHighFT3  upper bound for air flow rate value
    * @param airRateIdealFT3 ideal value for air flow rate
    * @param initPowerStatus initial power status at device creation
    */
   public FlexyDemoAirBlower( String name, int airRateLowFT3, int airRateHighFT3, int airRateIdealFT3,
                              boolean initPowerStatus )
   {
      super( name );
      this.airRateLowFT3 = airRateLowFT3;
      this.airRateHighFT3 = airRateHighFT3;
      this.airRateIdealFT3 = airRateIdealFT3;
      this.initPowerStatus = initPowerStatus;
   }

   /**
    * Method to handle creation and default value of applicable tags
    */
   protected void initTagConfigs()
   {
      addTagConfig( new FlexyDemoTagConfig( getTagFullName( "PWR" ), FlexyDemoTagConfig.TYPE_BOOLEAN ) );
      addTagConfig( new FlexyDemoTagConfig( getTagFullName( "RATE" ), FlexyDemoTagConfig.TYPE_INTEGER ) );
   }

   /**
    * Method to set tags to default values, if necessary
    */
   protected void tagDefaults()
   {
      FlexyDemoTagManager.setTagAsBoolean( getTagFullName( "PWR" ), initPowerStatus );
   }

   /**
    * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
    */
   protected void runCycleUpdate() throws EWException
   {
      int newRate = 0;
      if ( FlexyDemoTagManager.getTagAsBoolean( getTagFullName( "PWR" ) ) ) {
         newRate = FlexyDemo.randomIntMidWeight( airRateLowFT3, airRateHighFT3, airRateIdealFT3 );
      }
      FlexyDemoTagManager.setTagAsInt( getTagFullName( "RATE" ), newRate );
   }

}
