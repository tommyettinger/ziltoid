package ziltoid.phonetic;

import ziltoid.ds.CharCharMap;

/**
 * Created by Tommy Ettinger on 7/26/2016.
 */
public class ExactEncoder {
    private ExactEncoder(){}

    static final CharCharMap ipaToDigit = new CharCharMap(
            "ɾⱱɽrʀʙɸβʂʐħʕhɦʋɹɻjɰɬɮʎʟlɭcɟɴʔnɳɲŋmɱxɣθðfvçʝgqʃʒszχʁʈɖpbtdkɢɒoɔʊɞuyʏœøɵɶɑɤʌəɜɯɐiɪɛeɘɨæawYʧʨʤ".toCharArray(),
            "\u000E\u0014\u000E\u000E\u000E\u000E\u001B\u001A\u0010\u0011\u000C\u000C\u000C\u000C\u000F\u000E\u000E\u0008\u000F\u000F\u000F\u0008\u000F\u000F\u000F\u001D\u001C\u0019\u0000\u0019\u0019\u0019\u0019\u0018\u0018\u0017\u0017\u0015\u0015\u0016\u0014\u001D\u0008\u001C\u0017\u0012\u0013\u0010\u0011\u0017\u0017\u001F\u001E\u001B\u001A\u001F\u001E\u001D\u001C\u0002\u0007\u0002\u0001\u0001\u0006\u0006\u0006\u0003\u0003\u0001\u0001\u0002\u0001\u0004\u0001\u0003\u0001\u0004\u0009\u0008\u0003\n\u0001\u0006\u000B\u000B\r\u0005\u0017\u0017\u0013".toCharArray());
    //"\n\u0014\n\n\n\n\u001B\u001A\u0010\u0011\u0008\u0008\u0008\u0008\u000B\n\n\u0004\u000B\u000B\u000B\u0004\u000B\u000B\u000B\u001D\u001C\u0019\u0000\u0019\u0019\u0019\u0019\u0018\u0018\u0017\u0017\u0015\u0015\u0016\u0014\u001D\u0004\u001C\u0017\u0012\u0013\u0010\u0011\u0017\u0017\u001F\u001E\u001B\u001A\u001F\u001E\u001D\u001C\u0002\u000F\u0002\u0001\u0001\u000E\u000E\u000E\u0003\u0003\u0001\u0001\u0002\u0001\u000C\u0001\u0003\u0001\u000C\u0005\u0004\u0003\u0006\u0001\u000E\u0007\u0007\r\u0017\u0017\u0013".toCharArray());
    /*
    static final long[][] offsets = new long[][]{
            new long[]{},
            new long[]{14},
            new long[]{14, 54},
            new long[]{14, 34, 54},
            new long[]{14, 34, 54, 4},
            new long[]{14, 34, 54, 4, 24},
            new long[]{14, 34, 54, 4, 24, 44},
            new long[]{14, 34, 54, 4, 24, 44, 9},
            new long[]{14, 34, 54, 4, 24, 44, 9, 29},
            new long[]{14, 34, 54, 4, 24, 44, 9, 29, 49},
            new long[]{14, 34, 54, 4, 24, 44, 9, 29, 49, 19},
            new long[]{14, 34, 54, 4, 24, 44, 9, 29, 49, 19, 39},
            new long[]{14, 34, 54, 4, 24, 44, 9, 29, 49, 19, 39, 59},
    };
    */
    static final long[][] offsets = new long[][]{
        /*
            new long[]{}, //0
            new long[]{4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59}, //1
            new long[]{4, 34, 9, 39, 14, 44, 19, 49, 24, 54, 29, 59}, //2
            new long[]{4, 24, 44, 9, 29, 49, 14, 34, 54, 19, 39, 59}, //3
            new long[]{4, 19, 34, 49, 9, 24, 39, 54, 14, 29, 44, 59}, //4
            new long[]{4, 14, 24, 34, 44, 9, 19, 29, 39, 49, 54, 59}, //5
            new long[]{4, 14, 24, 34, 44, 54, 9, 19, 29, 39, 49, 59}, //6
            new long[]{4, 14, 19, 29, 39, 44, 54, 9, 24, 34, 49, 59}, //7
            new long[]{4, 9, 19, 24, 34, 39, 49, 54, 14, 29, 44, 59}, //8
            new long[]{4, 9, 14, 24, 29, 34, 44, 49, 54, 19, 39, 59}, //9
            new long[]{4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59}, //10
            new long[]{4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59}, //12
            new long[]{4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59}, //12
    */
            new long[]{},
            new long[]{4, 9, 14},
            new long[]{4, 14, 9, 19},
            new long[]{4, 14, 24, 9, 19, 29},
            new long[]{4, 14, 24, 34, 9, 19, 29, 39},
            new long[]{4, 14, 24, 34, 44, 9, 19, 29, 39, 49},
            new long[]{4, 14, 24, 34, 44, 54, 9, 19, 29, 39, 49, 59},
            new long[]{4, 14, 24, 34, 44, 54, 59, 9, 19, 29, 39, 49},
            new long[]{4, 14, 24, 34, 44, 49, 54, 59, 9, 19, 29, 39},
            new long[]{4, 14, 24, 34, 39, 44, 49, 54, 59, 9, 19, 29},
            new long[]{4, 14, 24, 29, 34, 39, 44, 49, 54, 59, 9, 19},
            new long[]{4, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59, 9},
            new long[]{4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59},
    };

