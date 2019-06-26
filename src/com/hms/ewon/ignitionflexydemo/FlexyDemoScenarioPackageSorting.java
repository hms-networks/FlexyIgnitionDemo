package com.hms.ewon.ignitionflexydemo;

import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoMotor;

import java.io.InputStream;

/**
 * FlexyDemoScenario Implementation of a Package Sorting Facility
 *
 * @author Alexander Hawk
 * @version 06/21/2019
 * @see FlexyDemoScenario
 */
public class FlexyDemoScenarioPackageSorting extends FlexyDemoScenario {


    /**
     * Construct a new <code>FlexyDemoScenarioPackageSorting</code> with the given name.
     *
     * @param name name of this demo scenario
     */
    FlexyDemoScenarioPackageSorting( String name ) {
        super( name );
    }

    /**
     * Perform initial generation and setup of this <code>FlexyDemoScenario</code>
     */
    protected void genScenario() {
        FlexyDemoMotor motorOne = new FlexyDemoMotor( "ConveyorAMotor3", 95, 105, 100 );
        assert devices != null;
        devices.add( motorOne );
    }

    /**
     * Return URL for This Scenario's Var_Lst.csv
     *
     * @return URL to Var_Lst for This Scenario
     */
    protected InputStream getVarLstCSV() {
        return this.getClass().getResourceAsStream( "/VarLstFiles/s2.csv" );
    }

    /**
     * Return URL for This Scenario's Var_Lst.txt
     *
     * @return URL to Var_Lst for This Scenario
     */
    protected InputStream getVarLstTXT() {
        return this.getClass().getResourceAsStream( "/VarLstFiles/s2.txt" );
    }

}
