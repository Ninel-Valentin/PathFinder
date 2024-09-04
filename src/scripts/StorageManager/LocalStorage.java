package src.scripts.StorageManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

import src.scripts.AppData.AppData;
import src.scripts.utils.Consts;

public class LocalStorage {
    public static void InitLocalStorage() {
        // Try to create the saves directory
        File savesDir = new File(Consts.localSavesDir);
        savesDir.mkdirs();
        File settingsDir = new File(Consts.localSettingsDir);
        settingsDir.mkdirs();

        File recentProjectsFile = new File(Consts.recentProjectsDir);
        try {
            if (recentProjectsFile.createNewFile())
                System.out.println("Recent projects file created successfully!");
            else
                System.out.println("Recent projects file already exists!");
        } catch (IOException e) {
            System.out.println("System failed creating Recent project file.");
            e.printStackTrace();
        }
    }

    public static String GetNextSaveIndex(String path) {
        File savesDir = new File(path);
        File[] saves = savesDir.listFiles();
        // Order alphabetically
        Arrays.sort(saves);

        int nextIndex = 0;
        for (File saveFile : saves) {
            String indexedFileName = "save" + nextIndex + ".ser";
            int fileIndex = Integer.valueOf(saveFile.getName().replace("save", "").replace(".ser", ""));

            // The nextIndex is incremented, and the file array is sorted thus, if at any point the fileIndex > nextIndex, there is a missing file index
            if (fileIndex > nextIndex)
                return indexedFileName;

            nextIndex++;
        }

        return "save" + nextIndex + ".ser";
    }

    public static String[] saveRecentProjects(String[] recentProjects) throws IOException {
        File savesDir = new File(Consts.recentProjectsDir);

        FileOutputStream fileOutputStream = new FileOutputStream(savesDir);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(recentProjects);
        objOutputStream.flush();
        objOutputStream.close();

        return null;
    }

    public static String[] loadRecentProjects() throws IOException, ClassNotFoundException {
        String[] recentProjects = new String[10];
        File savesDir = new File(Consts.recentProjectsDir);

        FileInputStream fileInputStream = new FileInputStream(savesDir);
        // If file is empty, close and return an empty string array
        if (fileInputStream.available() == 0) {
            fileInputStream.close();
        } else {
            // Read the data
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);

            recentProjects = (String[]) objInputStream.readObject();

            objInputStream.close();
        }

        return recentProjects;
    }

    public static void AddRecentProject(String path) {
        try {
            String[] recentProjects = loadRecentProjects();
            // Add the value to the start
            recentProjects = UnshiftValue(recentProjects, path);
            // Deduplicate the array
            recentProjects = DeduplicateArray(recentProjects);
            // Resize the array to only 10 values
            recentProjects = Arrays.copyOf(recentProjects, Math.min(10, recentProjects.length));

            // Store the values in the file
            saveRecentProjects(recentProjects);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Append the {newValue} param at the end of the initialArray
     * 
     * @param initialArray
     * @param newValue
     * @return
     */
    private static String[] PushValue(String[] initialArray, String newValue) {
        String[] newArray = new String[initialArray.length + 1];

        for (int i = 0; i < initialArray.length; i++)
            newArray[i] = initialArray[i];
        newArray[initialArray.length] = newValue;

        return newArray;
    }

    /**
     * Append the {newValue} param at the start of the initialArray
     * 
     * @param initialArray
     * @param newValue
     * @return
     */
    private static String[] UnshiftValue(String[] initialArray, String newValue) {
        String[] newArray = new String[initialArray.length + 1];

        newArray[0] = newValue;
        for (int i = 1; i < newArray.length; i++)
            newArray[i] = initialArray[i - 1];

        return newArray;
    }

    private static String[] DeduplicateArray(String[] initialArray) {
        String[] newArray = new String[] {};

        // Iterate all strings in the initialArray
        for (String oldStr : initialArray) {

            if (oldStr != null) {
                boolean isIncluded = false;
                // Check if the current iteration string has been added already
                for (String newStr : newArray)
                    if (newStr.compareTo(oldStr) == 0) {
                        isIncluded = true;
                        break;
                    }

                // If it's not included already, add it
                if (!isIncluded)
                    newArray = PushValue(newArray, oldStr);
            }
        }

        return newArray;
    }
}
