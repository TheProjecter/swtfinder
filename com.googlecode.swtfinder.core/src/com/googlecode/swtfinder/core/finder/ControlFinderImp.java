/*
 * 
 */
package com.googlecode.swtfinder.core.finder;

import java.util.Stack;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 *  a Finder implementation to find controls
 * @author Ben.Xu (xufengbing@gmail.com)
 */
class ControlFinderImp extends Finder {

	ControlFinderImp() {
		s = new Stack<Control>();
	}

	private void addStack(Control[] controls) {
		if (controls != null) {
			for (int i = controls.length - 1; i >= 0; i--) {
				if (controls[i] != null) {
					s.push(controls[i]);
				}
			}

		}
	}

	/**
	 * user can create a new Finder by reimplement this method.<br>
	 * for example, if you need to find special things inside KTable, you need
	 * to<br>
	 * implement this method to use special KTable method to add KTable sub
	 * items.
	 */

	@Override
	protected void addControl(Control c) {

		if (c instanceof Composite) {
			Composite cont = (Composite) c;
			Control[] children = cont.getChildren();
			addStack(children);
		}
	}
}
