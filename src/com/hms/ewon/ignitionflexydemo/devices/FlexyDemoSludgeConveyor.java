package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a conveyor belt for sludge.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSludgeConveyor extends FlexyDemoFlexy {

  /**
   * The lower bound for simulated motor rpm values
   */
  private final int motorLowRPM;

  /**
   * The upper bound for simulated motor rpm values
   */
  private final int motorHighRPM;

  /**
   * The ideal value for simulated motor rpm values
   */
  private final int motorIdealRPM;


  /**
   * Basic <code>FlexyDemoSludgeConveyor</code> constructor. Create and initialize a basic conveyor
   * belt for sludge with simulated data.
   *
   * @param name name of this <code>FlexyDemoSludgeConveyor</code>
   * @param motorLowRPM lower bound for motor rpm value
   * @param motorHighRPM upper bound for motor rpm value
   * @param motorIdealRPM ideal value for motor rpm
   * @param initPowerStatus initial power status at device creation
   */
  public FlexyDemoSludgeConveyor(String name, int motorLowRPM, int motorHighRPM, int motorIdealRPM,
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
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("MOTOR1-RPM"), FlexyDemoTagConfig.TYPE_INTEGER));
  }

  protected void tagDefaults() {
    FlexyDemoTagManager.setTagAsBoolean(getTagFullName("PWR"), initPowerStatus);
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() throws EWException {
    int newRPM = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newRPM = FlexyDemo.randomIntHighWeight(motorLowRPM, motorHighRPM, motorIdealRPM);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("MOTOR1-RPM"), newRPM);
  }

}
