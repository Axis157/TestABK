package sample;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class CreateItem {
    public static ArrayList<TreeItem<Item>> createItem(ArrayList<TreeItem<Item>> itemWbs, int countWbs) {
        for (int i = 1; i <= countWbs; i++) {
            int countAct = (int) (Math.random() * 100 + 1);
            //добавление древовидного итема в лист с вбс
            itemWbs.add(new TreeItem(new Item("0." + i, "wbs" + i, 0)));
            ArrayList<TreeItem<Item>> itemAct = new ArrayList<>();
            for (int z = 1; z <= countAct; z++) {
                Integer w = (int) (Math.random() * 100 + 1);
                itemAct.add(new TreeItem(new Item("0." + i + "." + z, "activitie" + z, w)));
                itemWbs.get(i-1).getChildren().add(itemAct.get(z-1));
                itemWbs.get(i-1).getValue().setWeightProperty(itemWbs.get(i-1).getValue().getWeight() + w);

            }

        }
        return itemWbs;
    }

}
