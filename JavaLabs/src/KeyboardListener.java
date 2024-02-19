import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    Habitat _habitat;

    public KeyboardListener(Habitat habitat) {
        _habitat = habitat;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_B) {
            _habitat.startSimulation();
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            _habitat.stopSimulation();
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            _habitat.toggleTimeDisplay();
        }
    }
}
