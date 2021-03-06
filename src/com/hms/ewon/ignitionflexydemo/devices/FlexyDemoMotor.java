package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a simple industrial motor with simulated data for rotations per
 * minute.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoMotor extends FlexyDemoFlexy {

  /**
   * The lower bound for simulated rpm values
   */
  private final int rpmLow;

  /**
   * The upper bound for simulated rpm values
   */
  private final int rpmHigh;

  /**
   * The ideal value for simulated rpm values;
   */
  private final int rpmIdeal;

  /**
   * Basic <code>FlexyDemoMotor</code> constructor. Create and initialize a basic industrial motor
   * with simulated values for RPM.
   *
   * @param name name of this <code>FlexyDemoMotor</code>
   * @param rpmLow lower bound for rpm value
   * @param rpmHigh upper bound for rpm value
   * @param rpmIdeal ideal value for rpm
   */
  public FlexyDemoMotor(String name, int rpmLow, int rpmHigh, int rpmIdeal) {
    super(name);
    this.rpmLow = rpmLow;
    this.rpmHigh = rpmHigh;
    this.rpmIdeal = rpmIdeal;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("RPM"), FlexyDemoTagConfig.TYPE_INTEGER));
  }

  protected void tagDefaults() {
    // NO TAG DEFAULTS TO SET
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() {
    int newRPM = FlexyDemo.randomIntHighWeight(rpmLow, rpmHigh, rpmIdeal);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("RPM"), newRPM);
  }

}
