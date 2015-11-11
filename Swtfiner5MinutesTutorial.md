# 5 Minutes Tutorial #
## Introduction ##
This 5 minutes tutorial shows how to use swtfinder.

In this tutorial, swtfinder is used to help testing the eclipse outline view.

## Eclipse Outline View ##
The Eclipse outline view displays an outline of a structured file that is currently open in the editor area.

When there is no editor page in current workbench page,the view looks like the following picture.

> http://swtfinder.googlecode.com/files/outline.GIF

Now, we will try to write a junit plug-in test to test this scenario from UI.

### Test case for this ###
How to do this?

test case 1:
  * Precondition: Outline view is not opened, there is no editor page in editor area.
  * Actions: Open the outline view
  * Postcondition: outline view appears as the above picture.

test case 2: ...

test case 3: ...

We will implement the test case 1 as junit plug-in test.

## Use UI tool plug-in for SWTFinder ##

first, use ui plug-in tool of swtfinder to analysis the controls in outline view(download and install it first).

Following the following way to analyze the initial state of outline view.
  * close all editors in current eclipse if there is any
  * open the outline view by click main menu  (window-->Show View-->Outline)
  * focus on view to be analyzed to make it the active part in current active workbench page, here is the outline view.
  * click the "analysis" main toolbar item http://swtfinder.googlecode.com/files/toolitem.GIF  to analysis the current active part.

the following picture lists the control tree of the outline view.

http://swtfinder.googlecode.com/files/swtfinderview.GIF


Let's locate the label control with text "An outline is not available"
  * click at any column at the row shows the label control (here it is row with index "5")
  * right click to show the popup menu
  * click "Find" in the popup menu to bring up the Locate dialog.
  * Choose the return type and find type both to Label
  * Click the find button on this dialog
  * java code will automatically be generated in the text field on the dialog.

http://swtfinder.googlecode.com/files/locate.GIF


## Use the core API in  test class ##
Actually, the generated code just invokes the core API to locate the control. Users can write such API manually. Core API provides many different ways to locate a control in eclipse view/editor. The generated code is just one of these powerful APIs.

```
Label returnControl=
(Label)Finder.getDefault().findIndexByClassInEclipseView(
"org.eclipse.ui.views.ContentOutline",Label.class,1);
```

The API can be used directly in you java program,and this API and other API in core plug-in can be invoked both in UI thread and non-UI thread.

An example project is created to implement the first test case to test the outline view. Download the project at [here](http://swtfinder.googlecode.com/files/com.googlecode.swtfinder.example1.zip).

Unzip it and import this project into eclipse with swtfinder core plug-in installed.

Read the source code of `TestOutlineView.java` to figure out how to use the generated code to locate control in java program.
