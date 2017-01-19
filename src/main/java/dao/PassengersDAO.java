package dao;

import model.Passengers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by slavik on 19.01.2017.
 */
public class PassengersDAO extends ConnectToMySQLDAO implements DAOInterface<Passengers> {

    public PassengersDAO(){}

    private String querryInsert = "INSERT INTO passengers (lastName,firstName,passport,sex,birthday,country," +
            "classTicket,flight_id) VALUES(?,?,?,?,?,?,?,?);";
    private String querryEdit = "UPDATE `passengers` SET `lastName`= ?, `firstName`= ?, `passport`= ?, `sex`= ?, " +
            "`birthday`= ?, `country`= ?, `classTicket`= ?, `flight_id`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `passengers` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `passengers`;";
    private String querryGetById = "Select * from `passengers` where `id` = ?;";

    @Override
    public boolean insertNewDAO(Passengers passenger) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(passenger.getBirtday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
            ps.setString(1, passenger.getLastName());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getPassportNumber());
            ps.setString(4, passenger.getSex());
            ps.setDate(5, sqlDate);
            ps.setString(6, passenger.getCountry());
            ps.setString(7, passenger.getClassTicket());
            ps.setLong(8, passenger.getFlight_id());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Passengers passenger) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(passenger.getBirtday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, passenger.getLastName());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getPassportNumber());
            ps.setString(4, passenger.getSex());
            ps.setDate(5, sqlDate);
            ps.setString(6, passenger.getCountry());
            ps.setString(7, passenger.getClassTicket());
            ps.setLong(8, passenger.getFlight_id());
            ps.setLong(9,passenger.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteInDBDAO(Passengers passenger) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, passenger.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Passengers> getAllDAO() {
        ArrayList<Passengers> list = new ArrayList<Passengers>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryGetAll);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                Passengers instPassengers = new Passengers();
                instPassengers.setId(rs.getInt("id"));
                instPassengers.setLastName(rs.getString("lastName"));
                instPassengers.setFirstName(rs.getString("firstName"));
                instPassengers.setPassportNumber(rs.getString("passport"));
                instPassengers.setSex(rs.getString("sex"));
                instPassengers.setBirtday(rs.getString("birthday"));
                instPassengers.setCountry(rs.getString("country"));
                instPassengers.setClassTicket(rs.getString("classTicket"));
                instPassengers.setFlight_id(rs.getInt("flight_id"));
                list.add(instPassengers);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Passengers getByIdDAO(Passengers passengers) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setInt(1,passengers.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Passengers instPassengers = new Passengers();
                instPassengers.setId(rs.getInt("id"));
                instPassengers.setLastName(rs.getString("lastName"));
                instPassengers.setFirstName(rs.getString("firstName"));
                instPassengers.setPassportNumber(rs.getString("passport"));
                instPassengers.setSex(rs.getString("sex"));
                instPassengers.setBirtday(rs.getString("birthday"));
                instPassengers.setCountry(rs.getString("country"));
                instPassengers.setClassTicket(rs.getString("classTicket"));
                instPassengers.setFlight_id(rs.getInt("flight_id"));
                return instPassengers;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
