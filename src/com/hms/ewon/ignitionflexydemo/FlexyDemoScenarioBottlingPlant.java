package com.hms.ewon.ignitionflexydemo;


import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoBottleCapMachine;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoBottleCounter;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoBottleFillMachine;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoConveyor;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoLabelMachine;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoLiquidFeed;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoPump;
import com.hms.ewon.ignitionflexydemo.devices.FlexyDemoSolidFeed;

/**
 * FlexyDemoScenario Implementation of a Bottling Plant Facility
 *
 * @see FlexyDemoScenario
 */
public class FlexyDemoScenarioBottlingPlant extends FlexyDemoScenario {


  /**
   * Construct a new <code>FlexyDemoScenarioBottlingPlant</code> with the given name.
   *
   * @param name name of this demo scenario
   */
  FlexyDemoScenarioBottlingPlant(String name) {
    super(name);
  }

  /**
   * Perform initial generation and setup of this <code>FlexyDemoScenarioBottlingPlant</code>
   */
  protected void genScenario() {
    // BOTTLES PER SECOND
    // SAME ACROSS ALL DEMO DEVICES
    int bottlesPerCycleLow = 6;
    int bottlesPerCycleHigh = 7;
    int bottlesPerCycleIdeal = 6;

    // CONVEYORS
    String[] conveyor_Names = {"BottleConveyor-A01", "BottleConveyor-A02", "BottleConveyor-A03",
        "BottleConveyor-A04", "BottleConveyor-A05", "BottleConveyor-B01", "BottleConveyor-B02",
        "BottleConveyor-B03", "BottleConveyor-B04", "BottleConveyor-B05", "BottleConveyor-C01",
        "BottleConveyor-C02", "BottleConveyor-C03", "BottleConveyor-C04", "BottleConveyor-C05"};
    boolean[] conveyor_initPowerStatus = {true, true, true, true, true, true, true, true, true,
        true, false, false, false, false, false};
    int[] conveyor_motorRPMLow = {70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70};
    int[] conveyor_motorRPMHigh = {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
    int[] conveyor_motorRPMIdeal = {75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75};
    for (int x = 0; x < conveyor_Names.length; x++) {
      devices.add(new FlexyDemoConveyor(conveyor_Names[x], conveyor_motorRPMLow[x],
          conveyor_motorRPMHigh[x], conveyor_motorRPMIdeal[x], conveyor_initPowerStatus[x]));
    }

    // SODA FEEDS
    String[] sodaFeed_Names = {"SodaFeed-A01", "SodaFeed-A02", "SodaFeed-B01", "SodaFeed-B02",
        "SodaFeed-C01", "SodaFeed-C02"};
    boolean[] sodaFeed_initPowerStatus = {true, true, true, true, false, false};
    double[] sodaFeed_fillLostPerCycle = {0.1, 0.2, 0.2, 0.1, 0.1, 0.2};
    int[] sodaFeed_mixerRPMLow = {20, 20, 20, 20, 20, 20};
    int[] sodaFeed_mixerRPMHigh = {30, 30, 30, 30, 30, 30};
    int[] sodaFeed_mixerRPMIdeal = {40, 40, 40, 40, 40, 40};
    for (int x = 0; x < sodaFeed_Names.length; x++) {
      devices.add(new FlexyDemoLiquidFeed(sodaFeed_Names[x], sodaFeed_initPowerStatus[x],
          sodaFeed_fillLostPerCycle[x], sodaFeed_mixerRPMLow[x], sodaFeed_mixerRPMHigh[x],
          sodaFeed_mixerRPMIdeal[x]));
    }

    // SODA PUMPS
    String[] sodaPump_Names = {"SodaPump-A01", "SodaPump-A02", "SodaPump-B01", "SodaPump-B02",
        "SodaPump-C01", "SodaPump-C02"};
    boolean[] sodaPump_initPowerStatus = {true, true, true, true, false, false};
    int[] sodaPump_flowGPMLow = {1, 1, 1, 1, 1, 1};
    int[] sodaPump_flowGPMHigh = {2, 2, 2, 2, 2, 2};
    int[] sodaPump_flowGPMIdeal = {1, 1, 1, 1, 1, 1};
    for (int x = 0; x < sodaPump_Names.length; x++) {
      devices.add(
          new FlexyDemoPump(sodaPump_Names[x], sodaPump_flowGPMLow[x], sodaPump_flowGPMHigh[x],
              sodaPump_flowGPMIdeal[x], sodaPump_initPowerStatus[x]));
    }

    // SODA FILLERS
    String[] sodaFiller_Names = {"SodaBottleFiller-A01", "SodaBottleFiller-B01",
        "SodaBottleFiller-C01"};
    boolean[] sodaFiller_initPowerStatus = {true, true, false};
    int[] sodaFiller_pressurePSILow = {50, 50, 50};
    int[] sodaFiller_pressurePSIHigh = {60, 60, 60};
    int[] sodaFiller_pressurePSIIdeal = {55, 55, 55};
    for (int x = 0; x < sodaFiller_Names.length; x++) {
      devices.add(new FlexyDemoBottleFillMachine(sodaFiller_Names[x], sodaFiller_initPowerStatus[x],
          sodaFiller_pressurePSILow[x], sodaFiller_pressurePSIHigh[x],
          sodaFiller_pressurePSIIdeal[x], bottlesPerCycleLow, bottlesPerCycleHigh,
          bottlesPerCycleIdeal));
    }

    // BOTTLE LABEL FEEDS
    String[] labelFeed_Names = {"LabelPaperFeed-A01", "LabelPaperFeed-B01", "LabelPaperFeed-C01"};
    boolean[] labelFeed_initPowerStatus = {true, true, false};
    double[] labelFeed_labelFillUsedPerCycle = {0.05, 0.04, 0.06};
    for (int x = 0; x < labelFeed_Names.length; x++) {
      devices.add(new FlexyDemoSolidFeed(labelFeed_Names[x], labelFeed_initPowerStatus[x],
          labelFeed_labelFillUsedPerCycle[x]));
    }

    // BOTTLE LABELER
    String[] bottleLabeler_Names = {"BottleLabeler-A01", "BottleLabeler-B01", "BottleLabeler-C01"};
    boolean[] bottleLabeler_initPowerStatus = {true, true, false};
    for (int x = 0; x < bottleLabeler_Names.length; x++) {
      devices.add(
          new FlexyDemoLabelMachine(bottleLabeler_Names[x], bottleLabeler_initPowerStatus[x],
              bottlesPerCycleLow, bottlesPerCycleHigh, bottlesPerCycleIdeal));
    }

    // BOTTLE CAP FEEDS
    String[] capFeed_Names = {"BottleCapFeed-A01", "BottleCapFeed-B01", "BottleCapFeed-C01"};
    boolean[] capFeed_initPowerStatus = {true, true, false};
    double[] capFeed_capFillUsedPerCycle = {0.07, 0.08, 0.07};
    for (int x = 0; x < capFeed_Names.length; x++) {
      devices.add(new FlexyDemoSolidFeed(capFeed_Names[x], capFeed_initPowerStatus[x],
          capFeed_capFillUsedPerCycle[x]));
    }

    // BOTTLE CAPPER
    String[] bottleCapper_Names = {"BottleCapper-A01", "BottleCapper-B01", "BottleCapper-C01"};
    boolean[] bottleCapper_initPowerStatus = {true, true, false};
    for (int x = 0; x < bottleCapper_Names.length; x++) {
      devices.add(
          new FlexyDemoBottleCapMachine(bottleCapper_Names[x], bottleCapper_initPowerStatus[x],
              bottlesPerCycleLow, bottlesPerCycleHigh, bottlesPerCycleIdeal));
    }

    // END BOTTLE COUNTER
    String[] bottleCounter_Names = {"BottleCounter-A01", "BottleCounter-B01", "BottleCounter-C01"};
    boolean[] bottleCounter_initPowerStatus = {true, true, false};
    int[] bottleCounter_initBottleCount = {875, 227, 953};
    for (int x = 0; x < bottleCounter_Names.length; x++) {
      devices.add(
          new FlexyDemoBottleCounter(bottleCounter_Names[x], bottleCounter_initBottleCount[x],
              bottlesPerCycleLow, bottlesPerCycleHigh, bottlesPerCycleIdeal,
              bottleCounter_initPowerStatus[x]));
    }
  }

}
