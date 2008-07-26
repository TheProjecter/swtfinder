/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

/**
 * this Condition checks the getText result of the Control to be checked.
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class ConditionGetTextFind extends ConditionMethodInvoke {

	public ConditionGetTextFind(String text) {
		super("getText", text, true);
	}
}