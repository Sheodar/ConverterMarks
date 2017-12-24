package methods;

import sample.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static methods.Utils.randomeNumder;

public class JMapTVoxel extends SQLException {
    private static String getFileExtension(String mystr) {
        int index = mystr.lastIndexOf('.');
        return index == -1 ? null : mystr.substring(index);
    }

    public static void remakeJV(String path1, String path2) throws Exception {
        File folder = new File(path1);
        File[] folderEntries = folder.listFiles();


        assert folderEntries != null;
        FileWriter writer = new FileWriter(path2 + "s4.red-server.ru~colon~16000.points", false);
        writer.write("subworlds:\n" +
                "oldNorthWorlds:\n" +
                "seeds:\n");
        for (File entry : folderEntries) {
            if (Objects.equals(getFileExtension(entry.getName()), ".json")) {
                if (entry.isFile()) {
                    FileReader marker = new FileReader(entry.getAbsolutePath());
                    Scanner scan = new Scanner(marker);
                    int z = 0;
                    String[] split = new String[7];
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine().replaceAll("(\"|,|\\b\"|\\bx|\\by|\\bz|:|\\bname|\\benable|\\bdimensions.*?\\b)", "");
                        String line2 = line.replaceAll("(\\\\|/|:|\\?|\\*|<|>|\\|.*?)", "+");
                        if (z == 2) {
                            split[0] = line2.replaceAll(" ", "");
                        } else if (z == 4) {
                            split[1] = line2.replaceAll(" ", "");
                        } else if (z == 6) {
                            split[2] = line2.replaceAll(" ", "");
                        } else if (z == 5) {
                            split[3] = line2.replaceAll(" ", "");
                        } else if (z == 10) {
                            split[4] = line2.replaceAll(" ", "");
                        } else if (z == 14) {
                            split[5] = line2.replaceAll(" ", "");
                        }
                        z++;
                    }
                    writer.write(
                            "name:" + split[0] +
                                    ",x:" + split[1] +
                                    ",z:" + split[2] +
                                    ",y:" + split[3] +
                                    ",enabled:" + split[4] +
                                    ",red:" + "0." + randomeNumder(1111111, 77777777) +
                                    ",green:" + "0." + randomeNumder(1111111, 77777777) +
                                    ",blue:" + "0." + randomeNumder(1111111, 77777777) +
                                    ",suffix:" +
                                    ",world:" +
                                    ",dimensions:" + split[5] + "#\n"
                    );
                    writer.flush();
                }
            }
        }
    }
}
