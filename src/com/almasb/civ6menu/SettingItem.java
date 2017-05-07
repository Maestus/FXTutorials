package com.almasb.civ6menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SettingItem extends Pane{

	protected Label field;
	protected TextField value;
	

	public SettingItem(String name) {

		field = new Label(name);
		
		field.setFont(Font.loadFont(Civ6MenuApp.class.getResource("res/discoduck3d.ttf").toExternalForm(), 24));
		field.setTextFill(Color.rgb(255,200,100));;
		
		value = new TextField();
		value.setAlignment(Pos.CENTER);
		
		HBox hb = new HBox();
		hb.getChildren().addAll(field, value);
		hb.setSpacing(10);
	    	
        HBox.setMargin(this, new Insets(10,1,10,1));

		getChildren().addAll(hb);
	}
}
