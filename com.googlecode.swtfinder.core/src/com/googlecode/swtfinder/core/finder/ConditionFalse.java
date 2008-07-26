/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

/**
 *  a always false condition
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionFalse implements IConditionFind {


	/*
	 * (non-Javadoc)
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {
		return false;
	}

}
