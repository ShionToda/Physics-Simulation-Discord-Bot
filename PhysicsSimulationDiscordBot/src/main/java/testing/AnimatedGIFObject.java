package testing;

import phyics.graphics.AnimatedGIF;

import java.awt.*;

public class AnimatedGIFObject extends AnimatedGIF {


    public AnimatedGIFObject() {
        super(30, 2000, 1000, 1000);
    }

    @Override
    public void draw(int currentFrame, Graphics2D graphics) {
        graphics.fillRect((int)(currentFrame*5), (int)(currentFrame*5), 50, 50);
    }
}
