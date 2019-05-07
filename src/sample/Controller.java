package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane drawPane;
    @FXML
    private Circle gold;
    @FXML
    private Circle blue;
    @FXML
    private Circle sun;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;

    @FXML
    private RadioButton trackGame;
    @FXML
    private RadioButton atomStructure;
    @FXML
    private RadioButton socialNetwork;



    //这是开始界面的装饰如果不想要可以注释
    @FXML
    private void circleRun(){
        Rotate rotate1 = new Rotate();
        Rotate rotate2 = new Rotate();

        sun.centerXProperty().bind(drawPane.widthProperty().divide(2));
        sun.centerYProperty().bind(drawPane.heightProperty().divide(2));

        circle1.centerXProperty().bind(sun.centerXProperty());
        circle1.centerYProperty().bind(sun.centerYProperty());
        circle2.centerXProperty().bind(sun.centerXProperty());
        circle2.centerYProperty().bind(sun.centerYProperty());

        gold.centerXProperty().bind(sun.centerXProperty().add(circle1.getRadius()));
        gold.centerYProperty().bind(sun.centerYProperty());
        blue.centerXProperty().bind(sun.centerXProperty().add(circle2.getRadius()));
        blue.centerYProperty().bind(sun.centerYProperty());

        rotate1.pivotXProperty().bind(sun.centerXProperty());
        rotate1.pivotYProperty().bind(sun.centerYProperty());
        rotate2.pivotXProperty().bind(sun.centerXProperty());
        rotate2.pivotYProperty().bind(sun.centerYProperty());

        gold.getTransforms().add(rotate1);
        blue.getTransforms().add(rotate2);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        final KeyValue goldAngle = new KeyValue(rotate1.angleProperty(), 720);
        final KeyValue blueAngle = new KeyValue(rotate2.angleProperty(), 360);
        final KeyFrame goldFrame = new KeyFrame(Duration.millis(5000),goldAngle);
        final KeyFrame blueFrame = new KeyFrame(Duration.millis(5000),blueAngle);
        timeline.getKeyFrames().add(goldFrame);
        timeline.getKeyFrames().add(blueFrame);
        timeline.play();
    }

    //点击开始后执行的内容
    @FXML
    private void start(){
        if (trackGame.isSelected()){

        }
        if (atomStructure.isSelected()){

        }
        if (socialNetwork.isSelected()){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        circleRun();
        ToggleGroup toggleGroup = new ToggleGroup();
        trackGame.setToggleGroup(toggleGroup);
        atomStructure.setToggleGroup(toggleGroup);
        socialNetwork.setToggleGroup(toggleGroup);
    }
}
