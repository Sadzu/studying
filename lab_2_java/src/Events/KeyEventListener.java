package Events;

import Generation.Habitat;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventListener implements KeyListener {
    Habitat _habitat;
    JButton _startButton;
    JButton _stopButton;
    JRadioButton _hideTimeButton;
    JRadioButton _showTimeButton;

    public KeyEventListener(JButton startButton, JButton stopButton, JRadioButton showTimeButton, JRadioButton hideTimeButton) {
        _habitat = Habitat.getInstance(0, 0, 0, 0);
        _startButton = startButton;
        _stopButton = stopButton;
        _showTimeButton = showTimeButton;
        _hideTimeButton = hideTimeButton;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_B) {
            _startButton.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            _stopButton.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_T) {
            if (_habitat.getShowTime()) {
                _hideTimeButton.doClick();
            } else {
                _showTimeButton.doClick();
            }
        } else {return;}
    }
}