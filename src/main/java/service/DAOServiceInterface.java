package service;

import model.Airlines;

import java.util.ArrayList;

/**
 * Created by slavik on 22.01.2017.
 */
public interface DAOServiceInterface<Entity> {

    ArrayList<Entity> getAllService();

    void insertNewService(Entity entity);

    void editDataService(Entity entity);

    void deleteDataService(Entity entity);

    void getByIdService(Entity entity);
}
