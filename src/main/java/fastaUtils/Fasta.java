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

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 *
 * @author lboat
 */
public class Fasta {
    private String filename;
    private String seq_type;
    private LinkedHashMap<String, Sequence> lhm = new LinkedHashMap<>();
        
    /**
     * A LinkedHashMap is parsed from a FASTA file
     * 
     * @param filename
     * @param seq_type Type of sequence data in FASTA
     * @throws IOException 
     */
    public Fasta(String filename, String seq_type) throws IOException{
        if (Files.exists(Paths.get(filename))){
            this.filename = filename;
        } else {
            System.err.println(
                    String.format("File, %s, not found.\n", filename));
        }// end if-else
        if (("DNA".equals(seq_type.toUpperCase())) || 
                ("RNA".equals(seq_type.toUpperCase()))){
            this.seq_type = seq_type.toUpperCase();
        } else {
            System.err.println( String.format(
                    "Sequence type, %s, not found.\n", seq_type));
            throw new IllegalArgumentException("Incompatible sequence type");
        }
        this.parse();
    }// end constructor from filename
    
    /**
     * A FASTA object is generated from a LinkedHashMap
     * @param lhm 
     */
    public Fasta(LinkedHashMap<String, Sequence> lhm){
        this.lhm = lhm;     
    }// end constructor from header-sequence hash
    
    @Override
    public String toString(){
        String seq = this.filename + " contains " + this.lhm.size() + " entries";
        return seq;
    }
    
    /**
     * 
     * @param fasta
     * @return 
     */
    public boolean equals(Fasta fasta){
        boolean header = this.getKeys().equals(fasta.getKeys());
        boolean seq_same = true;
        Object[] seqs = this.getValues().toArray();
        Object[] fa_seqs = fasta.getValues().toArray();
        for (int i = 0; i < seqs.length; i++) {
            seq_same = ((Sequence)seqs[i]).equals((Sequence)fa_seqs[i]) 
                    & seq_same;
        }
        return header & seq_same;
    }
    
    /**
     * 
     * @throws IOException 
     */
    private void parse() throws IOException{
        System.err.println("Parsing FASTA");
        Object[] file = Files.lines(Paths.get(this.filename)).toArray();
        String header = "";
        StringBuilder seq = new StringBuilder();
        for (int i = 0; i < file.length; i++){
            String line = String.valueOf(file[i]).trim();
            if (line.startsWith(">")){
                if (this.lhm.containsKey(line)){
                    System.err.println("Repeated header detected. " + 
                            "Using newest header-sequence pair.");
                }
                if (seq.length() == 0){
                    header = line;
                } else{
                    if ("RNA".equals(this.seq_type)){
                        this.lhm.put(header, new RNA(seq.toString()));
                    } else if ("DNA".equals(this.seq_type)){
                        this.lhm.put(header, new DNA(seq.toString()));
                    }
                    seq = new StringBuilder();
                    header = line;
                }// end if-else                
            } else if (i == file.length-1){
                seq.append(line);
                if ("RNA".equals(this.seq_type)){
                    this.lhm.put(header, new RNA(seq.toString()));
                } else if ("DNA".equals(this.seq_type)){
                    this.lhm.put(header, new DNA(seq.toString()));
                }
            }else {
                seq.append(line);
            }// end if-else
        }// end for
    }// end parse method
    
    /**
     * Writes a FASTA object to the file specified in the argument outfile.
     * 
     * @param outfile   name of the FASTA output file  
     * @param wrapcount  
     */
    public void toFile(String outfile, int wrapcount){
        System.err.println(String.format(
                "Writing FASTA to file %s at %d bases per line", 
                outfile, wrapcount));
        if ((wrapcount > 0) & (wrapcount < 60)){
            System.err.println("Unusual character wrap count " + 
                    "(typical {0,60,80})");
        }
        Path path = Paths.get(outfile);
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
            for (String key: this.lhm.keySet()){
                writer.write(String.format("%s%n",key));
                if (wrapcount == 0){                   
                    writer.write(String.format("%s%n",this.lhm.get(key)));
                } else {
                    char[] seq = this.lhm.get(key).toString().toCharArray();
                    for (int i = 0; i < seq.length; i = i + wrapcount) {
                        char[] substring;
                        if ((i+wrapcount) > (seq.length - 1)){
                            substring = Arrays.copyOfRange(seq, 
                                    i, seq.length);
                        } else {
                            substring = Arrays.copyOfRange(seq, 
                                    i, i + wrapcount);
                        }
                        writer.write(String.format("%s%n", 
                                String.valueOf(substring)));                     
                    }
                }// end if-else
            }// end for
        } catch (IOException ex){
            System.err.println(String.format("Error writing file %s",ex));
        }

    }// end toFile method
    
    /**
     * Writes Fasta to outfile name
     * 
     * @param outfile 
     */
    public void toFile(String outfile){
        this.toFile(outfile, 0);
    }
    
    /**
     * 
     * @return Fasta as LinkedHashMap
     */
    public LinkedHashMap<String, Sequence> getLhm(){
        return this.lhm;
    }
    
    /**
     * 
     * @return file name
     */
    public String getFilename(){
        return this.filename;
    }
    
    /**
     * 
     * @return sequence type DNA|RNA
     */
    public String getSequenceType(){
        return this.seq_type;
    }
    
    /**
     * Returns all of the FASTA headers as a set
     * 
     * @return  A set of the FASTA headers
     */
    public Set<String> getKeys(){
        return this.lhm.keySet();
    }// end getKeys method
    
    /**
     * Returns all of the FASTA sequences as a Collection.
     * 
     * @return Collection of FASTA sequences
     */
    public Collection<Sequence> getValues(){
        return this.lhm.values();
    }// end getValues method
    
    /**
     * Check to see if any FASTA sequences contain non [ACTGN-] characters
     * 
     * @return boolean true/false
     */
    public boolean containsAmbiguous(){
        int[] nucleotides = new int[7];
        this.lhm.values().forEach((seq) -> {
            if ("RNA".equals(seq_type)){
                Arrays.setAll(nucleotides, i -> nucleotides[i] + ((RNA)seq).getNucleotideCount().get(i));
            } else if ("DNA".equals(seq_type)){
                Arrays.setAll(nucleotides, i -> nucleotides[i] + ((DNA)seq).getNucleotideCount().get(i));
            }
        }); // end for
        return nucleotides[6] != 0;
    }// end containsAmbiguous method
}// end Fasta class 
