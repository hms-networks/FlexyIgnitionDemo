package com.hms.ewon.ignitionflexydemo;

import java.io.*;

/**
 * Abstract class representing an entire system of simulated devices/data on HMS Industrial Networks Flexy.
 *
 * @see FlexyDemo
 */
public abstract class FlexyDemoScenario {

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
    FlexyDemoScenario( String name ) {
        this.name = name;
        this.devices = new ArrayListFlexy();
        genScenario();
    }

    /**
     * Returns the name of this <code>FlexyDemoScenario</code>
     *
     * @return the name of this demo scenario
     */
    public String getName() {
        return name;
    }

    /**
     * Starts this demo scenario and marks it as active
     */
    synchronized void start() {
        if ( isActive ) return;

        writeVarLst();

        System.out.println( "FlexyDemo is starting " + name + " -- Please Wait!" );
        startScenario();
        isActive = true;
    }

    /**
     * Stops this demo scenario and removes its active mark
     */
    synchronized void stop() {
        if ( !isActive ) return;

        System.out.println( "FlexyDemo is stopping " + name + " -- Please Wait!" );
        isActive = false;
        stopScenario();
    }

    /**
     * Abstract method for implementation specific demo scenario starting procedures
     */
    private void startScenario() {
        for ( int i = 0; i < devices.size(); i++ ) {
            devices.get( i ).startRunning();
        }
    }

    /**
     * Abstract method for implementation specific demo scenario stopping procedures
     */
    private void stopScenario() {
        for ( int i = 0; i < devices.size(); i++ ) {
            devices.get( i ).stopRunning();
        }
    }

    /**
     * Abstract method for implementation specific demo scenario creation procedures
     */
    protected abstract void genScenario();

    /**
     * Return URL for This Scenario's Var_Lst.csv
     *
     * @return URL to Var_Lst for This Scenario
     */
    protected abstract InputStream getVarLstCSV();

    /**
     * Write scenario varLstCSV string to the eWON internal s2.csv
     */
    private void writeVarLst() {

        try {
            FlexyDemo.copyVarLst( getVarLstCSV() );
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println( "FlexyDemo encountered an error while configuring tags. Application will now " +
                    "terminate to prevent undefined behavior. ID: FDS01" );
            System.exit( -1 );
        }
    }

}