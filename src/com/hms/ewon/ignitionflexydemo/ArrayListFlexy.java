package com.hms.ewon.ignitionflexydemo;

import java.util.ArrayList;

/**
 * A Helper Class That Implements an ArrayList of FlexyDemoFlexy Objects
 *
 * @see ArrayList
 */
class ArrayListFlexy {

    /**
     * Main ArrayList Used
     */
    private ArrayList master = new ArrayList();

    /**
     * Appends the specified element to the end of this list.
     *
     * @param el element to be appended to this list
     */
    void add( FlexyDemoFlexy el ) {
        master.add( el );
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     *
     * @return the element at the specified position in this list
     *
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    FlexyDemoFlexy get( int index ) {
        return ( FlexyDemoFlexy ) master.get( index );
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size() {
        return master.size();
    }

}
