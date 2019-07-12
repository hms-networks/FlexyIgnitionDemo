package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a simple metal bar screen.
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoBarScreen extends FlexyDemoFlexy {

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
   * Basic <code>FlexyDemoBarScreen</code> constructor. Create and initialize a simple metal bar
   * screen with simulated data for flow.
   *
   * @param name name of this <code>FlexyDemoBarScreen</code>
   * @param flowLowGPM lower bound for flow value
   * @param flowHighGPM upper bound for flow value
   * @param flowIdealGPM ideal value for flow
   */
  public FlexyDemoBarScreen(String name, int flowLowGPM, int flowHighGPM, int flowIdealGPM) {
    super(name);
    this.flowLowGPM = flowLowGPM;
    this.flowHighGPM = flowHighGPM;
    this.flowIdealGPM = flowIdealGPM;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FLOW"), FlexyDemoTagConfig.TYPE_INTEGER));
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
    int newFlow = FlexyDemo.randomIntLowWeight(flowLowGPM, flowHighGPM, flowIdealGPM);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("FLOW"), newFlow);
  }

}
