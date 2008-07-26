/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

/**
 * this condition checks the tags for the Control
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionTagFind implements IConditionFind {

	private String tagProp;

	private String tagValue;

	public ConditionTagFind(String tagProp, String tagValue) {
		this.tagProp = tagProp;
		this.tagValue = tagValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {
		if (control != null) {
			String tagV = (String) control.getData(this.tagProp);
			if (tagV != null) {
				if (tagV.equals(this.tagValue)) {
					return true;
				}
			}
		}
		return false;
	}
}
