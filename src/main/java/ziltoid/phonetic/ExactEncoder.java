package ziltoid.phonetic;

import ziltoid.ds.CharCharMap;

/**
 * Created by Tommy Ettinger on 7/26/2016.
 */
public class ExactEncoder {
    private ExactEncoder(){}

    static final CharCharMap ipaToDigit = new CharCharMap(
            "ɾⱱɽrʀʙɸβʂʐħʕhɦʋɹɻjɰɬɮʎʟlɭcɟɴʔnɳɲŋmɱxɣθðfvçʝgqʃʒszχʁʈɖpbtdkɢɒoɔʊɞuyʏœøɵɶɑɤʌəɜɯɐiɪɛeɘɨæaYʧʨʤ".toCharArray(),
            "\u000E\u0014\u000E\u000E\u000E\u000E\u0013\u0012\u0018\u0019\u000C\u000C\u000C\u000C\u000F\u000E\u000E\u0004\u000F\u000F\u000F\u0004\u000F\u000F\u000F\u001D\u001C\u0011\u0000\u0011\u0011\u0011\u0011\u0010\u0010\u0017\u0017\u0015\u0015\u0016\u0014\u001D\u0004\u001C\u0017\u001A\u001B\u0018\u0019\u0017\u0017\u001F\u001E\u0013\u0012\u001F\u001E\u001D\u001C\u0002\u000B\u0002\u0001\u0001\n\n\n\u0003\u0003\u0001\u0001\u0002\u0001\u0008\u0001\u0003\u0001\u0008\u0005\u0004\u0003\u0006\u0001\n\u0007\u0007\u0009\u0017\u0017\u001B".toCharArray());
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
    public static int encodeIPA(CharSequence initial)
    {
        CharSequence word = processIPA(initial);
        int sz = word.length();
        if(sz == 0) return 0;
        if(sz == 1) return ipaToDigit.get(word.charAt(0)) << 1;
        char[] remap = new char[sz];
        int working = 0, start = 0, middle = 0, transition = 0, counter = 0;
        for(int i = 0; i < sz; i++)
        {
            remap[i] = ipaToDigit.get(word.charAt(i));
        }
        if(remap[0] == 24) // initial s
        {
            working = 1;
            start++;
        }
        if(sz >= 3 && remap[sz-1] == 24) // final s
        {
            working |= 0x80000000;
            sz--;
        }
        for (int i = 0; start < sz && i < 3; i++, start++) {
            working |= ((middle = remap[start]) << (1 + 5 * i));
        }
        for (int i = 0; start < sz && i < 12; start++, i++) {
            middle ^= (transition = remap[start]);
            if(i < 3)
                counter += ((middle & 1) << 1) + ((middle & 2) << 4)  + ((middle & 4) << 7) + ((middle & 8) << 10) + ((middle & 16) << 13);
            else
                counter += (middle & 1) + ((middle & 2) << 3) + ((middle & 4) << 6) + ((middle & 8) << 9) + ((middle & 16) << 12);
            middle = transition;
        }
        // gray coding each part of the counter
        counter = (((counter & 14) >> 1) ^ ((counter & 14) >>> 2))
                | (((((counter >>> 4) & 14) >> 1) ^ ((counter >>> 6) & 3)) << 3)
                | (((((counter >>> 8) & 14) >> 1) ^ ((counter >>> 10) & 3)) << 6)
                | (((((counter >>> 12) & 14) >> 1) ^ ((counter >>> 14) & 3)) << 9)
                | (((((counter >>> 16) & 14) >> 1) ^ ((counter >>> 18) & 3)) << 12);
        working |= counter << 16;
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

    public static double similarity(CharSequence a, CharSequence b)
    {
        int aCode = encodeIPA(a), bCode = encodeIPA(b),
                union = Integer.bitCount(aCode | bCode);
        if(union == 0)
            return 0;
        return Integer.bitCount(aCode & bCode) / (double)union;
    }
}
