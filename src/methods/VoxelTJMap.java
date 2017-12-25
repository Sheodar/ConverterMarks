package methods;

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

    public static void remakeVJ(String path1, String path2) throws Exception {
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
                String name = split[0] + "_" + split[1] + "," + split[3] + "," + split[2] + ".json";
                Writer writer = new OutputStreamWriter(new FileOutputStream(path2 + name, false), UTF_8);
//                System.out.print(Arrays.toString(split));
                writer.write("{ \n\"id\": \"" +
                        split[0] + "_" +
                        split[1] + "," +
                        split[3] + "," +
                        split[2] + " \",\n\"name\" : \"" +
                        split[0] + "\",\n\"icon\":\"waypoint-normal.png\",\n\"x\": " +
                        split[1] + ",\n\"y\": " +
                        split[3] + ",\n\"z\": " +
                        split[2] + ",\n\"r\": " +
                        randomeNumder(0, 255) +
                        ",\n\"g\": " +
                        randomeNumder(0, 255) +
                        ",\n\"b\": " +
                        randomeNumder(0, 255) +
                        ",\n\"enable\": true, \n\"type\": \"Normal\", \n\"origin\": \"JourneyMap\", \n\"dimensions\": [\n" +
                        split[10] + "\n]\n}");
                writer.flush();
                c++;
                writer.close();
            }
        }
    }
}
