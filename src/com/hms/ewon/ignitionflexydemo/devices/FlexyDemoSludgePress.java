package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a sludge press
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSludgePress extends FlexyDemoFlexy {

  /**
   * Number of motors associated with a sludge press
   */
  private static int NUM_MOTORS = 10;

  /**
   * The lower bound for simulated air movement rate (in feet^3/cubic feet)
   */
  private final int motorLowRPM;

  /**
   * The upper bound for simulated air movement rate (in feet^3/cubic feet)
   */
  private final int motorHighRPM;

  /**
   * The ideal value for simulated air movement rate (in feet^3/cubic feet)
   */
  private final int motorIdealRPM;


  /**
   * Basic <code>FlexyDemoSludgePress</code> constructor. Create and initialize a basic press to
   * squeeze liquid out of sludge.
   *
   * @param name name of this <code>FlexyDemoSludgePress</code>
   * @param motorLowRPM lower bound for motor rpm value
   * @param motorHighRPM upper bound for motor rpm value
   * @param motorIdealRPM ideal value for motor rpm
   * @param initPowerStatus initial power status at device creation
   */
  public FlexyDemoSludgePress(String name, int motorLowRPM, int motorHighRPM, int motorIdealRPM,
      boolean initPowerStatus) {
    super(name);
    this.motorLowRPM = motorLowRPM;
    this.motorHighRPM = motorHighRPM;
    this.motorIdealRPM = motorIdealRPM;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
    for (int x = 1; x <= NUM_MOTORS; x++) {
      String tagSuffix = "MOTOR" + x + "-RPM";
      addTagConfig(
          new FlexyDemoTagConfig(getTagFullName(tagSuffix), FlexyDemoTagConfig.TYPE_INTEGER));
    }
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
    for (int x = 1; x <= NUM_MOTORS; x++) {
      String tagSuffix = "MOTOR" + x + "-RPM";
      int newRPM = 0;
      if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
        newRPM = FlexyDemo.randomIntHighWeight(motorLowRPM, motorHighRPM, motorIdealRPM);
      }
      FlexyDemoTagManager.setTagAsInt(getTagFullName(tagSuffix), newRPM);
    }
  }

}
