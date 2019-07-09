package com.hms.ewon.ignitionflexydemo;

import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoMotor;

/**
 * FlexyDemoScenario Implementation of a Package Sorting Facility
 *
 * @see FlexyDemoScenario
 */
public class FlexyDemoScenarioPackageSorting extends FlexyDemoScenario
{


   /**
    * Construct a new <code>FlexyDemoScenarioPackageSorting</code> with the given name.
    *
    * @param name name of this demo scenario
    */
   FlexyDemoScenarioPackageSorting( String name )
   {
      super( name );
   }

   /**
    * Perform initial generation and setup of this <code>FlexyDemoScenario</code>
    */
   protected void genScenario()
   {
      int rpmLow = 95;
      int rpmHigh = 105;
      int rpmIdeal = 100;
      FlexyDemoMotor motorOne = new FlexyDemoMotor( "ConveyorAMotor3", rpmLow, rpmHigh, rpmIdeal );
      assert devices != null;
      devices.add( motorOne );
   }

}
