package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

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
                              int initPowerStatus )
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
   protected void initTags()
   {
      setTag( "RATE", new Integer( PWR_ON ) );
      setTag( "PWR", new Integer( initPowerStatus ) );
   }

   /**
    * Handle tag and data simulation updates. This method is called every {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle.
    */
   protected void runCycleUpdate()
   {
      try {
         if ( getTagValueAsLong( "PWR" ) == PWR_ON ) {
            setTag( "RATE",
                    new Integer( FlexyDemo.randomIntMidWeight( airRateLowFT3, airRateHighFT3, airRateIdealFT3 ) ) );
         }
         else {
            setTag( "RATE", new Integer( PWR_OFF ) );
         }
      }
      catch ( EWException e ) {
         System.out.println( "[FlexyDemo] An error occurred while updating AirBlower simulated data." );
      }
   }

}
