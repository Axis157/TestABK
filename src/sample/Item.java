package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    SimpleStringProperty idProperty;
    SimpleStringProperty nameProperty;
    SimpleIntegerProperty weightProperty;
    public String type;

    public Item(String idProperty, String nameProperty, Integer weightProperty, String type) {
        this.idProperty = new SimpleStringProperty(idProperty);
        this.nameProperty = new SimpleStringProperty(nameProperty);
        this.weightProperty = new SimpleIntegerProperty(weightProperty);
        this.type = type;
    }

    public SimpleStringProperty getIdProperty() {
        return idProperty;
    }
    public String getId() {return idProperty.getValue();}

    public SimpleStringProperty getNameProperty() {
        return nameProperty;
    }
    public String getName() {return nameProperty.getValue();}

    public SimpleIntegerProperty getWeightProperty() {
        return weightProperty;
    }
    public Integer getWeight() {
        return weightProperty.getValue();
    }

    public void setWeightProperty(int w) {
        this.weightProperty = new SimpleIntegerProperty(w);
    }
    public void setIdProperty(String id) {
        this.idProperty = new SimpleStringProperty(id);
    }
}
