package com.hms.ewon.ignitionflexydemo;

import com.ewon.ewonitf.EWException;
import com.ewon.ewonitf.TagControl;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Static Class Implementation of a System of Various Flexy Demo Scenarios
 *
 * @see FlexyDemoScenario
 */
public class FlexyDemo {

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
     * eWON Tag To Stop Demo (0=RUN,1=STOP)
     */
    private static TagControl demoStopTag;

    /**
     * eWON Tag to Select Scenario (0=NONE)
     */
    private static TagControl demoScenarioTag;


    /**
     * Add a <code>FlexyDemoScenario</code> to this <code>FlexyDemo</code>
     *
     * @param scenario FlexyDemoScenario to add to this <code>FlexyDemo</code>
     */
    private static void addDemoScenario( FlexyDemoScenario scenario ) {
        flexyDemoScenarios.add( scenario );
    }

    /**
     * Check DEMOSCENARIO eWON tag for changes in scenario selection.
     *
     * @return Index of chosen scenario
     */
    private static int getChosenScenario() {
        return ( int ) demoScenarioTag.getTagValueAsLong() - 1;
    }

    /**
     * Reset <code>FlexyDemo</code> system tags to their default value. Specifically, DEMOSTOP and DEMOSCENARIO are
     * reset to 0.
     */
    private static void resetAppTags() {
        try {
            demoStopTag.setTagValueAsInt( DEMO_NOT_STOPPED );
            // Add 1 necessary to account for internal 0-based vs Flexy 1-based
            demoScenarioTag.setTagValueAsInt( NO_SCENARIO_SELECTED + 1 );
        } catch ( EWException e ) {
            System.out.println( "FlexyDemo encountered an error while resetting application tags. Application will " +
                    "now terminate to prevent undefined behavior. ID: FD01" );
            System.exit( -1 );
        }
    }

    /**
     * Return a random value between <code>low</code> and <code>high</code> with no weight
     *
     * @param low  minimum value chosen
     * @param high maximum value chosen
     *
     * @return random number between <code>low</code> and <code>high</code> with no weight
     */
    public static int randomIntNoWeight( int low, int high ) {
        return APP_RANDOM.nextInt( high - low ) + low;
    }

    /**
     * Return a random value between <code>low</code> and <code>high</code> with low weight towards <code>ideal</code>
     *
     * @param low   minimum value chosen
     * @param high  maximum value chosen
     * @param ideal ideal value that has low weight
     *
     * @return random number between <code>low</code> and <code>high</code> with low weight towards
     * <code>ideal</code>
     */
    public static int randomIntLowWeight( int low, int high, int ideal ) {
        // Calculate Two Random Numbers Between High and Low
        int r1 = APP_RANDOM.nextInt( high - low ) + low;
        int r2 = APP_RANDOM.nextInt( high - low ) + low;

        // Calculate Difference Between Ideal and each Random Value
        int diff1 = Math.abs( ideal - r1 );
        int diff2 = Math.abs( ideal - r2 );


        // Return random value that is closest to ideal
        int minDiff = Math.min( diff1, diff2 );
        if ( minDiff == diff1 ) return r1;
        else return r2;
    }

    /**
     * Return a random value between <code>low</code> and <code>high</code> with medium weight towards
     * <code>ideal</code>
     *
     * @param low   minimum value chosen
     * @param high  maximum value chosen
     * @param ideal ideal value that has medium weight
     *
     * @return random number between <code>low</code> and <code>high</code> with medium weight towards
     * <code>ideal</code>
     */
    public static int randomIntMidWeight( int low, int high, int ideal ) {
        // Calculate Three Random Numbers Between High and Low
        int r1 = APP_RANDOM.nextInt( high - low ) + low;
        int r2 = APP_RANDOM.nextInt( high - low ) + low;
        int r3 = APP_RANDOM.nextInt( high - low ) + low;

        // Calculate Difference Between Ideal and each Random Value
        int diff1 = Math.abs( ideal - r1 );
        int diff2 = Math.abs( ideal - r2 );
        int diff3 = Math.abs( ideal - r3 );


        // Return random value that is closest to ideal
        int minDiff = Math.min( diff1, Math.min( diff2, diff3 ) );
        if ( minDiff == diff1 ) return r1;
        else if ( minDiff == diff2 ) return r2;
        else return r3;
    }

    /**
     * Return a random value between <code>low</code> and <code>high</code> with high towards <code>ideal</code>
     *
     * @param low   minimum value chosen
     * @param high  maximum value chosen
     * @param ideal ideal value that has high weight
     *
     * @return random number between <code>low</code> and <code>high</code> with high weight towards
     * <code>ideal</code>
     */
    public static int randomIntHighWeight( int low, int high, int ideal ) {
        // Calculate Four Random Numbers Between High and Low
        int r1 = APP_RANDOM.nextInt( high - low ) + low;
        int r2 = APP_RANDOM.nextInt( high - low ) + low;
        int r3 = APP_RANDOM.nextInt( high - low ) + low;
        int r4 = APP_RANDOM.nextInt( high - low ) + low;

        // Calculate Difference Between Ideal and each Random Value
        int diff1 = Math.abs( ideal - r1 );
        int diff2 = Math.abs( ideal - r2 );
        int diff3 = Math.abs( ideal - r3 );
        int diff4 = Math.abs( ideal - r4 );


        // Return random value that is closest to ideal
        int minDiff = Math.min( diff1, Math.min( diff2, Math.min( diff3, diff4 ) ) );
        if ( minDiff == diff1 ) return r1;
        else if ( minDiff == diff2 ) return r2;
        else if ( minDiff == diff3 ) return r3;
        else return r4;
    }

