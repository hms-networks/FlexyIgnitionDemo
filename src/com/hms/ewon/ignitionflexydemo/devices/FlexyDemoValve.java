package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a simple valve.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoValve extends FlexyDemoFlexy {

  /**
   * True/False for valve open/closed
   */
  private final boolean open;

  /**
   * Lowest value for simulated flow (GPM)
   */
  private final int flowLowGPM;

  /**
   * Highest value for simulated flow (GPM)
   */
  private final int flowHighGPM;

  /**
   * Ideal weighted value for simulated flow (GPM)
   */
  private final int flowIdealGPM;

  /**
   * Basic <code>FlexyDemoValve</code> constructor. Create and initialize a simple valve.
   *
   * @param name name of this <code>FlexyDemoValve</code>
   * @param flowLowGPM lower bound for flow value
   * @param flowHighGPM upper bound for flow value
   * @param flowIdealGPM ideal value for flow
   */
  public FlexyDemoValve(String name, boolean open, int flowLowGPM, int flowHighGPM,
      int flowIdealGPM) {
    super(name);
    this.open = open;
    this.flowLowGPM = flowLowGPM;
    this.flowHighGPM = flowHighGPM;
    this.flowIdealGPM = flowIdealGPM;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("OPEN"), FlexyDemoTagConfig.TYPE_BOOLEAN));
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FLOW"), FlexyDemoTagConfig.TYPE_INTEGER));
  }

  /**
   * Method to set tags to default values, if necessary
   */
  protected void tagDefaults() {
    FlexyDemoTagManager.setTagAsBoolean(getTagFullName("OPEN"), open);
  }

  /**
   * Handle tag and data simulation updates. This method is called every {@link
   * FlexyDemo#APP_CYCLE_TIME_MS} cycle.
   */
  protected void runCycleUpdate() {
    int newFlow = 0;
    if (open) {
      newFlow = FlexyDemo.randomIntMidWeight(flowLowGPM, flowHighGPM, flowIdealGPM);
    }
    FlexyDemoTagManager.setTagAsInt(getTagFullName("FLOW"), newFlow);
  }

}
