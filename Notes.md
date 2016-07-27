Bitwise feature encoding of phonemes
###

Layout
##
Each phoneme, either consonant or vowel, is encoded in a byte.
This byte will be referred to as P.

 * Most significant bit (sign bit of P, or `(p & 128)`) stores 0 for consonants, 1 for vowels.
 * For consonants:
   * Bit 6 is always 1; this is used to help mark the "significance" of consonants relative to vowels.
   * Bits 5, 4, and 3 (`(p & 56) >> 3`) store the manner of the consonant in a rough way.
   * Bits 2, 1, and 0 (`(p & 7)`) are used to index the exact consonant encoded from a sequence of up to 8 consonants, with spaces not used.
     * 0 refers to taps, flaps, and trills, such as the English 'r' sound (sometimes).
       * Sequence is `" ɾⱱɽrʀʙ "`.
     * 1 refers to rare fricatives, including 'h' in English but not much else that might be familiar.
       * Sequence is `"ɸβʂʐħʕhɦ"`.
     * 2 refers to approximants, such as the 'y' sound in English (IPA 'j'), plus 'w'.
       * Sequence is `"ʋɹɻ jɰw "`.
     * 3 refers to lateral fricatives and lateral approximates, such as English 'l', plus IPA 'c'.
       * Sequence is `"ɬɮʎʟlɭcɟ"`.
     * 4 refers to nasals, such as 'n' and 'm' in English, plus the glottal stop.
       * Sequence is `"ɴʔnɳɲŋmɱ"`.
     * 5 refers to various "less forceful" fricatives, such as English's 'th', 'f', and 'v' sounds.
       * Sequence is `"xɣθðfvçʝ"`.
     * 6 refers to various "more forceful" fricatives, such as English's 's', 'z', and 'sh' sounds, plus 'g'.
       * Sequence is `"gqʃʒszχʁ"`.
     * 7 refers to plosives, such as English's 'p', 'b', 't', 'd', and 'k'.
       * Sequence is `"ʈɖpbtdkɢ"`.
 * For vowels:
   * Bits 6 and 5 (`(p & 96) >> 5`) are used to store a 2-bit number representing tone or accentation.
     0 is the default for an untoned vowel.
     In languages like Spanish with one different tone, then 3 should be used for an accented vowel.
     In languages like French, where accents in writing can change the actual vowel but don't usually
     change its tone, then tones might not be used at all (always 0).
     3 tones (plus "normal tone") is not enough for many languages, but it should be enough for most
     European languages.
   * Bits 4, 3, 2, 1, and 0 (`(p & 31)`) store the code for the vowel.
       * 00000 0 / unused
       * 00001 1 ɒ open back rounded vowel
       * 00010 2 o close-mid back rounded vowel
       * 00011 3 ɔ open-mid back rounded vowel
       * 00100 4 ʊ lowered-close back rounded vowel
       * 00101 5 ɞ open-mid central rounded vowel
       * 00110 6 u close back rounded vowel
       * 00111 7 / unused
       * 01000 8 y close front rounded vowel
       * 01001 9 ʏ lowered-close front rounded vowel
       * 01010 10 œ open-mid front rounded vowel
       * 01011 11 ø close-mid front rounded vowel
       * 01100 12 ɵ close-mid central rounded vowel
       * 01101 13 ʊ lowered-close back rounded vowel
       * 01110 14 / unused
       * 01111 15 ɶ open front rounded vowel
       * 10000 16 / unused
       * 10001 17 ɑ open back unrounded vowel
       * 10010 18 ɤ close-mid back unrounded vowel
       * 10011 19 ʌ open-mid back unrounded vowel
       * 10100 20 ə mid central unrounded vowel
       * 10101 21 ɜ open-mid central unrounded vowel
       * 10110 22 ɯ close back unrounded vowel
       * 10111 23 ɐ raised-open central unrounded vowel
       * 11000 24 i close front unrounded vowel
       * 11001 25 ɪ lowered-close front unrounded vowel
       * 11010 26 ɛ open-mid front unrounded vowel
       * 11011 27 e close-mid front unrounded vowel
       * 11100 28 ɘ close-mid central unrounded vowel
       * 11101 29 ɨ close central unrounded vowel
       * 11110 30 æ raised-open front unrounded vowel
       * 11111 31 a open front unrounded vowel
 * A value of literal 0 means there is no sound stored at a position, though it may be comparable to another
   word with a sound present at a position where the 0 is in this word.
 * Words are compared using bitwise Jaccard similarity (sometimes called Tanimoto coefficient), using some
   positioning rules to ensure similar sounds are in similar places in the ordering.

Consonants can also be looked up using the manner at left and the position in the sequence at top.

```
      01234567
000 0  ɾⱱɽrʀʙ 
001 1 ɸβʂʐħʕhɦ
010 2 ʋɹɻ jɰ  
011 3 ɬɮʎʟlɭcɟ
100 4 ɴʔnɳɲŋmɱ
101 5 xɣθðfvçʝ
110 6 gqʃʒszχʁ
111 7 ʈɖpbtdkɢ
```

