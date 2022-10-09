package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Dimension;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class SettingsService {

    public static Settings GetSettings(String windowName) {
        // Instantiate the settings file
        File settingsFile = new File("./data/settings/settings.json");
        if (settingsFile.exists()) {
            System.out.println("SYSTEM: Settings file found!");
        } else {
            System.out.println("SYSTEM: Settings file does not exist! Creating...");
            CreateSettingsFile();
        }

        // Reading the content of file
        try {
            Scanner reader = new Scanner(settingsFile);
            String fileContent = "";
            // Chunk the content line by line in a fileContent String container
            while (reader.hasNextLine()) {
                String fileLine = reader.nextLine();
                fileContent += fileLine;
            }
            reader.close();

            // Load the file content in the JsonObject parser
            JsonObject settingsJson = JsonParser.parseString(fileContent).getAsJsonObject();

            // Get the data for the preffered window
            Settings settings = new Settings(settingsJson.get(windowName).getAsJsonObject());
            return settings;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    static void CreateSettingsFile() {

    }
}

class Settings {
    int width;
    int height;
    boolean isFullscreen;
    String closingOperation;
    String windowTitle;
    MenuSettings menuSettings;

    public Settings(JsonObject jsonData) {
        // Get values
        JsonElement _width = jsonData.get("width");
        JsonElement _height = jsonData.get("height");
        JsonElement _isFullscreen = jsonData.get("isFullscreen");
        JsonElement _closingOperation = jsonData.get("closingOperation");
        JsonElement _windowTitle = jsonData.get("windowTitle");
        JsonElement _menuSettings = jsonData.get("menuSettings");

        // Security check if they exist, otherwise set default values
        this.width = _width == null ? 300 : _width.getAsInt();
        this.height = _height == null ? 500 : _height.getAsInt();
        this.isFullscreen = _isFullscreen == null ? false : _isFullscreen.getAsBoolean();
        this.closingOperation = _closingOperation == null ? "EXIT_ON_CLOSE" : _closingOperation.getAsString();
        this.windowTitle = _windowTitle == null ? "Pathfinder" : _windowTitle.getAsString();
        this.menuSettings = new MenuSettings(_menuSettings == null ? null : _menuSettings.getAsJsonObject());

    }

    public Dimension GetSize() {
        return new Dimension(this.width, this.height);
    }

    public void SetSize(Dimension size) {
        this.width = size.width;
        this.height = size.height;
    }

    public void SetSize(int _width, int _height) {
        this.width = _width;
        this.height = _height;
    }

}