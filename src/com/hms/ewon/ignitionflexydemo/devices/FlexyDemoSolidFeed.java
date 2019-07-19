package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a solids feed
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSolidFeed extends FlexyDemoFlexy {

  /**
   * Amount of Fill Lost per Demo Cycle
   */
  private final double fillLostPerCycle;

  /**
   * Value for Current Fill
   */
  private double currentFill = FILL_FULL_PERCENT;

  /**
   * Basic <code>FlexyDemoSolidFeed</code> constructor. Create and initialize a solids feed with
   * simulated data for fill.
   *
   * @param name name of this <code>FlexyDemoSolidFeed</code>
   * @param initPowerStatus initial power status
   * @param fillLostPerCycle amount of fill used per cycle
   */
  public FlexyDemoSolidFeed(String name, boolean initPowerStatus, double fillLostPerCycle) {
    super(name);
    this.fillLostPerCycle = fillLostPerCycle;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FILL"), FlexyDemoTagConfig.TYPE_INTEGER));
  }

  /**
   * Method to set tags to default values, if necessary
   */
  protected void tagDefaults() {
    currentFill = FILL_FULL_PERCENT;
    FlexyDemoTagManager.setTagAsBoolean(getTagFullName("PWR"), initPowerStatus);
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() throws EWException {
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      FlexyDemoTagManager.setTagAsInt(getTagFullName("FILL"), (int) Math.round(currentFill));
      currentFill -= fillLostPerCycle;
      if (currentFill <= FILL_EMPTY_PERCENT) {
        currentFill = FILL_FULL_PERCENT;
      }
    }
  }

}
