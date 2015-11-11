## What is SWTFinder ##

### Is ###
SWTFinder is used to find swt control in eclipse views and editors.

Using this tool, you can automatically locate any control in eclipse view/editor.
The API can be used in both UI thread and non-UI thread.

### Is NOT ###
SWTFinder is just a control finder. Currenlty, it does not provide any function to drive UI actions. And I don't plan to provides such functionality in future version.


## Contains ##
SWTFinder contains the following two parts:
  * swtfinder core plug-in that provides the core java API
  * swtfinder ui plug-in tool used to show the control layer of active eclipse part and automatically generate API to find a special control.

## SWTFinder Usage ##

SWTFinder can be used to help eclipse developers write plug-in unit test.

SWTFinder can also be used together with the following tools(all these tools can drive UI actions):
  * Abbot (SWT part)---open source
  * Eclipse TPTP AGR---open source
  * SWTBot          ---open source
  * WindowTester    ---Commercial

also see this wiki: [AutomaticUITesting](http://code.google.com/p/swtfinder/wiki/AutomaticUITesting)

## Install ##
You can use the following ways to install swtfinder
  * Download the [core plug-in](http://swtfinder.googlecode.com/files/com.googlecode.swtfinder.core_0.1.0.jar) and [UI plug-in](http://swtfinder.googlecode.com/files/com.googlecode.swtfinder.ui_0.1.0.jar),  put them into your eclipse plugins folder manually.
  * Using the following update site: http://t4eclipse.googlecode.com/svn/trunk/updatesite/eclipse


SWTFinder support any version of eclipse from  3.2 to the latest 3.4

## 5 Minutes Tutorial ##
See this WiKI page: Swtfiner5MinutesTutorial


## SWTFinder Core API ##
[Download](http://swtfinder.googlecode.com/files/SWTFinderAPI.zip) javadoc for the core API.

You can also download the source code from SVN.


## License ##
The code follows EPL license.
Also,I permit anyone to use the swtfinder and its source code in any way, do any change without inform me.