/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

/**
 * 
 * This condition can combile an array of conditions, the check result is to
 * "&&" each condition.
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionCombinedFind implements IConditionFind {

	private IConditionFind[] conditions;

	public ConditionCombinedFind(IConditionFind[] cs) {

		this.conditions = cs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {

		if (this.conditions == null || this.conditions.length == 0) {
			return false;

		}
		for (IConditionFind fc : conditions) {
			if (!fc.check(control))
				return false;
		}
		return true;
	}

}
