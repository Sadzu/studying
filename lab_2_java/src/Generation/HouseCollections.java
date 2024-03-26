package Generation;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

public class HouseCollections {
    private final Vector<House> _houses;
    private final HashSet<Integer> _randomIdentificators;
    private final TreeMap<Integer, House> _bornTimeIdentificators;

    private static HouseCollections _instance;

    public static HouseCollections getInstance() {
        if (_instance == null) {
            _instance = new HouseCollections();
        }

        return _instance;
    }

    public HouseCollections() {
        _houses = new Vector<House>();
        _randomIdentificators = new HashSet<Integer>();
        _bornTimeIdentificators = new TreeMap<Integer, House>();
    }

    public void addToHouses(House house) {
        _houses.add(house);
    }
    public void addToBornTimeId(int key, House house) {
        _bornTimeIdentificators.put(key, house);
    }
    public void addToRandomId(int id) {
        _randomIdentificators.add(id);
    }

    public void checkAlive(int simulationTime) {
        if (_bornTimeIdentificators.containsKey(simulationTime)) {
            _randomIdentificators.remove(_bornTimeIdentificators.get(simulationTime).getId());
            _houses.remove(_bornTimeIdentificators.get(simulationTime));
            _bornTimeIdentificators.remove(simulationTime);
        }
        if (_bornTimeIdentificators.containsKey(simulationTime * (-1))) {
            _randomIdentificators.remove(_bornTimeIdentificators.get(simulationTime * (-1)).getId());
            _houses.remove(_bornTimeIdentificators.get(simulationTime * (-1)));
            _bornTimeIdentificators.remove(simulationTime * (-1));
        }
    }

    public Vector<House> getHouses() {return _houses;}

    public void clearCollections() {
        _houses.clear();
        _randomIdentificators.clear();
        _bornTimeIdentificators.clear();
    }

    public TreeMap<Integer, House> getBornTimeIds() {return _bornTimeIdentificators;}
}
