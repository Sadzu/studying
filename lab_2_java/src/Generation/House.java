package Generation;

import java.awt.*;

public abstract class House implements IBehaviour {
    private Image _image;
    private int _xCoordinate;
    private int _yCoordinate;

    private String _name;

    public Image getImage() {return _image;}
    public int get_xCoordinate() {return _xCoordinate;}
    public int get_yCoordinate() {return _yCoordinate;}
    public String getName() {return _name;}

    public void setImage(Image image) {_image = image;}
    public void set_xCoordinate(int x) {_xCoordinate = x;}
    public void set_yCoordinate(int y) {_yCoordinate = y;}
    public void setName(String name) {_name = name;}
}
