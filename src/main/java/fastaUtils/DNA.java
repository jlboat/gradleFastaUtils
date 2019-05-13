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

import java.util.ArrayList;

/**
 *
 * @author J. Lucas Boatwright
 */
public class DNA extends Sequence {
    public DNA(String sequence){
        super(sequence);
    }    
    
    /**
     * 
     * @return boolean indicating whether sequence contains ambiguous nucleotides
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
                case 'T':
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
                revComp[i] = 'T';
            } else if ((nucleotide == 'C') || (nucleotide == 'c')){
                revComp[i] = 'G';
            } else if ((nucleotide == 'G') || (nucleotide == 'g')){
                revComp[i] = 'C';
            } else if ((nucleotide == 'T') || (nucleotide == 't')){
                revComp[i] = 'A';
            } else {
                revComp[i] = nucleotide;
            }
        }// end for
        return String.valueOf(revComp);
    }// end reverseComplement method
    
    /**
     * 
     * @return String representation of RNA from DNA sequence
     */
    public String transcribe(){        
        return this.sequence.replace('T', 'U').replace('t', 'u');
    }
}
