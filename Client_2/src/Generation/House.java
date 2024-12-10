package Generation;

import java.awt.*;
import java.io.Serializable;

public abstract class House implements IBehaviour, Serializable {
    private int _xCoordinate;
    private int _yCoordinate;
    private int _bornTime;
    private String _name;
    private int _id;

    public int get_xCoordinate() {return _xCoordinate;}
    public int get_yCoordinate() {return _yCoordinate;}
    public String getName() {return _name;}
    public int getBornTime() {return _bornTime;}
    public int getId() {return _id;}

    public void set_xCoordinate(int x) {_xCoordinate = x;}
    public void set_yCoordinate(int y) {_yCoordinate = y;}
    public void setName(String name) {_name = name;}
    public void setBornTime(int bornTime) {_bornTime = bornTime;}
    public void setId(int id) {_id = id;}
}
