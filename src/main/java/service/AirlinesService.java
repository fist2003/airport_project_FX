package service;

import dao.AirlinesDAO;
import dao.DAOInterface;
import model.Airlines;

import java.util.ArrayList;

/**
 * Created by slavik on 21.01.2017.
 */
public class AirlinesService implements DAOServiceInterface<Airlines> {

    public AirlinesService(){
        this.instAirlinesDAO = new AirlinesDAO();
    }

    private AirlinesDAO instAirlinesDAO;

    public ArrayList<Airlines> getAllService(){return instAirlinesDAO.getAllDAO();}

    public void insertNewService(Airlines airline){instAirlinesDAO.insertNewDAO(airline);}

    public void editDataService(Airlines airline){
        instAirlinesDAO.editInDBDAO(airline);
    }

    public void deleteDataService(Airlines airline){
        instAirlinesDAO.deleteInDBDAO(airline);
    }

    public void getByIdService(Airlines airline){
        instAirlinesDAO.getByIdDAO(airline);
    }
}
