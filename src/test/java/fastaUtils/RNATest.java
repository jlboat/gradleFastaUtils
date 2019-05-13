package fastaUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author J. Lucas Boatwright
 */
public class RNATest {
    
    public RNATest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class RNA.
     */
    @Test
    public void testToString() {
        //System.out.println("toString");
        RNA instance = new RNA("AAAACCCGUU--N");
        String expResult = "AAAACCCGUU--N";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKmers method, of class Sequence
     */
    @Test
    public void testGetKmers(){
        //System.out.println("getKmers");
        RNA instance = new RNA("AAAACCCGUU--N");
        ArrayList<String> expResult = new ArrayList(Arrays.asList("AAA","AAA","AAC","ACC","CCC",
        "CCG","CGU","GUU","UU-","U--","--N"));
        ArrayList<String> result = instance.getKmers(3);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getNucleotideCount method, of class RNA.
     */
    @Test
    public void testGetNucleotideCount() {
        //System.out.println("getNucleotideCount");
        RNA instance = new RNA("AAAACCCGUU--N");
        RNA lower = new RNA("aaaacccguu--ni");
        ArrayList<Integer> expResult = new ArrayList<>(Arrays.asList(4,3,1,2,1,2,0));
        ArrayList<Integer> lowerResult = new ArrayList<>(Arrays.asList(4,3,1,2,1,2,1));
        ArrayList<Integer> result = instance.getNucleotideCount();
        assertEquals(expResult, result);
        assertEquals(lowerResult, lower.getNucleotideCount());
    }

    /**
     * Test of getPercGC method, of class RNA.
     */
    @Test
    public void testGetPercGC() {
        //System.out.println("getPercGC");
        RNA instance = new RNA("ACAACCCGUUC-NG");
        double expResult = 0.5;
        double result = instance.getPercGC();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of length method, of class RNA.
     */
    @Test
    public void testLength() {
        //System.out.println("length");
        RNA instance = new RNA("ACAACCCGUUC-NG");
        int expResult = 14;
        int result = instance.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAmbiguous method, of class RNA.
     */
    @Test
    public void testIsAmbiguous() {
        //System.out.println("isAmbiguous");
        RNA instance = new RNA("AAAACCCGUU--N");
        RNA bad = new RNA("GCHGIJL");
        assertEquals(false, instance.isAmbiguous());
        assertEquals(true, bad.isAmbiguous());
    }

    /**
     * Test of reverseComplement method, of class RNA.
     */
    @Test
    public void testReverseComplement() {
        //System.out.println("reverseComplement");
        RNA instance = new RNA("AAAACCCGUU--N");
        RNA lower = new RNA("aaaacccguu--nI");
        String expResult = "N--AACGGGUUUU";
        String lowerResult = "In--AACGGGUUUU";
        String result = instance.reverseComplement();
        assertEquals(expResult, result);
        assertEquals(lowerResult, lower.reverseComplement());
    }
    
    /** 
     * Test of rnaToProtein method, of class RNA
     */
    @Test
    public void testRnaToProtein(){
        assertTrue("F".equals(new RNA("UUU").rnaToProtein()));
        assertTrue("L".equals(new RNA("CUU").rnaToProtein()));
        assertTrue("I".equals(new RNA("AUU").rnaToProtein()));
        assertTrue("V".equals(new RNA("GUU").rnaToProtein()));
        assertTrue("F".equals(new RNA("UUC").rnaToProtein()));
        assertTrue("L".equals(new RNA("CUC").rnaToProtein()));
        assertTrue("I".equals(new RNA("AUC").rnaToProtein()));
        assertTrue("V".equals(new RNA("GUC").rnaToProtein()));
        assertTrue("L".equals(new RNA("UUA").rnaToProtein()));
        assertTrue("L".equals(new RNA("CUA").rnaToProtein()));
        assertTrue("I".equals(new RNA("AUA").rnaToProtein()));
        assertTrue("V".equals(new RNA("GUA").rnaToProtein()));
        assertTrue("L".equals(new RNA("UUG").rnaToProtein()));
        assertTrue("L".equals(new RNA("CUG").rnaToProtein()));        
        assertTrue("M".equals(new RNA("AUG").rnaToProtein()));
        assertTrue("V".equals(new RNA("GUG").rnaToProtein()));
        assertTrue("S".equals(new RNA("UCU").rnaToProtein()));
        assertTrue("P".equals(new RNA("CCU").rnaToProtein()));
        assertTrue("T".equals(new RNA("ACU").rnaToProtein()));
        assertTrue("A".equals(new RNA("GCU").rnaToProtein()));
        assertTrue("S".equals(new RNA("UCC").rnaToProtein()));
        assertTrue("P".equals(new RNA("CCC").rnaToProtein()));
        assertTrue("T".equals(new RNA("ACC").rnaToProtein()));
        assertTrue("A".equals(new RNA("GCC").rnaToProtein()));
        assertTrue("S".equals(new RNA("UCA").rnaToProtein()));
        assertTrue("P".equals(new RNA("CCA").rnaToProtein()));
        assertTrue("T".equals(new RNA("ACA").rnaToProtein()));
        assertTrue("A".equals(new RNA("GCA").rnaToProtein()));
        assertTrue("S".equals(new RNA("UCG").rnaToProtein()));
        assertTrue("P".equals(new RNA("CCG").rnaToProtein()));
        assertTrue("T".equals(new RNA("ACG").rnaToProtein()));
        assertTrue("A".equals(new RNA("GCG").rnaToProtein()));
        assertTrue("Y".equals(new RNA("UAU").rnaToProtein()));
        assertTrue("H".equals(new RNA("CAU").rnaToProtein()));
        assertTrue("N".equals(new RNA("AAU").rnaToProtein()));
        assertTrue("D".equals(new RNA("GAU").rnaToProtein()));
        assertTrue("Y".equals(new RNA("UAC").rnaToProtein()));
        assertTrue("H".equals(new RNA("CAC").rnaToProtein()));
        assertTrue("N".equals(new RNA("AAC").rnaToProtein()));
        assertTrue("D".equals(new RNA("GAC").rnaToProtein()));
        assertTrue("Stop".equals(new RNA("UAA").rnaToProtein()));
        assertTrue("Q".equals(new RNA("CAA").rnaToProtein()));
        assertTrue("K".equals(new RNA("AAA").rnaToProtein()));
        assertTrue("E".equals(new RNA("GAA").rnaToProtein()));
        assertTrue("Stop".equals(new RNA("UAG").rnaToProtein()));
        assertTrue("Q".equals(new RNA("CAG").rnaToProtein()));
        assertTrue("K".equals(new RNA("AAG").rnaToProtein()));
        assertTrue("E".equals(new RNA("GAG").rnaToProtein()));
        assertTrue("C".equals(new RNA("UGU").rnaToProtein()));
        assertTrue("R".equals(new RNA("CGU").rnaToProtein()));
        assertTrue("S".equals(new RNA("AGU").rnaToProtein()));
        assertTrue("G".equals(new RNA("GGU").rnaToProtein()));
        assertTrue("C".equals(new RNA("UGC").rnaToProtein()));
        assertTrue("R".equals(new RNA("CGC").rnaToProtein()));
        assertTrue("S".equals(new RNA("AGC").rnaToProtein()));
        assertTrue("G".equals(new RNA("GGC").rnaToProtein()));
        assertTrue("Stop".equals(new RNA("UGA").rnaToProtein()));
        assertTrue("R".equals(new RNA("CGA").rnaToProtein()));
        assertTrue("R".equals(new RNA("AGA").rnaToProtein()));
        assertTrue("G".equals(new RNA("GGA").rnaToProtein()));
        assertTrue("W".equals(new RNA("UGG").rnaToProtein()));
        assertTrue("R".equals(new RNA("CGG").rnaToProtein()));
        assertTrue("R".equals(new RNA("AGG").rnaToProtein()));
        assertTrue("G".equals(new RNA("GGG").rnaToProtein()));
    }
}
