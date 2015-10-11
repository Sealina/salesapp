package BasicUtility;

import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class RandomPasswordGenerator {

    private static final String AVALIABLE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "!@#$%^&*_=+-/";

    private static final Random rand = new Random();
    private static int len = 0;
    private static StringBuilder password = new StringBuilder();

    public static String generatePassword(int minLen, int maxLen) throws IllegalArgumentException {
        if (minLen > maxLen) {
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        }
        len = rand.nextInt(maxLen - minLen + 1) + minLen;
        //clear & init string
        password = new StringBuilder(StringUtils.repeat('\u0000', len));
        for (int i = 0; i < len; ++i)
        {
            password.setCharAt(i, AVALIABLE_CHARS.charAt(rand.nextInt(AVALIABLE_CHARS.length())));
        }
        return password.toString();
    }
}
