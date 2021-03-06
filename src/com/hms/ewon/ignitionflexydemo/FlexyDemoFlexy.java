package com.hms.ewon.ignitionflexydemo;

import com.ewon.ewonitf.EWException;

import java.util.ArrayList;

/**
 * Abstract Class Implementation of a Flexy And Devices Connected to Flexy
 *
 * @see FlexyDemoScenario
 */
public abstract class FlexyDemoFlexy implements Runnable {

  /**
   * Constant for Power Off
   */
  public static final boolean PWR_OFF = false;

  /**
   * Constant for Power On
   */
  public static final boolean PWR_ON = true;

  /**
   * Thread for Simulation of Data Tags and Values
   */
  private Thread flexyDemoThread;

  /**
   * Name of Flexy/Machine Unit
   */
  private final String name;

  /**
   * Temporary variable used for initializing the power state
   */
  public boolean initPowerStatus = PWR_ON;

  /**
   * Variable that controls if <code>run<code> should be running
   */
  private boolean threadShouldRun = true;

  /**
   * Variable to Store Tag Configs
   */
  private final ArrayList tagConfigs = new ArrayList();

  /**
   * Full/Maximum Fill Value
   */
  public static final double FILL_FULL_PERCENT = 100.0;

  /**
   * Empty Fill Value
   */
  public static final double FILL_EMPTY_PERCENT = 0.0;

  /**
   * Basic <code>FlexyDemoFlexy</code> constructor. Primarily called by implementation specific
   * constructors
   *
   * @param name name of this <code>FlexyDemoFlexy</code>
   */
  public FlexyDemoFlexy(String name) {
    this.name = name;
    initTagConfigs();
  }

  /**
   * Method called at creation of a <code>FlexyDemoFlexy</code> to handle adding tags and their
   * configurations to the <code>FlexyDemoTagManager</code>
   */
  abstract protected void initTagConfigs();

  /**
   * Method called at activation of this <code>FlexyDemoFlexy</code> corresponding scenario to
   * handle resetting tags to default values, where applicable
   */
  abstract protected void tagDefaults();

  /**
   * Returns the name of this <code>FlexyDemoFlexy</code>
   *
   * @return name of this <code>FlexyDemoFlexy</code>
   */
  public String getName() {
    return name;
  }

  /**
   * Method to start Flexy data simulation. Data simulation may be stopped via call to {@link
   * #stopRunning()}
   */
  void startRunning() {
    if (flexyDemoThread != null) {
      return;
    }

    tagDefaults();

    threadShouldRun = true;

    flexyDemoThread = new Thread(this);
    flexyDemoThread.start();
  }

  /**
   * Method to stop Flexy data simulation. This is a non-destructive action, and {@link
   * #startRunning()} may be called after this.
   */
  void stopRunning() {
    if (flexyDemoThread == null) {
      return;
    }

    threadShouldRun = false;
    flexyDemoThread = null;
  }

  /**
   * Get List of Tag Configs for this Device
   *
   * @return ArrayList of Tag Configs for this Device
   */
  ArrayList getTagConfigs() {
    return tagConfigs;
  }

  /**
   * Adds Given Tag Config to this Device
   *
   * @param flexyDemoTagConfig Tag Config to Add
   */
  protected void addTagConfig(FlexyDemoTagConfig flexyDemoTagConfig) {
    tagConfigs.add(flexyDemoTagConfig);
  }

  /**
   * Get and Return Full Tag Name, Including Device Name
   *
   * @param tagBaseName Base Tag Name
   * @return Full Tag Name
   */
  protected String getTagFullName(String tagBaseName) {
    return this.name + "-" + tagBaseName;
  }

  /**
   * Process tag updates on each {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle
   */
  protected abstract void runCycleUpdate() throws EWException;

  /**
   * Run method for handling tag updates/data simulation
   *
   * @see java.lang.Runnable
   */
  public void run() {
    boolean demoIsRunning = true;
    while (demoIsRunning && threadShouldRun) {
      try {
        runCycleUpdate();
      } catch (EWException e) {
        System.out
            .println("[FlexyDemo] An error occurred while updating tags/data for " + name + "!");
      }

      // UPDATE isRunning TAG
      try {
        demoIsRunning = FlexyDemo.isRunning();
      } catch (EWException e) {
        System.out.println(
            "[FlexyDemo] An error occurred while checking demo run status. Application will now " +
                "terminate to prevent unexpected behavior.");
        System.exit(-1);
      }

      try {
        Thread.sleep(FlexyDemo.APP_CYCLE_TIME_MS);
      } catch (InterruptedException e) {
        System.out.println(
            "FlexyDemo encountered an error while waiting for next tag update. Application " +
                "will now terminate to prevent undefined behavior and system overload. ID: FDF02");
        System.exit(-1);
      }
    }
  }

}
