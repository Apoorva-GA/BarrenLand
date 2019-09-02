import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class BarrenLandTest {

    BarrenLand test;

    @Test
    public void testZero() {
        test = new BarrenLand();
        String result = ("240000");
        String input = new String("{“”}");

        test.readInput(input);
        test.clearColoMatrix();
        test.colorBarrenRectangles();
        test.getFertileLands();
        test.printOutput();

        assertEquals(result,test.printOutput());
    }

    @Test
    public void testString1() {
        test = new BarrenLand();
        String result = ("116800 116800");
        String input = new String("{“0 292 399 307”}");

        test.readInput(input);
        test.clearColoMatrix();
        test.colorBarrenRectangles();
        test.getFertileLands();
        test.printOutput();

        assertEquals(result,test.printOutput());
    }

    @Test
    public void testString2() {
        test = new BarrenLand();
        String result = ("22816 192608");
        String input = new String("{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}");

        test.readInput(input);
        test.clearColoMatrix();
        test.colorBarrenRectangles();
        test.getFertileLands();
        test.printOutput();

        assertEquals(result,test.printOutput());
    }

    @Test
    public void testSTDIN() throws IOException {
        test = new BarrenLand();
        test.readFromSTDIN();
        test.clearColoMatrix();
        test.colorBarrenRectangles();
        test.getFertileLands();
        System.out.println(test.printOutput());
    }

}
