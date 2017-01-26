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
/*
    @Override
    protected ArrayList<String> checkInputValues(Entity entity) {
        ArrayList<String> checkList = new ArrayList<String>();
        Users user = (Users) entity;
        ArrayList<Users> allUsersList = getAllService();
        String validEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patIso = Pattern.compile(validEmail);
        Matcher matIso = patIso.matcher(user.getEmail());
        for (Users usersFromDB:allUsersList){
            if((usersFromDB.getLogin().toLowerCase().equals(user.getLogin().toLowerCase()))&&
                    (usersFromDB.getId() != user.getId())){
                checkList.add("User with this login is already exist");
            }
            else if((usersFromDB.getEmail().toLowerCase().equals(user.getEmail().toLowerCase()))&&
                    (usersFromDB.getId() != user.getId())){
                checkList.add("User with this email is already exist");
            }
        }
        if (checkForWhiteSpace(user.getLogin())){
            checkList.add(loginUserKey);
        }
        else if ((checkForWhiteSpace(user.getPassword()))||(user.getPassword().length() < 5)){
            checkList.add(passwordUserKey);
        }
        else if(!matIso.matches()){
            checkList.add(emailUserKey);
        }
        else if (checkForWhiteSpace(user.getLastName())){
            checkList.add(lastNameUserKey);
        }
        else if (checkForWhiteSpace(user.getFirstName())){
            checkList.add(firstNameUserKey);
        }
        else if ((checkForWhiteSpace(user.getSex()))||!((user.getSex().toLowerCase().equals("male"))||
                (user.getSex().toLowerCase().equals("female")))){
            checkList.add(sexUserKey);
        }
        else if((checkForWhiteSpace(String.valueOf(user.getIsAdmin())))||(user.getIsAdmin() < 0)||
                (user.getIsAdmin() > 1)){
            checkList.add(adminUserKey);
        }
        return checkList;
    }

    @Override
    protected ArrayList<Long> checkSafeDelete(Entity entity) {
        Users user = (Users) entity;
        ArrayList<Long> list = new ArrayList<Long>();
        if (user.getLogin().toLowerCase().equals(UserJPanelGUI.getUserName().toLowerCase())){
            list.add(-1l);
        }
        return list;
    }
    */
}
