package service;

import model.*;

/**
 * Created by ПК on 14.12.2016.
 */
public class PutStartDataService {
    public PutStartDataService(){}

    public void putStartData(){
        addAirlines();
        addAirplanes();
        addFlights();
        addPassengers();
        addUser();
    }

    private void addAirlines(){
        AirlinesService instAirlineService = new AirlinesService();
        instAirlineService.insertNewService(new Airlines(0,"AERO JET","Ukraine,Odessa,Odessa International Airport","8 048 777 83 03","www.flyaerojet.aero"));
        instAirlineService.insertNewService(new Airlines(0,"AZUR AIR","Ukraine,Odessa,Odessa International Airport","8 048  726 27 87","www.uhc.kiev.ua"));
        instAirlineService.insertNewService(new Airlines(0,"Turkish Airlines","Ukraine,Odessa, Zhukovskogo str. 26/28","8 0482 34 79 06","www.turkishairlines.com"));
    }

    private void addAirplanes(){
        AirplanesService instAirplaneService = new AirplanesService();
        instAirplaneService.insertNewService(new Airplanes(0,"SAAB","340B","ISO000001",2000,30,5,1));
        instAirplaneService.insertNewService(new Airplanes(0,"SAAB","340B","ISO000002",2000,30,5,1));
        instAirplaneService.insertNewService(new Airplanes(0,"Embraer","PHENOM 100","ISO100001",2010,6,6,2));
        instAirplaneService.insertNewService(new Airplanes(0,"Embraer","PHENOM 100","ISO100002",2010,6,6,2));
        instAirplaneService.insertNewService(new Airplanes(0,"Airbus","A319-132","ISO200001",2005,130,20,3));
        instAirplaneService.insertNewService(new Airplanes(0,"Airbus","A319-132","ISO200002",2005,130,20,3));
    }

