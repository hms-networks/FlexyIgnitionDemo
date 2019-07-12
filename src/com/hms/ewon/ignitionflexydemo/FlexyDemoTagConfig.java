package com.hms.ewon.ignitionflexydemo;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple Class Representing a Flexy Tag Configuration
 */
public class FlexyDemoTagConfig {

  /**
   * Static Value for Boolean Tag Type
   */
  public final static int TYPE_BOOLEAN = 0;

  /**
   * Static Value for Double Tag Type
   */
  public final static int TYPE_DOUBLE = 1;

  /**
   * Static Value for Integer Tag Type
   */
  public final static int TYPE_INTEGER = 2;

  /**
   * Tag Type
   */
  private final int tagType;

  /**
   * Tag Name
   */
  private final String tagName;

  /**
   * Boolean True/False for Tag Alarm Enabled
   */
  private final boolean tagAlarm;

  /**
   * HighHigh Value for Tag Alarm
   */
  private final double tagHighHigh;

  /**
   * High Value for Tag Alarm
   */
  private final double tagHigh;

  /**
   * Low Value for Tag Alarm
   */
  private final double tagLow;

  /**
   * LowLow Value for Tag Alarm
   */
  private final double tagLowLow;

  /**
   * Deadband Value for Tag Alarm
   */
  private final double tagDeadband;

  /**
   * Constructor for Flexy Demo Tag Config with Alarming Disabled
   *
   * @param tagName Name of Tag
   * @param tagType Type of Tag
   */
  public FlexyDemoTagConfig(String tagName, int tagType) {
    if (tagType != TYPE_BOOLEAN && tagType != TYPE_DOUBLE && tagType != TYPE_INTEGER) {
      throw new IllegalArgumentException("tagType specified is invalid");
    }

    this.tagName = tagName;
    this.tagType = tagType;
    this.tagAlarm = false;
    tagDeadband = 0.0;
    tagHigh = 0.0;
    tagHighHigh = 0.0;
    tagLow = 0.0;
    tagLowLow = 0.0;
  }

  /**
   * Constructor for Flexy Demo Tag Config with Alarming Enabled
   *
   * @param tagName Name of Tag
   * @param tagType Type of Tag
   * @param tagLowLow LowLow Alarm Value
   * @param tagLow Low Alarm Value
   * @param tagHigh High Alarm Value
   * @param tagHighHigh HighHigh Alarm Value
   * @param tagDeadband Alarm Deadband Value
   */
  public FlexyDemoTagConfig(String tagName, int tagType, double tagLowLow, double tagLow,
      double tagHigh,
      double tagHighHigh, double tagDeadband) {
    if (tagType != TYPE_BOOLEAN && tagType != TYPE_DOUBLE && tagType != TYPE_INTEGER) {
      throw new IllegalArgumentException("tagType specified is invalid");
    }

    this.tagName = tagName;
    this.tagType = tagType;
    this.tagLow = tagLow;
    this.tagHigh = tagHigh;
    this.tagHighHigh = tagHighHigh;
    this.tagDeadband = tagDeadband;
    this.tagAlarm = true;
    this.tagLowLow = tagLowLow;
  }

  /**
   * Get Tag Name
   *
   * @return Tag Name
   */
  String getTagName() {
    return tagName;
  }

  /**
   * Formulate a JSON String Representation of This Tag Config
   *
   * @return JSON Tag Config
   */
  String getTagAsJSON() {
    // GET DATE/TIME AS STRING
    String dateTime = new SimpleDateFormat("D T").format(new Date());

    // GET tagAlarm AS INTEGER REPRESENTATION OF BOOLEAN
    int tagAlarmInt = tagAlarm ? 1 : 0;

    return FlexyDemoTagManager.getNewTagID() + ";\"" + tagName + "\";\"\";\"MEM\";\"\";\"" + tagName
        +
        "\";1.000000;0" + ".000000;0;" + tagAlarmInt + ";0;1;0;0;0;0;0;0;1;0;\"\";" + tagHigh + ";"
        + tagLow +
        ";0;" + tagDeadband + ";0;0;0;0;1;" + "600;10;-1.000000;0;" + tagLowLow + ";" + tagHighHigh
        +
        ";1;1.000000;0.000000;;\"\";\"\";\"\";" + "\"\";;;\"\";\"\";;\"\";;\"\";\"\";0;" + tagType
        + ";2;\"" +
        dateTime + "\";0;65472;5\r\n";
  }

}
