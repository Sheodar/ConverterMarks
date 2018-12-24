package methods;

import javafx.scene.control.Label;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static methods.Utils.randomeNumder;

public class VoxelTJMap extends SQLException {

    private static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }
    private static int centredMarker(double x) {
        x = Math.ceil(x / 16);
        x = x - 0.5;
        x = x * 16;
        return (int) x;
    }

    public static void remakeVJ(String path1, String path2, boolean centre, boolean changeColor, double r, double g, double b, boolean changeColor2, double r2, double g2, double b2, String name2, Label success) throws Exception {
        InputStreamReader marker = new InputStreamReader(new FileInputStream(path1), "Cp1251");
        Scanner scan = new Scanner(marker);
        int c = 1;
        while (scan.hasNextLine()) {
            if (c < 4) {
                c++;
                scan.nextLine();
            } else {
                String line = scan.nextLine().replaceAll("(\\bname|\\b:|\\bsuffixworld|\\bx|\\bz|\\by|\\benabled|\\bdimensions|\\b#.*?\\b)", "");
                String line2 = removeLastChar(line).replaceAll("(\\\\|/|:|\\?|\\*|<|>|\\|.*?)", "+");
                String[] split = line2.split(",");

                int red = (int) Math.floor(Double.parseDouble(split[5].substring(3))*255);
                int green = (int) Math.floor(Double.parseDouble(split[6].substring(5))*255);
                int blue = (int) Math.floor(Double.parseDouble(split[7].substring(4))*255);
                double x = Double.parseDouble(split[1]);
                double z = Double.parseDouble(split[2]);

                int x2 = (int) x;
                int z2 = (int) z;
                if (centre) {
                    x2 = centredMarker(x);
                    z2 = centredMarker(z);
                }
                String name = split[0] + "_" + x2 + "," + split[3] + "," + z2 + ".json";
                if (changeColor) {
                    red = (int) Math.floor(r*255);
                    green = (int) Math.floor(g*255);
                    blue = (int) Math.floor(b*255);
                }
                if (changeColor2 && name.equals(name2)) {
                    red = (int) Math.floor(r2*255);
                    green = (int) Math.floor(g2*255);
                    blue = (int) Math.floor(b2*255);
                }

                Writer writer = new OutputStreamWriter(new FileOutputStream(path2 + name, false), UTF_8);

                writer.write("{ \n\"id\": \"" +
                        split[0] + "_" +
                        split[1] + "," +
                        split[3] + "," +
                        split[2] + " \",\n\"name\" : \"" +
                        split[0] + "\",\n\"icon\":\"waypoint-normal.png\",\n\"x\": " +
                        x2 + ",\n\"y\": " + //x
                        split[3] + ",\n\"z\": " +
                        z2 + ",\n\"r\": " + //z
                        red +
                        ",\n\"g\": " +
                        green +
                        ",\n\"b\": " +
                        blue +
                        ",\n\"enable\": true, \n\"type\": \"Normal\", \n\"origin\": \"JourneyMap\", \n\"dimensions\": [\n" +
                        split[10] + "\n]\n}");
                writer.flush();
                c++;
                writer.close();

            }
        }
        marker.close();
    }
}
