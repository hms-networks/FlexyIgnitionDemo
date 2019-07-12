package com.hms.ewon.ignitionflexydemo.devices;

import com.hms.ewon.ignitionflexydemo.FlexyDemo;
import com.hms.ewon.ignitionflexydemo.FlexyDemoFlexy;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagConfig;
import com.hms.ewon.ignitionflexydemo.FlexyDemoTagManager;

/**
 * FlexyDemoFlexy implementation of a sludge digestion tank
 *
 * @see FlexyDemoFlexy
 */
public class FlexyDemoSludgeDigester extends FlexyDemoFlexy {

  /**
   * The lowest value for simulated fill
   */
  private final int fillLowPerc;

  /**
   * The highest value for simulated fill
   */
  private final int fillHighPerc;

  /**
   * The ideal value for simulated fill
   */
  private final int fillIdealPerc;

  /**
   * Basic <code>FlexyDemoSludgeDigester</code> constructor. Create and initialize a sludge digester
   * with simulated data for fill.
   *
   * @param name name of this <code>FlexyDemoSludgeDigester</code>
   * @param fillLowPerc lower bound for fill value
   * @param fillHighPerc upper bound for fill value
   * @param fillIdealPerc ideal value for fill
   */
  public FlexyDemoSludgeDigester(String name, int fillLowPerc, int fillHighPerc,
      int fillIdealPerc) {
    super(name);
    this.fillLowPerc = fillLowPerc;
    this.fillHighPerc = fillHighPerc;
    this.fillIdealPerc = fillIdealPerc;
  }

  /**
   * Method to handle creation and default value of applicable tags
   */
  protected void initTagConfigs() {
    addTagConfig(new FlexyDemoTagConfig(getTagFullName("FILL"), FlexyDemoTagConfig.TYPE_INTEGER));
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
    int newFill = FlexyDemo.randomIntMidWeight(fillLowPerc, fillHighPerc, fillIdealPerc);
    FlexyDemoTagManager.setTagAsInt(getTagFullName("FILL"), newFill);
  }

}
