package utils.DataStorageServices;

import java.io.File;

public class LocalDataService {
    public static void SaveLocalData() {
        File localDataFile = new File("%appdata%/PathfinderApp/saves/*.txt");
        if (localDataFile.exists()) {
            // create new file
            // TODO: Rename settings file when saved/remove when not saved.
            // TODO: MAYBE: Check when opening app if there are any settings files which
            // have no associated save files
            // TODO: Add button to remove save + settings files (all/unused [case above])

        } else {

        }
    }
}
