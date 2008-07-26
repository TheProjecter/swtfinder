package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

/**
 * This Condition checks if class of <code>control</code> to be checked is
 * <code>classV</code> or subclass of <code>classV</code>.
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionClassAssignableFind implements IConditionFind {

	private Class classV;

	public ConditionClassAssignableFind(Class c) {
		this.classV = c;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {
		if (classV == null || control == null)
			return false;
		Class c = control.getClass();
		return this.classV.isAssignableFrom(c);

	}
}