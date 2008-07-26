package com.googlecode.swtfinder.core.uithread;

import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Thread wrapper calss to make sure that the run method always run in the UI
 * thread.
 * 
 * 
 * @author Ben.Xu (xufengbing@gmail.com)
 * 
 */
public abstract class UIThreadRunnable implements Runnable {

	public interface ControlResult {
		Control run();
	}

	public interface ObjectResult {
		Object run();
	}

	public interface VoidResult {
		void run();
	}

	public interface BoolResult {
		boolean run();
	}

	public interface IntResult {
		int run();
	}

	public interface CharResult {
		char run();
	}

	public interface StringResult {
		String run();
	}

	public interface ListResult {
		List run();
	}

	public interface Result<T> {
		T run();
	}

	protected final Display display;

	private final boolean async;

	/**
	 * Runs synchronously in the UI thread.
	 * 
	 * @param display
	 */
	private UIThreadRunnable(Display display) {
		this(display, false);
	}

	/**
	 * @param display
	 * @param async
	 *            if the thread should run asynchronously or not.
	 * @see Display#syncExec(Runnable)
	 * @see Display#asyncExec(Runnable)
	 */
	private UIThreadRunnable(Display display, boolean async) {
		this.display = display;
		this.async = async;
	}

	/**
	 * This method is intelligent to execute in the UI thread.
	 */
	public void run() {
		if ((display == null) || display.isDisposed())
			return;
		if (!isUIThread()) {
			if (async)
				display.asyncExec(runnable());
			else
				display.syncExec(runnable());
		} else
			doRun();
	}

	private Runnable runnable() {
		final Runnable runnable = new Runnable() {
			public void run() {
				doRun();
			}
		};
		return runnable;
	}

	/**
	 * This MUST be invoked in the UI thread.
	 */
	protected abstract void doRun();

	/**
	 * @return <code>true</code> if this instance is running in the UI thread,
	 *         <code>false</code> otherwise.
	 */
	public boolean isUIThread() {
		return isUIThread(display);
	}

	public static boolean isUIThread(Display display) {
		return display.getThread() == Thread.currentThread();
	}

	public static int syncExec(Display display,
			final UIThreadRunnable.IntResult result) {
		final int wrapper[] = new int[1];
		new UIThreadRunnable(display) {
			protected void doRun() {
				wrapper[0] = result.run();
			}
		}.run();
		return wrapper[0];
	}

	public static boolean syncExec(Display display,
			final UIThreadRunnable.BoolResult result) {
		final boolean wrapper[] = new boolean[1];
		new UIThreadRunnable(display) {
			protected void doRun() {
				wrapper[0] = result.run();
			}
		}.run();
		return wrapper[0];
	}

	public static String syncExec(Display display,
			final UIThreadRunnable.StringResult result) {
		final String wrapper[] = new String[1];
		new UIThreadRunnable(display) {
			protected void doRun() {
				wrapper[0] = result.run();
			}
		}.run();
		return wrapper[0];
	}

	public static char syncExec(Display display,
			final UIThreadRunnable.CharResult result) {
		final char wrapper[] = new char[1];
		new UIThreadRunnable(display) {
			protected void doRun() {
				wrapper[0] = result.run();
			}
		}.run();
		return wrapper[0];
	}

	public static Control syncExec(Display display,
			final UIThreadRunnable.ControlResult result) {
		final Control wrapper[] = new Control[1];

		new UIThreadRunnable(display) {
			protected void doRun() {
				wrapper[0] = result.run();
			}
		}.run();
		return wrapper[0];
	}

	public static void syncExec(Display display,
			final UIThreadRunnable.VoidResult result) {
		new UIThreadRunnable(display) {
			protected void doRun() {
				result.run();
			}
		}.run();
	}

	public static <T> T syncExec(Display display, final Result<T> result) {
		final Reference<T> reference = new Reference<T>();
		display.syncExec(new Runnable() {
			public void run() {
				reference.referent = result.run();
			}
		});
		return reference.referent;
	}

	public static void asyncExec(Display display,
			final UIThreadRunnable.VoidResult result) {
		new UIThreadRunnable(display, true) {
			protected void doRun() {
				result.run();
			}
		}.run();
	}

	private static class Reference<T> {
		public volatile T referent;
	}
}
