package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a bottle filling machine
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoBottleFillMachine extends FlexyDemoFlexy {

  /**
   * Lowest value for pressure
   */
  private final int pressurePSILow;

  /**
   * Highest value for pressure
   */
  private final int pressurePSIHigh;

  /**
   * Ideal value for pressure
   */
  private final int pressurePSIIdeal;

  /**
   * Lowest value for bottles processed per cycle
   */
  private final int bottlesPerCycLow;

  /**
   * Highest value for bottles processed per cycle
   */
  private final int bottlesPerCycHigh;

  /**
   * Ideal value for bottles processed per cycle
   */
  private final int bottlesPerCycIdeal;

  /**
   * Basic <code>FlexyDemoBottleFillMachine</code> constructor. Create and initialize a simulated
   * machine for filling bottles
   *
   * @param name name of this <code>FlexyDemoBottleFillMachine</code>
   * @param initPowerStatus initial power status
   * @param pressurePSILow lowest value for simulated pressure
   * @param pressurePSIHigh highest value for simulated pressure
   * @param pressurePSIIdeal ideal value for simulated pressure
   */
  public FlexyDemoBottleFillMachine(String name, boolean initPowerStatus, int pressurePSILow,
      int pressurePSIHigh, int pressurePSIIdeal, int bottlesPerCycLow, int bottlesPerCycHigh,
      int bottlesPerCycIdeal) {
    super(name);
    this.pressurePSILow = pressurePSILow;
    this.pressurePSIHigh = pressurePSIHigh;
    this.pressurePSIIdeal = pressurePSIIdeal;
    this.bottlesPerCycLow = bottlesPerCycLow;
    this.bottlesPerCycHigh = bottlesPerCycHigh;
    this.bottlesPerCycIdeal = bottlesPerCycIdeal;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("PRESSURE"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("BOTTLESPERCYCLE"), FlexyDemoTagConfig.TYPE_INTEGER));
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
    int newBottlesPerCycle = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newPressure = FlexyDemo
          .randomIntHighWeight(pressurePSILow, pressurePSIHigh, pressurePSIIdeal);
      newBottlesPerCycle = FlexyDemo
          .randomIntHighWeight(bottlesPerCycLow, bottlesPerCycHigh, bottlesPerCycIdeal);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("PRESSURE"), newPressure);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("BOTTLESPERCYCLE"), newBottlesPerCycle);
  }

}
