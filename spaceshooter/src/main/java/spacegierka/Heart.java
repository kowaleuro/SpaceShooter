package spacegierka;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Heart extends Component {

    Heart(int hP, double speed, Pane layer, int x , int y) {
        super(hP, speed, layer);
        loadImage(x,y);
    }

    public void loadImage(int x, int y) {
        ImageView ii;
        ii = new ImageView("file:src\\main\\resources\\image\\heart.png");
        this.image = ii.getImage();
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.imageView = ii;
        this.imageView.relocate(x, y);
        this.addToLayer();
    }

}
