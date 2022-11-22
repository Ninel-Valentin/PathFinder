package utils.DataStorageServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utils.Consts;

public class SessionStorageService {
    public static void DeleteSessionStorage() {
        File sessionStorage = new File(Consts.Storage.SESSION_STORAGE_PATH);
        if (sessionStorage.delete()) {
            System.out.println("SYSTEM: Successfully deleted the active session storage.");
        } else {
            System.out.println("ERROR: Failed to delete the active session storage.");
        }
    }

    public static void WriteSessionStorage(String data) {
        File sessionStorage = new File(Consts.Storage.SESSION_STORAGE_PATH);
        if (sessionStorage.exists()) {
            try {
                FileWriter fw = new FileWriter(sessionStorage);
                fw.write(data);
                fw.close();
            } catch (IOException e) {
                System.err.println("ERROR: Couldn't write to save file!");
                e.printStackTrace();
            }
        } else {
            try {
                sessionStorage.getParentFile().mkdirs();
                if (sessionStorage.createNewFile()) {
                    FileWriter fw = new FileWriter(sessionStorage);
                    fw.write(data);
                    fw.close();
                } else {
                    System.err.println("ERROR: Couldn't create save file!");
                }
            } catch (IOException e) {
                System.err.println("ERROR: Couldn't create save file!");
                e.printStackTrace();
            }
        }
    }

    public static String ReadSessionStorage() {
        File sessionStorage = new File(Consts.Storage.SESSION_STORAGE_PATH);
        String data = null;
        if (sessionStorage.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(sessionStorage));
                data = br.readLine();
                br.close();
            } catch (IOException e) {
                System.err.println("ERROR: Couldn't read from session storage.");
                e.printStackTrace();
            }
        } else {
            System.err.println(
                    "ERROR: There is no session storage file saved at \"" + Consts.Storage.SESSION_STORAGE_PATH + "\".");
        }
        return data;
    }
}
