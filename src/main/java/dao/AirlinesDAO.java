package dao;

import model.Airlines;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ПК on 08.12.2016.
 */
public class AirlinesDAO extends ConnectToMySQLDAO implements DAOInterface<Airlines> {
    public AirlinesDAO(){}
    private String querryInsert = "INSERT INTO airlines (name,adress,telephone,website) VALUES(?,?,?,?);";
    private String querryEdit = "UPDATE `airlines` SET `name`= ?,`adress`= ?,`telephone`= ?,`website`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `airlines` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `airlines`;";
    private String querryGetById = "Select * from `airlines` where `id` = ?;";

    @Override
    public boolean insertNewDAO(Airlines airline) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
            ps.setString(1, airline.getName());
            ps.setString(2, airline.getAdress());
            ps.setString(3,airline.getTelephone() );
            ps.setString(4,airline.getWebsite());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Airlines airline) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, airline.getName());
            ps.setString(2, airline.getAdress());
            ps.setString(3,airline.getTelephone() );
            ps.setString(4,airline.getWebsite());
            ps.setInt(5,airline.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteInDBDAO(Airlines airline) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, airline.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Airlines> getAllDAO() {
        ArrayList<Airlines> list = new ArrayList<Airlines>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryGetAll);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                Airlines instAirlines = new Airlines();
                instAirlines.setId(rs.getInt("id"));
                instAirlines.setName(rs.getString("name"));
                instAirlines.setAdress(rs.getString("adress"));
                instAirlines.setTelephone(rs.getString("telephone"));
                instAirlines.setWebsite(rs.getString("website"));
                list.add(instAirlines);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Airlines getByIdDAO(Airlines airlines) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setLong(1,airlines.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Airlines instAirlines = new Airlines();
                instAirlines.setId(rs.getInt("id"));
                instAirlines.setName(rs.getString("name"));
                instAirlines.setAdress(rs.getString("adress"));
                instAirlines.setTelephone(rs.getString("telephone"));
                instAirlines.setWebsite(rs.getString("website"));
                return instAirlines;
            }
                return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


}
