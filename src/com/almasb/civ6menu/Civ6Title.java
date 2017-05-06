package com.almasb.civ6menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Civ6Title extends Pane {
    private Text text;

    public Civ6Title(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }

        text = new Text(spread);
        text.setFont(Font.loadFont(Civ6MenuApp.class.getResource("res/discoduckchromeital.ttf").toExternalForm(), 74));
        text.setFill(Color.rgb(255, 180, 67));
        //text.setEffect(new DropShadow(15, Color.RED));

        getChildren().addAll(text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}
