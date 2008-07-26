package com.googlecode.swtfinder.core.util;

import org.eclipse.swt.widgets.Widget;

import com.googlecode.swtfinder.core.uithread.UIThreadRunnable;
import com.googlecode.swtfinder.core.uithread.UIThreadRunnable.BoolResult;
import com.googlecode.swtfinder.core.uithread.UIThreadRunnable.StringResult;

/**
 *  Utility class provides some SWT related functions
 * @author Ben.Xu (xufengbing@gmail.com)
 */
public class SWTUtil {
	public static String getText(final Object w) {
		if ((w instanceof Widget) && !((Widget) w).isDisposed()) {
			final Widget widget = (Widget) w;
			return UIThreadRunnable.syncExec(widget.getDisplay(),
					new StringResult() {

						public String run() {
							Object returnV = ReflectionUtil.invokeMethod(
									"getText", widget);
							return (String) returnV;
						}
					});
		}
		return null;
	}

	public static boolean hasStyle(final Widget w, final int style) {
		if ((w == null) || w.isDisposed())
			return false;
		return UIThreadRunnable.syncExec(w.getDisplay(), new BoolResult() {
			public boolean run() {
				return (w.getStyle() & style) != 0;
			}
		});
	}
}
