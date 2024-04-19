package Generation;

import javax.swing.*;

public class Wooden extends House{
    public Wooden(int x, int y) {
        setImage(new ImageIcon("src/Images/images.jpeg").getImage());
        setName("Wooden");
        set_xCoordinate(x);
        set_yCoordinate(y);
    }

    @Override
    public void move(boolean runStatus) {
        int w = 1920;
        int h = 1080;
        if (get_xCoordinate() > w/2 && get_yCoordinate() > h/2) { return; }
        if (!runStatus) { return; }
        set_xCoordinate(get_xCoordinate() + 1);
        set_yCoordinate(get_yCoordinate() + 1);
    }
}
