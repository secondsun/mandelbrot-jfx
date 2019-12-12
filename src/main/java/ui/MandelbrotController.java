package ui;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import render.Renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class MandelbrotController {
    @FXML
    TextField minReal;
    @FXML  TextField maxReal;
    @FXML  TextField minImaginary;
    @FXML  TextField maxImaginary;

    @FXML
    ImageView imageView;

    @FXML
    private void initialize()
    {
        minReal.setText("-2");
        maxReal.setText("2");
        minImaginary.setText("-2");
        maxImaginary.setText("2");
    }

    public void render(ActionEvent actionEvent) {
        double minReal = Double.parseDouble(this.minReal.getText());
        double maxReal = Double.parseDouble(this.maxReal.getText());
        double minImaginary = Double.parseDouble(this.minImaginary.getText());
        double maxImaginary = Double.parseDouble(this.maxImaginary.getText());
        double width = maxReal - minReal;
        double height = maxImaginary - minImaginary;
        Renderer renderer = new Renderer(minReal, minImaginary, width, height, 1024,1024);
        BufferedImage image = null;
        try {
            image = renderer.render();
            Image jfxImage = SwingFXUtils.toFXImage(image, null);
            imageView.setImage(jfxImage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleZoom(MouseEvent mouseEvent) {
        double minReal = Double.parseDouble(this.minReal.getText());
        double maxReal = Double.parseDouble(this.maxReal.getText());
        double minImaginary = Double.parseDouble(this.minImaginary.getText());
        double maxImaginary = Double.parseDouble(this.maxImaginary.getText());
        double width = maxReal - minReal;
        double height = maxImaginary - minImaginary;
        Renderer renderer = new Renderer(minReal, minImaginary, width, height, 1024,1024);
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            //zoom in
            var posX = mouseEvent.getX();
            var posY = mouseEvent.getY();

            var clickComplex = renderer.toComplex(posX, posY);
            this.minReal.setText((clickComplex.real - width/4) +"");
            this.maxReal.setText((clickComplex.real + width/4) +"");
            this.minImaginary.setText((clickComplex.imaginary + height/4) +"");
            this.maxImaginary.setText((clickComplex.imaginary - height/4) +"");
            this.render(null);
        } else {
            //zoom out
            var posX = mouseEvent.getX();
            var posY = mouseEvent.getY();

            var clickComplex = renderer.toComplex(posX, posY);
            this.minReal.setText((clickComplex.real - width*1.1) +"");
            this.maxReal.setText((clickComplex.real + width*1.1) +"");
            this.minImaginary.setText((clickComplex.imaginary + height*1.1) +"");
            this.maxImaginary.setText((clickComplex.imaginary - height*1.1) +"");
            this.render(null);
        }
    }
}
