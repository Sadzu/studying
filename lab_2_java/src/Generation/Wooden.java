package Generation;

import javax.swing.*;

public class Wooden extends House{
    public Wooden(int x, int y) {
        setImage(new ImageIcon("src/Images/images.jpeg").getImage());
        setName("Wooden");
        set_xCoordinate(x);
        set_yCoordinate(y);
    }
}
