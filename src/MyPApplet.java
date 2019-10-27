import processing.core.PApplet;
import processing.core.PImage;

public class MyPApplet extends PApplet {
    private String URL ="https://anitab.org/wp-content/uploads/2015/08/Christine-Alvarado-700x467.jpg";
    private PImage backgroundImage;
    @Override
    public void setup() {
        size(500, 300);
        backgroundImage = loadImage(URL, "jpg");
    }
    @Override
    public void draw() {
        //backgroundImage.resize(0,height);
        image(backgroundImage,0,0);
    }
}
