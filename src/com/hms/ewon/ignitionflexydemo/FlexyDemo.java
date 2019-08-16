package com.hms.ewon.ignitionflexydemo;

import com.ewon.ewonitf.EWException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Static Class Implementation of a System of Various Flexy Demo Scenarios
 *
 * @see FlexyDemoScenario
 */
public class FlexyDemo {

  /**
   * Number of Scenarios Available
   */
  private static final int NUM_SCENARIOS = 2;

  /**
   * Constant for length of each Demo cycle
   */
  public final static int APP_CYCLE_TIME_MS = 1000;

  /**
   * Constant for No Scenario Selected
   */
  private static final int NO_SCENARIO_SELECTED = -1;

  /**
   * Constant for Demo Stop Tag RUNNING
   */
  private static final int DEMO_NOT_STOPPED = 0;

  /**
   * Constant for the minimum amount of available memory before the application pauses and forces
   * garbage collection
   */
  private static final int MIN_FREE_MEMORY_BYTES = 1000000;


  /*
   * Constant for the maximum amount of time the application will wait for memory to be deallocated
   * before stopping
   */
  private static final int MAX_MEMORY_WAIT_MILLIS = 10000;

  /**
   * Constant fro Demo Stop Tag STOPPED
   */
  private final static int DEMO_STOPPED = 1;

  /**
   * Instance of Java Random used throughout application.
   */
  private final static Random APP_RANDOM = new Random();

  /**
   * Internal Variable Used to Store Current Scenario
   */
  private static int currentScenario = NO_SCENARIO_SELECTED;

  /**
   * Hash Map Linking Each Demo Scenario to Its String Name
   */
  private static ArrayList flexyDemoScenarios = new ArrayList();


  /**
   * Add a <code>FlexyDemoScenario</code> to this <code>FlexyDemo</code>
   *
   * @param scenario FlexyDemoScenario to add to this <code>FlexyDemo</code>
   */
  private static void addDemoScenario(FlexyDemoScenario scenario) {
    flexyDemoScenarios.add(scenario);
  }

  /**
   * Check DEMOSCENARIO eWON tag for changes in scenario selection.
   *
   * @return Index of chosen scenario
   */
  private static int getChosenScenario() throws EWException {
    int chosen = (int) FlexyDemoTagManager.getTagAsInt("DEMOSCENARIO") - 1;
    if (chosen >= NUM_SCENARIOS) {
      System.out
          .println("FlexyDemo couldn't locate the selected scenario. Reverting to no scenario.");
      return -1;
    }
    return chosen;
  }

  /**
   * Return a random int between <code>low</code> and <code>high</code> with no weight
   *
   * @param low minimum value chosen
   * @param high maximum value chosen
   * @return random int between <code>low</code> and <code>high</code> with no weight
   */
  public static int randomIntNoWeight(int low, int high) {
    return APP_RANDOM.nextInt(high - low) + low;
  }

  /**
   * Return a random int between <code>low</code> and <code>high</code> with low weight towards
   * <code>ideal</code>
   *
   * @param low minimum value chosen
   * @param high maximum value chosen
   * @param ideal ideal value that has low weight
   * @return random int between <code>low</code> and <code>high</code> with low weight towards
   * <code>ideal</code>
   */
  public static int randomIntLowWeight(int low, int high, int ideal) {
    // Calculate Two Random Numbers Between High and Low
    int r1 = APP_RANDOM.nextInt(high - low) + low;
    int r2 = APP_RANDOM.nextInt(high - low) + low;

    // Calculate Difference Between Ideal and each Random Value
    int diff1 = Math.abs(ideal - r1);
    int diff2 = Math.abs(ideal - r2);

    // Return random value that is closest to ideal
    int minDiff = Math.min(diff1, diff2);
    if (minDiff == diff1) {
      return r1;
    } else {
      return r2;
    }
  }

