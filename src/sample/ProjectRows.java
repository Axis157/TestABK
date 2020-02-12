package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProjectRows {
    SimpleIntegerProperty idProperty;
    SimpleStringProperty nameProperty;
    SimpleIntegerProperty weightProperty;

    public ProjectRows(Integer idProperty, String nameProperty, Integer weightProperty) {
        this.idProperty = new SimpleIntegerProperty(idProperty);
        this.nameProperty = new SimpleStringProperty(nameProperty);
        this.weightProperty = new SimpleIntegerProperty(weightProperty);
    }

    public SimpleIntegerProperty getIdProperty() {
        return idProperty;
    }

    public SimpleStringProperty getNameProperty() {
        return nameProperty;
    }

    public SimpleIntegerProperty getWeightProperty() {
        return weightProperty;
    }
}
