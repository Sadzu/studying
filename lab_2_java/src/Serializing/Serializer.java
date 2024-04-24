package Serializing;

import Generation.Habitat;
import Generation.House;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

public class Serializer implements Serializable {
    @Serial
    private static final long serialVersionUID = 6473L;

    private static final String $directory = "src/Serializing/status.bin";

    private int _currentSimulationTime, _woodenCount, _capitalCount, _totalCount;
    private Vector<House> _houses;
    private HashSet<Integer> _ids;
    private TreeMap<Integer, House> _bornTimeIDs;

    public Serializer() {
        super();
        Habitat habitat = Habitat.getInstance();
        _woodenCount = habitat.getWoodenCount();
        _capitalCount = habitat.getCapitalCount();
        _totalCount = habitat.getAllObjectsCount();
        _currentSimulationTime = habitat.getSimulationTime();
        _houses = habitat.getHouses();
        _ids = habitat.getRandomIDs();
        _bornTimeIDs = habitat.getBornTimeIDs();
    }

    public static void serialize(Object object) {
        try {
            FileOutputStream out = new FileOutputStream($directory);
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(object);
            objectOut.close();
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No such file or directory");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Serializer deserialize() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream($directory);
        ObjectInputStream objectIn = new ObjectInputStream(in);
        Serializer res = (Serializer)objectIn.readObject();
        return res;
    }

    public int getWoodenCount() { return _woodenCount; }
    public int getCapitalCount() { return _capitalCount; }
    public int getTotalCount() { return _totalCount; }
    public int getCurrentSimulationTime() { return _currentSimulationTime; }
    public Vector<House> getHouses() { return _houses; }
    public HashSet<Integer> getIDs() { return _ids; }
    public TreeMap<Integer, House> getBornTimeIDs() { return _bornTimeIDs; }
}
