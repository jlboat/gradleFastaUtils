/*
 * The MIT License
 *
 * Copyright 2018 J. Lucas Boatwright.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fastaUtils;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author J. Lucas Boatwright
 */
public class RNA extends Sequence {
    public RNA(String sequence){
        super(sequence);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public boolean isAmbiguous(){
        return this.getNucleotideCount().get(6) != 0;
    }// end isAmbiguous method
    
    /**
     * Returns counts for nucleotides (or N/-/other) in a sequence in an integer array
     * Will count both lower or uppercase nucleotides
     * 
     * @return integer array with [A,C,G,T,N,-,other] counts
     */
    public ArrayList<Integer> getNucleotideCount(){
        char[] string = this.sequence.toCharArray();
        ArrayList<Integer> counts = new ArrayList<>(7);
        while(counts.size() < 7) counts.add(0);
        for (char nucleotide: string){
            switch (Character.toUpperCase(nucleotide)) {
                case 'A':
                    counts.set(0, counts.get(0) + 1);
                    break;
                case 'C':
                    counts.set(1, counts.get(1) + 1);
                    break;
                case 'G':
                    counts.set(2, counts.get(2) + 1);
                    break;
                case 'U':
                    counts.set(3, counts.get(3) + 1);
                    break;
                case 'N':
                    counts.set(4, counts.get(4) + 1);
                    break;
                case '-':
                    counts.set(5, counts.get(5) + 1);
                    break;                
                default:
                    counts.set(6, counts.get(6) + 1);
            }// end switch
        }// end for
        return counts;
    }// end getNucleotideCount method
    
        /**
     * Returns the percent GC of the sequence
     * @return double percent GC in sequence
     */
    public double getPercGC(){
        ArrayList<Integer> nuc_count = this.getNucleotideCount();
        double total = 0;
        for (int count: nuc_count){
            total += count;
        }// end for
        return (nuc_count.get(1)+nuc_count.get(2))/total;
    }// end getPercGC method
    
    /**
     * 
     * @return String reverse complement of the sequence
     */
    public String reverseComplement(){
        char[] revComp = new char[this.sequence.length()];
        char[] string = this.sequence.toCharArray();
        for (int i = 0; i < string.length; i++) {
            revComp[string.length-(i+1)] = string[i];
        } 
        for (int i = 0; i < revComp.length; i++){
            char nucleotide = revComp[i];
            if ((nucleotide == 'A') || (nucleotide == 'a')){
                revComp[i] = 'U';
            } else if ((nucleotide == 'C') || (nucleotide == 'c')){
                revComp[i] = 'G';
            } else if ((nucleotide == 'G') || (nucleotide == 'g')){
                revComp[i] = 'C';
            } else if ((nucleotide == 'U') || (nucleotide == 'u')){
                revComp[i] = 'A';
            } else {
                revComp[i] = nucleotide;
            }
        }// end for
        return String.valueOf(revComp);
    }// end reverseComplement method
    
    /**
     * Translate RNA to peptide sequence
     * 
     * @return String representing translation of RNA
     */
    public String rnaToProtein() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("UUU", "F");
        lhm.put("CUU", "L");
        lhm.put("AUU", "I");
        lhm.put("GUU", "V");
        lhm.put("UUC", "F");
        lhm.put("CUC", "L");
        lhm.put("AUC", "I");
        lhm.put("GUC", "V");
        lhm.put("UUA", "L");
        lhm.put("CUA", "L");
        lhm.put("AUA", "I");
        lhm.put("GUA", "V");
        lhm.put("UUG", "L");
        lhm.put("CUG", "L");
        lhm.put("AUG", "M");
        lhm.put("GUG", "V");
        lhm.put("UCU", "S");
        lhm.put("CCU", "P");
        lhm.put("ACU", "T");
        lhm.put("GCU", "A");
        lhm.put("UCC", "S");
        lhm.put("CCC", "P");
        lhm.put("ACC", "T");
        lhm.put("GCC", "A");
        lhm.put("UCA", "S");
        lhm.put("CCA", "P");
        lhm.put("ACA", "T");
        lhm.put("GCA", "A");
        lhm.put("UCG", "S");
        lhm.put("CCG", "P");
        lhm.put("ACG", "T");
        lhm.put("GCG", "A");
        lhm.put("UAU", "Y");
        lhm.put("CAU", "H");
        lhm.put("AAU", "N");
        lhm.put("GAU", "D");
        lhm.put("UAC", "Y");
        lhm.put("CAC", "H");
        lhm.put("AAC", "N");
        lhm.put("GAC", "D");
        lhm.put("UAA", "Stop");
        lhm.put("CAA", "Q");
        lhm.put("AAA", "K");
        lhm.put("GAA", "E");
        lhm.put("UAG", "Stop");
        lhm.put("CAG", "Q");
        lhm.put("AAG", "K");
        lhm.put("GAG", "E");
        lhm.put("UGU", "C");
        lhm.put("CGU", "R");
        lhm.put("AGU", "S");
        lhm.put("GGU", "G");
        lhm.put("UGC", "C");
        lhm.put("CGC", "R");
        lhm.put("AGC", "S");
        lhm.put("GGC", "G");
        lhm.put("UGA", "Stop");
        lhm.put("CGA", "R");
        lhm.put("AGA", "R");
        lhm.put("GGA", "G");
        lhm.put("UGG", "W");
        lhm.put("CGG", "R");
        lhm.put("AGG", "R");
        lhm.put("GGG", "G");
        char[] seq_array = this.sequence.toCharArray();
        StringBuilder protein = new StringBuilder();
        for (int i = 0; i < this.sequence.length(); i += 3) {
            char[] slice = Arrays.copyOfRange(seq_array, i, i + 3);
            String codon = String.valueOf(slice);
            if (codon != null) {
                protein.append(lhm.get(codon));
            }
        }
        return protein.toString();
    } // end rnaToProtein method
}
