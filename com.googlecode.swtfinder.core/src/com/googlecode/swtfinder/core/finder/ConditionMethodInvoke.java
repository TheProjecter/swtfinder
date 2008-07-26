/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

import com.googlecode.swtfinder.core.util.ReflectionUtil;

/**
 *
 * This Condition checks the result of a method,(method must have no para)<br>
 *
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionMethodInvoke implements IConditionFind {

	String methodName;

	Object object;

	boolean equals;

	/**
	 *
	 * @param methodName
	 *            method name to invoke.
	 * @param object
	 *            not null
	 * @param equals
	 *            if true, use equals to compare ,else use "=="
	 *
	 */
	public ConditionMethodInvoke(String methodName, Object object,
			boolean equals) {

		this.methodName = methodName;
		this.object = object;
		this.equals = equals;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.swtfinder.core.finder.IConditionFind#check(org.eclipse.swt.widgets.Control)
	 */
	public boolean check(Control control) {

		Object o = ReflectionUtil.invokeMethod(this.methodName, control);
		if (o != null) {
			if (this.equals)
				return o.equals(this.object);
			return (o == this.object);
		}
		return false;
	}

}
