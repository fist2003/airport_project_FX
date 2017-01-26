package dao;

import model.Flights;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ПК on 22.12.2016.
 */
public class FlightsDAO extends ConnectToMySQLDAO implements DAOInterface<Flights> {
    public FlightsDAO(){}

    private String querryInsert = "INSERT INTO flights (number,departPort,destinationPort,dateDepart,dateArrive,timeDepart,timeArrive," +
            "priceEconom,priceBusiness,airplane_id,status,gate,currentTime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String querryEdit = "UPDATE `flights` SET `number`= ?,`departPort`= ?,`destinationPort`= ?,`dateDepart` = ?,`dateArrive` = ?," +
            "`timeDepart`= ?,`timeArrive`= ?, `priceEconom`= ?, `priceBusiness`= ?,`airplane_id`= ?, `status`= ?, `gate`= ?," +
            " `currentTime`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `flights` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `flights`;";
    private String querryGetById = "Select * from `flights` where `id` = ?;";

    @Override
    public boolean insertNewDAO(Flights flight) {
        java.sql.Date sqlDateDepart = makeDateOfFlight(flight)[0];
        java.sql.Date sqlDateArrive = makeDateOfFlight(flight)[1];
        try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
            ps.setString(1, flight.getNumber());
            ps.setString(2, flight.getDepartPort());
            ps.setString(3,flight.getDestinationPort());
            ps.setDate(4,sqlDateDepart );
            ps.setDate(5,sqlDateArrive );
            ps.setString(6,flight.getTimeDepart());
            ps.setString(7,flight.getTimeDestin());
            ps.setInt(8,flight.getPriceEconom());
            ps.setInt(9,flight.getPriceBusiness());
            ps.setInt(10,flight.getAirplane_id());
            ps.setString(11,flight.getStatusOfFlight());
            ps.setString(12,flight.getGateName());
            ps.setString(13,flight.getCurrentTime());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Flights flight) {
        java.sql.Date sqlDateDepart = makeDateOfFlight(flight)[0];
        java.sql.Date sqlDateArrive = makeDateOfFlight(flight)[1];
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, flight.getNumber());
            ps.setString(2, flight.getDepartPort());
            ps.setString(3,flight.getDestinationPort());
            ps.setDate(4,sqlDateDepart );
            ps.setDate(5,sqlDateArrive );
            ps.setString(6,flight.getTimeDepart());
            ps.setString(7,flight.getTimeDestin());
            ps.setInt(8,flight.getPriceEconom());
            ps.setInt(9,flight.getPriceBusiness());
            ps.setInt(10,flight.getAirplane_id());
            ps.setString(11,flight.getStatusOfFlight());
            ps.setString(12,flight.getGateName());
            ps.setString(13,flight.getCurrentTime());
            ps.setInt(14,flight.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private java.sql.Date[] makeDateOfFlight(Flights flight){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDepart = null;
        Date parsedArrive = null;
        try {
            parsedDepart = format.parse(flight.getDateDepart());
            parsedArrive = format.parse(flight.getDateDestin());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDateDepart = new java.sql.Date(parsedDepart.getTime());
        java.sql.Date sqlDateArrive = new java.sql.Date(parsedArrive.getTime());
        return new java.sql.Date[]{sqlDateDepart,sqlDateArrive};
    }

    @Override
    public boolean deleteInDBDAO(Flights flight) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, flight.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Flights> getAllDAO() {
        ArrayList<Flights> list = new ArrayList<Flights>();
        if(getConnection() != null) {
            try (Statement st = getConnection().createStatement()) {
                st.execute(querryGetAll);
                ResultSet rs = st.getResultSet();
                while (rs.next()) {
                    list.add(makeInstFlightAndPutValues(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Flights getByIdDAO(Flights flight) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setLong(1,flight.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return makeInstFlightAndPutValues(rs);
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Flights makeInstFlightAndPutValues(ResultSet rs)throws SQLException{
        Flights instFlights = new Flights();
        instFlights.setId(rs.getInt("id"));
        instFlights.setNumber(rs.getString("number"));
        instFlights.setDepartPort(rs.getString("departPort"));
        instFlights.setDestinationPort(rs.getString("destinationPort"));
        instFlights.setDateDepart(rs.getString("dateDepart"));
        instFlights.setDateDestin(rs.getString("dateArrive"));
        instFlights.setTimeDepart(rs.getString("timeDepart"));
        instFlights.setTimeDestin(rs.getString("timeArrive"));
        instFlights.setPriceEconom(rs.getInt("priceEconom"));
        instFlights.setPriceBusiness(rs.getInt("priceBusiness"));
        instFlights.setAirplane_id(rs.getInt("airplane_id"));
        instFlights.setStatusOfFlight(rs.getString("status"));
        instFlights.setGateName(rs.getString("gate"));
        instFlights.setCurrentTime(rs.getString("currentTime"));
        return instFlights;
    }

}
