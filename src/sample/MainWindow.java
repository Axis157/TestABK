package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainWindow {

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

    treeTable.setRoot(itemWbs.get(0));

    //добавление колонок в древовидную таблицу
    treeTable.getColumns().add(col1);
    treeTable.getColumns().add(col2);
    treeTable.getColumns().add(col3);

    treeTable.setPrefSize(700,700);
    Button btnGen = new Button("Generate");
    Button btnLoad = new Button("Load");
    btnGen.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Stage stage2 = new Stage();
            stage2.show();
        }
    });




    //создание панели
    AnchorPane root = new AnchorPane(treeTable);
    root.getChildren().addAll(btnGen, btnLoad);
    AnchorPane.setBottomAnchor(treeTable, 0d);
    AnchorPane.setTopAnchor(treeTable, 0d);
    AnchorPane.setLeftAnchor(treeTable, 0d);
    AnchorPane.setRightAnchor(treeTable, 0d);

    //создание сцены на основе панели
    Scene scene = new Scene(root);
    Stage primaryStage = new Stage();
    primaryStage.setTitle("Primavera");
    primaryStage.setScene(scene);
    primaryStage.show();
}