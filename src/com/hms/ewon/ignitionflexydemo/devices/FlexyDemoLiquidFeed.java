package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a liquids feed
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoLiquidFeed extends FlexyDemoFlexy {

  /**
   * Amount of Fill Lost per Demo Cycle
   */
  private final double fillLostPerCycle;

  /**
   * Lowest value for mixer RPM
   */
  private final int mixerRPMLow;

  /**
   * Highest value for mixer RPM
   */
  private final int mixerRPMHigh;

  /**
   * Ideal value for mixer rpm
   */
  private final int mixerRPMIdeal;

  /**
   * Value for Current Fill
   */
  private double currentFill = FILL_FULL_PERCENT;

  /**
   * Basic <code>FlexyDemoLiquidFeed</code> constructor. Create and initialize a liquids feed with
   * simulated data for fill and mixer rpm.
   *
   * @param name name of this <code>FlexyDemoLiquidFeed</code>
   * @param initPowerStatus initial power status
   * @param fillLostPerCycle amount of fill used per cycle
   * @param mixerRPMLow lowest value for mixer rpm
   * @param mixerRPMHigh highest value for mixer rpm
   * @param mixerRPMIdeal ideal value for mixer rpm
   */
  public FlexyDemoLiquidFeed(String name, boolean initPowerStatus, double fillLostPerCycle,
      int mixerRPMLow, int mixerRPMHigh, int mixerRPMIdeal) {
    super(name);
    this.fillLostPerCycle = fillLostPerCycle;
    this.mixerRPMLow = mixerRPMLow;
    this.mixerRPMHigh = mixerRPMHigh;
    this.mixerRPMIdeal = mixerRPMIdeal;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FILL"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("MIXER-RPM"), FlexyDemoTagConfig.TYPE_INTEGER));
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
    int newMixerRPM = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newMixerRPM = FlexyDemo.randomIntHighWeight(mixerRPMLow, mixerRPMHigh, mixerRPMIdeal);
      FlexyDemoTagManager.setTagAsInt(getTagFullName("FILL"), (int) Math.round(currentFill));
      currentFill -= fillLostPerCycle;
      if (currentFill <= FILL_EMPTY_PERCENT) {
        currentFill = FILL_FULL_PERCENT;
      }
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("MIXER-RPM"), newMixerRPM);
  }

}
