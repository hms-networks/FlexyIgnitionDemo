package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a bottle capping machine
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoBottleCapMachine extends FlexyDemoFlexy {

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
   * Basic <code>FlexyDemoBottleCapMachine</code> constructor. Create and initialize a bottle cap
   * machine with simulated data for bottles per cycle
   *
   * @param name name of this <code>FlexyDemoBottleCapMachine</code>
   * @param initPowerStatus Initial power status
   * @param bottlesPerCycLow lowest value for simulated bottles per cycle
   * @param bottlesPerCycIdeal ideal value for simulated bottles per cycle
   * @param bottlesPerCycHigh highest value for simulated bottles per cycle
   */
  public FlexyDemoBottleCapMachine(String name, boolean initPowerStatus, int bottlesPerCycLow,
      int bottlesPerCycHigh, int bottlesPerCycIdeal) {
    super(name);
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
    int newBottlesPerCycle = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newBottlesPerCycle = FlexyDemo
          .randomIntHighWeight(bottlesPerCycLow, bottlesPerCycHigh, bottlesPerCycIdeal);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("BOTTLESPERCYCLE"), newBottlesPerCycle);
  }

}
