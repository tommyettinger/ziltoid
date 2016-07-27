Bitwise feature encoding of phonemes
###

Layout
##
 * It's pretty complicated.
 * A value of literal 0 means there is no sound stored at a position, though it may be comparable to another
   word with a sound present at a position where the 0 is in this word.
 * Words are compared using bitwise Jaccard similarity (sometimes called Tanimoto coefficient), using some
   positioning rules to ensure similar sounds are in similar places in the ordering.

Both consonants and vowels can be looked up in the table below, with more significant bits at left.

```
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
ɾ 01110 14
ⱱ 10100 20
ɽ 01110 14
r 01110 14
ʀ 01110 14
ʙ 01110 14
ɸ 10011 19
β 10010 18
ʂ 11000 24
ʐ 11001 25
ħ 01100 12
ʕ 01100 12
h 01100 12
ɦ 01100 12
ʋ 01111 15
ɹ 01110 14
ɻ 01110 14
j 00100 4
ɰ 01111 15
ɬ 01111 15
ɮ 01111 15
ʎ 00100 4
ʟ 01111 15
l 01111 15
ɭ 01111 15
c 11101 29
ɟ 11100 28
ɴ 10001 17
ʔ 00000 0
n 10001 17
ɳ 10001 17
ɲ 10001 17
ŋ 10001 17
m 10000 16
ɱ 10000 16
x 10111 23
ɣ 10111 23
θ 10101 21
ð 10101 21
f 10110 22
v 10100 20
ç 11101 29
ʝ 00100 4
g 11100 28
q 10111 23
ʃ 11010 26
ʒ 11011 27
s 11000 24
z 11001 25
χ 10111 23
ʁ 10111 23
ʈ 11111 31
ɖ 11110 30
p 10011 19
b 10010 18
t 11111 31
d 11110 30
k 11101 29
ɢ 11100 28
ɒ 00010 2
o 01011 11
ɔ 00010 2
ʊ 00001 1
ɞ 00001 1
u 01010 10
y 01010 10
ʏ 01010 10
œ 00011 3
ø 00011 3
ɵ 00001 1
ɶ 00001 1
ɑ 00010 2
ɤ 00001 1
ʌ 01000 8
ə 00001 1
ɜ 00011 3
ɯ 00001 1
ɐ 01000 8
i 00101 5
ɪ 00100 4
ɛ 00011 3
e 00110 6
ɘ 00001 1
ɨ 01010 10
æ 00111 7
a 00111 7
```

