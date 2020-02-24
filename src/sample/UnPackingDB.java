package sample;

import javafx.scene.control.TreeItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static sample.MainDB.DB_Driver;
import static sample.MainDB.DB_URL;

public class UnPackingDB {

    public static ArrayList<TreeItem<Item>>  unPackingDB() throws Exception{
        Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
        Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
        System.out.println("Соединение с СУБД выполнено.");
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM items");

//        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM items");
//        rs.next();
//        ArrayList<Item> items = new ArrayList<>(Integer.parseInt(rs.getString(1)));
        ArrayList<TreeItem<Item>> items = new ArrayList<>();

        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            Integer weight = rs.getInt(3);
            String type = rs.getString(4);
            items.add(new TreeItem(new Item(id, name, weight, type)));
        }
        ArrayList<TreeItem<Item>> result = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getValue().type.equals("W")) {
                String idW = items.get(i).getValue().getId();
                for(int z = 0; z < items.size(); z++){
                    if (z == i) continue;
                    if (items.get(z).getValue().type.equals("A")) {
                        String idA = items.get(z).getValue().getId().substring(0, idW.length());
                        if (idA.equals(idW)) {
                            items.get(i).getChildren().add(items.get(z));
                            result.add(items.get(i));
                        }
                    }
                }
            }
            if(items.get(i).getValue().type.equals("P")){
                for(int z = 0; z < items.size(); z++){
                    if (z == i) continue;
                    if(items.get(z).getValue().type.equals("W")){
                        items.get(i).getChildren().add(items.get(z));
                        result.add(items.get(i));
                    }
                }
            }
        }
        rs.close();
        st.close();
        connection.close();
        return result;
    }
}
