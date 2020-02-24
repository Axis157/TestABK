package sample;

import javafx.scene.control.TreeItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static sample.MainDB.DB_Driver;
import static sample.MainDB.DB_URL;

public class IntoDB {

    public static void intoA(TreeItem<Item> item) throws Exception{
        Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
        Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
        Statement st = connection.createStatement();
        st.execute("INSERT INTO items VALUES ('"+item.getValue().getId()+"', " +
                " '"+item.getValue().getName()+"', "+
                item.getValue().getWeight()+", 'A')");
        st.close();
        connection.close();
    }

    public static void intoW(TreeItem<Item> item) throws Exception{
        Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
        Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
        Statement st = connection.createStatement();
        st.execute("INSERT INTO items VALUES ('"+item.getValue().getId()+"', " +
                " '"+item.getValue().getName()+"', "+
                item.getValue().getWeight()+", 'W')");
        st.close();
        connection.close();
    }

    public static void intoP(TreeItem<Item> item) throws Exception{
        Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
        Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
        Statement st = connection.createStatement();
        st.execute("INSERT INTO items VALUES ('"+item.getValue().getId()+"', " +
                " '"+item.getValue().getName()+"', "+
                item.getValue().getWeight()+", 'P')");
        st.close();
        connection.close();
    }

}
