package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a basic bottle counter
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoBottleCounter extends FlexyDemoFlexy {

  /**
   * Initial value for bottle count
   */
  private final int initBottleCount;

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
   * The current value for bottle count
   */
  private int currentBottleCount;

  /**
   * Basic <code>FlexyDemoBottleCounter</code> constructor. Create and initialize a counter for
   * number of bottles processed.
   *
   * @param name name of this <code>FlexyDemoBottleCounter</code>
   * @param initBottleCount initial bottle count value
   * @param bottlesPerCycLow lowest value for simulated bottles per cycle
   * @param bottlesPerCycIdeal ideal value for simulated bottles per cycle
   * @param bottlesPerCycHigh highest value for simulated bottles per cycle
   * @param initPowerStatus initial power status
   */
  public FlexyDemoBottleCounter(String name, int initBottleCount, int bottlesPerCycLow,
      int bottlesPerCycHigh,
      int bottlesPerCycIdeal, boolean initPowerStatus) {
    super(name);
    this.initBottleCount = initBottleCount;
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
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("COUNT"), FlexyDemoTagConfig.TYPE_INTEGER));
  }

  /**
   * Method to set tags to default values, if necessary
   */
  protected void tagDefaults() {
    this.currentBottleCount = initBottleCount;
    FlexyDemoTagManager.setTagAsBoolean(getTagFullName("PWR"), initPowerStatus);
    FlexyDemoTagManager.setTagAsDouble(getTagFullName("COUNT"), currentBottleCount);
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() throws EWException {
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      FlexyDemoTagManager.setTagAsDouble(getTagFullName("COUNT"), currentBottleCount);
      currentBottleCount += FlexyDemo.randomIntHighWeight(bottlesPerCycLow, bottlesPerCycHigh,
          bottlesPerCycIdeal);
    }
  }

}
