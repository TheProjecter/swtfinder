/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import org.eclipse.swt.widgets.Control;

/**
 * Condition interface for Finder class.<br>
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public interface IConditionFind {
    boolean check(Control control);
}
