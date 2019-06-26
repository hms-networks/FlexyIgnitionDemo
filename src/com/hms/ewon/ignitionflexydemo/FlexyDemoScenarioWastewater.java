package com.hms.ewon.ignitionflexydemo;

import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoPump;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoTank;

import java.io.InputStream;

/**
 * FlexyDemoScenario Implementation of a Wastewater Treatment Facility
 *
 * @author Alexander Hawk
 * @version 06/19/2019
 * @see FlexyDemoScenario
 */
public class FlexyDemoScenarioWastewater extends FlexyDemoScenario {

    /**
     * Construct a new <code>FlexyDemoScenarioWastewater</code> with the given name.
     *
     * @param name name of this demo scenario
     */
    FlexyDemoScenarioWastewater( String name ) {
        super( name );
    }

    /**
     * Perform initial generation and setup of this <code>FlexyDemoScenario</code>
     */
    protected void genScenario() {
        FlexyDemoPump pumpOne = new FlexyDemoPump( "IntakePump", 100, 200, 150, 115, 122, 120 );
        FlexyDemoTank settleTank = new FlexyDemoTank( "SettleTank01", 30, 100, 55, 20, 90, 45, 0, 100, 85, 7 );
        assert devices != null;
        devices.add( pumpOne );
        devices.add( settleTank );
    }

    /**
     * Return URL for This Scenario's Var_Lst.csv
     *
     * @return URL to Var_Lst for This Scenario
     */
    protected InputStream getVarLstCSV() {
        return this.getClass().getResourceAsStream( "/VarLstFiles/s1.csv" );
    }

    /**
     * Return URL for This Scenario's Var_Lst.txt
     *
     * @return URL to Var_Lst for This Scenario
     */
    protected InputStream getVarLstTXT() {
        return this.getClass().getResourceAsStream( "/VarLstFiles/s1.txt" );
    }

}