    //static final long[] offset = new long[]{14, 34, 54, 4, 24, 44, 9, 29, 49, 19, 39, 59};
    //static final long[] offset = new long[]{9, 19, 29, 39, 49, 59, 4, 14, 24, 34, 44, 54};
    static final long[] pattern = new long[]{4, 4, 4, 9, 9, 9, 14, 14, 14, 19, 19, 24, 29, 34, 39, 44, 49, 54, 59};
    static {
        ipaToDigit.defaultReturnValue('\000');
    }
    public static CharSequence processIPA(CharSequence initial)
    {
        StringBuilder sb = new StringBuilder(initial);
        char c;
        for (int i = 0; i < sb.length();) {
            c = sb.charAt(i);
            if(c == 'ɪ' && i > 0)
            {
                c = sb.charAt(i-1);
                switch (c)
                {
                    case 'a':
                    case 'ɐ':
                    case 'ɑ':
                    case 'ɔ':
                        sb.setCharAt(i-1, '\u0009'); // special replacement for common sound "i" as in "sigh" or "y" as in "by"
                        sb.deleteCharAt(i);
                        break;
                    default: i++;
                }
            }
            else if(c == 'ʒ' && i > 0)
            {
                c = sb.charAt(i-1);
                switch (c)
                {
                    case 'd':
                        sb.deleteCharAt(i-1); // the blend of "d" and "zh" is essentially "j" in english, so we use "zh" for both its original use,
                                               // as the "s" in "treasure" or the "g" in "assuage", and the more common "j" sound, as in "jet".
                        break;
                    default: i++;
                }
            }
            else if(c == 'ʃ' && i > 0)
            {
                c = sb.charAt(i-1);
                switch (c)
                {
                    case 't':
                        sb.setCharAt(i-1, '\u0017'); // 'ch' or 'x' character used in "loch" and "ach"
                        sb.deleteCharAt(i);
                        break;
                    default: i++;
                }
            }
            else if(ipaToDigit.containsKey(c)) {
                i++;
            }
            else
            {
                sb.deleteCharAt(i);
            }
        }
        return sb;
    }
    public static long encodeIPA(CharSequence initial) {
        CharSequence word = processIPA(initial);
        int sz = word.length(), start = 0, startingS = 0;
        if (sz == 0) return 0;
        if (sz == 1)
        {
            long l = ipaToDigit.get(word.charAt(0));
            return (l << 14) | (l << 29);
        }
        long[] remap = new long[sz], off;
        long working = 0, middle = 0, transition, counter = 0;
        for (int i = 0; i < sz; i++) {
            remap[i] = ipaToDigit.get(word.charAt(i));
        }
        if (remap[0] == 16) // initial s
        {
            working = 1L;
            start = startingS = 1;
        }
        if (sz >= 3 && remap[sz - 1] == 16) // final s
        {
            working |= 2L;
            sz--;
        }
        if (sz - startingS > 12) {
            if (sz - startingS > 15)
                working |= 12;
            off = offsets[12];
        }
        else
            off = offsets[sz - startingS];

        for (int i = 0; i < off.length; i++) {
            working |= (remap[start] << off[i]);
            start = (start == sz - 1) ? startingS : start + 1;
        }
        return working;
    }
    public static int encodeIPA_nonMetric(CharSequence initial)
    {
        CharSequence word = processIPA(initial);
        int sz = word.length();
        if(sz == 0) return 0;
        if(sz == 1) return ipaToDigit.get(word.charAt(0)) << 1;
        double dsz = sz;
        char[] remap = new char[sz];
        int working = 0, start = 0, middle = 0, transition = 0, counter = 0;
        for(int i = 0; i < sz; i++)
        {
            remap[i] = ipaToDigit.get(word.charAt(i));
        }
        if(remap[0] == 16) // initial s
        {
            working = 1;
            start++;
        }
        if(sz >= 3 && remap[sz-1] == 16) // final s
        {
            working |= 0x80000000;
            sz--;
        }
        for (int i = 0; start < sz && i < 2; i++, start++) {
            working |= (remap[start] << (1 + 5 * i));
        }
        if(start < sz)
            middle = remap[start];
        for (int i = 0; start < sz && i < 12; start++, i++) {
            middle ^= (transition = remap[start]);
            if((sz >= 6 && i < 2) || (sz < 6 && i / dsz < 0.38))
                counter += ((middle & 1) << 2) + ((middle & 2) << 7)  + ((middle & 4) << 12) + ((middle & 8) << 15) + ((middle & 16) << 20);
            if((sz >= 6 && i < 6 && i >= 2) || (sz < 6 && i / dsz > 0.38 && i / dsz < 0.7))
                counter += ((middle & 1) << 1) + ((middle & 2) << 6)  + ((middle & 4) << 11) + ((middle & 8) << 15) + ((middle & 16) << 20);
            if((sz >= 6 && (i >= 6 || (i & 2) == 2)) || (sz < 6 && i / dsz > 0.38 && i / dsz < 0.55))
                counter += (middle & 1) + ((middle & 2) << 5) + ((middle & 4) << 10) + ((middle & 8) << 15) + ((middle & 16) << 20);
            middle = transition;
        }
        // gray coding each part of the counter
        counter = (((counter >>> 1) & 14) ^ ((counter >>> 2) & 7))
                | ((((counter >>> 6) & 14) ^ ((counter >>> 7) & 7)) << 4)
                | ((((counter >>> 11) & 14) ^ ((counter >>> 12) & 7)) << 8)
                | ((((counter >>> 16) & 14) ^ ((counter >>> 17) & 7)) << 12)
                | ((((counter >>> 21) & 14) ^ ((counter >>> 22) & 7)) << 16);
        working |= counter << 11;
        return working;
    }
    public static int encodeIPA_old(CharSequence initial)
    {
        CharSequence word = processIPA(initial);
        int sz = word.length();
        if(sz == 0) return 0;
        if(sz == 1) return ipaToDigit.get(word.charAt(0)) << 1;
        char[] remap = new char[sz];
        int working = 0, start = 0, middle = 0;
        for(int i = 0; i < sz; i++)
        {
            remap[i] = ipaToDigit.get(word.charAt(i));
        }
        if(remap[0] == 24) // initial s
        {
            working = 1;
            start++;
        }
        if(sz >= 3 && remap[sz-1] == 24) // initial s
        {
            working |= 0x80000000;
            sz--;
        }
        for (int i = 0; start < sz - 1 && i < 3; i++, start++) {
            working |= (remap[start] << (1 + 5 * i));
        }
        for (int i = 32; start < sz - 1; i = (i - 1) & 31, start++) {
            middle += i * remap[start];
            middle &= 1023;
        }
        working |= middle << 16;
        if(start == sz - 1)
        {
            working |= remap[start] << 26;
        }
        return working;
    }

