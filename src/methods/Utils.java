package methods;

import java.util.Random;

public class Utils {
    public static int randomeNumder (int min, int max){
        Random rnd = new Random(System.currentTimeMillis());
        return min + rnd.nextInt(max - min + 1);
    }
}
