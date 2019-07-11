package com.hms.ewon.ignitionflexydemo;

/**
 * Abstract class representing an entire system of simulated devices/data on HMS Industrial Networks Flexy.
 *
 * @see FlexyDemo
 */
public abstract class FlexyDemoScenario
{

   /**
    * Array of Flexys and devices involved in this <code>FlexyDemoScenario</code>
    */
   final ArrayListFlexy devices;

   /**
    * String name of this <code>FlexyDemoScenario</code>
    */
   private final String name;

   /**
    * Flag for tracking active status of this <code>FlexyDemoScenario</code>
    */
   private boolean isActive = false;

   /**
    * Construct a new <code>FlexyDemoScenario</code> with the given name.
    *
    * @param name name of this demo scenario
    */
   FlexyDemoScenario( String name )
   {
      this.name = name;
      this.devices = new ArrayListFlexy();
      genScenario();
   }

   /**
    * Returns the name of this <code>FlexyDemoScenario</code>
    *
    * @return the name of this demo scenario
    */
   public String getName()
   {
      return name;
   }

   /**
    * Starts this demo scenario and marks it as active
    */
   synchronized void start()
   {
      if ( isActive ) return;

      System.out.println( "FlexyDemo is starting " + name + " -- Please Wait!" );
      startScenario();
      isActive = true;
   }

   /**
    * Stops this demo scenario and removes its active mark
    */
   synchronized void stop()
   {
      if ( !isActive ) return;

      System.out.println( "FlexyDemo is stopping " + name + " -- Please Wait!" );
      isActive = false;
      stopScenario();
   }

   public ArrayListFlexy getDevices()
   {
      return devices;
   }

   /**
    * Abstract method for implementation specific demo scenario starting procedures
    */
   private void startScenario()
   {
      for ( int i = 0; i < devices.size(); i++ ) {
         devices.get( i ).startRunning();
      }
   }

   /**
    * Abstract method for implementation specific demo scenario stopping procedures
    */
   private void stopScenario()
   {
      for ( int i = 0; i < devices.size(); i++ ) {
         devices.get( i ).stopRunning();
      }
   }

   /**
    * Abstract method for implementation specific demo scenario creation procedures
    */
   protected abstract void genScenario();
}