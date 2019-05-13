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

import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.Math.round;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author lucas
 */
public class SequenceUtils {
        
    /**
     * 
     * @param length
     * @param percent_gc
     * @return Random DNA object of specified length and GC content
     */
    public static DNA randomDNA(int length, double percent_gc){
        char[] dna_string = new char[length];
        char[] gc_content = {'A','G'};
        char[] at_content = {'C','T'};
        for (int i = 0; i < dna_string.length; i++) {
            if (random() < percent_gc){
                dna_string[i] = gc_content[(int)round(random())];
            } else {
                dna_string[i] = at_content[(int)round(random())];
            }// end if-else           
        }// end for    
        DNA dna = new DNA(String.valueOf(dna_string));
        return dna;
    }
    
    /**
     * 
     * @param length
     * @return Random DNA object of specified length and ~50% GC content
     */
    public static DNA randomDNA(int length){
        return randomDNA(length, 0.5);
    }
    
    /**
     * 
     * @param length
     * @param percent_gc
     * @return Random RNA object of specified length and GC content
     */
    public static RNA randomRNA(int length, double percent_gc){
        // TODO -- extend for GC content in randomDNA
        DNA dna = randomDNA(length, percent_gc);
        return new RNA(dna.transcribe());
    }
    
    /**
     * 
     * @param length
     * @return Random RNA object of specified length with ~50% GC content
     */
    public static RNA randomRNA(int length){
        return randomRNA(length, 0.5);
    }
    
    /**
     * 
     * @param a
     * @param b
     * @return 
     */
    public static int hammingDistance(String a, String b){
        int distance = 0;
        char[] seq_a = a.toCharArray();
        char[] seq_b = b.toCharArray();
        for (int i = 0; i < a.length(); i++) {
            if (seq_a[i] != seq_b[i]){
                distance += 1;
            }
        }
        return distance;
    }// end hammingDistance

    /**
     * 
     * @param seqs
     * @return
     * @throws IOException 
     */
    public static Object[] consensus(Collection<Sequence> seqs) throws IOException {
        Object[] sequences = seqs.toArray();
        char[] first_seq = sequences[0].toString().toCharArray();
        char[][] profile = new char[sequences.length][first_seq.length];
        int[][] profile_matrix = new int[4][first_seq.length];
        // populate profile
        for (int i = 0; i < sequences.length; i++) {
            profile[i] = sequences[i].toString().toCharArray();
        }
        // fill profile_matrix
        System.out.println(sequences.length);
        for (int i = 0; i < first_seq.length; i++) {
            int a = 0;
            int c = 0;
            int g = 0;
            int t = 0;
            for (int j = 0; j < sequences.length; j++) {
                switch (Character.toUpperCase(profile[j][i])) {
                    case 'A':
                        a += 1;
                        break;
                    case 'C':
                        c += 1;
                        break;
                    case 'G':
                        g += 1;
                        break;
                    case 'T':
                        t += 1;
                        break;
                    default:
                        break;
                }
            }// end inner for
            profile_matrix[0][i] = a;
            profile_matrix[1][i] = c;
            profile_matrix[2][i] = g;
            profile_matrix[3][i] = t;
        }// end outer for

        // Generate concensus from profile_matrix
        // Return concensus and profile in object array
        Object[] consensus_and_profile = new Object[2];
        consensus_and_profile[0] = "";
        consensus_and_profile[1] = profile_matrix;
        for (int[] profile_matrix1 : profile_matrix) {
            System.err.println(Arrays.toString(profile_matrix1));
        }
        return consensus_and_profile;
    }// end consensus

    
}