  /**
   * Return a random int between <code>low</code> and <code>high</code> with medium weight towards
   * <code>ideal</code>
   *
   * @param low minimum value chosen
   * @param high maximum value chosen
   * @param ideal ideal value that has medium weight
   * @return random int between <code>low</code> and <code>high</code> with medium weight towards
   * <code>ideal</code>
   */
  public static int randomIntMidWeight(int low, int high, int ideal) {
    // Calculate Three Random Numbers Between High and Low
    int r1 = APP_RANDOM.nextInt(high - low) + low;
    int r2 = APP_RANDOM.nextInt(high - low) + low;
    int r3 = APP_RANDOM.nextInt(high - low) + low;

    // Calculate Difference Between Ideal and each Random Value
    int diff1 = Math.abs(ideal - r1);
    int diff2 = Math.abs(ideal - r2);
    int diff3 = Math.abs(ideal - r3);

    // Return random value that is closest to ideal
    int minDiff = Math.min(diff1, Math.min(diff2, diff3));
    if (minDiff == diff1) {
      return r1;
    } else if (minDiff == diff2) {
      return r2;
    } else {
      return r3;
    }
  }

  /**
   * Return a random int between <code>low</code> and <code>high</code> with high towards
   * <code>ideal</code>
   *
   * @param low minimum value chosen
   * @param high maximum value chosen
   * @param ideal ideal value that has high weight
   * @return random int between <code>low</code> and <code>high</code> with high weight towards
   * <code>ideal</code>
   */
  public static int randomIntHighWeight(int low, int high, int ideal) {
    // Calculate Four Random Numbers Between High and Low
    int r1 = APP_RANDOM.nextInt(high - low) + low;
    int r2 = APP_RANDOM.nextInt(high - low) + low;
    int r3 = APP_RANDOM.nextInt(high - low) + low;
    int r4 = APP_RANDOM.nextInt(high - low) + low;

    // Calculate Difference Between Ideal and each Random Value
    int diff1 = Math.abs(ideal - r1);
    int diff2 = Math.abs(ideal - r2);
    int diff3 = Math.abs(ideal - r3);
    int diff4 = Math.abs(ideal - r4);

    // Return random value that is closest to ideal
    int minDiff = Math.min(diff1, Math.min(diff2, Math.min(diff3, diff4)));
    if (minDiff == diff1) {
      return r1;
    } else if (minDiff == diff2) {
      return r2;
    } else if (minDiff == diff3) {
      return r3;
    } else {
      return r4;
    }
  }

  /**
   * Return a random double between <code>low</code> and <code>high</code> with high towards
   * <code>ideal</code>
   *
   * @param low minimum value chosen
   * @param high maximum value chosen
   * @param ideal ideal value that has high weight
   * @return random double between <code>low</code> and <code>high</code> with high weight towards
   * <code>ideal</code>
   */
  public static double randomDoubleHighWeight(double low, double high, double ideal) {
    // Calculate Four Random Numbers Between 0.0 and 1.0 and round to 1 decimal place
    double r1 = low + APP_RANDOM.nextDouble() * (high - low);
    double r2 = low + APP_RANDOM.nextDouble() * (high - low);
    double r3 = low + APP_RANDOM.nextDouble() * (high - low);
    double r4 = low + APP_RANDOM.nextDouble() * (high - low);

    // Round to Two Decimal Places
    r1 = Math.round(r1 * 100.0) / 100.0;
    r2 = Math.round(r2 * 100.0) / 100.0;
    r3 = Math.round(r3 * 100.0) / 100.0;
    r4 = Math.round(r4 * 100.0) / 100.0;

    // Calculate Difference Between Ideal and each Random Value
    double diff1 = Math.abs(ideal - r1);
    double diff2 = Math.abs(ideal - r2);
    double diff3 = Math.abs(ideal - r3);
    double diff4 = Math.abs(ideal - r4);

    // Return random value that is closest to ideal
    double minDiff = Math.min(diff1, Math.min(diff2, Math.min(diff3, diff4)));
    if (minDiff == diff1) {
      return r1;
    } else if (minDiff == diff2) {
      return r2;
    } else if (minDiff == diff3) {
      return r3;
    } else {
      return r4;
    }
  }

  /**
   * Return true if this <code>FlexyDemo</code> is active and running.
   *
   * @return true if this <code>FlexyDemo</code> is active and running.
   */
  public static boolean isRunning() throws EWException {
    return FlexyDemoTagManager.getTagAsInt("DEMOSTOP") < DEMO_STOPPED;
  }

