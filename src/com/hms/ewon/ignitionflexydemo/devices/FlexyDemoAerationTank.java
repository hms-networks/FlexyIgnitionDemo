package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of an aeration tank
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoAerationTank extends FlexyDemoFlexy {

  /**
   * The lower bound for simulated pressure values
   */
  private final int pressureLowPSI;

  /**
   * The upper bound for simulated pressure values
   */
  private final int pressureHighPSI;

  /**
   * The ideal value for simulated pressure values
   */
  private final int pressureIdealPSI;

  /**
   * The lower bound for simulated fill value
   */
  private final int fillLowPerc;

  /**
   * The upper bound for simulated fill value
   */
  private final int fillHighPerc;

  /**
   * The ideal value for simulated fill value
   */
  private final int fillIdealPerc;

  /**
   * The lower bound for simulated biological oxygen demand value
   */
  private final int bioOxygenDemandLow;

  /**
   * The upper bound for simulated biological oxygen demand value
   */
  private final int bioOxygenDemandHigh;

  /**
   * The ideal value for simulated biological oxygen demand
   */
  private final int bioOxygenDemandIdeal;

  /**
   * The lower bound for simulated dissolved O2 value
   */
  private final int dissolvedOxygenLow;

  /**
   * The upper bound for simulated dissolved O2 value
   */
  private final int dissolvedOxygenHigh;

  /**
   * The ideal value for simulated dissolved O2
   */
  private final int dissolvedOxygenIdeal;


  /**
   * Basic <code>FlexyDemoAerationTank</code> constructor. Create and initialize a basic air
   * compressor with simulated data.
   *
   * @param name name of this <code>FlexyDemoAerationTank</code>
   * @param pressureLowPSI lower bound for pressure value
   * @param pressureHighPSI upper bound for pressure value
   * @param pressureIdealPSI ideal value for pressure
   * @param fillLowPerc lower bound for fill value
   * @param fillHighPerc upper bound for fill value
   * @param fillIdealPerc ideal value for fill
   * @param bioOxygenDemandLow lower bound for bod value
   * @param bioOxygenDemandHigh upper bound for bod value
   * @param bioOxygenDemandIdeal ideal value for bod
   * @param dissolvedOxygenLow lower bound for dissolved oxygen
   * @param dissolvedOxygenHigh upper bound for dissolved oxygen
   * @param dissolvedOxygenIdeal ideal value for dissolved oxygen
   * @param initPowerStatus initial power status at device creation
   */
  public FlexyDemoAerationTank(String name, int pressureLowPSI, int pressureHighPSI,
      int pressureIdealPSI,
      int fillLowPerc, int fillHighPerc, int fillIdealPerc, int bioOxygenDemandLow,
      int bioOxygenDemandHigh, int bioOxygenDemandIdeal, int dissolvedOxygenLow,
      int dissolvedOxygenHigh, int dissolvedOxygenIdeal, boolean initPowerStatus) {
    super(name);
    this.pressureLowPSI = pressureLowPSI;
    this.pressureHighPSI = pressureHighPSI;
    this.pressureIdealPSI = pressureIdealPSI;
    this.fillLowPerc = fillLowPerc;
    this.fillHighPerc = fillHighPerc;
    this.fillIdealPerc = fillIdealPerc;
    this.bioOxygenDemandLow = bioOxygenDemandLow;
    this.bioOxygenDemandHigh = bioOxygenDemandHigh;
    this.bioOxygenDemandIdeal = bioOxygenDemandIdeal;
    this.dissolvedOxygenLow = dissolvedOxygenLow;
    this.dissolvedOxygenHigh = dissolvedOxygenHigh;
    this.dissolvedOxygenIdeal = dissolvedOxygenIdeal;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("PRESSURE"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("BOD"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("DISSOLVEDO2"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FILL"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
  }

  /**
   * Method to set tags to default values, if necessary
   */
  protected void tagDefaults() {
    FlexyDemoTagManager.setTagAsBoolean(getTagFullName("PWR"), initPowerStatus);
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() throws EWException {
    int newPressure = 0;
    int newBOD = 0;
    int newDissolvedO2 = 0;
    int newFill = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newPressure = FlexyDemo
          .randomIntHighWeight(pressureLowPSI, pressureHighPSI, pressureIdealPSI);
      newBOD = FlexyDemo
          .randomIntHighWeight(bioOxygenDemandLow, bioOxygenDemandHigh, bioOxygenDemandIdeal);
      newDissolvedO2 = FlexyDemo.randomIntHighWeight(dissolvedOxygenLow, dissolvedOxygenHigh,
          dissolvedOxygenIdeal);
      newFill = FlexyDemo.randomIntMidWeight(fillLowPerc, fillHighPerc, fillIdealPerc);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("PRESSURE"), newPressure);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("BOD"), newBOD);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("DISSOLVEDO2"), newDissolvedO2);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("FILL"), newFill);
  }

}
