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
import java.util.LinkedHashMap;
import java.util.Set;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.numbers.Stats;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Histogram;

/**
 *
 * @author lboat
 */
public class FastaUtils {
    
    /**
     * 
     * @param fasta
     * @return 
     */
    public static LinkedHashMap<String, Number> stats(Fasta fasta) {
        LinkedHashMap<String, Number> stats = new LinkedHashMap<>();
        stats.put("Count", seqCount(fasta));
        stats.put("Minimum", minLength(fasta));
        stats.put("Maximum", maxLength(fasta));
        stats.put("Median", medianLength(fasta));
        stats.put("Mean", meanLength(fasta));
        stats.put("N50", nX(fasta, 50));
        return stats;
    }
    
    /**
     * 
     * @param fasta
     * @return 
     */
    public static Stats describe(Fasta fasta){
        int[] lengths = seqLengths(fasta);
        IntColumn nc = IntColumn.create("SeqLengths", lengths);
        return nc.stats();
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static int seqCount(Fasta fasta) {
        Set<String> keys = fasta.getKeys();
        return keys.size();
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static int[] seqLengths(Fasta fasta) {
        Sequence[] seqs = fasta.getValues().toArray(new Sequence[0]);
        int[] lengths = new int[seqs.length];
        for (int i = 0; i < seqs.length; i++) {
            lengths[i] = seqs[i].length();
        }
        return lengths;
    }

    /**
     * 
     * @param fasta
     * @param x
     * @return 
     */
    public static int nX(Fasta fasta, double x) {
        if ((x > 100) || (x < 0)) {
            System.err.println(
                    String.format("Invalid value for nX: %f:.2", x));
            System.err.println("Must be between 0 and 100");
            System.err.println("Typical values include N25, N50, N75");
            return -1;
        }
        int[] lengths = seqLengths(fasta);
        if (lengths.length == 1) {
            System.err.println(
                    String.format(
                            "Only one sequence present. Cannot calculate N%f:.0",
                            x));
        }
        x = x / 100;
        Arrays.sort(lengths);
        int[] cum_sum = baseCumulativeSum(fasta);
        int total_base_count = cum_sum[lengths.length - 1];
        for (int i = 0; i < cum_sum.length; i++) {
            int index = cum_sum.length - (i + 1);
            if (((total_base_count - cum_sum[index]) / (double) total_base_count) > x) {
                return lengths[index + 1];
            }
        }
        // In the event the above code fails
        return -1;
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static int minLength(Fasta fasta) {
        int[] lengths = seqLengths(fasta);
        Arrays.sort(lengths);
        return lengths[0];
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static int maxLength(Fasta fasta) {
        int[] lengths = seqLengths(fasta);
        Arrays.sort(lengths);
        return lengths[lengths.length - 1];
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static double medianLength(Fasta fasta) {
        int[] lengths = seqLengths(fasta);
        Arrays.sort(lengths);
        int index = lengths.length / 2;
        if (lengths.length % 2 == 1) { //odd num -- exact middle           
            return lengths[index];
        } else { // even number -- double
            double median = (lengths[index - 1] + (double) lengths[index]) / 2;
            return median;
        }
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static double meanLength(Fasta fasta) {
        int[] lengths = seqLengths(fasta);
        int total_length = baseCumulativeSum(fasta)[lengths.length - 1];
        return total_length / (double) lengths.length;
    }

    /**
     * 
     * @param fasta
     * @return 
     */
    public static int[] baseCumulativeSum(Fasta fasta) {
        int[] lengths = seqLengths(fasta);
        Arrays.sort(lengths);
        int[] cum_sum = new int[lengths.length];
        cum_sum[0] = lengths[0];
        for (int i = 1; i < cum_sum.length; i++) {
            cum_sum[i] = cum_sum[i - 1] + lengths[i];
        }
        return cum_sum;
    }

    /**
     * 
     * @param number_seqs
     * @param seq_lengths
     * @return 
     */
    public static Fasta randomFasta(int number_seqs, int seq_lengths) {
        LinkedHashMap<String, Sequence> lhm = new LinkedHashMap<>();
        for (int i = 0; i < number_seqs; i++) {
            String key = ">sequence_" + i;
            DNA dna = SequenceUtils.randomDNA((int)Math.ceil(Math.random() * seq_lengths));
            lhm.put(key, dna);
        }
        return new Fasta(lhm);
    }
    
    /**
     * 
     * @param fasta 
     */
    public static void generateHistogram(Fasta fasta){
        int[] lengths = seqLengths(fasta);
        IntColumn nc = IntColumn.create("SeqLengths", lengths);
        Table table = Table.create("FastaTable").addColumns(nc);
        Plot.show(Histogram.create("Sequence lengths", table, "SeqLengths"));
    }
}
