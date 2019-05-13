package fastaUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import fastaUtils.DNA;
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
public class DNATest {
    
    public DNATest() {
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
     * Test of toString method, of class DNA.
     */
    @Test
    public void testToString() {
        //System.out.println("toString");
        DNA instance = new DNA("AAAACCCGTT--N");
        String expResult = "AAAACCCGTT--N";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
    /**
     * Test of getKmers method, of class Sequence
     */
    @Test
    public void testGetKmers(){
        //System.out.println("getKmers");
        DNA instance = new DNA("AAAACCCGTT--N");
        ArrayList<String> expResult = new ArrayList(Arrays.asList("AAA","AAA","AAC","ACC","CCC",
        "CCG","CGT","GTT","TT-","T--","--N"));
        ArrayList<String> result = instance.getKmers(3);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNucleotideCount method, of class DNA.
     */
    @Test
    public void testGetNucleotideCount() {
        //System.out.println("getNucleotideCount");
        DNA instance = new DNA("AAAACCCGTT--N");
        DNA lower = new DNA("aaaacccgtt--ni");
        ArrayList<Integer> expResult = new ArrayList<>(Arrays.asList(4,3,1,2,1,2,0));
        ArrayList<Integer> lowerResult = new ArrayList<>(Arrays.asList(4,3,1,2,1,2,1));
        ArrayList<Integer> result = instance.getNucleotideCount();
        assertEquals(expResult, result);
        assertEquals(lowerResult, lower.getNucleotideCount());
    }

    /**
     * Test of getPercGC method, of class DNA.
     */
    @Test
    public void testGetPercGC() {
        //System.out.println("getPercGC");
        DNA instance = new DNA("ACAACCCGTTC-NG");
        double expResult = 0.5;
        double result = instance.getPercGC();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of length method, of class DNA.
     */
    @Test
    public void testLength() {
        //System.out.println("length");
        DNA instance = new DNA("ACAACCCGTTC-NG");
        int expResult = 14;
        int result = instance.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAmbiguous method, of class DNA.
     */
    @Test
    public void testIsAmbiguous() {
        //System.out.println("isAmbiguous");
        DNA instance = new DNA("AAAACCCGTT--N");
        DNA bad = new DNA("GCHGIJL");
        assertEquals(false, instance.isAmbiguous());
        assertEquals(true, bad.isAmbiguous());
    }

    /**
     * Test of reverseComplement method, of class DNA.
     */
    @Test
    public void testReverseComplement() {
        //System.out.println("reverseComplement");
        DNA instance = new DNA("AAAACCCGTT--N");
        DNA lower = new DNA("aaaacccgtt--nI");
        String expResult = "N--AACGGGTTTT";
        String lowerResult = "In--AACGGGTTTT";
        String result = instance.reverseComplement();
        assertEquals(expResult, result);
        assertEquals(lowerResult, lower.reverseComplement());
    }
    
    /**
     * Test of length method, of class DNA.
     */
    @Test
    public void testTranscribe() {
        //System.out.println("length");
        DNA instance = new DNA("ACAttCCGTTC-NG");
        String expResult = "ACAuuCCGUUC-NG";
        String result = instance.transcribe();
        assertEquals(expResult, result);
    }
    
    /** 
     * Test of Sequence.containsMotif()
     */
    @Test
    public void testContainsMotif(){
        DNA instance = new DNA("AAAACCCGTT--N");
        ArrayList indices = instance.containsMotif("AAA");
        ArrayList answer = new ArrayList();
        answer.add(0);
        answer.add(1);
        assertEquals(indices, answer);
    }
}
