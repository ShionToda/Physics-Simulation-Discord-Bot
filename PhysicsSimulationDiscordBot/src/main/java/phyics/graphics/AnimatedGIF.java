package phyics.graphics;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class AnimatedGIF {

    private int duration;
    private int totalFrame;
    private int width, height;
    public File gif = new File("res\\output.gif");



    public AnimatedGIF (int totalFrame, int duration, int width, int height) {
        this.width = width;
        this.height = height;

        this.duration = duration;
        this.totalFrame = totalFrame;
    }

    protected abstract void draw(int currentFrame, Graphics2D graphics);

    public void convert() throws IOException {

        gif.createNewFile();
        ImageOutputStream output = new FileImageOutputStream(gif);

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        File[] files = new File[totalFrame];

        // Saves every frame as a png
        for (int i = 0; i < totalFrame; i++) {
            draw(i, g2d);
            g2d.dispose();
            files[i] = new File("res\\image-" + i + ".jpg");
            files[i].createNewFile();
            ImageIO.write(bufferedImage, "jpg", files[i]);
        }

        BufferedImage initialImage = ImageIO.read(files[0]);
        GifSequenceWriter writer = new GifSequenceWriter(output, initialImage.getType(), totalFrame/duration, true);

        for (File file : files) {
            BufferedImage image = ImageIO.read(file);
            writer.writeToSequence(image);
            file.delete();
        }

        writer.close();
        output.close();
    }


}
