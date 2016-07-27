package ziltoid.phonetic;

import org.junit.Test;

/**
 * Created by Tommy Ettinger on 7/27/2016.
 */
public class ExactEncoderTest {
    public void printBits(int n)
    {
        System.out.println(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
    }
    public void printLimitedBits(int n, int limit)
    {
        System.out.print(String.format("%" + limit + "s", Integer.toBinaryString(n)).replace(' ', '0'));
    }
    public void printSectionedBits(int n)
    {
        printLimitedBits(n & 1, 1);
        System.out.print('|');
        printLimitedBits((n >>> 1) & 31, 5);
        System.out.print('|');
        printLimitedBits((n >>> 6) & 31, 5);
        System.out.print('|');
        printLimitedBits((n >>> 11) & 0xFFFFF, 20);
        System.out.print('|');
        printLimitedBits(n >>> 31, 1);
    }
    public void similaritySuite(String a, String b)
    {
        System.out.printf("%-14s vs. %-14s : ", a, b);
        //printSectionedBits(ExactEncoder.encodeIPA(a));
        //System.out.print(" vs. ");
        //printSectionedBits(ExactEncoder.encodeIPA(b));
        //System.out.println();
        System.out.println(ExactEncoder.similarity(a, b));
    }
    @Test
    public void testEncode()
    {
        int necromancy = ExactEncoder.encodeIPA("nɛkromænsi");
        System.out.println(necromancy);
        printBits(necromancy);
        printSectionedBits(necromancy);
        /*
        for (int i = 0; i < "nɛkromænsi".length(); i++) {
            System.out.print("nɛkromænsi".charAt(i) + " " + (int)ExactEncoder.ipaToDigit.get("nɛkromænsi".charAt(i)) + " ");
            printLimitedBits((int)ExactEncoder.ipaToDigit.get("nɛkromænsi".charAt(i)), 5);
            System.out.println();
        }
        */
        int strengths = ExactEncoder.encodeIPA("strɛngθs");
        System.out.println(strengths);
        printBits(strengths);
        printSectionedBits(strengths);
    }

    @Test
    public void testSimilarity()
    {
        similaritySuite("nɛkromænsi", "strɛngθs");
        similaritySuite("nɛkromænsi", "nɛkromænsi");
        similaritySuite("nɛkromænsi", "tɛknomænsi");
        similaritySuite("nɛkromænsi", "tɛkromænsi");
        similaritySuite("nɛkromænsi", "nɛkromənsi");
        similaritySuite("strɛngθs", "strɛngθs");
        similaritySuite("strɛngθs", "strɛngθ");
        similaritySuite("strɛngθs", "strɛngθən");
        similaritySuite("strɛngθ", "strɑng");
        similaritySuite("strenʒ", "strɑng");
        similaritySuite("trenkwɪl", "strɑng");
        similaritySuite("trenkwɪl", "strenʒ");
        similaritySuite("sɪt", "sæt");
        similaritySuite("spætər", "pætər");
        similaritySuite("spɑrtən", "spɑrtʌ");
    }
}
