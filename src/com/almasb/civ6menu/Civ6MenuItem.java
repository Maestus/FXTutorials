package com.almasb.civ6menu;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Civ6MenuItem extends Pane {
    protected Text text;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 5);

    public Civ6MenuItem(String name) {

        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont(Civ6MenuApp.class.getResource("res/discoduck3d.ttf").toExternalForm(), 24));
        text.setFill(Color.rgb(255,200,100));

        text.effectProperty().bind(Bindings.when(hoverProperty()).then(shadow).otherwise(blur));

        getChildren().addAll(text);
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
        System.out.println("lmzl");
    }
}