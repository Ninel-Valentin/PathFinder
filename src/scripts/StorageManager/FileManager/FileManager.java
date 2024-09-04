package src.scripts.StorageManager.FileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.scripts.AppData.AppData;
import src.scripts.StorageManager.LocalStorage;
import src.scripts.utils.Consts;

public class FileManager {
    public static void SaveAppData(AppData appData) throws IOException, NullPointerException {
        File savesDir;
        if (appData.savePath == null) {
            int overwrite;
            do {
                overwrite = 0;
                appData.savePath = getFilePath();
                if (appData.savePath.endsWith("\\"))
                    appData.savePath = appData.savePath + "\\" + LocalStorage.GetNextSaveIndex(appData.savePath);
                else if (!appData.savePath.endsWith(".ser"))
                    appData.savePath = appData.savePath + ".ser";

                savesDir = new File(appData.savePath);
                if (savesDir.exists())
                    overwrite = JOptionPane.showConfirmDialog(null, "That path belongs to an existing file, do you want to overwrite it?", "Existing file detected", JOptionPane.OK_CANCEL_OPTION);
            } while (overwrite != 0);
        } else
            savesDir = new File(appData.savePath);

        FileOutputStream fileOutputStream = new FileOutputStream(savesDir);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(appData);
        objOutputStream.flush();
        objOutputStream.close();

        // Log the save to the recent projects file
        LocalStorage.AddRecentProject(appData.savePath);

    }

    public static void SaveAppDataAs(AppData appData) throws IOException, NullPointerException {
        File savesDir;
        int overwrite;
        do {
            overwrite = 0;
            appData.savePath = getFilePath();
            if (appData.savePath.endsWith("\\"))
                appData.savePath = appData.savePath + "\\" + LocalStorage.GetNextSaveIndex(appData.savePath);
            else if (!appData.savePath.endsWith(".ser"))
                appData.savePath = appData.savePath + ".ser";

            savesDir = new File(appData.savePath);
            if (savesDir.exists())
                overwrite = JOptionPane.showConfirmDialog(null, "That path belongs to an existing file, do you want to overwrite it?", "Existing file detected", JOptionPane.OK_CANCEL_OPTION);
        } while (overwrite != 0);

        FileOutputStream fileOutputStream = new FileOutputStream(savesDir);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(appData);
        objOutputStream.flush();
        objOutputStream.close();

        // Log the save to the recent projects file
        LocalStorage.AddRecentProject(appData.savePath);
    }

    public static AppData LoadAppData(String path) throws IOException, ClassNotFoundException, NullPointerException, InvalidClassException {
        File saveFile = new File(path);

        FileInputStream fileInputStream = new FileInputStream(saveFile);
        ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);

        AppData loadedData = (AppData) objInputStream.readObject();
        objInputStream.close();

        // Log the save to the recent projects file
        LocalStorage.AddRecentProject(path);

        return loadedData;
    }

    public static String getFilePath() {
        JFileChooser pathChooser = new JFileChooser(Consts.localSavesDir);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("SER Save files", "ser");
        pathChooser.setFileFilter(filter);

        int statusCode = pathChooser.showOpenDialog(null);
        if (statusCode == JFileChooser.APPROVE_OPTION) {
            String path = pathChooser.getSelectedFile().getAbsolutePath();
            return path;
        }
        return null;
    }
}