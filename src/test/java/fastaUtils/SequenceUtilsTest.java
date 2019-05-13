package fastaUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class SequenceUtilsTest {
    
    public SequenceUtilsTest() {
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
     * Test of randomDNA method, of class SequenceUtils.
     */
    @Test
    public void testRandomDNA_int_double() {
        System.out.println("randomDNA");
        int length = 100;
        double percent_gc = 0.5;
        int expResult = 100;
        DNA result = SequenceUtils.randomDNA(length, percent_gc);
        assertEquals(expResult, result.length());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of randomDNA method, of class SequenceUtils.
     */
    @Test
    public void testRandomDNA_int() {
        System.out.println("randomDNA");
        int length = 100;
        int expResult = 100;
        DNA result = SequenceUtils.randomDNA(length);
        assertEquals(expResult, result.length());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of randomRNA method, of class SequenceUtils.
     */
    @Test
    public void testRandomRNA_int_double() {
        System.out.println("randomRNA");
        int length = 100;
        double percent_gc = 0.5;
        int expResult = 100;
        RNA result = SequenceUtils.randomRNA(length, percent_gc);
        assertEquals(expResult, result.length());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of randomRNA method, of class SequenceUtils.
     */
    @Test
    public void testRandomRNA_int() {
        System.out.println("randomRNA");
        int length = 100;
        int expResult = 100;
        RNA result = SequenceUtils.randomRNA(length);
        assertEquals(expResult, result.length());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hammingDistance method, of class SequenceUtils.
     */
    @Test
    public void testHammingDistance() {
        System.out.println("hammingDistance");
        String a = "ACTAGTAA";
        String b = "ACTGGTCA";
        int expResult = 2;
        int result = SequenceUtils.hammingDistance(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

//    /**
//     * Test of consensus method, of clas
//     * @throws java.io.IOException
//     */
//    @Test
//    public void testConsensus() throws IOException {
//        System.out.println("consensus");
//        Collection<Sequence> seqs = null;
//        Object[] expResult = null;
//        Object[] result = SequenceUtils.consensus(seqs);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}
