package com.hms.ewon.ignitionflexydemo;

import java.io.InputStream;

/**
 * FlexyDemoScenario Implementation of a Wastewater Treatment Facility
 *
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
    }

    /**
     * Return URL for This Scenario's Var_Lst.csv
     *
     * @return URL to Var_Lst for This Scenario
     */
    protected InputStream getVarLstCSV() {
        return this.getClass().getResourceAsStream( "/VarLstFiles/Wastewater.csv" );
    }

}
