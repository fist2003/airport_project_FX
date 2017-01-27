package service;

import dao.UsersDAO;
import model.Users;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ПК on 13.12.2016.
 */
public class UsersService implements CRUDServiceInterface<Users>{
    public UsersService(){
        this.instUsersDAO = new UsersDAO();
    }
    private UsersDAO instUsersDAO;

    private static boolean flagIsAdmin = false;

    public static String getUserLoginedName() {return userLoginedName;}
    public static void setUserLoginedName(String userLoginedName) {UsersService.userLoginedName = userLoginedName;}

    private static String userLoginedName = null;

    public static void setFlagIsAdmin(boolean isAdmin) {
        UsersService.flagIsAdmin = isAdmin;
    }
    public static boolean isFlagIsAdmin(){return flagIsAdmin;}

    public ArrayList<Users> getAllService(){
        ArrayList<Users> list = instUsersDAO.getAllDAO();
        for(Users user:list){
            putValueToTypeUser(user);
        }
        return list;
    }

    public void insertNewService(Users user){instUsersDAO.insertNewDAO(user);}

    public void editDataService(Users user){
        instUsersDAO.editInDBDAO(user);
    }

    public void deleteDataService(Users user){
        instUsersDAO.deleteInDBDAO(user);
    }

    public Users getByIdService(Users user){
        return instUsersDAO.getByIdDAO(user);
    }

    public void putValueToTypeUser(Users user){
        if(user.getIsAdmin() == 1){
            user.setTypeUser(new EditDataService().getTypeUserAdmin());
        }
        else user.setTypeUser(new EditDataService().getTypeUserUser());
    }

    public int getIsAdminFromTypeUser(String typeUser){
        int result = 0;
        if(typeUser.toLowerCase().equals(new EditDataService().getTypeUserAdmin())){
            result = 1;
        }
        return result;
    }

    public boolean validateEmail(String value){
        String validEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pat = Pattern.compile(validEmail);
        Matcher mat = pat.matcher(value);
        return mat.matches();
    }

    public Users checkInputUser(Users userParametr){
        ArrayList<Users> allList = getAllService();
        for (Users user:allList){
            if ((userParametr.getLogin().toLowerCase().equals(user.getLogin().toLowerCase()))&&
            (userParametr.getPassword().toLowerCase().equals(user.getPassword().toLowerCase()))){
                return user;
            }
        }
        return null;
    }
}
