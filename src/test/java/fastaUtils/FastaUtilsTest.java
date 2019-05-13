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
public class FastaUtilsTest {
    
    public FastaUtilsTest() {
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
     * Test of getSeqCount method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testSeqCount() throws IOException {
        System.out.println("getSeqCount");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        int expResult = 50;
        int result = FastaUtils.seqCount(fasta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLengths method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testSeqLengths() throws IOException {
        System.out.println("getLengths");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        int[] expResult =
                new int[]{1143, 1287,  879,  522,  306, 3216,  948,  693, 1293,  429,  195,
        357, 7047,  981, 1272,  504,  651, 1275, 1587,  321,  255,  381,
       1116,  981, 1017,  653,  294,  201,  735,  918,  882,  411, 1368,
        921, 2262, 1446,  921, 1470,  273,  375,  231, 1128,  972,  621,
        795, 2682,  189, 5553, 1341,  339};
        int[] result = FastaUtils.seqLengths(fasta);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of nX method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testNX() throws IOException {
        System.out.println("nX");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        double x = 50.0;
        int expResult = 1341;
        int result = FastaUtils.nX(fasta, x);
        assertEquals(expResult, result);
        double y = 90.0;
        int expResult2 = 522;
        int result2 = FastaUtils.nX(fasta, y);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of minLength method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testMinLength() throws IOException {
        System.out.println("minLength");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        int expResult = 189;
        int result = FastaUtils.minLength(fasta);
        assertEquals(expResult, result);
    }

    /**
     * Test of maxLength method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testMaxLength() throws IOException {
        System.out.println("maxLength");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        int expResult = 7047;
        int result = FastaUtils.maxLength(fasta);
        assertEquals(expResult, result);
    }

    /**
     * Test of medianLength method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testMedianLength() throws IOException {
        System.out.println("medianLength");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        double expResult = 900;
        double result = FastaUtils.medianLength(fasta);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of meanLength method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testMeanLength() throws IOException {
        System.out.println("meanLength");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        double expResult = 1112.74;
        double result = FastaUtils.meanLength(fasta);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of baseCumulativeSum method, of class FastaUtils.
     * @throws java.io.IOException
     */
    @Test
    public void testBaseCumulativeSum() throws IOException {
        System.out.println("baseCumulativeSum");
        Fasta fasta = new Fasta("src/test/resources/com/github/" + 
                "jlboat/fastautils/DNA.fasta", "DNA");
        int[] expResult = new int[]{189,   384,   585,   816,  1071,  1344,  1638,  1944,  2265,
        2604,  2961,  3336,  3717,  4128,  4557,  5061,  5583,  6204,
        6855,  7508,  8201,  8936,  9731, 10610, 11492, 12410, 13331,
       14252, 15200, 16172, 17153, 18134, 19151, 20267, 21395, 22538,
       23810, 25085, 26372, 27665, 29006, 30374, 31820, 33290, 34877,
       37139, 39821, 43037, 48590, 55637};
        int[] result = FastaUtils.baseCumulativeSum(fasta);
        assertArrayEquals(expResult, result);
    }
    
}
