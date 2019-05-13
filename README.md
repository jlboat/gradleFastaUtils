# FastaUtils

[![Build Status](https://travis-ci.com/jlboat/FastaUtils.png?branch=master)](https://travis-ci.com/jlboat/FastaUtils)
[![codecov.io Code Coverage](https://img.shields.io/codecov/c/github/jlboat/FastaUtils.svg?maxAge=2592000)](https://codecov.io/github/jlboat/FastaUtils?branch=master)
[![GitHub license](https://img.shields.io/github/license/jlboat/FastaUtils.svg)](https://github.com/jlboat/FastaUtils)
[![issues](https://img.shields.io/github/issues/jlboat/FastaUtils.svg)](https://github.com/jlboat/FastaUtils/issues)
[![jitpack](https://jitpack.io/v/jlboat/FastaUtils.svg)](https://jitpack.io/#jlboat/FastaUtils)

## Use
This package was designed to facilitate biological sequence manipulation.

## Build
```bash
git clone https://github.com/jlboat/FastaUtils
cd FastaUtils
mvn package
```

Or see JitPack link above

## Examples (run in JShell)
### In terminal:
```bash
export CLASSPATH="./target/FastaUtils-1.0-jar-with-dependencies.jar"
jshell -c "./target/FastaUtils-1.0-jar-with-dependencies.jar"
```

### In JShell:

**Working with DNA**
```java
jshell> import com.github.jlboat.fastautils.DNA;

jshell> DNA dna = new DNA("ACTGCATGCTGACTGATCGAT");
dna ==> ACTGCATGCTGACTGATCGAT

jshell> dna.getNucleotideCount()
$3 ==> int[7] { 5, 5, 5, 6, 0, 0, 0 }

jshell> dna.reverseComplement()
$4 ==> "ATCGATCAGTCAGCATGCAGT"

jshell> dna.getKmers(18)
$5 ==> String[4] { "ACTGCATGCTGACTGATC", "CTGCATGCTGACTGATCG", "TGCATGCTGACTGATCGA", "GCATGCTGACTGATCGAT" }
```

**Working with RNA**
```java
jshell> import com.github.jlboat.fastautils.RNA;

jshell> RNA rna = new RNA("ACUGCAUGCUGACUGAUCGAU");
```

**Working with FASTA files**
```java
jshell> import com.github.jlboat.fastautils.Fasta;
jshell> import com.github.jlboat.fastautils.FastaUtils;
jshell> Fasta fasta = new Fasta("DNA.fasta","DNA");
fasta ==> DNA.fasta contains 50 entries

jshell> fasta.containsAmbiguous() // Check for characters other than { ACGTN- }
$4 == > false

jshell> fasta.toFile("New_DNA.fasta", 60)  // optionally takes characters per line -- ex. 60

jshell> Set<String> keys = fasta.getKeys()
keys ==> [>asmbl_477, >asmbl_1949, >asmbl_61, >asmbl_1533, ... >asmbl_1885, >asmbl_1469]

jshell> Collection<Sequence> values = fasta.getValues()
values ==> [ATGGTTCACCACCATCGCCACCACCCCCCAGCAGCAGCGGAGCAAGTAT ... GGTGCATCATTTATGTTTGGAAGATT
```

**Static utilities for FASTA files**
```java
jshell> FastaUtils.stats(fasta)
$8 ==> {Count=50, Minimum=189, Maximum=7047, Median=900.0, Mean=1112.74, N50=1341}

jshell> FastaUtils.minLength(fasta) // also, maxLength, medianLength, meanLength
$9 ==> 189

jshell> FastaUtils.nX(fasta, 90) // ex. N90 or any NX between 0-100
$10 ==> 522
```
