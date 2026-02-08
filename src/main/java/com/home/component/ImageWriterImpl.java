package com.home.component;

import com.home.model.ImageData;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


@Component
public class ImageWriterImpl implements ImageWriter {

    @Override
    public ImageData write(BufferedImage image) {
        try {
            byte[] pngBytes = convertToByteArray(image, "PNG");
            // ImageIO.write(image, "PNG", new File(filename));
            return ImageData.builder()
                    .format("PNG")
                    .width(image.getWidth())
                    .height(image.getHeight())
                    .data(pngBytes)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] convertToByteArray(BufferedImage image, String format) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, format, outputStream);
        return outputStream.toByteArray();
    }
}
