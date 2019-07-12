package com.hms.ewon.ignitionflexydemo;

import com.ewon.ewonitf.EWException;
import com.ewon.ewonitf.TagControl;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Static Class for Handling Flexy Tags
 */
public class FlexyDemoTagManager {

  /**
   * Tag Config for Demo Stop Tag
   */
  private static final FlexyDemoTagConfig DEMOSTOP_TAGCONFIG =
      new FlexyDemoTagConfig("DEMOSTOP", FlexyDemoTagConfig.TYPE_INTEGER);

  /**
   * Tag Config for Demo Scenario Selection Tag
   */
  private static final FlexyDemoTagConfig DEMOSCENARIO_TAGCONFIG =
      new FlexyDemoTagConfig("DEMOSCENARIO", FlexyDemoTagConfig.TYPE_INTEGER);

  /**
   * Header for var_lst.csv files
   */
  private static final String VARLST_HEADER =
      "\"Id\";\"Name\";\"Description\";\"ServerName\";\"TopicName\";\"Address\";\"Coef\";\"Offset\";\"LogEnabled\";"
          +
          "\"AlEnabled\";\"AlBool\";\"MemTag\";\"MbsTcpEnabled\";\"MbsTcpFloat\";\"SnmpEnabled\";\"RTLogEnabled\";"
          +
          "\"AlAutoAck\";\"ForceRO\";\"SnmpOID\";\"AutoType\";\"AlHint\";\"AlHigh\";\"AlLow\";\"AlTimeDB\";"
          +
          "\"AlLevelDB\";\"IVGroupA\";\"IVGroupB\";\"IVGroupC\";\"IVGroupD\";\"PageId\";\"RTLogWindow\";\"RTLogTimer\";"
          +
          "\"LogDB\";\"LogTimer\";\"AlLoLo\";\"AlHiHi\";\"MbsTcpRegister\";\"MbsTcpCoef\";\"MbsTcpOffset\";\"EEN\";"
          +
          "\"ETO\";\"ECC\";\"ESU\";\"EAT\";\"ESH\";\"SEN\";\"STO\";\"SSU\";\"TEN\";\"TSU\";\"FEN\";\"FFN\";\"FCO\";"
          +
          "\"KPI\";\"Type\";\"AlStat\";\"ChangeTime\";\"TagValue\";\"TagQuality\";\"AlType\"\r\n";

  /**
   * Counter for Unique Tag IDs
   */
  private static int TAG_ID_COUNTER = 1;

  /**
   * Get and Return Value of Tag as an Integer If unable to get value, Integer.MIN_VALUE is
   * returned
   *
   * @param tagName Name of Tag
   * @return Value of Tag with tagName
   */
  public static int getTagAsInt(String tagName) throws EWException {
    TagControl intTag = new TagControl(tagName);
    return (int) intTag.getTagValueAsLong();
  }

  /**
   * Get and Return Value of Tag as a Boolean If unable to get value, false is returned
   *
   * @param tagName Name of Tag
   * @return Value of Tag with tagName
   */
  public static boolean getTagAsBoolean(String tagName) throws EWException {
    TagControl boolTag = new TagControl(tagName);
    return boolTag.getTagValueAsLong() == 1;
  }

  /**
   * Get and Return Value of Tag as a Double If unable to get value, Double.MIN_VALUE is returned
   *
   * @param tagName Name of Tag
   * @return Value of Tag with tagName
   */
  public static double getTagAsDouble(String tagName) throws EWException {
    TagControl dblTag = new TagControl(tagName);
    return dblTag.getTagValueAsDouble();
  }

  /**
   * Set Value of Tag as an Integer
   *
   * @param tagName Name of Tag
   * @param newValue Value to Set
   */
  public static void setTagAsInt(String tagName, int newValue) {
    try {
      TagControl intTag = new TagControl(tagName);
      intTag.setTagValueAsInt(newValue);
    } catch (EWException e) {
      System.out.println(
          "[FlexyDemo] An error occurred while setting tag " + tagName + " as an Integer. Ensure " +
              "that the tag exists, and can be written as an Integer.");
    }
  }

