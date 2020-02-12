package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //создание древовидной таблицы
        TreeTableView<ProjectRows> treeTable = new TreeTableView<>();

        //создание колонок
        TreeTableColumn<ProjectRows, Number> col1 = new TreeTableColumn<>("ID");
        TreeTableColumn<ProjectRows, String> col2 = new TreeTableColumn<>("Name");
        TreeTableColumn<ProjectRows, Number> col3 = new TreeTableColumn<>("Weight");

        //создание данных
        TreeItem<ProjectRows> rows1 = new TreeItem<>(new ProjectRows(11,"activities1",50));
        TreeItem<ProjectRows> rows2 = new TreeItem<>(new ProjectRows(12,"activities2",50));
        TreeItem<ProjectRows> rows3 = new TreeItem<>(new ProjectRows(21,"activities1",50));
        TreeItem<ProjectRows> rows4 = new TreeItem<>(new ProjectRows(22,"activities2",50));

        TreeItem<ProjectRows> root0 = new TreeItem<>(new ProjectRows(0,"Project", 200));
        TreeItem<ProjectRows> root1 = new TreeItem<>(new ProjectRows(1,"WBS1",100));
        TreeItem<ProjectRows> root2 = new TreeItem<>(new ProjectRows(2,"WBS2",100));

        root1.getChildren().addAll(rows1,rows2);
        root2.getChildren().addAll(rows3,rows4);
        root0.getChildren().addAll(root1, root2);

        //задание источника данных
        col1.setCellValueFactory(param -> param.getValue().getValue().getIdProperty());
        col2.setCellValueFactory(param -> param.getValue().getValue().getNameProperty());
        col3.setCellValueFactory(param -> param.getValue().getValue().getWeightProperty());

        treeTable.setRoot(root0);

        //добавление колонок в древовидную таблицу
        treeTable.getColumns().add(col1);
        treeTable.getColumns().add(col2);
        treeTable.getColumns().add(col3);

        //создание панели
        FlowPane root = new FlowPane(10, 10, treeTable);

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
