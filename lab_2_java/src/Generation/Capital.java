package Generation;

import javax.swing.*;

public class Capital extends House {
    public Capital(int x, int y) {
        setImage(new ImageIcon("src/Images/capital.png").getImage());
        setName("Capital");
        set_xCoordinate(x);
        set_yCoordinate(y);
    }
}
