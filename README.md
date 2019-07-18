# HMS Ewon Ignition Integration Demo #

----------

## About ##
HMS Ewon Ignition Integration Demo is a small suite of applications that showcase the features of integration between HMS Industrial Network's Ewon platform, and the Inductive Automation Ignition platform.

HMS Ewon Ignition Integration Demo was developed by HMS Industrial Networks using the Ewon Java Library. For more information on this demo and developing applications with the Ewon Java Library, please contact us at [us-services@hms.se](mailto:us-services@hms.se "us-services@hms.se") and/or visit the Ewon Developer website at [https://developer.ewon.biz/](https://developer.ewon.biz/ "https://developer.ewon.biz/")

## Issues/Problems ##
If you experience a problem or bug, please check the Ewon Ignition Integration Demo Wiki for information, found here: [https://github.com/hms-networks/FlexyIgnitionDemo/wiki](https://github.com/hms-networks/FlexyIgnitionDemo/wiki "hms-networks/FlexyIgnitionDemo/wiki")

If no information is available on the Ewon Ignition Integration Demo Wiki, or if your issue persists, please create a GitHub issue, found here: [https://github.com/hms-networks/FlexyIgnitionDemo/issues](https://github.com/hms-networks/FlexyIgnitionDemo/issues "hms-networks/FlexyIgnitionDemo/issues")

## Getting Started ##
To complete installation, please perform all the setup steps in the sections below! To begin, the following items are required:

- HMS Ewon Product
- Computer with Administrator Privileges
- Talk2M API Key
  - If you do not have a Talk2M/Ewon API Key, you may request one here: [https://developer.ewon.biz/registration](https://developer.ewon.biz/registration "https://developer.ewon.biz/registration").
- Demo Resource Pack (.zip)
	- Download Here: [https://github.com/hms-networks/FlexyIgnitionDemo/releases](https://github.com/hms-networks/FlexyIgnitionDemo/releases "https://github.com/hms-networks/FlexyIgnitionDemo/releases")

## Ewon Setup ##
*During Ewon setup, you will ensure that a user account with the correct permissions is available for use with the Ewon Ignition Integration Demo.*

**Step 1:** If your Ewon device is not setup, use eBuddy and a web browser to complete the `System`, `Internet` and `VPN` wizards. Detailed instructions can be found in section 5 or 6 of your product's installation guide.

- *Flexy 205*: [https://websupport.ewon.biz/sites/default/files/ig-0028-00-en-installation-guide-for-flexy-205.pdf#page=22](https://websupport.ewon.biz/sites/default/files/ig-0028-00-en-installation-guide-for-flexy-205.pdf#page=22 "https://websupport.ewon.biz/sites/default/files/ig-0028-00-en-installation-guide-for-flexy-205.pdf#page=22")
- *Flexy Base Unit*: [https://websupport.ewon.biz/sites/default/files/ig-014-0-en-ewon_flexy_-_base_units.pdf#page=28](https://websupport.ewon.biz/sites/default/files/ig-014-0-en-ewon_flexy_-_base_units.pdf#page=28 "https://websupport.ewon.biz/sites/default/files/ig-014-0-en-ewon_flexy_-_base_units.pdf#page=28")

**Step 2:** Using a web browser, navigate to your Ewon's web page and select the `Users` tab, located under `Setup`.

**Step 3:** Click `Add`, then create a username and password of your choice. Take note of this username and password, as it will be required in later setup steps. If you'd like, you may populate the `First Name`, `Last Name` and/or `Information` fields. IMPORTANT: Do not modify the rights of the new user.

**Step 4:** Click `Add User` to complete the addition of the new user account.

## Ignition Setup ##
*Information for setup of Ignition (Steps 1 - 4) can also be found here: [https://docs.inductiveautomation.com/display/DOC80/Installing+and+Upgrading](https://docs.inductiveautomation.com/display/DOC80/Installing+and+Upgrading "https://docs.inductiveautomation.com/display/DOC80/Installing+and+Upgrading")*

**Step 1:** Using a web browser, download **Ignition 8** from [https://inductiveautomation.com/downloads/ignition](https://inductiveautomation.com/downloads/ignition "https://inductiveautomation.com/downloads/ignition").

**Step 2:** After the download is complete, run the Ignition installer. The default installation options are satisfactory, although you may change them to fit your preference(s).

**Step 3:** After Ignition is installed, the Ignition Gateway web page will open and guide you through commissioning Ignition. If the Ignition Gateway web page does not automatically open, using your browser, navigate to [http://localhost:8088](http://localhost:8088 "http://localhost:8088"). Take note of the username and password you set during commissioning, as this will be required in later setup steps.

**Step 4:** After Ignition is commissioned, you will be redirected to the Ignition Gateway homepage. Take note of the URL in your browser's address bar as this will be required in later setup steps.

**Step 5:** Download the latest HMS Ewon Connector from the EwonConnector releases page, found here: [https://github.com/hms-networks/EwonConnector/releases](https://github.com/hms-networks/EwonConnector/releases "https://github.com/hms-networks/EwonConnector/releases").

- To download, expand the `Assets` menu under the latest Ignition 8 release, and click on the first file, commonly named `EwonConnector-X.Y.Z.zip`, where X.Y.Z is the version number. Please note that the minimum supported version is 1.1.7.

**Step 6:** After the download is complete, install the HMS Ewon Connector by going to the `Config` tab in Ignition, clicking `Modules` and then choosing `Install or Upgrade a Module...`. Click `Choose File` and select the file you've just downloaded, then click `Install`.

**Step 7:** After installation of the HMS Ewon Connector, browse to the Ewon Connector configuration by going to the `Config` tab in Ignition, and clicking `Ewon Connector`, located under the `Tags` heading. *NOTE: the default name "Ewon" should NOT be changed*.

**Step 8:** Ensure that the `Enabled` check-box is checked.

**Step 9:** Set the `Live Data Poll Rate in Seconds` value to 1 second.

**Step 10:** Ensure that the `Read all values in realtime` check-box is checked.

**Step 11:** Under `Ewon Account Information`, input your Talk2M account information. All fields are required. Please note that you may need to select the `Change Password?` option to enable the password fields.

**Step 12:** Under `Ewon Device Information`, input the username and password you created in Steps 2-4 of Ewon Setup. All fields are required. Please note that you may need to select the `Change Password?` option to enable the password fields.

**Step 13:** Click `Save Changes` to apply your changes.

**Step 14:** Restart the HMS Ewon Connector by going to the `Config` tab in Ignition, clicking `Modules` and clicking `restart` on the EwonConnector Ignition Module.

## Demo Application Setup ##

**Step 1:** Connect your computer to the LAN of your Ewon device, and open an FTP connection to your Ewon device. To create an FTP connection, using your file manager (i.e. Windows Explorer), enter `ftp://[EWON IP HERE]/` in the address bar, and hit enter. If you don't know the IP address of your Ewon device, you can locate it using eBuddy.

**Step 2:** Copy the files `EwonIgnitionDemoApp.jar` and `jvmrun` from the demo resource pack into the usr folder on your Ewon device using the FTP connection you've just opened.

**Step 3:** Reboot your Ewon device by navigating to its web page, clicking the `Setup` tab, pressing `Reboot` and then pressing `REBOOT` once more.

## Demo Dashboard Setup ##

**Step 1:** Browse to your Ignition Gateway's web page using the URL from Step 4 of Ignition Setup. Click the `Config` tab, and open `Projects`.

**Step 2:** Click `Import Project`, select `Choose File`, and choose the file `EwonIgnitionDemo.zip` from the demo resource pack.

**Step 3:** Assign the project a name of your choice, and click `Import`.

**Step 4:** After successfully importing the Vision project, click the `Home` tab.

**Step 5:** Download Vision Client Launcher by clicking `Download Vision Client Launcher`, and choosing your operating system.

**Step 6:** After the download is complete, open the downloaded file, and follow the installation procedure to completion. The default settings are satisfactory, but you may modify them as desired.

**Step 7:** After the installation is complete, open Vision Client Launcher if you have not done so already, and click `Add Vision Client`. For more information on location/opening Vision Client Launcher, see Starting the Demo Step 1.

**Step 8:** Double-click your Ignition gateway from the list of `On Your Network` gateways. Since your gateway is installed on the same computer as Ignition Vision Client Launcher, the URL field of your gateway should display `http://localhost:8088`. Please note that `8088` is a user-configurable port number, and your installation may not use port `8088` if you modified the port settings.

**Step 9:** Ensure the Vision project you imported in Step 3 is selected, and click `Add Application(s)`. Please note, the imported Vision project may not use the name you chose earlier, and can be found as `HMS-Ewon-Ignition-Demo`.

**Step 10:** [Optional] Click the down arrow, located to the right of `Launch Fullscreen`, and click `Create Shortcut` to add a demo shortcut to your desktop for convenience.

## Starting the Demo ##

**Step 1:** Launch Vision Client Launcher by clicking it's icon in your operating system's main menu.

* *Windows:* Start Button
* *macOS:* Launchpad
* *Linux:* Varies (commonly upper left corner)

**Step 2:** Locate your Vision Project, and click `Launch Fullscreen`.

**Step 3:** Enter the username and password you set during Ignition commissioning, and click `Login`.

**Step 4:** Fill out the requested information, and if desired, choose `Remember Me` to allow for easy log in for subsequent demos. In the `Ewon Name` field, enter the exact name of the Ewon device you've configured for this demo, and click `Save`. Please note that you can locate the name of your Ewon device by browsing to your Ewon device's web page, expanding `Setup`, expanding `System`, clicking `Main` and selecting `Identification`.

**Step 5:** Click `Begin` to start the demo.