    public static float similarity(CharSequence a, CharSequence b)
    {
        long aCode = encodeIPA(a), bCode = encodeIPA(b), ta, tb, pat,
                union = (Math.max(aCode & 3, bCode & 3) << 4) + (Math.max(aCode & 12, bCode & 12) << 2) + (Math.max(a.length(), b.length()) << 5),
                intersection = (Math.min(aCode & 3, bCode & 3) << 4) + (Math.min(aCode & 12, bCode & 12) << 2) + (Math.min(a.length(), b.length()) << 5);
        for (int i = 0; i < 19; i++) {
            pat = pattern[i];
            ta = (aCode >>> pat) & 31;
            tb = (bCode >>> pat) & 31;
            union += (((Math.max(ta & 3, tb & 3) + Math.max(ta >>> 2, tb >>> 2)) << 2) + 3);
            intersection += (((Math.min(ta & 3, tb & 3) + Math.min(ta >>> 2, tb >>> 2)) << 2) + 3);
        }
        return intersection / (float)union;
    }
    public static double similarity_old(CharSequence a, CharSequence b)
    {
        int aCode = encodeIPA_nonMetric(a), bCode = encodeIPA_nonMetric(b),
                union = Integer.bitCount(aCode | bCode);
        if(union == 0)
            return 0;
        return Integer.bitCount(aCode & bCode) / (double)union;
    }
}