    private void addFlights(){
        FlightsService instFlightService = new FlightsService();
        //2017-01-27
        String date = "2017-01-27";
        instFlightService.insertNewService(new Flights(1,"ODKI0001","ODESSA","KIEV",date,date,"06:00:00","07:30:00",150,250,1));
        instFlightService.insertNewService(new Flights(2,"KIOD0001","KIEV","ODESSA",date,date,"09:30:00","11:00:00",150,250,1));
        instFlightService.insertNewService(new Flights(3,"ODKI0002","ODESSA","KIEV",date,date,"13:00:00","14:30:00",150,250,1));
        instFlightService.insertNewService(new Flights(4,"KIOD0002","KIEV","ODESSA",date,date,"16:30:00","18:00:00",150,250,1));

        instFlightService.insertNewService(new Flights(5,"ODLV0001","ODESSA","LVIV",date,date,"07:00:00","08:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(6,"LVOD0001","LVIV","ODESSA",date,date,"10:30:00","12:00:00",150,250,2));
        instFlightService.insertNewService(new Flights(7,"ODLV0002","ODESSA","LVIV",date,date,"14:00:00","15:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(8,"LVOD0002","LVIV","ODESSA",date,date,"17:30:00","19:00:00",150,250,2));

        instFlightService.insertNewService(new Flights(9,"ODMI0001","ODESSA","MINSK",date,date,"05:00:00","07:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(10,"MIOD0001","MINSK","ODESSA",date,date,"09:00:00","11:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(11,"ODMI0002","ODESSA","MINSK",date,date,"13:00:00","15:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(12,"MIOD0002","MINSK","ODESSA",date,date,"17:00:00","19:00:00",200,300,3));

        instFlightService.insertNewService(new Flights(13,"ODTB0001","ODESSA","TBILICI",date,date,"05:30:00","07:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(14,"TBOD0001","TBILICI","ODESSA",date,date,"09:30:00","11:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(15,"ODTB0002","ODESSA","TBILICI",date,date,"13:30:00","15:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(16,"TBOD0002","TBILICI","ODESSA",date,date,"17:30:00","19:30:00",200,300,4));

        instFlightService.insertNewService(new Flights(17,"STOD0001","STAMBUL","ODESSA",date,date,"04:30:00","06:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(18,"ODST0001","ODESSA","STAMBUL",date,date,"08:30:00","10:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(19,"STOD0002","STAMBUL","ODESSA",date,date,"12:30:00","14:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(20,"ODST0002","ODESSA","STAMBUL",date,date,"16:30:00","18:30:00",250,350,5));

        instFlightService.insertNewService(new Flights(21,"KAOD0001","KAIR","ODESSA",date,date,"07:30:00","09:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(22,"ODKA0001","ODESSA","KAIR",date,date,"10:30:00","12:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(23,"KAOD0002","KAIR","ODESSA",date,date,"14:30:00","16:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(24,"ODKA0002","ODESSA","KAIR",date,date,"18:30:00","20:30:00",250,350,6));
//2017-01-28
        date = "2017-01-28";
        instFlightService.insertNewService(new Flights(1,"ODKI0011","ODESSA","KIEV",date,date,"06:00:00","07:30:00",150,250,1));
        instFlightService.insertNewService(new Flights(2,"KIOD0011","KIEV","ODESSA",date,date,"09:30:00","11:00:00",150,250,1));
        instFlightService.insertNewService(new Flights(3,"ODKI0012","ODESSA","KIEV",date,date,"13:00:00","14:30:00",150,250,1));
        instFlightService.insertNewService(new Flights(4,"KIOD0012","KIEV","ODESSA",date,date,"16:30:00","18:00:00",150,250,1));

        instFlightService.insertNewService(new Flights(5,"ODLV0011","ODESSA","LVIV",date,date,"07:00:00","08:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(6,"LVOD0011","LVIV","ODESSA",date,date,"10:30:00","12:00:00",150,250,2));
        instFlightService.insertNewService(new Flights(7,"ODLV0012","ODESSA","LVIV",date,date,"14:00:00","15:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(8,"LVOD0012","LVIV","ODESSA",date,date,"17:30:00","19:00:00",150,250,2));

        instFlightService.insertNewService(new Flights(9,"ODMI0011","ODESSA","MINSK",date,date,"05:00:00","07:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(10,"MIOD0011","MINSK","ODESSA",date,date,"09:00:00","11:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(11,"ODMI0012","ODESSA","MINSK",date,date,"13:00:00","15:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(12,"MIOD0012","MINSK","ODESSA",date,date,"17:00:00","19:00:00",200,300,3));

        instFlightService.insertNewService(new Flights(13,"ODTB0011","ODESSA","TBILICI",date,date,"05:30:00","07:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(14,"TBOD0011","TBILICI","ODESSA",date,date,"09:30:00","11:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(15,"ODTB0012","ODESSA","TBILICI",date,date,"13:30:00","15:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(16,"TBOD0012","TBILICI","ODESSA",date,date,"17:30:00","19:30:00",200,300,4));

        instFlightService.insertNewService(new Flights(17,"STOD0011","STAMBUL","ODESSA",date,date,"04:30:00","06:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(18,"ODST0011","ODESSA","STAMBUL",date,date,"08:30:00","10:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(19,"STOD0012","STAMBUL","ODESSA",date,date,"12:30:00","14:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(20,"ODST0012","ODESSA","STAMBUL",date,date,"16:30:00","18:30:00",250,350,5));

        instFlightService.insertNewService(new Flights(17,"KAOD0011","KAIR","ODESSA",date,date,"07:30:00","09:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(18,"ODKA0011","ODESSA","KAIR",date,date,"10:30:00","12:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(19,"KAOD0012","KAIR","ODESSA",date,date,"14:30:00","16:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(20,"ODKA0012","ODESSA","KAIR",date,date,"18:30:00","20:30:00",250,350,6));

        instFlightService.insertNewService(new Flights(21,"ODLV0111","ODESSA","LVIV",date,date,"19:15:00","21:00:00",150,250,2));
        instFlightService.insertNewService(new Flights(22,"LVOD0111","LVIV","ODESSA",date,date,"18:00:00","19:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(23,"ODLV0112","ODESSA","LVIV",date,date,"19:45:00","21:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(24,"LVOD0112","LVIV","ODESSA",date,date,"19:15:00","20:30:00",150,250,2));
        //2016-12-17
        date = "2017-01-29";
        instFlightService.insertNewService(new Flights(1,"ODKI0021","ODESSA","KIEV",date,date,"06:00:00","07:30:00",150,250,1));
        instFlightService.insertNewService(new Flights(2,"KIOD0021","KIEV","ODESSA",date,date,"09:30:00","11:00:00",150,250,1));
        instFlightService.insertNewService(new Flights(3,"ODKI0022","ODESSA","KIEV",date,date,"13:00:00","14:30:00",150,250,1));
        instFlightService.insertNewService(new Flights(4,"KIOD0022","KIEV","ODESSA",date,date,"16:30:00","18:00:00",150,250,1));

        instFlightService.insertNewService(new Flights(5,"ODLV0021","ODESSA","LVIV",date,date,"07:00:00","08:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(6,"LVOD0021","LVIV","ODESSA",date,date,"10:30:00","12:00:00",150,250,2));
        instFlightService.insertNewService(new Flights(7,"ODLV0022","ODESSA","LVIV",date,date,"14:00:00","15:30:00",150,250,2));
        instFlightService.insertNewService(new Flights(8,"LVOD0022","LVIV","ODESSA",date,date,"17:30:00","19:00:00",150,250,2));

        instFlightService.insertNewService(new Flights(9,"ODMI0021","ODESSA","MINSK",date,date,"05:00:00","07:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(10,"MIOD0021","MINSK","ODESSA",date,date,"09:00:00","11:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(11,"ODMI0022","ODESSA","MINSK",date,date,"13:00:00","15:00:00",200,300,3));
        instFlightService.insertNewService(new Flights(12,"MIOD0022","MINSK","ODESSA",date,date,"17:00:00","19:00:00",200,300,3));

        instFlightService.insertNewService(new Flights(13,"ODTB0021","ODESSA","TBILICI",date,date,"05:30:00","07:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(14,"TBOD0021","TBILICI","ODESSA",date,date,"09:30:00","11:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(15,"ODTB0022","ODESSA","TBILICI",date,date,"13:30:00","15:30:00",200,300,4));
        instFlightService.insertNewService(new Flights(16,"TBOD0022","TBILICI","ODESSA",date,date,"17:30:00","19:30:00",200,300,4));

        instFlightService.insertNewService(new Flights(17,"STOD0021","STAMBUL","ODESSA",date,date,"04:30:00","06:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(18,"ODST0021","ODESSA","STAMBUL",date,date,"08:30:00","10:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(19,"STOD0022","STAMBUL","ODESSA",date,date,"12:30:00","14:30:00",250,350,5));
        instFlightService.insertNewService(new Flights(20,"ODST0022","ODESSA","STAMBUL",date,date,"16:30:00","18:30:00",250,350,5));

        instFlightService.insertNewService(new Flights(21,"KAOD0021","KAIR","ODESSA",date,date,"07:30:00","09:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(22,"ODKA0021","ODESSA","KAIR",date,date,"10:30:00","12:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(23,"KAOD0022","KAIR","ODESSA",date,date,"14:30:00","16:30:00",250,350,6));
        instFlightService.insertNewService(new Flights(24,"ODKA0022","ODESSA","KAIR",date,date,"18:30:00","20:30:00",250,350,6));
    }

    public void addPassengers(){
        PassengersService instPassengersService = new PassengersService();
        instPassengersService.insertNewService(new Passengers(0,"Ivanov","Ivan","UA0001","MALE","1990-01-01","UKRAINE","ECONOM",1));
        instPassengersService.insertNewService(new Passengers(0,"Petrov","Dima","UA0002","MALE","1991-02-11","UKRAINE","ECONOM",1));
        instPassengersService.insertNewService(new Passengers(0,"Simanov","Sasha","UA0003","MALE","1992-03-21","UKRAINE","ECONOM",1));
        instPassengersService.insertNewService(new Passengers(0,"Stepanova","Masha","UA0004","FEMALE","1993-02-11","UKRAINE","ECONOM",1));
        instPassengersService.insertNewService(new Passengers(0,"Gluhova","Natasha","UA0005","FEMALE","1994-01-21","UKRAINE","BUSINESS",1));
        instPassengersService.insertNewService(new Passengers(0,"Stoikov","Ivan","UA0006","MALE","1995-02-01","UKRAINE","BUSINESS",1));

        instPassengersService.insertNewService(new Passengers(0,"Ivanov","Ivan","UA0001","MALE","1990-01-01","UKRAINE","ECONOM",8));
        instPassengersService.insertNewService(new Passengers(0,"Petrov","Dima","UA0002","MALE","1991-02-11","UKRAINE","ECONOM",8));
        instPassengersService.insertNewService(new Passengers(0,"Simanov","Sasha","UA0003","MALE","1992-03-21","UKRAINE","ECONOM",8));
        instPassengersService.insertNewService(new Passengers(0,"Stepanova","Masha","UA0004","FEMALE","1993-02-11","UKRAINE","ECONOM",8));
        instPassengersService.insertNewService(new Passengers(0,"Gluhova","Natasha","UA0005","FEMALE","1994-01-21","UKRAINE","BUSINESS",8));
        instPassengersService.insertNewService(new Passengers(0,"Stoikov","Ivan","UA0006","MALE","1995-02-01","UKRAINE","BUSINESS",8));

        instPassengersService.insertNewService(new Passengers(0,"Sidorova","Anya","UA0011","FEMALE","1990-01-01","UKRAINE","ECONOM",9));
        instPassengersService.insertNewService(new Passengers(0,"Petrovskii","Lesha","UA0012","MALE","1991-02-11","UKRAINE","ECONOM",9));
        instPassengersService.insertNewService(new Passengers(0,"Simanov","Vasya","UA0013","MALE","1992-03-21","UKRAINE","ECONOM",9));
        instPassengersService.insertNewService(new Passengers(0,"Babich","Dasha","UA0014","FEMALE","1993-02-11","UKRAINE","ECONOM",9));
        instPassengersService.insertNewService(new Passengers(0,"Simonova","Sasha","UA0015","FEMALE","1994-01-21","UKRAINE","BUSINESS",9));
        instPassengersService.insertNewService(new Passengers(0,"Ivanov","Ivan","UA0016","MALE","1995-02-01","UKRAINE","BUSINESS",9));

        instPassengersService.insertNewService(new Passengers(0,"Sidorova","Anya","UA0011","FEMALE","1990-01-01","UKRAINE","ECONOM",12));
        instPassengersService.insertNewService(new Passengers(0,"Petrovskii","Lesha","UA0012","MALE","1991-02-11","UKRAINE","ECONOM",12));
        instPassengersService.insertNewService(new Passengers(0,"Simanov","Vasya","UA0013","MALE","1992-03-21","UKRAINE","ECONOM",12));
        instPassengersService.insertNewService(new Passengers(0,"Babich","Dasha","UA0014","FEMALE","1993-02-11","UKRAINE","ECONOM",12));
        instPassengersService.insertNewService(new Passengers(0,"Simonova","Sasha","UA0015","FEMALE","1994-01-21","UKRAINE","BUSINESS",12));
        instPassengersService.insertNewService(new Passengers(0,"Ivanov","Ivan","UA0016","MALE","1995-02-01","UKRAINE","BUSINESS",12));
    }

    public void addUser(){
        UsersService instUsersService = new UsersService();
        instUsersService.insertNewService(new Users(0,"admin","123456","ivanov","ivan","MALE","admin@ukr.net",1));
        instUsersService.insertNewService(new Users(0,"user","123456","ivanov","ivan","MALE","user@ukr.net",0));
    }

}
