import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Renderer {

    public static final int THREADS = 8;
    public static final ExecutorService EX = Executors.newFixedThreadPool(THREADS);
    private int threshold = 2;
    private int iterations = 100;

    private final int width;
    private final int height;

    private final double stepX;

    private final double stepY;

    private final ComplexPlane plane;




    public Renderer(double planeX, double planeY, double planeWidth, double planHeight, int viewportWidth, int viewportHeight) {
        this.width = viewportWidth;
        this.height = viewportHeight;
        this.stepX = 1d / (double) width;
        this.stepY = 1d / (double) height;
        this.plane = new ComplexPlane(planeX, planeY, planeWidth, planHeight);
    }

    public BufferedImage render() throws InterruptedException, IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int band = width/THREADS;
        IntStream.range(0, THREADS).forEach(thread -> {

            EX.submit(() -> {
                for (int x = thread*band; x < (1+thread)*(band); x++) {
                    for (int y = 0; y < height; y++) {
                        ComplexNumber point = toComplex(x, y);

                        try {
                            var escape = new Escape(point, threshold, iterations);
                            if (escape.escapes()) {
                                image.setRGB(x, y, ColorEscapeMap.rgbColorEscapeMap[escape.escapedAt()]);
                            } else {
                                image.setRGB(x, y, 0);
                            }
                        } catch (Exception ignore) {
                            System.err.println(ignore);
                        }
                    }
                }
            });
        });
        EX.shutdown();
        if (!EX.awaitTermination(1, TimeUnit.DAYS)) {
            EX.shutdownNow();
        }
        return image;
    }

    public ComplexNumber toComplex(double x, double y) {
        return new ComplexNumber((stepX * x) * plane.width + plane.x, -1 * ((stepY * y) * plane.height + plane.y));
    }

}
