import java.awt.*;
import javax.swing.*;

public class CapitalHouse extends House implements IBehaviour {
    public CapitalHouse() {
        //set_color(Color.GRAY);
        set_image(new ImageIcon("capital.png").getImage());
        set_name("Capital");
        set_xCoordinate((int) (Math.random() * 1800));
        set_yCoordinate((int) (Math.random() * 900));
    }
}