  /**
   * Set Value of Tag as a Boolean
   *
   * @param tagName Name of Tag
   * @param newValue Value to Set
   */
  public static void setTagAsBoolean(String tagName, boolean newValue) {
    try {
      TagControl boolTag = new TagControl(tagName);
      boolTag.setTagValueAsInt(newValue ? 1 : 0);
    } catch (EWException e) {
      System.out.println(
          "[FlexyDemo] An error occurred while setting tag " + tagName + " as a Boolean. Ensure " +
              "that the tag exists, and can be written as a Boolean.");
    }
  }

  /**
   * Set Value of Tag as a Double
   *
   * @param tagName Name of Tag
   * @param newValue Value to Set
   */
  public static void setTagAsDouble(String tagName, double newValue) {
    try {
      TagControl dblTag = new TagControl(tagName);
      dblTag.setTagValueAsDouble(newValue);
    } catch (EWException e) {
      System.out.println(
          "[FlexyDemo] An error occurred while setting tag " + tagName + " as a Double. Ensure " +
              "that the tag exists, and can be written as a Double.");
    }
  }

  /**
   * Create and Upload a Tag Configuration File for Flexy
   *
   * @param scenarios Scenarios With Tag Configurations to Add
   */
  static void uploadTagConfig(ArrayList scenarios) {
    // ADD INITIAL HEADER
    StringBuffer builtVarLst = new StringBuffer(VARLST_HEADER);

    // ADD REQUIRED BASIC DEMO TAGS
    builtVarLst.append(DEMOSTOP_TAGCONFIG.getTagAsJSON());
    builtVarLst.append(DEMOSCENARIO_TAGCONFIG.getTagAsJSON());

    // ADD TAGS FROM SCENARIOS
    for (int scenarioID = 0; scenarioID < scenarios.size(); scenarioID++) {
      FlexyDemoScenario flexyDemoScenario = (FlexyDemoScenario) scenarios.get(scenarioID);
      ArrayListFlexy flexyDemoScenarioDevices = flexyDemoScenario.getDevices();
      for (int devID = 0; devID < flexyDemoScenarioDevices.size(); devID++) {
        ArrayList deviceTags = flexyDemoScenarioDevices.get(devID).getTagConfigs();
        for (int tagID = 0; tagID < deviceTags.size(); tagID++) {
          builtVarLst.append(((FlexyDemoTagConfig) deviceTags.get(tagID)).getTagAsJSON());
        }
      }
    }

    // CONVERT VAR_LST STRING TO BYTE ARRAY INPUT STREAM
    InputStream varLst = new ByteArrayInputStream(builtVarLst.toString().getBytes());

    // UPLOAD VAR_LST BYTE ARRAY TO FLEXY FTP
    try {
      // OUTPUT TO CONSOLE
      System.out.println("[FlexyDemo] FTP automatic tag configuration has started");

      // CREATE FTP CONNECTION TO LOCALHOST
      FTPClient ftpClient = new FTPClient();
      ftpClient.connect("localhost");

      // VERIFY CONNECTION - Close if an error occurred
      if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
        ftpClient.disconnect();
        System.out.println(
            "[FlexyDemo] FTP automatic tag configuration has encountered an error creating a " +
                "connection. FlexyDemo may experience undefined or improper behavior");
      }

      // LOGIN AND SET FILE TYPE
      ftpClient.login("adm", "adm");
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

      // PERFORM FTP UPLOAD
      ftpClient.storeFile("var_lst.csv", varLst);

      // LOGOUT OF AND DISCONNECT FROM FTP
      ftpClient.logout();
      ftpClient.disconnect();

      // RESET DEMOSTOP AND DEMOSCENARIO TAGS TO ZERO
      setTagAsInt(DEMOSTOP_TAGCONFIG.getTagName(), 0);
      setTagAsInt(DEMOSCENARIO_TAGCONFIG.getTagName(), 0);

      // OUTPUT TO CONSOLE
      System.out.println("[FlexyDemo] FTP automatic tag configuration has finished successfully");
    } catch (IOException e) {
      System.out.println(
          "[FlexyDemo] FTP automatic tag configuration has encountered an error. Please ensure " +
              "that Flexy has a default login. (User: adm, Pass: adm)");
    }

    // ENSURE GARBAGE COLLECTION RUNS
    System.gc();
  }

  /**
   * Get a Unique Tag ID and Increment Tag ID Counter
   *
   * @return Unique Tag ID
   */
  synchronized static int getNewTagID() {
    return TAG_ID_COUNTER++;
  }

}
