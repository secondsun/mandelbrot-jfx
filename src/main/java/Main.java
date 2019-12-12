import render.Renderer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String args[]) throws IOException, InterruptedException {
        Renderer renderer = new Renderer(-1, 0, .5, .5, 1024, 1024);
        System.out.print("Beginning");
        var start = System.nanoTime();
        var image = renderer.render();
        var end = System.nanoTime();

        long elapsedTime = end - start;
        double seconds = (double) elapsedTime / 1_000_000_000.0;

        System.out.println(String.format(" Ended in %.4f seconds", seconds));
        File outputfile = new File("saved.png");
        ImageIO.write(image, "png", outputfile);
    }
}
