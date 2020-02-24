package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class MainDB extends Application {
    public static final String DB_URL = "jdbc:h2:.\\db\\TreeTable";
    public static final String DB_Driver = "org.h2.Driver";
    public static String sqlCrTbl = "CREATE TABLE IF NOT EXISTS items("+
                                    "id VARCHAR(255) PRIMARY KEY,"+
                                    "name VARCHAR(255) NOT NULL,"+
                                    "weight INTEGER," +
                                    "typefield VARCHAR(255) )";

    @Override
    public void start(Stage primaryStage) throws Exception{

        //кол-во вбс
        int countWbs = (int)(Math.random()*10+1);
        //создание листа с вбс
        ArrayList<TreeItem<Item>> itemWbs = CreateItem.createItem(new ArrayList<>(countWbs), countWbs);

        TreeTableColumn<Item, String> col1 = new TreeTableColumn<>("ID");
        TreeTableColumn<Item, String> col2 = new TreeTableColumn<>("Name");
        TreeTableColumn<Item, Number> col3 = new TreeTableColumn<>("Weight");

        //задание источника данных
        col1.setCellValueFactory(param -> param.getValue().getValue().getIdProperty());
        col2.setCellValueFactory(param -> param.getValue().getValue().getNameProperty());
        col3.setCellValueFactory(param -> param.getValue().getValue().getWeightProperty());

        TreeTableView<Item> treeTable = new TreeTableView<>();
//        TreeItem<Item> root0 = new TreeItem<>(new Item("0","Project", 0));

//        for(int i = 0; i < itemWbs.size(); i++){
//            root0.getChildren().add(itemWbs.get(i));
//            Integer w = root0.getValue().getWeight();
//            root0.getValue().setWeightProperty(itemWbs.get(i).getValue().getWeight()+w);
//        }

        treeTable.setRoot(itemWbs.get(0));

        //добавление колонок в древовидную таблицу
        treeTable.getColumns().add(col1);
        treeTable.getColumns().add(col2);
        treeTable.getColumns().add(col3);

        treeTable.setPrefSize(700,700);
        Button btnGen = new Button("Generate");
        Button btnLoad = new Button("Load");




        //создание панели
        AnchorPane root = new AnchorPane(treeTable);
//        root.getChildren().addAll(btnGen, btnLoad);

        AnchorPane.setBottomAnchor(treeTable, 0d);
        AnchorPane.setTopAnchor(treeTable, 0d);
        AnchorPane.setLeftAnchor(treeTable, 0d);
        AnchorPane.setRightAnchor(treeTable, 0d);

        //создание сцены на основе панели
        Scene scene = new Scene(root);

        primaryStage.setTitle("Primavera");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{

        launch(args);


    }
}
