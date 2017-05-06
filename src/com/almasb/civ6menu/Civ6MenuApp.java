package com.almasb.civ6menu;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Civ6MenuApp extends Application {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Single Player", () -> {}),
            new Pair<String, Runnable>("Game Options", launch_setting()),
            new Pair<String, Runnable>("Credits", () -> {}),
            new Pair<String, Runnable>("Exit", Platform::exit)
    );

    private Pane root = new Pane();
    private VBox menuBox = new VBox();
    private Line line;


    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/bg.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    private Runnable launch_setting() {

    	
    	return null;
	}

	private void addTitle() {
        Civ6Title title = new Civ6Title("FxBattle");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 200);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITE);
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation() {
    	ParallelTransition deploy = new ParallelTransition();
        TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
        ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
        barre.setToY(1);
        
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            Node n = menuBox.getChildren().get(i);
            menuitems[i] = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
            menuitems[i].setToX(0);
            menuitems[i].setOnFinished(e2 -> n.setClip(null));
        }
        
        deploy.getChildren().add(barre);
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            deploy.getChildren().add(menuitems[i]);
        }
        deploy.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            Civ6MenuItem item = new Civ6MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(200);
            
            VBox.setMargin(item, new Insets(10,1,10,1));
            
            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();
        Scene scene = new Scene(root);
        primaryStage.setTitle("FxBattle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
