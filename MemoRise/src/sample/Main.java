package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;


public class Main extends Application {
    static int level = 10;
    public int array[][] = new int[level][level];
    public static boolean unclok[] = new boolean[10];
    public Scene sc;
    public int right = 0;

    @Override
    public void start(Stage window) throws Exception {
        unclok[0] = true;
        BorderPane pane = new BorderPane();
        Label l1 = new Label("                       ");
        Label l2 = new Label("                       ");
        Label l3 = new Label("                       ");
        Label l4 = new Label("                       ");
        pane.setLeft(l1);
        pane.setRight(l2);
        pane.setTop(l3);
        pane.setBottom(l4);
        VBox vBox = new VBox();
        pane.setCenter(vBox);
        vBox.setSpacing(10.0);
        pane.setStyle("-fx-background-color: #2F4F4F");
        int lv = 1;
        for (int i = 0; i < 2; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10.0);
            for (int j = 0; j < 5; j++) {
                Button temp = new Button(String.valueOf(lv));
                temp.setId(String.valueOf(lv));
                temp.setPrefSize(60.0, 50.0);
                temp.setOnAction(event -> startGame(temp, window));
                if(unclok[lv-1]==false){temp.setDisable(true);}
                hBox.getChildren().add(temp);
                lv++;
            }
            vBox.getChildren().add(hBox);
        }

        Scene mainsc = new Scene(pane);
        window.setScene(mainsc);
        window.show();
    }

    public void did(Button button, Stage w) {
        String value = button.getId();
        char temp[] = value.toCharArray();
        String temp2[] = new String[2];
        temp2[0] = String.valueOf(temp[0]);
        temp2[1] = String.valueOf(temp[1]);

        button.setDisable(true);
        if (array[Integer.parseInt(temp2[0])][Integer.parseInt(temp2[1])] == 1) {
            right++;
            button.setStyle("-fx-background-color: #025FFE");
            if (right == level) {
                endScene(w);
                if(right!=10) {
                    unclok[right] = true;
                }
            }
        } else {
            button.setStyle("-fx-background-color: #D41A1A");
            endScene(w);
        }
    }
    public void showingPositions(){
        for(int i=0;i<level;i++){
            for(int j= 0;j<level;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("----------------------------------- ");
    }


    public void StarterScene(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        Label l1 = new Label("                       ");
        Label l2 = new Label("                       ");
        Label l3 = new Label("                       ");
        Label l4 = new Label("                       ");
        pane.setLeft(l1);
        pane.setRight(l2);
        pane.setTop(l3);
        pane.setBottom(l4);
        VBox vBox = new VBox();
        pane.setCenter(vBox);
        vBox.setSpacing(10.0);
        pane.setStyle("-fx-background-color: #778899");
        for (int i = 0; i < level; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10.0);

            for (int j = 0; j < level; j++) {
                Button button = new Button();
                if (array[i][j] == 1) {
                    button.setStyle("-fx-background-color: #025FFE");
                } else {
                    button.setStyle("-fx-background-color: #D41A1A");
                }
                button.setPrefSize(60.0, 50.0);
                button.setDisable(true);
                hBox.getChildren().add(button);
            }
            vBox.getChildren().add(hBox);
        }


        Scene sc2 = new Scene(pane);
        primaryStage.setScene(sc2);
        primaryStage.setResizable(false);
        primaryStage.show();

        Thread t = new Thread(new H());
        try {
            Thread.sleep(5000);

        } catch (Exception e) {
        }
        primaryStage.setScene(sc);

    }

    public void endScene(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        Label l1 = new Label("             ");
        Label l2 = new Label("                       ");
        Label l3 = new Label("                       ");
        Label l4 = new Label("                       ");
        Button b = new Button("<--");
        b.setOnAction(event -> {
            try {
                goBack(primaryStage);
            }catch(Exception e){}
        });

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(b,l1);
        pane.setLeft(hBox1);
        pane.setRight(l2);
        pane.setTop(l3);
        pane.setBottom(l4);
        VBox vBox = new VBox();
        pane.setCenter(vBox);
        vBox.setSpacing(10.0);
        pane.setStyle("-fx-background-color: #778899");
        for (int i = 0; i < level; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10.0);

            for (int j = 0; j < level; j++) {
                Button button = new Button();
                if (array[i][j] == 1) {
                    button.setStyle("-fx-background-color: #025FFE");
                } else {
                    button.setStyle("-fx-background-color: #D41A1A");
                }
                button.setPrefSize(60.0, 50.0);
                button.setDisable(true);
                hBox.getChildren().add(button);
            }
            vBox.getChildren().add(hBox);
        }


        Scene sc2 = new Scene(pane);
        primaryStage.setScene(sc2);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public void game(Stage primaryStage) {
        Random ram = new Random();

        for (int i = 0; i < level; i++) {
            int value = ram.nextInt(level);
            array[i][value] = 1;
        }
        StarterScene(primaryStage);

        BorderPane pane = new BorderPane();
        Label l1 = new Label("                       ");
        Label l2 = new Label("                       ");
        Label l3 = new Label("                       ");
        Label l4 = new Label("                       ");
        pane.setLeft(l1);
        pane.setRight(l2);
        pane.setTop(l3);
        pane.setBottom(l4);
        VBox vBox = new VBox();
        pane.setCenter(vBox);
        vBox.setSpacing(10.0);
        pane.setStyle("-fx-background-color: #778899");

        //====================================================================
        for (int i = 0; i < level; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10.0);

            for (int j = 0; j < level; j++) {
                Button button = new Button();
                button.setId(String.valueOf(i + "" + j));
                button.setStyle("-fx-background-color: #2F4F4F");
                button.setPrefSize(60.0, 50.0);

                button.setOnAction(event -> did(button, primaryStage));


                hBox.getChildren().add(button);
            }
            vBox.getChildren().add(hBox);
        }


        sc = new Scene(pane);
        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public void goBack(Stage window)throws Exception{
        for(int i=0;i<level;i++){
            for(int j=0;j<level;j++){
                array[i][j] = 0;
            }
        }
        right = 0;
        start(window);
    }

    public void startGame(Button button, Stage window) {
        int val = Integer.parseInt(button.getId());
        level = val;
        right = 0;
        game(window);
    }

    public static void main(String[] args) {
        launch(args);
    }
}