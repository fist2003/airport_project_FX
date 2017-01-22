package ui.model;

import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Airlines;

/**
 * Created by slavik on 22.01.2017.
 */
public interface TableModelInterface<T> {

     BorderPane getTablePane();
     TableView<T> getTable();
}
