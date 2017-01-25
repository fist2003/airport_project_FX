package ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Users;
import service.UsersService;

import java.io.IOException;

/**
 * Created by ПК on 22.12.2016.
 */
public class UsersTableModel implements TableModelInterface {

    public UsersTableModel(){}

    private ObservableList<Users> dataList = FXCollections.observableArrayList();
    private BorderPane tablePane;
    private final  String tablePaneFxmlFile = "/fxml/edit/tableUsers.fxml";

    @FXML
    private TableView<Users> tableView;
    @FXML
    private TableColumn<Users, String> loginColumn;
    @FXML
    private TableColumn<Users, String> emailColumn;
    @FXML
    private TableColumn<Users, String> lastNameColumn;
    @FXML
    private TableColumn<Users, String> firstNameColumn;
    @FXML
    private TableColumn<Users, String> sexColumn;
    @FXML
    private TableColumn<Users, String> typeUserColumn;

    public ObservableList<Users> getDataList() {
        return dataList;
    }

    public BorderPane getTablePane() {
        loadTablePane();
        return tablePane;
    }

    private void addDataToTable() {
        dataList.addAll(new UsersService().getAllService());
        tableView.setItems(getDataList());
        tableView.setPlaceholder(new Label("THERE AREN`T NO DATA"));
    }

    @FXML
    private void initialize() {
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        typeUserColumn.setCellValueFactory(cellData -> cellData.getValue().typeUserProperty());
    }

    private void loadTablePane(){
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsersTableModel controller = loaderTablePane.getController();
        controller.addDataToTable();
    }

}
