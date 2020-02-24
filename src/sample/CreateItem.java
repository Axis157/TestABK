package sample;

import javafx.scene.control.TreeItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static sample.IntoDB.*;
import static sample.MainDB.*;

public class CreateItem {
    public static ArrayList<TreeItem<Item>> createItem(ArrayList<TreeItem<Item>> itemWbs, int countWbs) throws Exception {
        Class.forName(DB_Driver); //Проверяет наличие JDBC драйвера для работы с БД
        Connection connection = DriverManager.getConnection(DB_URL); //соед. с БД
        System.out.println("Соединение с СУБД выполнено.");
        Statement st = connection.createStatement();
//        st.execute("DROP TABLE IF EXISTS items CASCADE;");
        st.execute("DELETE FROM items");
        System.out.println("Записи из БД удалены;");
        System.out.println(st.execute(sqlCrTbl));

        TreeItem<Item> root0 = new TreeItem<>(new Item("0","Project", 0, "P"));
        itemWbs.add(root0);
        System.out.println("root0 добавлен в массив;");

        for (int i = 2; i <= countWbs; i++) {
            int countAct = (int) (Math.random() * 10 + 1);
            int num = i - 1;
            //добавление древовидного итема в лист с вбс
            itemWbs.add(new TreeItem(new Item("0." + num, "wbs" + num, 0, "W")));
            ArrayList<TreeItem<Item>> itemAct = new ArrayList<>(countAct);
            for (int z = 1; z <= countAct; z++) {
                Integer w = (int) (Math.random() * 100 + 1);
                itemAct.add(new TreeItem(new Item("0." + num + "." + z, "activitie" + z, w, "A")));
                itemWbs.get(num).getChildren().add(itemAct.get(z-1));
                itemWbs.get(num).getValue().setWeightProperty(itemWbs.get(num).getValue().getWeight() + w);
                intoA(itemAct.get(z-1));

            }
        }
        System.out.println("Создан весь массив айтемов;");

        for(int i = 1; i < itemWbs.size(); i++){
            itemWbs.get(0).getChildren().add(itemWbs.get(i));
            Integer w = itemWbs.get(0).getValue().getWeight();
            itemWbs.get(0).getValue().setWeightProperty(itemWbs.get(i).getValue().getWeight()+w);
        }
        System.out.println("Посчитан вес для рут;");

        for(int i = 0; i < itemWbs.size(); i++){
            if (i == 0) intoP(itemWbs.get(i));
            else intoW(itemWbs.get(i));
        }

        st.close();
        connection.close();
        System.out.println("Отключение от СУБД выполнено.");
        return itemWbs;
    }

}
