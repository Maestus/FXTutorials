package com.almasb.civ6menu;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import javafx.util.Duration;
import java.util.ArrayList;

public class SettingMenu {
	protected Setting configuration = new Setting();
	Line line;
    VBox menuBox;
    Civ6MenuApp menu;
    protected ArrayList<String> settingData = new ArrayList<>();

	public SettingMenu(Civ6MenuApp _menu){
		menu = _menu;
		menuBox = menu.menuBox;
		line = menu.line;
		settingData.add("Haut");
		settingData.add("Bas");
		settingData.add("Gauche");
		settingData.add("Droite");
		settingData.add("Retour");
	}
	
	protected void init() {
		
	    ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
	    barre.setToY(0);
		
		ParallelTransition deploy = new ParallelTransition();
		TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
        for (int i = menuBox.getChildren().size() - 1; i >= 0; i--) {
            Node n = menuBox.getChildren().get(i);
            menuitems[i] = new TranslateTransition(Duration.millis(500), n);
            menuitems[i].setToY(200);
            menuitems[i].setOnFinished(e2 -> menuBox.getChildren().remove(n));
        }
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            deploy.getChildren().add(menuitems[i]);
        }
        deploy.getChildren().add(barre);
        deploy.play();
        deploy.setOnFinished(e -> addMenu(line.getStartX() + 5, line.getStartY() + 5));
	}
	        
	
	public void home_menu(){
		
	    ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
	    barre.setToY(0);
		
		ParallelTransition deploy = new ParallelTransition();
		TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
        for (int i = menuBox.getChildren().size() - 1; i >= 0; i--) {
            Node n = menuBox.getChildren().get(i);
            menuitems[i] = new TranslateTransition(Duration.millis(500), n);
            menuitems[i].setToY(200);
            menuitems[i].setOnFinished(e2 -> menuBox.getChildren().remove(n));
        }
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            deploy.getChildren().add(menuitems[i]);
        }
        deploy.getChildren().add(barre);
        deploy.play();
        deploy.setOnFinished(e -> menu.addMenu(line.getStartX() + 5, line.getStartY() + 5));
	}
	
	 protected void addMenu(double x, double y) {
		 menuBox.setTranslateX(x);
		 menuBox.setTranslateY(y);
		 settingData.forEach(data -> {
			 if(!data.equals("Retour")){
				 SettingItem item = new SettingItem(data);
				 item.setTranslateX(200);		            
				 VBox.setMargin(item, new Insets(10,1,10,1));
		         
				 menuBox.getChildren().add(item);
			 } else {
				 Civ6MenuItem item = new Civ6MenuItem(data);
				 item.setTranslateX(200);        
				 VBox.setMargin(item, new Insets(10,1,10,1));
		         item.setOnMouseClicked(e -> home_menu());
				 menuBox.getChildren().add(item);
			 }	 
	     });

		 startAnimation();
	 }
	 
	 protected void startAnimation() {
		 
		line.setEndY(line.getEndY() + 100);
		ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
		barre.setToY(1);
	     
		ParallelTransition deploy = new ParallelTransition();
		TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
		 
		for (int i = 0; i < menuBox.getChildren().size(); i++) {
			Node n = menuBox.getChildren().get(i);
			menuitems[i] = new TranslateTransition(Duration.seconds(1), n);
			menuitems[i].setToX(0);
	    }	
	        
		for (int i = 0; i < menuBox.getChildren().size(); i++) {
			deploy.getChildren().add(menuitems[i]);
	    }
		 
		deploy.getChildren().add(barre);
		 
		deploy.play();
	 }

	
}
