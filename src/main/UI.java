package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Font arial40, arial80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat df = new DecimalFormat("#.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {

        if (gameFinished) {
            g2d.setFont(arial40);
            g2d.setColor(Color.WHITE);

            String text;
            int textLength, x, y;

            text = "You found the treasure!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 - (gamePanel.tileSize * 3);
            g2d.drawString(text, x, y);

            text = "Your Time is: " + df.format(playTime) + "!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 4);
            g2d.drawString(text, x, y);

            g2d.setFont(arial80B);
            g2d.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 2);
            g2d.drawString(text, x, y);

            gamePanel.gameThread = null;
        } else {
            g2d.setFont(arial40);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
            g2d.drawString("x " + gamePanel.player.hasKey, 74, 65);

            playTime += (double) 1/60;
            g2d.drawString("Time: " + df.format(playTime), gamePanel.tileSize*11, 65);

            if (messageOn) {
                g2d.setFont(g2d.getFont().deriveFont(30f));
                g2d.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
