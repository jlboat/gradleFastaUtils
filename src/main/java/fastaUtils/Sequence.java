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
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author lboat
 */
public abstract class Sequence {
    String sequence = "";
    
    /**
     * 
     * @param sequence 
     */
    public Sequence(String sequence){
        this.sequence = sequence;
    }// end construtor
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return this.sequence;
    }// end toString method
    
    /**
     * Check to see if sequence contains non [ACTGN-] characters
     * Or, for protein, non-standard amino acids
     * 
     * @return Boolean whether sequence contains ambiguous codes
     */
    public abstract boolean isAmbiguous();
    
    /**
     * 
     * @return integer length of sequence
     */
    public int length(){
        return this.sequence.length();
    }
    
    /**
     * 
     * @param seq
     * @return 
     */
    public boolean equals(Sequence seq){
        return this.sequence.equals(seq.sequence);
    }
    
    /**
     * 
     * @param kmer_length
     * @return 
     */
    public ArrayList<String> getKmers(int kmer_length){   
        int total_kmers = this.sequence.length() - kmer_length + 1;
        ArrayList<String> kmers = new ArrayList<>(total_kmers);
        char[] seq = this.sequence.toCharArray();
        for (int i = 0; i < total_kmers; i++) {
            char[] kmer_array = Arrays.copyOfRange(seq, i, i+kmer_length);
            kmers.add(new String(kmer_array));
        }
        return kmers;
    }
    
    /**
     * This function is designed to take a motif as a regular 
     * expression and find it in the sequence. If the motif is
     * not found, returns -1.
     * 
     * @param motif
     * @return index location where motif starts
     */
    public ArrayList containsMotif(String motif){
        ArrayList indices = new ArrayList();
        Pattern pattern = Pattern.compile(motif);
        ArrayList<String> kmers = getKmers(motif.length());
        for (int i = 0; i < kmers.size(); i++) {
            String kmer = kmers.get(i);
            Matcher matcher = pattern.matcher(kmer);
            if(matcher.matches()){
                indices.add(i);
            }
        }
        return indices;
    }// end containsMotif
}// end class
