package net.nerds.fishtraps.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FishTrapsConfig {

    private static Logger logger = LogManager.getLogger();
    private String fishTrapJsonLocation = FabricLoader.getInstance().getConfigDirectory().getPath() + "/fishTrap.json";
    private File configFile = new File(fishTrapJsonLocation);
    private JsonObject config;
    private Gson gson;

    public void loadConfig() {
        try {
            FileReader fileReader = new FileReader(fishTrapJsonLocation);
            setConfigs(fileReader);
        } catch (FileNotFoundException e) {
            buildFile();
        }
    }

    public void buildFile() {
        JsonObject jsonObject = getDefaults();
        try {
            FileWriter writer = new FileWriter(fishTrapJsonLocation);
            writer.write(jsonObject.toString());
            writer.flush();
        } catch (IOException e) {
            logger.fatal("Config IO Error", e);
        }
        config = jsonObject;
    }

    private void setConfigs(FileReader fileReader) {
            JsonParser parser = new JsonParser();
            config = parser.parse(fileReader).getAsJsonObject();
    }


    private JsonObject getDefaults() {
        JsonObject fishTrapJson = new JsonObject();
        fishTrapJson.add(FishTrapValues.WOODEN_TIME, new JsonPrimitive(900));
        fishTrapJson.add(FishTrapValues.WOODEN_LURE, new JsonPrimitive(1));
        fishTrapJson.add(FishTrapValues.WOODEN_LUCK, new JsonPrimitive(1));
        fishTrapJson.add(FishTrapValues.IRON_TIME, new JsonPrimitive(600));
        fishTrapJson.add(FishTrapValues.IRON_LURE, new JsonPrimitive(2));
        fishTrapJson.add(FishTrapValues.IRON_LUCK, new JsonPrimitive(2));
        fishTrapJson.add(FishTrapValues.DIAMOND_TIME, new JsonPrimitive(400));
        fishTrapJson.add(FishTrapValues.DIAMOND_LURE, new JsonPrimitive(3));
        fishTrapJson.add(FishTrapValues.DIAMOND_LUCK, new JsonPrimitive(3));
        fishTrapJson.add(FishTrapValues.PENALTY_MULTIPLIER_AMOUNT, new JsonPrimitive(40));
        fishTrapJson.add(FishTrapValues.SHOULD_PENALTY_MULTIPLIER, new JsonPrimitive(true));
        fishTrapJson.add(FishTrapValues.FISH_BAIT_DURABILITY, new JsonPrimitive(400));
        return fishTrapJson;
    }

    public int getProperty(String key) {
        try{
            return config.get(key).getAsInt();
        } catch (NumberFormatException nex) {
            logger.error("Can not Format the value you entered for {} into a number.  reverting to default", key);
            return getDefaults().get(key).getAsInt();
        } catch (Exception ex) {
            logger.error("Can not Format the value you entered for {} into a number..  reverting to default", key);
            return getDefaults().get(key).getAsInt();
        }
    }

    public boolean getBooleanProperty(String key) {
        try{
            return config.get(key).getAsBoolean();
        } catch (UnsupportedOperationException ex) {
            logger.error("Can not Format the value you entered for {} into a boolean..  reverting to default", key);
            return getDefaults().get(key).getAsBoolean();
        } catch (Exception ex) {
            logger.error("Can not Format the value you entered for {} into a boolean..  reverting to default", key);
            return getDefaults().get(key).getAsBoolean();
        }
    }
}
