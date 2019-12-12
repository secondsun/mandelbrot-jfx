import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComplextNumberTest {
    @Test
    public void testComplexSquare() {
        var complex = new ComplexNumber(1,1);
        assertEquals(new ComplexNumber(0,2), complex.square());
    }

    @Test
    public void testABS() {
        var complex = new ComplexNumber(3,4);
        assertEquals(5, complex.abs());
    }

    @Test
    public void testAdd() {
        var addend = new ComplexNumber(3,2);
        var augend = new ComplexNumber(1,7);
        assertEquals(new ComplexNumber(4,9), addend.add(augend));

    }

    @Test
    public void mapCoordinates() {
        Renderer renderer = new Renderer(-2,-2,4,4,256,256);
        assertEquals(new ComplexNumber(-2,2), renderer.toComplex(0,0));
        assertEquals(new ComplexNumber(2,-2), renderer.toComplex(256,256));
        assertEquals(new ComplexNumber(0,0), renderer.toComplex(128,128));
        assertEquals(new ComplexNumber(0,-2), renderer.toComplex(128,256));

        renderer = new Renderer(0,0,2,2,256,256);
        assertEquals(new ComplexNumber(0,0), renderer.toComplex(0,0));
        assertEquals(new ComplexNumber(2,-2), renderer.toComplex(256,256));
        assertEquals(new ComplexNumber(1,-1), renderer.toComplex(128,128));

        assertEquals(new ComplexNumber(1,-2), renderer.toComplex(128,256));
    }

    @Test
    public void testEscape() {
        assertTrue(new Escape(new ComplexNumber(-2,2),2,100).escapes());
        assertEquals(0, new Escape(new ComplexNumber(-2,2),2,100).escapedAt());
        assertFalse(new Escape(new ComplexNumber(0,0),2,100).escapes());
        assertEquals(101, new Escape(new ComplexNumber(0,0),2,100).escapedAt());

    }

}
