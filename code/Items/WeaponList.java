package itcom.gangstersquirrel.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WeaponList {

    private FileHandle fileHandle;
    private Gson gson = new Gson();

    /**
     * Reads the list of all weapons in the game from a JSON file and returns a list to use in the game.
     * If the file doesn't exist, this method will create a new file with the default configuration that is described here.
     * @return a list of weapon objects which contains all weapons in the game
     */
    public List<WeaponObject> getAllWeapons() {
        // JSON file to store all weapons in the game
        fileHandle = Gdx.files.local("json/weaponlist.json");

        // Default weapons list
        WeaponObject[] defaultWeaponList = new WeaponObject[] {
                new WeaponObject(0, "Stick", true, 5)
        };

        if (fileHandle.exists()) {
            String json = fileHandle.readString();
            if (!json.trim().isEmpty()) {
                return deserializeWeaponList(json);
            } else {
                System.err.println("JSON weapon list string is empty, creating default weapon list");
                serializeWeaponList(defaultWeaponList);
                return deserializeWeaponList(json);
            }
        } else {
            serializeWeaponList(defaultWeaponList);
            return deserializeWeaponList(fileHandle.readString());
        }
    }

    /**
     * Serialize weapon objects into JSON and write to the weapon list JSON file.
     * @param weaponList an array of weapon objects
     */
    private void serializeWeaponList(WeaponObject[] weaponList) {
        String json = gson.toJson(weaponList);

        // Creates new JSON file, if it doesn't exist already
        if (!fileHandle.exists()) {
            try {
                boolean successfull = fileHandle.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileHandle.writeString(json, false); // false = overwrite instead of append
    }

    /**
     * Deserialize JSON weapon list file and return it
     * @param json the JSON string that should be deserialized
     */
    private List<WeaponObject> deserializeWeaponList(String json) {
        return Arrays.asList(gson.fromJson(json, WeaponObject[].class));
    }
}
