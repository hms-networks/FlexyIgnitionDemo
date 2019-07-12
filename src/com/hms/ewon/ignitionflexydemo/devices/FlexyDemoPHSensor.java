package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a pH sensor
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoPHSensor extends FlexyDemoFlexy {

  /**
   * The lowest value for pH
   */
  private final double phLow;

  /**
   * The highest value for pH
   */
  private final double phHigh;

  /**
   * The ideal value for pH
   */
  private final double phIdeal;

  /**
   * Basic <code>FlexyDemoPHSensor</code> constructor. Create and initialize a settling tank with
   * simulated data for fill.
   *
   * @param name name of this <code>FlexyDemoPHSensor</code>
   * @param phLow lower bound for pH value
   * @param phHigh upper bound for pH value
   * @param phIdeal ideal value for pH
   */
  public FlexyDemoPHSensor(String name, double phLow, double phHigh, double phIdeal) {
    super(name);
    this.phLow = phLow;
    this.phHigh = phHigh;
    this.phIdeal = phIdeal;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PH"), FlexyDemoTagConfig.TYPE_DOUBLE));
  }

  /**
   * Method to set tags to default values, if necessary
   */
  protected void tagDefaults() {
    // NO TAG DEFAULTS TO SET
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() {
    double newPH = FlexyDemo.randomDoubleHighWeight(phLow, phHigh, phIdeal);
    FlexyDemoTagManager.setTagAsDouble(getTagFullName("PH"), newPH);
  }

}
