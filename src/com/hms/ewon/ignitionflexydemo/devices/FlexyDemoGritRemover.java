package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a grit remover.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoGritRemover extends FlexyDemoFlexy {

  /**
   * The lowest value for simulated flow
   */
  private final int flowLowGPM;

  /**
   * The highest value for simulated flow
   */
  private final int flowHighGPM;

  /**
   * The ideal value for simulated flow
   */
  private final int flowIdealGPM;

  /**
   * The lowest value for simulated motor rpm
   */
  private final int motorLowRPM;

  /**
   * The highest value for simulated motor rpm
   */
  private final int motorHighRPM;

  /**
   * The ideal value for simulated motor rpm
   */
  private final int motorIdealRPM;

  /**
   * Basic <code>FlexyDemoGritRemover</code> constructor. Create and initialize a grit remover with
   * simulated data for flow.
   *
   * @param name name of this <code>FlexyDemoGritRemover</code>
   * @param flowLowGPM lower bound for flow value
   * @param flowHighGPM upper bound for flow value
   * @param flowIdealGPM ideal value for flow
   * @param motorLowRPM lower bound for motor rpm value
   * @param motorHighRPM upper bound for motor rpm value
   * @param motorIdealRPM ideal value for motor rpm
   * @param initPowerStatus initial power status at device creation
   */
  public FlexyDemoGritRemover(String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM,
      int motorLowRPM,
      int motorHighRPM, int motorIdealRPM, boolean initPowerStatus) {
    super(name);
    this.flowLowGPM = flowLowGPM;
    this.flowHighGPM = flowHighGPM;
    this.flowIdealGPM = flowIdealGPM;
    this.motorLowRPM = motorLowRPM;
    this.motorHighRPM = motorHighRPM;
    this.motorIdealRPM = motorIdealRPM;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FLOW"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(
        new FlexyDemoTagConfig(getTagFullName("MOTOR1-RPM"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
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
    int newFlow = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newRPM = FlexyDemo.randomIntHighWeight(motorLowRPM, motorHighRPM, motorIdealRPM);
      newFlow = FlexyDemo.randomIntMidWeight(flowLowGPM, flowHighGPM, flowIdealGPM);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("MOTOR1-RPM"), newRPM);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("FLOW"), newFlow);
  }

}
