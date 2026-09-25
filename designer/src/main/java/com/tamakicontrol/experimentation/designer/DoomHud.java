package com.tamakicontrol.experimentation.designer;

import org.slf4j.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class DoomHud extends JComponent implements AWTEventListener {
    int mx;
    int my;

    // Portrait bounds
    int facing = 0; // -1 = Left, 0 = forward, 1 = right
    int s_padding_w = 10; // Amount of pixels between each frame
    int offset_x = 250;
    int sx = 95;
    int sy = 15;
    int sw = 71;
    int sh = 92;
    int dx = 887;
    int dy = 11;
    int dw = 148;
    int dh = 162;

    BufferedImage image_base;
    BufferedImage image_portrait;

    Logger logger;

    DoomHud(Logger logger) {
        this.logger = logger;
        try {
            image_base = ImageIO.read(new File("C:/Users/IvanArmstrong/Pictures/doom_hud.png"));
            image_portrait = ImageIO.read(new File("C:/Users/IvanArmstrong/Pictures/doom_portrait.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw base hud
        g.drawImage(image_base, offset_x, 0, null);

        // Draw portrait
        int x = sx + facing * (sw + s_padding_w);
        g.drawImage(image_portrait,
                dx + offset_x, dy, dx + dw + offset_x, dy + dh,
                x, sy, x + sw, sy + sh,
                null);
    }

    @Override
    public int getWidth() {
        return 1920;
    }

    @Override
    public int getHeight() {
        return 173;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1920, 173);
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent me = (MouseEvent) event;
            Point pos = me.getLocationOnScreen();

            // Get monitor the mouse is on
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            for (GraphicsDevice device : ge.getScreenDevices()) {
                Rectangle bounds = device.getDefaultConfiguration().getBounds();
                if (bounds.contains(pos)) {
                    mx = pos.x - bounds.x;
                    my = pos.y - bounds.y;
                    repaint();

                    if (mx < dx - 250) {
                        facing = 1;
                    } else if (mx >= dx + dw + 250) {
                        facing = -1;
                    } else {
                        facing = 0;
                    }

                    break;
                }
            }
        }
    }
}