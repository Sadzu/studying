import java.awt.*;

public class House {
    //private Color _color;
    private String _name;
    private int _xCoordinate;
    private int _yCoordinate;
    private Image _image;

    public Image get_image() {
        return _image;
    }


    //public Color getColor() {
        //return _color;
    //}
    public String getName() {
        return _name;
    }

//    public void set_color(Color color) {
//        _color = color;
//    }
    public void set_name(String name) {
        _name = name;
    }

    public void set_xCoordinate(int x) {
        _xCoordinate = x;
    }
    public void set_yCoordinate(int y) {
        _yCoordinate = y;
    }
    public int get_xCoordinate() {
        return _xCoordinate;
    }
    public int get_yCoordinate() {
        return _yCoordinate;
    }

    public void set_image(Image image) {
        _image = image;
    }
}
