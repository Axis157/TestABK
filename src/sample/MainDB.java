package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainDB extends Application {
    public static final String DB_URL = "jdbc:h2:C:\\Users\\вероника\\IdeaProjects\\TestABK\\db\\TreeTable";
    public static final String DB_Driver = "org.h2.Driver";

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
        TreeItem<Item> root0 = new TreeItem<>(new Item("0","Project", 0));

        for(int i = 0; i < itemWbs.size(); i++){
            root0.getChildren().add(itemWbs.get(i));
            Integer w = root0.getValue().getWeight();
            root0.getValue().setWeightProperty(itemWbs.get(i).getValue().getWeight()+w);
        }

        treeTable.setRoot(root0);

        //добавление колонок в древовидную таблицу
        treeTable.getColumns().add(col1);
        treeTable.getColumns().add(col2);
        treeTable.getColumns().add(col3);

        treeTable.setPrefSize(700,700);



        //создание панели
        FlowPane root = new FlowPane(treeTable);

        //создание сцены на основе панели
        Scene scene = new Scene(root);

        primaryStage.setTitle("Primavera");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        try{
            Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
            System.out.println("Соединение с СУБД выполнено.");
            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ошибка SQL");
        }


        launch(args);

    }
}
