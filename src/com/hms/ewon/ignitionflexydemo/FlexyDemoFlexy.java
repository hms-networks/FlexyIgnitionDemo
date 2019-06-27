package com.hms.ewon.ignitionflexydemo;

import com.ewon.ewonitf.EWException;
import com.ewon.ewonitf.TagControl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract Class Implementation of a Flexy And Devices Connected to Flexy
 *
 * @see FlexyDemoScenario
 */
public abstract class FlexyDemoFlexy implements Runnable {

    /**
     * Thread for Simulation of Data Tags and Values
     */
    private Thread flexyDemoThread;

    /**
     * Name of Flexy/Machine Unit
     */
    private final String name;

    /**
     * Internal Storage for Flexy Tags and Associated Values
     */
    private final HashMap tags;

    /**
     * Basic <code>FlexyDemoFlexy</code> constructor. Primarily called by implementation specific constructors
     *
     * @param name name of this <code>FlexyDemoFlexy</code>
     */
    public FlexyDemoFlexy( String name ) {
        this.name = name;
        this.tags = new HashMap();
    }

    /**
     * Returns the name of this <code>FlexyDemoFlexy</code>
     *
     * @return name of this <code>FlexyDemoFlexy</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Set value of tag with given name to given value.
     *
     * @param name  name of tag
     * @param value new tag value
     */
    protected void setTag( String name, Object value ) {
        tags.put( name, value );
    }

    /**
     * Return value of tag with given name
     *
     * @param name name of tag
     *
     * @return value of tag with given name
     */
    public Object getTag( String name ) {
        return tags.get( name );
    }

    /**
     * Return a {@link java.util.Map} with all tags belonging to this <code>FlexyDemoFlexy</code>
     *
     * @return Map of this <code>FlexyDemoFlexy</code>'s tags
     */
    public Map getAllTags() {
        return tags;
    }

    /**
     * Method to start Flexy data simulation. Data simulation may be stopped via call to {@link #stopRunning()}
     */
    void startRunning() {
        if ( flexyDemoThread != null ) return;

        flexyDemoThread = new Thread( this );
        flexyDemoThread.start();
    }

    /**
     * Method to stop Flexy data simulation. This is a non-destructive action, and {@link #startRunning()} may be called
     * after this.
     */
    void stopRunning() {
        if ( flexyDemoThread == null ) return;

        flexyDemoThread.stop();
        flexyDemoThread = null;
    }

    /**
     * Write all stored tags to the remote Flexy
     */
    private void writeAllTags() {
        Iterator tagPairs = tags.entrySet().iterator();
        while ( tagPairs.hasNext() ) {
            Map.Entry tag = ( Map.Entry ) tagPairs.next();
            String tagName = ( String ) tag.getKey();
            Object tagValue = tag.getValue();

            try {
                TagControl thisTag = new TagControl( name + "-" + tagName );
                if ( tagValue instanceof Integer ) {
                    thisTag.setTagValueAsInt( ( ( Integer ) tagValue ).intValue() );
                } else if ( tagValue instanceof Long ) {
                    thisTag.setTagValueAsLong( ( ( Long ) tagValue ).longValue() );
                }
            } catch ( EWException e ) {
                System.out.println( "FlexyDemo encountered an error while updating " + tagName + " as a tag! Check " +
                        "your connection to Flexy, and ensure that tag exists. ID: FDF01" );
            }
        }
    }

    /**
     * Process tag updates on each {@link FlexyDemo#APP_CYCLE_TIME_MS} cycle
     */
    protected abstract void runCycleUpdate();

    /**
     * Run method for handling tag updates/data simulation
     *
     * @see java.lang.Runnable
     */
    public void run() {
        while ( FlexyDemo.isRunning() ) {
            runCycleUpdate();
            writeAllTags();

            try {
                Thread.sleep( FlexyDemo.APP_CYCLE_TIME_MS );
            } catch ( InterruptedException e ) {
                System.out.println( "FlexyDemo encountered an error while waiting for next tag update. Application " +
                        "will now terminate to prevent undefined behavior and system overload. ID: FDF02" );
                System.exit( -1 );
            }
        }
    }

}