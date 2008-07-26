package com.googlecode.swtfinder.core.finder;

import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.swt.widgets.Control;

import com.googlecode.swtfinder.core.eclipse.EclipseWorkBenchpartHelper;
import com.googlecode.swtfinder.core.exception.FindException;
import com.googlecode.swtfinder.core.uithread.UIThreadRunnable;

/**
 * 
 * This class is used to find a Control in View/Editor or under any Control<br >
 * 
 * all method of this class can be called both in UI thread and non ui thread.
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 * 
 */
public abstract class Finder {
	Stack<Control> s;

	/**
	 * @return return a default Control Finder.
	 */
	public static synchronized Finder getDefault() {
		return new ControlFinderImp();
	}

	/**
	 * find the only one control from <code>control</code> and all sub
	 * controls of this control that accept condition <code>fc</code>
	 * 
	 * @param control
	 *            find under this control
	 * @param fc
	 *            the condition
	 * @return the contorl found, or null if no control found or multi control
	 *         found
	 */
	public Control findOneByCondition(final Control control,
			final IConditionFind fc) {
		checkNotNull(control, fc);
		Control[] cs = this.findAllByCondition(control, fc);
		if (cs.length == 1) {
			return cs[0];
		}
		return null;
	}

	/**
	 * find the index-ed control from <code>control</code> and all sub
	 * controls of this control<br>
	 * that accept condition <code>fc</code>
	 * 
	 * @param control
	 *            find under this control
	 * @param fc
	 *            the condition
	 * @param index
	 *            the index-ed found control using the <code>fc</code>
	 *            condition
	 * @return
	 */
	public Control findIndexByCondition(final Control control,
			final IConditionFind fc, final int index) {
		checkNotNull(control, fc);
		if (index <= 0) {
			return null;
		}
		return (Control) UIThreadRunnable.syncExec(control.getDisplay(),
				new UIThreadRunnable.Result<Object>() {
					public Object run() {
						return Finder.this.internalfindIndexByCondition(
								control, fc, index);
					}
				});

	}

	/**
	 * find all the controls from <code>control</code> and all sub controls of
	 * this control<br>
	 * that accept condition <code>fc</code>
	 * 
	 * @param control
	 *            find under this control
	 * @param fc
	 *            the condition
	 * @return
	 */
	public Control[] findAllByCondition(final Control control,
			final IConditionFind fc) {
		checkNotNull(control, fc);

		return (Control[]) UIThreadRunnable.syncExec(control.getDisplay(),
				new UIThreadRunnable.Result<Object>() {

					public Object run() {
						return Finder.this.internalfindAllByCondition(control,
								fc);
					}
				});
	}

	private Control[] internalfindAllByCondition(Control control,
			IConditionFind fc) {
		ArrayList<Control> ws = new ArrayList<Control>();
		s.push(control);
		while (!s.isEmpty()) {
			Control tmpControl = s.pop();
			if (tmpControl != null) {
				if (fc.check(tmpControl)) {
					ws.add(tmpControl);
				}
				addControl(tmpControl);
			}
		}
		return ws.toArray(new Control[0]);
	}

	private Control internalfindIndexByCondition(Control control,
			IConditionFind fc, int index) {
		int count = 0;
		s.push(control);
		while (!s.isEmpty()) {
			Control tmpControl = s.pop();
			if (tmpControl != null) {
				if (fc.check(tmpControl)) {
					count++;
					if (count == index) {
						return tmpControl;
					}
				}
				addControl(tmpControl);
			}
		}
		return null;
	}

	/**
	 * this method is used to locate a special Control in an eclipse view with
	 * viewID<br>
	 * 
	 * this method will always try to check if the current active part is the
	 * view with viewID,<br>
	 * if it is, it will find the control in this view.<br>
	 * 
	 * the condition used to find the control is
	 * <code>ConditionClassAssignableFind</code>
	 * 
	 * @param viewID
	 *            view's id
	 * @param clazz
	 *            find condition
	 * @param index
	 *            start from 1
	 * @return the control found
	 */
	public Control findIndexByClassInEclipseView(final String viewID,
			final Class clazz, final int index) {
		Control viewControl = EclipseWorkBenchpartHelper
				.getPartControlWithViewID(viewID);
		return this.findIndexByCondition(viewControl,
				new ConditionClassAssignableFind(clazz), index);
	}

	/**
	 * this mehthod is used to find control in the active editor of the active
	 * page
	 * 
	 * @param viewID
	 *            view's id
	 * @param clazz
	 *            find condition
	 * @param index
	 *            start from 1
	 * @return the control found
	 */
	public Control findIndexByClassInEclipseActiveEditor(final Class clazz,
			final int index) {
		Control viewControl = EclipseWorkBenchpartHelper
				.getPartControlFromActiveEditor();
		return this.findIndexByCondition(viewControl,
				new ConditionClassAssignableFind(clazz), index);
	}

	protected abstract void addControl(Control w);

	private void checkNotNull(Control control, IConditionFind fc) {
		if (control == null)
			throw new FindException("control can not be null");
		if (control.isDisposed())
			throw new FindException("control can not be disposed");
		if (fc == null)
			throw new FindException("condition can not be null");
	}
}