  /**
   * Activate and process this <code>FlexyDemo</code>
   */
  private static void runDemo() {
    boolean isRunning = true;
    while (isRunning) {
      // CHECK FOR FREE MEMORY AND PAUSE FOR GC IF NEEDED
      long currFreeMemoryBytes = Runtime.getRuntime().freeMemory();
      long gcStartTimeMillis = System.currentTimeMillis();
      if (currFreeMemoryBytes < MIN_FREE_MEMORY_BYTES) {
        System.gc();

        while (currFreeMemoryBytes < MIN_FREE_MEMORY_BYTES) {
          currFreeMemoryBytes = Runtime.getRuntime().freeMemory();

          // KILL THE APPLICATION IF UNABLE TO FREE MEMORY IN TIME
          long gcElapsedTimeMillis = System.currentTimeMillis() - gcStartTimeMillis;
          if (gcElapsedTimeMillis > MAX_MEMORY_WAIT_MILLIS) {
            System.out.println("[FlexyDemo] Application unable to allocate memory."
                + " Application will now terminate to prevent unexpected behavior.");
            System.exit(-1);
          }
        }
      }

      // UPDATE SELECTED SCENARIO
      int chosenScenario = currentScenario;
      try {
        chosenScenario = getChosenScenario();
      } catch (EWException e) {
        System.out.println(
            "[FlexyDemo] An error occurred while updating the current running demo scenario. Demo" +
                " scenario switching may be unavailable.");
      }

      // STOP PREVIOUS SCENARIO
      if (chosenScenario != currentScenario) {
        if (currentScenario > -1) {
          FlexyDemoScenario previousRunning = (FlexyDemoScenario) flexyDemoScenarios
              .get(currentScenario);
          previousRunning.stop();
        }
        // MARK CURRENT SCENARIO
        currentScenario = chosenScenario;

        // FORCE GARBAGE COLLECTION
        System.gc();

        // START NEW SELECTED SCENARIO
        if (chosenScenario >= 0 && chosenScenario < flexyDemoScenarios.size()) {
          FlexyDemoScenario nextRunning = (FlexyDemoScenario) flexyDemoScenarios
              .get(chosenScenario);
          nextRunning.start();
        }
      }

      // UPDATE isRunning TAG
      try {
        isRunning = isRunning();
      } catch (EWException e) {
        System.out.println(
            "[FlexyDemo] An error occurred while checking demo run status. Application will now " +
                "terminate to prevent unexpected behavior.");
        System.exit(-1);
      }

      // WAIT ONE SECOND BEFORE LOOPING AGAIN
      try {
        Thread.sleep(APP_CYCLE_TIME_MS);
      } catch (InterruptedException e) {
        System.out.println(
            "FlexyDemo encountered an error while waiting for next tag update. Application " +
                "will now terminate to prevent undefined behavior. ID: FD02");
        System.exit(-1);
      }
    }
  }

  /**
   * Method to trigger <code>FlexyDemoTagManager</code> uploadTagConfig with the scenarios
   * in this <code>FlexyDemo</code>
   */
  private static void uploadTagConfig() {
    if (flexyDemoScenarios.size() == 0) {
      System.out.println("[FlexyDemo] No scenarios configured during Tag Config " +
          "Upload");
    }

    FlexyDemoTagManager.uploadTagConfig(flexyDemoScenarios);
  }


  /**
   * Construct and run a system of Flexy demo scenarios
   *
   * @param args ignored
   * @see FlexyDemoScenario
   */
  public static void main(String[] args) {
    // START MSG
    System.out
        .println(
            "FlexyDemo has started! Tags and Data are simulated and may not reflect actual system data.");

    // CREATE DEMO
    addDemoScenario(new FlexyDemoScenarioWastewater("HMS Wastewater Treatment Facility"));
    addDemoScenario(new FlexyDemoScenarioBottlingPlant("HMS Bubbles (Soda) Bottling Plant"));

    // PERFORM AUTOMATIC TAG CONFIGURATION
    uploadTagConfig();

    // RUN DEMO
    runDemo();

    // EXIT MSG
    System.out.println(
        "FlexyDemo has terminated! Tags and Data are no longer simulated or updated, and may not reflect actual system data.");
  }

}
