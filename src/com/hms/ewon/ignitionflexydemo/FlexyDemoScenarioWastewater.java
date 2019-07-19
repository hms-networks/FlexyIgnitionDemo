package com.hms.ewon.ignitionflexydemo;

import com.hms.ewon.ignitionflexydemo.devices.*;

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
  FlexyDemoScenarioWastewater(String name) {
    super(name);
  }

  /**
   * Perform initial generation and setup of this <code>FlexyDemoScenario</code> Note: This scenario
   * generation code is following the same order of devices as the order of the process
   */
  protected void genScenario() {
    // Lift Stations
    String[] liftStation_names = {"LiftStation-A01", "LiftStation-B01"};
    int liftStation_flowLowGPM = 8000;
    int liftStation_flowHighGPM = 10000;
    int liftStation_flowIdealGPM = 9000;
    for (int i = 0; i < liftStation_names.length; i++) {
      devices.add(new FlexyDemoLiftStation(liftStation_names[i], liftStation_flowLowGPM,
          liftStation_flowHighGPM,
          liftStation_flowIdealGPM, FlexyDemoFlexy.PWR_ON));
    }

    // Bar Screens
    String[] barScreen_names = {"BarScreen-A01", "BarScreen-A02", "BarScreen-B01", "BarScreen-B02"};
    int barScreen_flowLowGPM = 4000;
    int barScreen_flowHighGPM = 5000;
    int barScreen_flowIdealGPM = 4500;
    for (int i = 0; i < barScreen_names.length; i++) {
      devices.add(
          new FlexyDemoBarScreen(barScreen_names[i], barScreen_flowLowGPM, barScreen_flowHighGPM,
              barScreen_flowIdealGPM));
    }

    // Grit Remover
    String[] gritRemover_names = {"GritRemover-A01", "GritRemover-A02", "GritRemover-B01",
        "GritRemover-B02"};
    int gritRemover_flowLowGPM = 3800;
    int gritRemover_flowHighGPM = 4800;
    int gritRemover_flowIdealGPM = 4500;
    int gritRemover_motorLowRPM = 100;
    int gritRemover_motorHighRPM = 120;
    int gritRemover_motorIdealRPM = 110;
    for (int i = 0; i < gritRemover_names.length; i++) {
      devices.add(new FlexyDemoGritRemover(gritRemover_names[i], gritRemover_flowLowGPM,
          gritRemover_flowHighGPM,
          gritRemover_flowIdealGPM, gritRemover_motorLowRPM,
          gritRemover_motorHighRPM, gritRemover_motorIdealRPM,
          FlexyDemoFlexy.PWR_ON));
    }

    // Pumps for Primary Settle Tank
    String[] pumpSettlePrimary_names = {"PumpSettlePrimary-A01", "PumpSettlePrimary-A02",
        "PumpSettlePrimary-B01", "PumpSettlePrimary-B02"};
    boolean[] pumpSettlePrimary_powerStatus = {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_OFF,
        FlexyDemoFlexy.PWR_OFF, FlexyDemoFlexy.PWR_ON};
    int pumpSettlePrimary_flowLowGPM = 3800;
    int pumpSettlePrimary_flowHighGPM = 4800;
    int pumpSettlePrimary_flowIdealGPM = 4500;
    for (int i = 0; i < pumpSettlePrimary_names.length; i++) {
      devices.add(new FlexyDemoPump(pumpSettlePrimary_names[i], pumpSettlePrimary_flowLowGPM,
          pumpSettlePrimary_flowHighGPM, pumpSettlePrimary_flowIdealGPM,
          pumpSettlePrimary_powerStatus[i]));
    }

    // Primary Settle Tanks
    String[] settleTankPrimary_names = {"SettleTankPrimary-A01", "SettleTankPrimary-A02",
        "SettleTankPrimary-B01", "SettleTankPrimary-B02"};
    int[] settleTankPrimary_fillLowPerc = {30, 60, 97, 10};
    int[] settleTankPrimary_fillIdealPerc = {35, 62, 97, 15};
    int[] settleTankPrimary_fillHighPerc = {40, 64, 98, 20};
    int[] settleTankPrimary_drainOpenPerc = {0, 41, 0, 0};
    for (int i = 0; i < settleTankPrimary_names.length; i++) {
      devices.add(new FlexyDemoSettlingDrainTank(settleTankPrimary_names[i],
          settleTankPrimary_fillLowPerc[i],
          settleTankPrimary_fillHighPerc[i],
          settleTankPrimary_fillIdealPerc[i],
          settleTankPrimary_drainOpenPerc[i]));
    }

    // Sludge Digesters (Sludge comes from Settle Tanks, Water Continues from Settle Tank to Next Stage)
    String[] sludgeDigester_names = {"SludgeDigester-A01", "SludgeDigester-B01"};
    int[] sludgeDigester_fillLowPerc = {45, 23};
    int[] sludgeDigester_fillIdealPerc = {48, 30};
    int[] sludgeDigester_fillHighPerc = {51, 32};
    for (int i = 0; i < sludgeDigester_names.length; i++) {
      devices
          .add(new FlexyDemoSludgeDigester(sludgeDigester_names[i], sludgeDigester_fillLowPerc[i],
              sludgeDigester_fillHighPerc[i],
              sludgeDigester_fillIdealPerc[i]));
    }

    // Sludge Biogas Blowers
    String[] biogasBlower_names = {"BiogasBlower-A01", "BiogasBlower-B01"};
    int[] biogasBlower_rateLowCubicFtSec = {1, 1};
    int[] biogasBlower_rateIdealCubicFtSec = {2, 2};
    int[] biogasBlower_rateHighCubicFtSec = {3, 3};
    boolean[] biogasBlower_powerStatus = {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_ON};
    for (int i = 0; i < biogasBlower_names.length; i++) {
      devices.add(new FlexyDemoAirBlower(biogasBlower_names[i], biogasBlower_rateLowCubicFtSec[i],
          biogasBlower_rateHighCubicFtSec[i],
          biogasBlower_rateIdealCubicFtSec[i], biogasBlower_powerStatus[i]));
    }

    // Air Compressors for Aeration Tanks
    String[] airCompressor_names =
        {"AirCompressor-A01", "AirCompressor-A02", "AirCompressor-B01", "AirCompressor-B02"};
    int[] airCompressor_pressureLowPSI = {200, 190, 150, 220};
    int[] airCompressor_pressureIdealPSI = {230, 230, 230, 230};
    int[] airCompressor_pressureHighPSI = {260, 260, 260, 260};
    boolean[] airCompressor_powerStatus =
        {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_ON,
            FlexyDemoFlexy.PWR_ON};
    for (int i = 0; i < airCompressor_names.length; i++) {
      devices
          .add(new FlexyDemoAirCompressor(airCompressor_names[i], airCompressor_pressureLowPSI[i],
              airCompressor_pressureHighPSI[i],
              airCompressor_pressureIdealPSI[i],
              airCompressor_powerStatus[i]));
    }

    // Sludge Press
    String[] sludgePress_names = {"SludgePress-A01", "SludgePress-B01"};
    int[] sludgePress_motorLowRPM = {80, 80};
    int[] sludgePress_motorIdealRPM = {90, 90};
    int[] sludgePress_motorHighRPM = {100, 100};
    boolean[] sludgePress_powerStatus = {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_OFF};
    for (int i = 0; i < sludgePress_names.length; i++) {
      devices.add(new FlexyDemoSludgePress(sludgePress_names[i], sludgePress_motorLowRPM[i],
          sludgePress_motorHighRPM[i], sludgePress_motorIdealRPM[i],
          sludgePress_powerStatus[i]));
    }

    // Sludge Conveyor
    String[] sludgeConveyor_names = {"SludgeConveyor-A01", "SludgeConveyor-B01"};
    int[] sludgeConveyor_motorLowRPM = {100, 100};
    int[] sludgeConveyor_motorIdealRPM = {110, 110};
    int[] sludgeConveyor_motorHighRPM = {120, 120};
    boolean[] sludgeConveyor_powerStatus = {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_OFF};
    for (int i = 0; i < sludgeConveyor_names.length; i++) {
      devices
          .add(new FlexyDemoConveyor(sludgeConveyor_names[i], sludgeConveyor_motorLowRPM[i],
              sludgeConveyor_motorHighRPM[i], sludgeConveyor_motorIdealRPM[i],
              sludgeConveyor_powerStatus[i]));
    }

    // Aeration Tanks
    String[] aerationTank_names =
        {"AerationTank-A01", "AerationTank-A02", "AerationTank-B01", "AerationTank-B02"};
    int[] aerationTank_fillLowPerc = {91, 40, 92, 50};
    int[] aerationTank_fillIdealPerc = {91, 42, 92, 51};
    int[] aerationTank_fillHighPerc = {92, 44, 93, 52};
    int[] aerationTank_oxygenLowPPM = {40, 8, 18, 10};
    int[] aerationTank_oxygenIdealPPM = {45, 9, 19, 11};
    int[] aerationTank_oxygenHighPPM = {50, 10, 20, 12};
    int[] aerationTank_bodLowPPM = {50, 100, 20, 0};
    int[] aerationTank_bodIdealPPM = {51, 110, 25, 1};
    int[] aerationTank_bodHighPPM = {52, 120, 30, 2};
    int[] aerationTank_pressureLowPSI = {12, 1, 13, 0};
    int[] aerationTank_pressureIdealPSI = {13, 2, 14, 1};
    int[] aerationTank_pressureHighPSI = {14, 3, 15, 2};
    boolean[] aerationTank_powerStatus = {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_ON,
        FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_ON};
    for (int i = 0; i < aerationTank_names.length; i++) {
      devices.add(new FlexyDemoAerationTank(aerationTank_names[i], aerationTank_pressureLowPSI[i],
          aerationTank_pressureHighPSI[i], aerationTank_pressureIdealPSI[i],
          aerationTank_fillLowPerc[i], aerationTank_fillHighPerc[i],
          aerationTank_fillIdealPerc[i], aerationTank_bodLowPPM[i],
          aerationTank_bodHighPPM[i], aerationTank_bodIdealPPM[i],
          aerationTank_oxygenLowPPM[i], aerationTank_oxygenHighPPM[i],
          aerationTank_oxygenIdealPPM[i], aerationTank_powerStatus[i]));
    }

    // Pumps for Secondary Settle Tank
    String[] pumpSettleSecondary_names =
        {"PumpSettleSecondary-A01", "PumpSettleSecondary-A02", "PumpSettleSecondary-B01",
            "PumpSettleSecondary-B02"};
    boolean[] pumpSettleSecondary_powerStatus = {FlexyDemoFlexy.PWR_OFF, FlexyDemoFlexy.PWR_OFF,
        FlexyDemoFlexy.PWR_OFF, FlexyDemoFlexy.PWR_ON};
    int[] pumpSettleSecondary_flowLowGPM = {3000, 3000, 3500, 3500};
    int[] pumpSettleSecondary_flowHighGPM = {4250, 4250, 4250, 4250};
    int[] pumpSettleSecondary_flowIdealGPM = {3500, 3500, 4000, 4000};
    for (int i = 0; i < pumpSettleSecondary_names.length; i++) {
      devices.add(new FlexyDemoPump(pumpSettleSecondary_names[i], pumpSettleSecondary_flowLowGPM[i],
          pumpSettleSecondary_flowHighGPM[i], pumpSettleSecondary_flowIdealGPM[i],
          pumpSettleSecondary_powerStatus[i]));
    }

    // Secondary Settle Tanks
    String[] settleTankSecondary_names = {"SettleTankSecondary-A01", "SettleTankSecondary-A02",
        "SettleTankSecondary-B01", "SettleTankSecondary-B02"};
    int[] settleTankSecondary_fillLowPerc = {96, 91, 73, 46};
    int[] settleTankSecondary_fillIdealPerc = {96, 91, 74, 47};
    int[] settleTankSecondary_fillHighPerc = {97, 92, 75, 48};
    for (int i = 0; i < settleTankSecondary_names.length; i++) {
      devices.add(new FlexyDemoSettlingTank(settleTankSecondary_names[i],
          settleTankSecondary_fillLowPerc[i],
          settleTankSecondary_fillHighPerc[i],
          settleTankSecondary_fillIdealPerc[i]));
    }

    // Chlorinator Valve
    String[] chlorinatorValve_names = {"ValveChlorinator-A01", "ValveChlorinator-A02",
        "ValveChlorinator-B01", "ValveChlorinator-B02"};
    boolean[] chlorinatorValve_open = {false, false, true, false};
    int[] chlorinatorValve_flowLowGPM = {1250, 1250, 1250, 1250};
    int[] chlorinatorValve_flowIdealGPM = {1500, 1500, 1500, 1500};
    int[] chlorinatorValve_flowHighGPM = {1750, 1750, 1750, 1750};
    for (int i = 0; i < chlorinatorValve_names.length; i++) {
      devices.add(new FlexyDemoValve(chlorinatorValve_names[i], chlorinatorValve_open[i],
          chlorinatorValve_flowLowGPM[i], chlorinatorValve_flowHighGPM[i],
          chlorinatorValve_flowIdealGPM[i]));
    }

    // Chlorinators
    String[] chlorinator_names = {"Chlorinator-A01", "Chlorinator-A02", "Chlorinator-B01",
        "Chlorinator-B02"};
    boolean[] chlorinator_egressValveOpen = {true, false, false, false};
    int[] chlorinator_fillLowPerc = {53, 96, 95, 94};
    int[] chlorinator_fillIdealPerc = {54, 96, 95, 94};
    int[] chlorinator_fillHighPerc = {55, 97, 96, 95};
    for (int i = 0; i < chlorinatorValve_names.length; i++) {
      devices.add(new FlexyDemoChlorinator(chlorinator_names[i], chlorinator_fillLowPerc[i],
          chlorinator_fillHighPerc[i], chlorinator_fillIdealPerc[i],
          chlorinator_egressValveOpen[i]));
    }

    // pH Sensors
    String[] pHSensor_names = {"PHSensor-A01", "PHSensor-A02", "PHSensor-B01", "PHSensor-B02"};
    double[] pHSensor_pHLow = {7.0, 7.9, 8.0, 7.8};
    double[] pHSensor_pHIdeal = {7.1, 8.0, 8.1, 7.9};
    double[] pHSensor_pHHigh = {7.2, 8.1, 8.2, 8.0};
    for (int i = 0; i < pHSensor_names.length; i++) {
      devices.add(new FlexyDemoPHSensor(pHSensor_names[i], pHSensor_pHLow[i], pHSensor_pHHigh[i],
          pHSensor_pHIdeal[i]));
    }

    // Egress Pumps
    String[] egressPump_names = {"EgressPump-A01", "EgressPump-A02", "EgressPump-B01",
        "EgressPump-B02"};
    boolean[] egressPump_powerStatus =
        {FlexyDemoFlexy.PWR_ON, FlexyDemoFlexy.PWR_OFF, FlexyDemoFlexy.PWR_OFF,
            FlexyDemoFlexy.PWR_OFF};
    int[] egressPump_flowLowGPM = {1500, 1500, 1500, 1500};
    int[] egressPump_flowHighGPM = {1750, 1750, 1750, 1750};
    int[] egressPump_flowIdealGPM = {2000, 2000, 2000, 2000};
    for (int i = 0; i < egressPump_names.length; i++) {
      devices.add(new FlexyDemoPump(egressPump_names[i], egressPump_flowLowGPM[i],
          egressPump_flowHighGPM[i],
          egressPump_flowIdealGPM[i], egressPump_powerStatus[i]));
    }
  }

}
