package dao;

import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by ПК on 08.12.2016.
 */
public class UsersDAO extends ConnectToMySQLDAO implements DAOInterface<Users> {
    public UsersDAO(){}

    private String querryInsert = "INSERT INTO users (login,password,email,lastName,firstName,sex,isAdmin) VALUES(?,?,?,?,?,?,?);";
    private String querryEdit = "UPDATE `users` SET `login`= ?, `password`= ?,`email` = ?, `lastName`= ?, `firstName`= ?," +
            " `sex`= ?,`isAdmin`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `users` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `users`;";
    private String querryGetById = "Select * from `users` where `id` = ?;";

    @Override
    public boolean insertNewDAO(Users user) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getFirstName());
            ps.setString(6, user.getSex());
            ps.setInt(7, user.getIsAdmin());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Users user) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getFirstName());
            ps.setString(6, user.getSex());
            ps.setInt(7, user.getIsAdmin());
            ps.setLong(8,user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteInDBDAO(Users user) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Users> getAllDAO() {
        ArrayList<Users> list = new ArrayList<Users>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryGetAll);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                Users instUsers = new Users();
                instUsers.setId(rs.getInt("id"));
                instUsers.setLastName(rs.getString("lastName"));
                instUsers.setFirstName(rs.getString("firstName"));
                instUsers.setLogin(rs.getString("login"));
                instUsers.setPassword(rs.getString("password"));
                instUsers.setSex(rs.getString("sex"));
                instUsers.setEmail(rs.getString("email"));
                instUsers.setIsAdmin(rs.getInt("isAdmin"));
                list.add(instUsers);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Users getByIdDAO(Users user) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setLong(1,user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users instUsers = new Users();
                instUsers.setId(rs.getInt("id"));
                instUsers.setLastName(rs.getString("lastName"));
                instUsers.setFirstName(rs.getString("firstName"));
                instUsers.setLogin(rs.getString("login"));
                instUsers.setPassword(rs.getString("password"));
                instUsers.setSex(rs.getString("sex"));
                instUsers.setEmail(rs.getString("email"));
                instUsers.setIsAdmin(rs.getInt("isAdmin"));
                return instUsers;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
/*
    public boolean[] checkUserDAO(Users user) {
        boolean[] arrBool = {false, false, false};
        String querry = "Select login,password,isAdmin from users where login = '" + user.getLogin() + "'";
        try (Statement st = getConnection().createStatement()) {
            st.execute((querry));
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                arrBool[0] = true;
                if (user.getPassword().equals(rs.getString("password"))) {
                    arrBool[1] = true;
                    if (rs.getBoolean("isAdmin")) {
                        arrBool[2] = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrBool;
    }
*/
}
