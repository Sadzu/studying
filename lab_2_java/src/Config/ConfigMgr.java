package Config;

import Generation.Habitat;
import frames.GenerationFrame;

import java.io.*;

public class ConfigMgr {
    private static final String $directory = "src/Config/config.cfg";
    private static final Habitat _habitat = Habitat.getInstance();

    public void saveConfig() {
        try {
            FileWriter writer = new FileWriter(new File($directory));
            String config = _habitat.getWoodenTime() + "\n";
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            FileReader reader = new FileReader(new File($directory));
            reader.read();
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
