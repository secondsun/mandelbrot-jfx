import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final ExecutorService EX = Executors.newFixedThreadPool(4);
    private int threshold = 2;
    private int iterations = 100;

    private final int width;
    private final int height;

    private final double stepX;

    private final double stepY;

    private final Rectangle plane;
    private int[] rgbColorEscapeMap;

    public Main(int planeX, int planeY, int planeWidth, int planHeight, int viewportWidth, int viewportHeight) {
        this.width = viewportWidth;
        this.height = viewportHeight;
        this.stepX = 1d / (double) width;
        this.stepY = 1d / (double) height;
        this.plane = new Rectangle(planeX, planeY, planeWidth, planHeight);
    }

    public static void main(String... args) throws Exception {
        Main main = new Main(-1, -1, 1, 1, 1024, 1024);
        main.buildColorMap();
        System.out.print("Beginning");
        var start = System.nanoTime();
        main.render();
        var end = System.nanoTime();

        long elapsedTime = end - start;
        double seconds = (double)elapsedTime / 1_000_000_000.0;

        System.out.println(String.format(" Ended in %.4f seconds", seconds));
    }

    private void buildColorMap() {
        double coloStep = 1 / iterations;
        rgbColorEscapeMap = new int[iterations + 1];
        rgbColorEscapeMap[iterations] = Integer.MAX_VALUE;//white
        for (int i = 0; i < iterations; i++) {
            rgbColorEscapeMap[i] = 0;
            int firstThirdEnds = Math.floorDiv(iterations, 3);
            int secondThirdBegins = firstThirdEnds + 1;
            int thirdThirdBegins = 2 * firstThirdEnds + 1;

            int r = 0;
            int g = 0;
            int b = 0;

            if (i < secondThirdBegins) {
                b = (int) Math.round(255 - 255 * ((double) i / (double) firstThirdEnds));
                b = Math.max(0, b);
                b = Math.min(254, b);
            } else if (i < thirdThirdBegins) {
                g = (int) Math.round(255 - 255 * ((double) (i - firstThirdEnds) / (double) firstThirdEnds));
                g = Math.max(0, g);
                g = Math.min(254,g);
            } else {
                r = (int) Math.round(255 - 255 * ((double) (i - thirdThirdBegins) / (double) firstThirdEnds));
                r = Math.max(0, r);
                r = Math.min(254, r);
            }

            rgbColorEscapeMap[i] = new Color(Math.min(254, r), Math.min(254, g), Math.min(254, b)).getRGB();
            System.out.println(String.format("{r:%d,g:%d,b:%d}", Math.min(255, r), Math.min(255, g), Math.min(255, b)));
        }
    }

    public void render() throws InterruptedException, IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final var x1 = x;
                final var y1 = y;
                ComplexNumber point = toComplex(x, y);
                //EX.submit(() -> {
                    try {
                        var escape = new Escape(point, threshold, iterations);
                        if (escape.escapes()) {
                            image.setRGB(x1, y1, rgbColorEscapeMap[escape.escapedAt()]);
                        } else {
                            image.setRGB(x1, y1, Integer.MAX_VALUE);
                        }
                    } catch (Exception ignore) {
                        System.err.println(ignore);
                    }
                //});
            }
        }
        EX.shutdown();
        if (!EX.awaitTermination(1, TimeUnit.DAYS)) {
            EX.shutdownNow();
        }
        File outputfile = new File("saved.png");
        ImageIO.write(image, "png", outputfile);
    }

    public ComplexNumber toComplex(double x, double y) {
        return new ComplexNumber((stepX * x) * plane.width + plane.x, -1 * ((stepY * y) * plane.height + plane.y));
    }

}
