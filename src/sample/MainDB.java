package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        BorderPane bp = new BorderPane();

        ArrayList<TreeItem<Item>> itemWbs = UnPackingDB.unPackingDB();

        if(itemWbs.isEmpty()){
            //кол-во вбс
            int countWbs = (int)(Math.random()*10+1);
            //создание листа с вбс
            itemWbs = CreateItem.createItem(new ArrayList<>(countWbs), countWbs);
        }

        TreeTableColumn<Item, String> col1 = new TreeTableColumn<>("ID");
        TreeTableColumn<Item, String> col2 = new TreeTableColumn<>("Name");
        TreeTableColumn<Item, Number> col3 = new TreeTableColumn<>("Weight");

        //задание источника данных
        col1.setCellValueFactory(param -> param.getValue().getValue().getIdProperty());
        col2.setCellValueFactory(param -> param.getValue().getValue().getNameProperty());
        col3.setCellValueFactory(param -> param.getValue().getValue().getWeightProperty());

        //возможность изменять название колонок

        TreeTableView<Item> treeTable = new TreeTableView<>();

        treeTable.setRoot(itemWbs.get(0));

        col2.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        col2.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                TreeItem<Item> currentEditingItem = treeTable.getTreeItem(event.getTreeTablePosition().getRow());
                currentEditingItem.getValue().setNameProperty(event.getNewValue());
                try {
                    Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
                    Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
                    Statement st = connection.createStatement();
                    st.execute("UPDATE items SET NAME = '"+currentEditingItem.getValue().getName()+"' WHERE ID = '"+currentEditingItem.getValue().getId()+"'");
                    st.close();
                    connection.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //добавление колонок в древовидную таблицу
        treeTable.getColumns().add(col1);
        treeTable.getColumns().add(col2);
        treeTable.getColumns().add(col3);
        treeTable.setEditable(true);

        treeTable.setPrefSize(700,700);
        Button btnGen = new Button("Generate");
        bp.setTop(btnGen);
//        Button btnLoad = new Button("Load");
        btnGen.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                //кол-во вбс
                ArrayList<TreeItem<Item>> newItemWbs = null;
                try {
                    int countWbs = (int) (Math.random() * 10 + 1);
                    //создание листа с вбс
                    newItemWbs = CreateItem.createItem(new ArrayList<>(countWbs), countWbs);
                }
                catch(Exception e){
                    System.out.println(e);
                }
                treeTable.setRoot(newItemWbs.get(0));
            }
        });

        //создание панели
        AnchorPane root = new AnchorPane(treeTable);

        AnchorPane.setBottomAnchor(treeTable, 0d);
        AnchorPane.setTopAnchor(treeTable, 0d);
        AnchorPane.setLeftAnchor(treeTable, 0d);
        AnchorPane.setRightAnchor(treeTable, 0d);
        bp.setCenter(root);

        //создание сцены на основе панели
        Scene scene = new Scene(bp);

        primaryStage.setTitle("Primavera");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{

        launch(args);


    }
}
