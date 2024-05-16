package Config;

import Generation.Habitat;
import frames.GenerationFrame;

import java.io.*;

public class ConfigMgr {
    private static final String $directory = "src/Config/config.cfg";
    private static Habitat _habitat = Habitat.getInstance(0, 0, 0, 0, 0, 0);

    public static void saveConfig() {
        try {
            FileWriter writer = new FileWriter(new File($directory));
            String config = _habitat.getWoodenTime() + "\n" + _habitat.getWoodenChance() + '\n' + _habitat.getWoodenAliveTime() + '\n' +
                    _habitat.getCapitalTime() + '\n' + _habitat.getCapitalChance() + '\n' + _habitat.getCapitalAliveTime();
            if (GenerationFrame.getShowInfoFlag()) {
                writer.write("1" + '\n');
            } else {
                writer.write("0" + '\n');
            }
            if (_habitat.getShowTime()) {
                writer.write("1" + '\n');
            } else {
                writer.write("0" + '\n');
            }
            writer.write(config);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            _habitat = Habitat.getInstance(0, 0, 0, 0, 0 ,0);
            BufferedReader reader = new BufferedReader(new FileReader($directory));
            GenerationFrame.setShowInfoFlag(Integer.parseInt(reader.readLine()) == 1);
            _habitat.setShowTime(Integer.parseInt(reader.readLine()) == 1);
            _habitat.setWoodenTime(Integer.parseInt(reader.readLine()));
            _habitat.setWoodenChance(Double.parseDouble(reader.readLine()));
            _habitat.setWoodenAliveTime(Integer.parseInt(reader.readLine()));
            _habitat.setCapitalTime(Integer.parseInt(reader.readLine()));
            _habitat.setCapitalChance(Double.parseDouble(reader.readLine()));
            _habitat.setCapitalAliveTime(Integer.parseInt(reader.readLine()));

            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /*  Save structure
    * show info flag
    * show time flag
    * wooden gen time
    * wooden gen chance
    * wooden lifetime
    * capital gen time
    * capital gen chance
    * capital lifetime
    *   */
}
