package com.googlecode.swtfinder.core.eclipse;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartPane;
import org.eclipse.ui.internal.PartSite;

import com.googlecode.swtfinder.core.uithread.UIThreadRunnable;

/**
 * 
 * this class provides utility class for eclispe view/editor<br>
 * all public method for this class can be run in UI and non-UI thread.
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class EclipseWorkBenchpartHelper {

	/**
	 * 
	 * get view in current page according to viewid.<br>
	 * this method will first check if the current active part is the view with
	 * <code>viewid</code>.<br>. if it is, then return this active view.<br>
	 * else, iterate all the views on current active page and return the first
	 * one with <code>viewid</code><br>
	 * 
	 * 
	 * 
	 * 
	 * @param viewid
	 *            view's ID
	 * @return IViewPart ref
	 */
	public static IViewPart getViewInCurrentpage(final String viewid) {
		return (IViewPart) UIThreadRunnable.syncExec(Display.getDefault(),
				new UIThreadRunnable.Result<Object>() {
					public Object run() {

						IWorkbenchPart activePart = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.getActivePart();
						// this the current active part is a view and it's id
						// equals viewid, just retutrn it.
						if (activePart != null
								&& activePart instanceof IViewPart
								&& activePart.getSite().getId().equals(viewid)) {
							return activePart;
						}
						// otherwise, find it in current workbench page!
						IViewReference[] views = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.getViewReferences();
						for (IViewReference view : views) {
							IViewPart v = view.getView(false);

							if (v != null && v.getSite().getId().equals(viewid)) {
								return view.getView(false);
							}
						}
						return null;
					}
				});
	}

	/**
	 * get Control from an eclipse workbenchpart
	 * 
	 * @param part
	 * @return
	 */
	public static Control getPartControl(final IWorkbenchPart part) {
		return (Control) UIThreadRunnable.syncExec(Display.getDefault(),
				new UIThreadRunnable.Result<Object>() {
					public Object run() {
						if (part != null) {
							IWorkbenchPartSite site = part.getSite();
							PartPane pane = ((PartSite) site).getPane();
							return pane.getControl();
						} else {
							return null;
						}

					}
				});
	}

	/**
	 * get Control from view whose id equals viewID in current page<br>
	 * 
	 * @param viewID
	 * @return
	 */
	public static Control getPartControlWithViewID(String viewID) {

		IViewPart vp = getViewInCurrentpage(viewID);
		if (vp != null) {
			return getPartControl(vp);
		}
		return null;

	}

	/**
	 * 
	 * get Control from the active editor in current page.<br>
	 * 
	 * @return
	 */
	public static Control getPartControlFromActiveEditor() {
		IEditorPart editorpart = getActiveEditorPart();
		if (editorpart == null)
			return null;
		return getPartControl(editorpart);
	}

	/**
	 * get Control from the active editor in current page
	 * 
	 * @return
	 */
	public static Control getPartControlFromActivePart() {
		IWorkbenchPart activePart = getActivePart();
		if (activePart == null)
			return null;
		return getPartControl(activePart);
	}

	/**
	 * @return active editor in the current page,null if no active editor in
	 *         current page.
	 */
	public static IEditorPart getActiveEditorPart() {
		return (IEditorPart) UIThreadRunnable.syncExec(Display.getDefault(),
				new UIThreadRunnable.Result<Object>() {

					public Object run() {
						return PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.getActiveEditor();
					}
				});
	}

	/**
	 * @return active workbenchpart in the current page
	 */

	public static IWorkbenchPart getActivePart() {
		return (IWorkbenchPart) UIThreadRunnable.syncExec(Display.getDefault(),
				new UIThreadRunnable.Result<Object>() {
					public Object run() {
						return PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.getActivePart();

					}
				});
	}
}
