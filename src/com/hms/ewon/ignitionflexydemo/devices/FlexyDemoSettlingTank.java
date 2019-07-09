package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;

/**
 * FlexyDemoFlexy implementation of a settling tank
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSettlingTank extends FlexyDemoTank
{

   /**
    * Basic <code>FlexyDemoTank</code> constructor. Create and initialize a settling tank with simulated data for fill.
    *
    * @param name          name of this <code>FlexyDemoTank</code>
    * @param fillLowPerc   lower bound for fill value
    * @param fillHighPerc  upper bound for fill value
    * @param fillIdealPerc ideal value for fill
    */
   public FlexyDemoSettlingTank( String name, int fillLowPerc, int fillHighPerc, int fillIdealPerc )
   {
      super( name, fillLowPerc, fillHighPerc, fillIdealPerc );
   }

}
