package utils.DataStorageServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import utils.Consts;

public class LocalDataService {
    public static void WriteLocalDataFile(SaveEntry saveEntryInstance) {
        File localDataFile = new File(saveEntryInstance.saveFileName);
        if (localDataFile.exists()) {
            try {
                FileWriter fw = new FileWriter(localDataFile);
                fw.write(ParseLocalDataEntry(saveEntryInstance));
                fw.close();
            } catch (IOException e) {
                System.err.println("ERROR: Couldn't write to save file!");
                e.printStackTrace();
            }

            // create new file
            // TODO: Rename settings file when saved/remove when not saved.
            // TODO: MAYBE: Check when opening app if there are any settings files which
            // have no associated save files
            // TODO: Add button to remove save + settings files (all/unused [case above])
        } else {
            try {
                if (localDataFile.createNewFile()) {
                    FileWriter fw = new FileWriter(localDataFile);
                    fw.write(ParseLocalDataEntry(saveEntryInstance));
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

    public static SaveEntry ReadLocalDataFile(String fileName) {
        // Find next index of unused file
        String localPath = Consts.LOCAL_STORAGE_DIRECTORY_PATH + "\\" + fileName;
        File localDataSaves = new File(localPath);
        SaveEntry entry = null;
        if (localDataSaves.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(localDataSaves));
                String data = br.readLine();
                entry = ParseLocalDataString(data);
                br.close();
            } catch (IOException e) {
                System.err.println("ERROR: Couldn't read from save file: \"" + fileName + "\"");
                e.printStackTrace();
            }
        } else {
            System.err.println("ERROR: Provided file name \"" + fileName + "\" can't be found as a save file!");
        }

        return entry;
    }

    private static SaveEntry ParseLocalDataString(String localData) {
        // TODO: continue here
        SaveEntry parsedEntry = new SaveEntry(localData);
        return parsedEntry;
    }

    private static String ParseLocalDataEntry(SaveEntry saveEntryInstance) {

        String index = saveEntryInstance.GetParsedIndex();
        String parsedSaveEntry = "index:" + index +
                "|data:" + saveEntryInstance.dataInfo;
        return parsedSaveEntry;
    }

    public static File[] GetAllSaveFilePaths() {
        // Find next index of unused file
        File localDataSaves = new File(Consts.LOCAL_STORAGE_DIRECTORY_PATH);

        if (!localDataSaves.exists()) {
            System.out.println("SYSTEM: Created local data saves directory!");
            localDataSaves.mkdirs();
        }

        // Override the filtering method for the custom save files we require
        FilenameFilter textFilter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                boolean isText = name.endsWith(".txt"),
                        hasCorrectName = name.startsWith("save"),
                        hasCorrectFormat = name.matches("save\\d+\\.txt");
                return isText && hasCorrectName && hasCorrectFormat;
            }
        };

        return localDataSaves.listFiles(textFilter);
    }

    public static String GetNextAvailableFileName() {

        // Get the very first index unused
        File[] filePathNames = GetAllSaveFilePaths();
        int index = 0;
        if (filePathNames != null) {
            for (File filePathName : filePathNames) {
                int fileIndex = Integer.valueOf(filePathName.getName()
                        .replace("save", "")
                        .replace(".txt", ""));
                if (fileIndex == index)
                    index++;
                else
                    break;
            }
        }

        String parsedIndex = ("00" + index);
        // Parse the number 0-999 to a 3-digit index for readablity and to work with the
        // parser above
        parsedIndex = parsedIndex.substring(parsedIndex.length() - 3, parsedIndex.length());

        String finalPath = Consts.LOCAL_STORAGE_DIRECTORY_PATH + "\\save" + parsedIndex + ".txt";
        return finalPath;
    }

    public static class SaveEntry {
        public String saveFileName;
        public String dataInfo;

        public SaveEntry(String dataString) {
            this.saveFileName = GetNextAvailableFileName();
            this.dataInfo = dataString;
        }

        public String GetParsedIndex() {
            if (saveFileName == null)
                return null;
            String[] saveSplit = saveFileName.split("save", 0);
            String index = saveSplit[saveSplit.length - 1].split(".txt")[0];
            String parsedIndex = ("00" + index);
            parsedIndex = parsedIndex.substring(parsedIndex.length() - 3, parsedIndex.length());
            return parsedIndex;
        }

        public String GetName() {
            if (saveFileName == null)
                return null;

            return "save" + this.GetParsedIndex() + ".txt";
        }
    }
}
