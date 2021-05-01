import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseListener;
import java.awt.Frame;



public class Input2 {
	private static volatile boolean wPressed = false;
	private static volatile boolean sPressed = false;
	private static volatile boolean aPressed = false;
	private static volatile boolean dPressed = false;
	private static volatile boolean ePressed = false;
	private static volatile boolean qPressed = false;

	

	
	static {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (e.getKeyCode() == KeyEvent.VK_S) Input2.sPressed = true;	
					if (e.getKeyCode() == KeyEvent.VK_W) Input2.wPressed = true;
					if (e.getKeyCode() == KeyEvent.VK_A) Input2.aPressed = true;	
					if (e.getKeyCode() == KeyEvent.VK_D) Input2.dPressed = true;
					if (e.getKeyCode() == KeyEvent.VK_Q) Input2.ePressed = true;	
					if (e.getKeyCode() == KeyEvent.VK_E) Input2.qPressed = true;
				
				}
				
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					if (e.getKeyCode() == KeyEvent.VK_S) Input2.sPressed = false;	
					if (e.getKeyCode() == KeyEvent.VK_W) Input2.wPressed = false;
					if (e.getKeyCode() == KeyEvent.VK_A) Input2.aPressed = false;	
					if (e.getKeyCode() == KeyEvent.VK_D) Input2.dPressed = false;
					if (e.getKeyCode() == KeyEvent.VK_E) Input2.qPressed = false;	
					if (e.getKeyCode() == KeyEvent.VK_Q) Input2.ePressed = false;
				}
				return false;
			}
		});
	}
	
	public static synchronized boolean isSPressed() { return Input2.sPressed; }
	public static synchronized boolean isWPressed() { return Input2.wPressed; }
	public static synchronized boolean isAPressed() { return Input2.aPressed; }
	public static synchronized boolean isDPressed() { return Input2.dPressed; }
	public static synchronized boolean isEPressed() { return Input2.ePressed; }
	public static synchronized boolean isQPressed() { return Input2.qPressed; }

	
	
}
