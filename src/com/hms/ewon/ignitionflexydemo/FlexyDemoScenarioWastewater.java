package com.hms.ewon.ignitionflexydemo;

import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoPump;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoTank;

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
        int pumpPressureLowPSI = 100;
        int pumpPressureHighPSI = 200;
        int pumpPressureIdealPSI = 150;
        int pumpPowerLowVolt = 115;
        int pumpPowerHighVolt = 122;
        int pumpPowerIdealVolt = 120;
        FlexyDemoPump pumpOne = new FlexyDemoPump( "IntakePump", pumpPressureLowPSI, pumpPressureHighPSI, pumpPressureIdealPSI,
                pumpPowerLowVolt, pumpPowerHighVolt, pumpPowerIdealVolt );

        int tankInPressureLowPSI = 30;
        int tankInPressureHighPSI = 100;
        int tankInPressureIdealPSI = 55;
        int tankOutPressureLowPSI = 20;
        int tankOutPressureHighPSI = 90;
        int tankOutPressureIdealPSI = 45;
        int tankFillLow = 0;
        int tankFillHigh = 10;
        int tankFillIdeal = 85;
        int tankProcessTimeSecs = 7;
        FlexyDemoTank settleTank = new FlexyDemoTank( "SettleTank01",  tankInPressureLowPSI,tankInPressureHighPSI,
                tankInPressureIdealPSI,tankOutPressureLowPSI,tankOutPressureHighPSI,tankOutPressureIdealPSI,tankFillLow,
                tankFillHigh,tankFillIdeal,tankProcessTimeSecs );
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
        return this.getClass().getResourceAsStream( "/VarLstFiles/Wastewater.csv" );
    }

}
