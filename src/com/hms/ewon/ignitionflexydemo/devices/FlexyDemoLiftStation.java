package com.hms.ewon.ignitionflexydemo.devices;

import com.ewon.ewonitf.EWException;
import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a simple industrial lift station with simulated data.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoLiftStation extends FlexyDemoFlexy {

  /**
   * The lower bound for simulated flow values
   */
  private final int flowLowGPM;

  /**
   * The upper bound for simulated flow values
   */
  private final int flowHighGPM;

  /**
   * The ideal value for simulated flow values
   */
  private final int flowIdealGPM;


  /**
   * Basic <code>FlexyDemoLiftStation</code> constructor. Create and initialize a basic industrial
   * lift station with simulated data.
   *
   * @param name name of this <code>FlexyDemoLiftStation</code>
   * @param flowLowGPM lower bound for flow value
   * @param flowHighGPM upper bound for flow value
   * @param flowIdealGPM ideal value for flow
   * @param initPowerStatus initial power status at device creation
   */
  public FlexyDemoLiftStation(String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM,
      boolean initPowerStatus) {
    super(name);
    this.flowLowGPM = flowLowGPM;
    this.flowHighGPM = flowHighGPM;
    this.flowIdealGPM = flowIdealGPM;
    this.initPowerStatus = initPowerStatus;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FLOW"), FlexyDemoTagConfig.TYPE_INTEGER));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("PWR"), FlexyDemoTagConfig.TYPE_BOOLEAN));
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
    int newFlow = 0;
    if (FlexyDemoTagManager.getTagAsBoolean(getTagFullName("PWR"))) {
      newFlow = FlexyDemo.randomIntMidWeight(flowLowGPM, flowHighGPM, flowIdealGPM);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("FLOW"), newFlow);
  }

}
