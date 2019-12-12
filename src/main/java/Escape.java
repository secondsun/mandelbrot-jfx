import java.util.concurrent.Callable;

public class Escape {

    public final ComplexNumber coordinate;
    public final double threshold;
    public final int iterations;
    public final ComplexNumber[] calculated;
    public Escape(ComplexNumber coordinate, double threshold, int iterations) {
        this.coordinate = coordinate;
        this.threshold = threshold;
        this.iterations = iterations;
        calculated = new ComplexNumber[iterations];
    }

    public Boolean escapes() {
        for (int iteration =0; iteration < iterations; iteration++) {
            if (calculate(iteration).abs() > threshold) {
                return true;
            }
        }
        return false;
    }

    public int escapedAt() {
        if (!escapes()) {
            return iterations + 1;//Diesn;t escaoe
        } else {
            for (int index = 0; index < calculated.length; index++) {
                if (calculated[index] == null) {
                    return index -1;//escaped before this
                }
            }

        }
        return iterations;//escaped on last iteration?s
    }

    private ComplexNumber calculate(int iteration) {
        if (iteration == 0) {
            if(calculated[0] == null) {
                calculated[0] = coordinate;
            }
            return calculated[0];
        } else {
            if (calculated[iteration] == null) {
                calculated[iteration] = calculate(iteration -1).square().add(coordinate);
            }
            return calculated[iteration];
        }
    }

}
