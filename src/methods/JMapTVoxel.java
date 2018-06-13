package methods;

import javafx.scene.control.*;
import sample.Main;
import javafx.scene.control.Label;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static methods.Utils.randomeNumder;

public class JMapTVoxel extends SQLException {
    private static String getFileExtension(String mystr) {
        int index = mystr.lastIndexOf('.');
        return index == -1 ? null : mystr.substring(index);
    }

    private static int centredMarker(double x) {
        x = Math.ceil(x / 16);
        x = x - 0.5;
        x = x * 16;
        return (int) x;
    }

    public static void remakeJV(String path1, String path2, String name, boolean centre, boolean changeColor, double red, double green, double blue, Label success) throws Exception {
        File folder = new File(path1);
        File[] folderEntries = folder.listFiles();
        assert folderEntries != null;
        if (Objects.equals(name, "")) {
            name = "node4.red-server.ru~colon~16000.points";
        } else {
            name = name + ".points";
        }
        Writer writer = new OutputStreamWriter(new FileOutputStream(path2 + name, false), "Cp1251");
        writer.write("subworlds:\n" +
                "oldNorthWorlds:\n" +
                "seeds:\n");

        for (File entry : folderEntries) {
            if (Objects.equals(getFileExtension(entry.getName()), ".json")) {
                if (entry.isFile()) {
                    InputStreamReader marker = new InputStreamReader(new FileInputStream(entry.getAbsolutePath()), UTF_8);
                    Scanner scan = new Scanner(marker);
                    int q = 0;
                    String[] split = new String[9];
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine().replaceAll("(\"|,|\\b\"|\\bx|\\by|\\bz|:|\\bname|\\benable|\\bdimensions.*?\\b)", "");
//                        String line2 = line.replaceAll("(\\\\|/|:|\\?|\\*|<|>|\\|.*?)", "+");
                        //System.out.println(line);
                        if (q == 2) {
                            split[0] = line.replaceAll(" ", "");
                        } else if (q == 4) {
                            split[1] = line.replaceAll(" ", "");
                        } else if (q == 6) {
                            split[2] = line.replaceAll(" ", "");
                        } else if (q == 5) {
                            split[3] = line.replaceAll(" ", "");
                        } else if (q == 10) {
                            split[4] = line.replaceAll(" ", "");
                        } else if (q == 14) {
                            split[5] = line.replaceAll(" ", "");
                        } else if (q == 7) {
                            split[6] = line.replaceAll(" ", ""); //r
                        } else if (q == 8) {
                            split[7] = line.replaceAll(" ", ""); //g
                        } else if (q == 9) {
                            split[8] = line.replaceAll(" ", ""); //b
                        }
                        q++;
                    }

                    double x = Double.parseDouble(split[1]);
                    double z = Double.parseDouble(split[2]);
                    int x2 = (int) x;
                    int z2 = (int) z;
                    double r = (double) (Integer.parseInt(split[6].substring(1))) / 255;
                    double g = (double) (Integer.parseInt(split[7].substring(1))) / 255;
                    double b = (double) (Integer.parseInt(split[8].substring(1))) / 255;

                    if (centre) {
                        x2 = centredMarker(x);
                        z2 = centredMarker(z);
                    }
                    if (changeColor) {
                        r = red;
                        g = green;
                        b = blue;
                    }
                    writer.write(
                            "name:" + split[0] +
                                    ",x:" + x2 +
                                    ",z:" + z2 +
                                    ",y:" + split[3] +
                                    ",enabled:" + split[4] +
                                    ",red:" + r +
                                    ",green:" + g +
                                    ",blue:" + b +
                                    ",suffix:" +
                                    ",world:" +
                                    ",dimensions:" + split[5] + "#\n"
                    );
                    writer.flush();
                    marker.close();
                }
            }

        }
        success.setVisible(true);
        writer.close();
    }
}
