package methods;

import sample.Main;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
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
        Writer writer = new OutputStreamWriter(new FileOutputStream(path2 + "s4.red-server.ru~colon~16000.points", false), StandardCharsets.UTF_8);
        writer.write("subworlds:\n" +
                "oldNorthWorlds:\n" +
                "seeds:\n");
        for (File entry : folderEntries) {
            if (Objects.equals(getFileExtension(entry.getName()), ".json")) {
                if (entry.isFile()) {
                    InputStreamReader marker = new InputStreamReader(new FileInputStream(entry.getAbsolutePath()), UTF_8);
                    Scanner scan = new Scanner(marker);
                    int z = 0;
                    String[] split = new String[7];
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine().replaceAll("(\"|,|\\b\"|\\bx|\\by|\\bz|:|\\bname|\\benable|\\bdimensions.*?\\b)", "");
//                        String line2 = line.replaceAll("(\\\\|/|:|\\?|\\*|<|>|\\|.*?)", "+");
                        System.out.println(line);
                        if (z == 2) {
                            split[0] = line.replaceAll(" ", "");
                        } else if (z == 4) {
                            split[1] = line.replaceAll(" ", "");
                        } else if (z == 6) {
                            split[2] = line.replaceAll(" ", "");
                        } else if (z == 5) {
                            split[3] = line.replaceAll(" ", "");
                        } else if (z == 10) {
                            split[4] = line.replaceAll(" ", "");
                        } else if (z == 14) {
                            split[5] = line.replaceAll(" ", "");
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
                    marker.close();
                }
            }
        }
        writer.close();
    }
}
