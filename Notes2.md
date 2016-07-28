Bitwise feature encoding of phonemes
###

Layout
##
 * It's pretty complicated.
 * A value of literal 0 means there is no sound stored at a position, though it may be comparable to another
   word with a sound present at a position where the 0 is in this word.
 * Words are compared using a variant on Jaccard similarity, using several
   positioning rules to ensure similar sounds are in similar places in the index.
 * This document doesn't adequately describe the current approach, which uses a long as an index and produces
   a double as a rating of similarity.

Both consonants and vowels can be looked up in the table below, with more significant bits at left.

```
      0123
000 0  əɑɛ      put pot pet
001 1 ʌYuo  won why woo woe
010 2 ɪieæ  wit wee way wan
011 3 hwrl  hay wee ray lie
100 4 szʃʒ  sue zoo shy joe
101 5 vθfx  vie thy fie ach
110 6 mnbp  mom nun boy pie
111 7 gkdt  goo coo dye tie
```
strengths
s|t    |r    |e    |ng...     |th   |s
1|11111|01110|00011|0110000100|10101|1

2r10001 2r11100

necromancy
 |n    |e    |c    |romanc... |y    | 
0|10001|00011|11101|1000100100|00101|0
00010101011100001110100011100010
2r01110 2r01011 2r10000 2r00111 2r10001 2r11000

n 10001
ɛ 00011
k 11101
r 01110
o 01011
m 01010
æ 00111
n 10001
s 11000
i 00101
_____


```
ɾ 01110 14 \u000E
ⱱ 10100 20 \u0014
ɽ 01110 14 \u000E
r 01110 14 \u000E
ʀ 01110 14 \u000E
ʙ 01110 14 \u000E
ɸ 11011 27 \u001B
β 11010 26 \u001A
ʂ 10000 16 \u0010
ʐ 10001 17 \u0011
ħ 01100 12 \u000C
ʕ 01100 12 \u000C
h 01100 12 \u000C
ɦ 01100 12 \u000C
ʋ 01111 15 \u000F
ɹ 01110 14 \u000E
ɻ 01110 14 \u000E
j 01000 08 \u0008
ɰ 01111 15 \u000F
ɬ 01111 15 \u000F
ɮ 01111 15 \u000F
ʎ 01000 08 \u0008
ʟ 01111 15 \u000F
l 01111 15 \u000F
ɭ 01111 15 \u000F
c 11101 29 \u001D
ɟ 11100 28 \u001C
ɴ 11001 25 \u0019
ʔ 00000 00 \u0000
n 11001 25 \u0019
ɳ 11001 25 \u0019
ɲ 11001 25 \u0019
ŋ 11001 25 \u0019
m 11000 24 \u0018
ɱ 11000 24 \u0018
x 10111 23 \u0017
ɣ 10111 23 \u0017
θ 10101 21 \u0015
ð 10101 21 \u0015
f 10110 22 \u0016
v 10100 20 \u0014
ç 11101 29 \u001D
ʝ 01000 08 \u0008
g 11100 28 \u001C
q 10111 23 \u0017
ʃ 10010 18 \u0012
ʒ 10011 19 \u0013
s 10000 16 \u0010
z 10001 17 \u0011
χ 10111 23 \u0017
ʁ 10111 23 \u0017
ʈ 11111 31 \u001F
ɖ 11110 30 \u001E
p 11011 27 \u001B
b 11010 26 \u001A
t 11111 31 \u001F
d 11110 30 \u001E
k 11101 29 \u001D
ɢ 11100 28 \u001C
ɒ 00010 02 \u0002
o 00111 07 \u0007
ɔ 00010 02 \u0002
ʊ 00001 01 \u0001
ɞ 00001 01 \u0001
u 00110 06 \u0006
y 00110 06 \u0006
ʏ 00110 06 \u0006
œ 00011 03 \u0003
ø 00011 03 \u0003
ɵ 00001 01 \u0001
ɶ 00001 01 \u0001
ɑ 00010 02 \u0002
ɤ 00001 01 \u0001
ʌ 00100 04 \u0004
ə 00001 01 \u0001
ɜ 00011 03 \u0003
ɯ 00001 01 \u0001
ɐ 00100 04 \u0004
i 01001 09 \u0009
ɪ 01000 08 \u0008
ɛ 00011 03 \u0003
e 01010 10 \n
ɘ 00001 01 \u0001
ɨ 00110 06 \u0006
æ 01011 11 \u000B
a 01011 11 \u000B
w 01101 13 \r
Y 00101 05 \u0005
ʧ 10111 23 \u0017
ʨ 10111 23 \u0017
ʤ 10011 19 \u0013
```
Possible Alternates (not used)
##

```
     0123
00 0  wrl            wee/goo   ray       lie/you
01 1 hfm6  hay/uh-oh fee/vie   may/nay   the/thick
10 2 s3kx  sue/zoo   shy/joe   coo       ach/chi
11 3 bpdt  boy       pie       dye       tie

      0123
000 0  əɑɛ      put pot pet
001 1 ɪieæ  wit wee way wan
010 2 ʌYuo  won why woo woe
011 3 hwrl  hay wee ray lie
100 4 mnbp  mom nun boy pie 
101 5 vθfx  vie thy fie ach
110 6 szʃʒ  sue zoo shy joe
111 7 gkdt  goo coo dye tie

```

