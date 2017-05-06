package com.almasb.civ6menu;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.util.ArrayList;

public class Civ6MenuApp extends Application {

    protected static final int WIDTH = 1280;
    protected static final int HEIGHT = 720;
    double lineX = WIDTH / 2 - 100;
    double lineY = HEIGHT / 3 + 50;
    boolean menuBox_already_add_in_pane = false;

    
    protected Pane root = new Pane();
    protected Setting key_choose = new Setting();
    protected VBox menuBox = new VBox();
    protected Line line;
    protected SettingMenu setting;
    
    protected ArrayList<String> menuData = new ArrayList<>();
 
    
    protected void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/bg.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

	protected void addTitle() {
        Civ6Title title = new Civ6Title("FxBattle");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    protected void addLine(double x, double y) {
        line = new Line(x, y, x, y + 250);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITE);
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    protected void startAnimation() {
		line.setEndY(line.getEndY() - 100);
    	ParallelTransition deploy = new ParallelTransition();
        TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
        ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
        barre.setToY(1);
        
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            Node n = menuBox.getChildren().get(i);
            menuitems[i] = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
            menuitems[i].setToX(0);
        }
        
        deploy.getChildren().add(barre);
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            deploy.getChildren().add(menuitems[i]);
        }
        deploy.play();
    }

    protected void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);

        menuData.forEach(data -> {
            Civ6MenuItem item = new Civ6MenuItem(data);
            if(data.equals("Game Options")){
                item.setOnMouseClicked(e -> setting.init());
            } else if(data.equals("Exit")){
                item.setOnMouseClicked(e -> Platform.exit());
            }

            item.setTranslateX(200);
            
            VBox.setMargin(item, new Insets(10,1,10,1));
            
            menuBox.getChildren().addAll(item);
        });
		if(!menuBox_already_add_in_pane){
        	root.getChildren().add(menuBox);
        	menuBox_already_add_in_pane = true;
		}
        startAnimation();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        menuData.add("Play");
        menuData.add("Game Options");
        menuData.add("Exit");
        
    	addBackground();
        addTitle();

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        setting = new SettingMenu(this);

        
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("FxBattle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
