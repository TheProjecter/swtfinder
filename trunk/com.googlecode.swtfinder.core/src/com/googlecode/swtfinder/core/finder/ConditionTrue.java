/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

/**
 * a always true condition
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionTrue implements IConditionFind {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {
		return true;
	}

}