    /**
     * Return true if this <code>FlexyDemo</code> is active and running.
     *
     * @return true if this <code>FlexyDemo</code> is active and running.
     */
    public static boolean isRunning() {
        return demoStopTag.getTagValueAsLong() < DEMO_STOPPED;
    }

    /**
     * Activate and process this <code>FlexyDemo</code>
     */
    private static void runDemo() {
        while ( isRunning() ) {
            // Notice: Subtract 1 necessary as Java ArrayList is 0-based.
            int chosenScenario = getChosenScenario();

            // Stop Previous Scenario
            if ( chosenScenario != currentScenario && currentScenario > -1 ) {
                FlexyDemoScenario previousRunning = ( FlexyDemoScenario ) flexyDemoScenarios.get( currentScenario );
                previousRunning.stop();
            }

            // Start New Selected Scenario
            if ( chosenScenario >= 0 && chosenScenario < flexyDemoScenarios.size() ) {
                FlexyDemoScenario nextRunning = ( FlexyDemoScenario ) flexyDemoScenarios.get( chosenScenario );
                nextRunning.start();
            }

            // Mark Current Scenario
            currentScenario = chosenScenario;

            // WAIT ONE SECOND BEFORE LOOPING AGAIN
            try {
                Thread.sleep( APP_CYCLE_TIME_MS );
            } catch ( InterruptedException e ) {
                System.out.println( "FlexyDemo encountered an error while waiting for next tag update. Application " +
                        "will now terminate to prevent undefined behavior. ID: FD02" );
                System.exit( -1 );
            }
        }
    }

    public static void createAndInitTags() {
        try {
            demoStopTag = new TagControl( "DEMOSTOP" );
            demoScenarioTag = new TagControl( "DEMOSCENARIO" );
        } catch ( Exception e ) {
            try {
                System.out.println( "FlexyDemo was unable to locate required tags on your system. They will now be " +
                        "created." );
                FlexyDemo.copyVarLst( FlexyDemo.class.getResourceAsStream( "/VarLstFiles/NoScenario.csv" ) );
                demoStopTag = new TagControl( "DEMOSTOP" );
                demoScenarioTag = new TagControl( "DEMOSCENARIO" );
            } catch ( Exception ex ) {
                ex.printStackTrace();
                System.out.println( "FlexyDemo was still unable to locate required tags on your system. Application " +
                        "will now terminate to prevent undefined behavior. ID: FD03" );
                System.exit( -1 );
            }
        }
    }

    /**
     * Handle Copying Source File to Destination File
     *
     * @param csvSrc Stream to Copy From for CSV
     */
    static void copyVarLst( InputStream csvSrc) {
        try {
            System.out.println( "FlexyDemo is starting FTP Tag Configuration -- Please Wait!" );
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect( "localhost" );
            if ( !FTPReply.isPositiveCompletion( ftpClient.getReplyCode() ) ) {
                ftpClient.disconnect();
                System.out.println( "FlexyDemo encountered an error while preparing FTP Tag Configuration. " +
                        "Application will now terminate to prevent undefined behavior. ID: FD04" );
            }
            ftpClient.login( "adm", "adm" );
            ftpClient.setFileType( FTP.BINARY_FILE_TYPE );
            ftpClient.storeFile( "var_lst.csv", csvSrc );
            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println( "FlexyDemo has finished FTP Tag Configuration -- Success!" );
        } catch ( IOException e ) {
            e.printStackTrace();
            System.out.println( "FlexyDemo encountered an error while configuring tags via FTP. Application will now " +
                    "terminate to prevent undefined behavior. ID: FD05" );
        }
    }


    /**
     * Construct and run a system of Flexy demo scenarios
     *
     * @param args ignored
     *
     * @see FlexyDemoScenario
     */
    public static void main( String[] args ) {
        // START MSG
        System.out.println( "FlexyDemo has started! Tags and Data are simulated and may not reflect actual system data." );

        // Create TagControl Objects and Ensure Tags Exists on Flexy
        createAndInitTags();

        // RESET TAGS
        resetAppTags();

        // CREATE DEMO
        addDemoScenario( new FlexyDemoScenarioWastewater( "ExampleWastewaterPlant" ) );
        addDemoScenario( new FlexyDemoScenarioPackageSorting( "ExamplePackageSortFacility" ) );

        // RUN DEMO
        runDemo();

        // EXIT MSG
        System.out.println( "FlexyDemo has terminated! Tags and Data are no longer simulated or updated, and may not reflect actual system data." );
    }

}
