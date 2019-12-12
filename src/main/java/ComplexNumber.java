import java.math.BigDecimal;
import java.util.Objects;

public final class ComplexNumber {
    public final double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        if (imaginary == -0d) {
            imaginary = 0d;
        }
        this.imaginary = imaginary;
    }

    public ComplexNumber square() {
        return new ComplexNumber(real*real - imaginary*imaginary, 2*real*imaginary);
    }

    public ComplexNumber add(ComplexNumber augend) {
        return new ComplexNumber(real + augend.real, imaginary+augend.imaginary);
    }

    public double abs() {
        return Math.sqrt(real*real + imaginary * imaginary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(that.real, real) == 0 &&
                Double.compare(that.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}';
    }
}
