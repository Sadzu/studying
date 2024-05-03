package frames;

import Generation.Habitat;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Cmd {
    private static JDialog _frame;
    private static JTextPane _pane;
    private static JPanel _panel;
    private static JScrollPane _scroll;
    private static final Habitat _habitat = Habitat.getInstance();
    private static Document _doc;
    private static Scanner _scanner;
    private static int _off, _len = 0;
    private static boolean _eol = false, _isClearing = false;
    private static byte[] _cbuf;

    public void init(JFrame parent) {
        System.setOut(new PrintStream(new CmdOutputStream(this)));
        _scanner = new Scanner(new CmdInputStream(this));
        _frame = new JDialog(parent, "cmd", false);
        _frame.setSize(400, 400);
        _frame.setMinimumSize(new Dimension(400, 400));
        _frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        _frame.setLocationRelativeTo(null);

        _panel = new JPanel();
        _panel.setLayout(new BorderLayout());
        _frame.add(_panel);

        _pane = new JTextPane();
        ((AbstractDocument)_pane.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length,
                                String text, AttributeSet attrs) throws BadLocationException {
                if (_isClearing || offset >= _off - 1) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (_isClearing || offset >= _off) {
                    super.remove(fb, offset, length);
                }
            }
        });
        _doc = _pane.getDocument();
        _pane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String command = _scanner.nextLine();
                    _checkCommand(command);
                    _scanner.nextLine();
                }
            }
        });
        _scroll = new JScrollPane(_pane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        _panel.add(_scroll, BorderLayout.CENTER);
    }

    public void write(int b) {
        try {
            _doc.insertString(_doc.getLength(), "" + (char)b, null);
            _off = _doc.getLength();
        } catch (BadLocationException err) {
            err.printStackTrace();
        }
    }

    public int read() {
        try {
            if (_eol) {
                _cbuf = null;
                _off = 0;
                _eol = false;
                return -1;
            }
            if (_cbuf == null) {
                _cbuf = _pane.getText().getBytes();
                _len = _doc.getLength();
                return _cbuf[_off++];
            } else if (_off < _len) {
                return _cbuf[_off++];
            } else {
                _eol = true;
                _off = 0;
                return (int) '\n';
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            _off = _cbuf.length;
            _eol = true;
            _checkCommand("err");
            return -1;
        }
    }

    private void _checkCommand(String command) {
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
            case "change_capital_chance":
                try {
                    _habitat.setCapitalChance(Double.parseDouble(splitCommand[1]));
                    System.out.println("Changed");
                } catch (NumberFormatException err) {
                    System.out.println("Invalid number. Chance should be positive double (0.3 for example)");
                }
                break;
            case "get_capital_chance":
                System.out.println(_habitat.getCapitalChance());
                break;
            case "clear":
                try {
                    _isClearing = true;
                    _doc.remove(0, _doc.getLength());
                    _isClearing = false;
                    _off = 0;
                } catch (BadLocationException err) {
                    err.printStackTrace();
                }
                break;
            case "err":
                try {
                    _isClearing = true;
                    _doc.remove(0, _doc.getLength());
                    _isClearing = false;
                    _off = 0;
                    System.out.println("Error in cmd, console cleared");
                } catch (BadLocationException err) {
                    err.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

    public void showCmd() {
        _frame.setVisible(true);
    }
}

class CmdOutputStream extends OutputStream {
    private final Cmd _console;

    public CmdOutputStream(Cmd console) {
        super();
        _console = console;
    }

    @Override
    public void write(int b) {
        _console.write(b);
    }
}

class CmdInputStream extends InputStream {
    private final Cmd _console;

    public CmdInputStream(Cmd console) {
        super();
        _console = console;
    }

    @Override
    public int read() {
        return _console.read();
    }
}
