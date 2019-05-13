package fastaUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
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
public class FastaTest {
    
    public FastaTest() {
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
     * Test of toString method, of class Fasta.
     * @throws java.io.IOException
     */
    @Test
    public void testToString() throws IOException {
        System.out.println("toString");
        Fasta instance = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        String expResult = "src/test/resources/com/github/"+
                "jlboat/fastautils/DNA.fasta contains 50 entries";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
    @Test(expected = NullPointerException.class)
    public void testBadFasta() throws IOException {
        Fasta fasta = new Fasta("DNE.fa","DNA");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBadSeqType() throws IOException{
        Fasta instance = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "mRNA");
    }

//    /**
//     * Test of toFile method, of class Fasta.
//     * @throws java.io.IOException
//     */
    @Test
    public void testToFile() throws IOException {
        System.out.println("toFile");
        String outfile = "src/test/resources/com/github/" + 
                "jlboat/fastautils/test.fasta";
        Fasta instance = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        instance.toFile(outfile, 60);
        Fasta written = new Fasta(outfile, "DNA");
        assertTrue(instance.equals(written));
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of getKeys method, of class Fasta.
//     * @throws java.io.IOException
//     */
//    @Test
//    public void testGetKeys() throws IOException {
//        System.out.println("getKeys");
//        Fasta instance = new Fasta("src/test/resources/com/github/" + 
//                "jlboat/fastautils/DNA.fasta", "DNA");
//        Set<String> expResult = null;
//        Set<String> result = instance.getKeys();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getValues method, of class Fasta.
//     * @throws java.io.IOException
//     */
//    @Test
//    public void testGetValues() throws IOException {
//        System.out.println("getValues");
//        Fasta instance = new Fasta("src/test/resources/com/github/" + 
//                "jlboat/fastautils/DNA.fasta", "DNA");
//        Collection<Sequence> expResult = null;
//        Collection<Sequence> result = instance.getValues();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of containsAmbiguous method, of class Fasta.
     * @throws java.io.IOException
     */
    @Test
    public void testContainsAmbiguous() throws IOException {
        System.out.println("containsAmbiguous");
        Fasta instance = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        boolean expResult = false;
        boolean result = instance.containsAmbiguous();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
