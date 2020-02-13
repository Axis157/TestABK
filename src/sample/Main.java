package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //кол-во вбс
        int countWbs = (int)(Math.random()*100+1);
        //создание листа с вбс
        ArrayList<TreeItem<Item>> itemWbs = new ArrayList<>(countWbs);

        TreeTableColumn<Item, String> col1 = new TreeTableColumn<>("ID");
        TreeTableColumn<Item, String> col2 = new TreeTableColumn<>("Name");
        TreeTableColumn<Item, Number> col3 = new TreeTableColumn<>("Weight");

        ArrayList<TreeItem<Item>> itemWBS = new ArrayList<>(countWbs);
        itemWBS = CreateItem.createItem(itemWbs, countWbs);

        //задание источника данных
        col1.setCellValueFactory(param -> param.getValue().getValue().getIdProperty());
        col2.setCellValueFactory(param -> param.getValue().getValue().getNameProperty());
        col3.setCellValueFactory(param -> param.getValue().getValue().getWeightProperty());

        TreeTableView<Item> treeTable = new TreeTableView<>();
        TreeItem<Item> root0 = new TreeItem<>(new Item("0","Project", 0));

        for(int i = 0; i < itemWBS.size(); i++){
            root0.getChildren().add(itemWBS.get(i));
            Integer w = root0.getValue().getWeight();
            root0.getValue().setWeightProperty(itemWBS.get(i).getValue().getWeight()+w);
        }

        treeTable.setRoot(root0);

        //добавление колонок в древовидную таблицу
        treeTable.getColumns().add(col1);
        treeTable.getColumns().add(col2);
        treeTable.getColumns().add(col3);

        //создание панели
        Group root = new Group(treeTable);

        //создание сцены на основе панели
        Scene scene = new Scene(root);

        primaryStage.setTitle("Hello Sereja");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
