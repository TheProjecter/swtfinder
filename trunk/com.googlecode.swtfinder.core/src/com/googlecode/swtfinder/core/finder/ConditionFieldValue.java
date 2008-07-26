/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

import com.googlecode.swtfinder.core.util.ReflectionUtil;

/**
 * this Condition uses java reflection to check a special field of a Control.<br>
 *  
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionFieldValue implements IConditionFind {

	private String fieldName;

	private Object object;


	/**
	 * 
	 * @param filedName the field of the control to be checked.
	 * @param object
	 */
	public ConditionFieldValue(String filedName, Object object) {

		this.fieldName = filedName;
		this.object = object;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {
		Object o = ReflectionUtil.getField(this.fieldName, control);
		if (o != null) {
			return o.equals(this.object);
		}
		return false;
	}

}
