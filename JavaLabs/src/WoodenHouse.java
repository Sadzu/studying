import javax.swing.*;
import java.awt.*;

public class WoodenHouse extends House implements IBehaviour{
    public WoodenHouse() {
        //set_color(Color.ORANGE);
        set_image(new ImageIcon("images.jpeg").getImage());
        set_name("Wood");
        set_xCoordinate((int) (Math.random() * 1800));
        set_yCoordinate((int) (Math.random() * 900));
    }
}