package com.almasb.civ6menu;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SettingItem extends Pane{

	protected Label field;
	protected TextField value;
	
	private Effect shadow = new DropShadow(5, Color.BLACK);
	private Effect blur = new BoxBlur(1, 1, 5);

	public SettingItem(String name) {

		field = new Label(name);
		
		field.setFont(Font.loadFont(Civ6MenuApp.class.getResource("res/discoduck3d.ttf").toExternalForm(), 24));
		field.setTextFill(Color.rgb(255,200,100));;
		
		value = new TextField();
		HBox hb = new HBox();
		hb.getChildren().addAll(field, value);
		hb.setSpacing(10);
	    	
		getChildren().addAll(hb);
	}
}
