/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Bus;
import java.sql.Time;
import Model.BusRoute;
import Model.RouteTransitPoint;
import Model.TransitPoint;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author V
 */
public class DAO extends DBContext {

    // tim tat ca TransitPoint
    public ArrayList<TransitPoint> getAllTransitPoint() {
        ArrayList<TransitPoint> listTransitPoint = new ArrayList<>();
        String sql = "select * from TransitPoint";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TransitPoint t = new TransitPoint(rs.getInt("TransitPointID"),
                        rs.getString("Kind"),
                        rs.getString("Name"),
                        rs.getString("Location"));
                listTransitPoint.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listTransitPoint;
    }

    // tim User khi co role
    public List<User> getUserByRole(String role) {
        List<User> list = new ArrayList<>();
        String sql = """
                     SELECT  [UserID]
                           ,[Username]
                           ,[Password]
                           ,[Role]
                       FROM [dbo].[User]
                     where Role=?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, role);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"), rs.getString("role"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // tim TransitPoint khi co Location
    public List<TransitPoint> getTransitPointByLocation(String Location) {
        List<TransitPoint> list = new ArrayList<>();
        String sql = """
                    SELECT [TransitPointID]
                           ,[Kind]
                           ,[Name]
                           ,[Location]
                       FROM [BusPRJ_Real].[dbo].[TransitPoint]
                       where """;
        if (Location != null) {
            sql += " [Location] = N'" + Location + "'";
        }
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TransitPoint tr = new TransitPoint(rs.getInt("TransitPointID"),
                        rs.getString("Kind"),
                        rs.getString("Name"), rs.getString("Location"));
                list.add(tr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // tim TransitPoint khi co Id
    public TransitPoint getTransitPointByID(int id) {
        
        String sql = """
                    SELECT [TransitPointID]
                           ,[Kind]
                           ,[Name]
                           ,[Location]
                       FROM [TransitPoint]
                       where [TransitPointID]=?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TransitPoint tr = new TransitPoint(rs.getInt("TransitPointID"),
                        rs.getString("Kind"),
                        rs.getString("Name"), rs.getString("Location"));
                return tr;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    

    // get all BusRoute
    public ArrayList<BusRoute> getAllBusRoute() {
        ArrayList<BusRoute> list = new ArrayList<>();
        String sql = "SELECT * FROM [BusRoute]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Time startTime = rs.getTime("StartTime");
                Time EndTime = rs.getTime("EndTime");
                Time Frequency = rs.getTime("Frequency");

                BusRoute b = new BusRoute(rs.getInt("RouteID"),
                        rs.getInt("StartPointID"),
                        rs.getInt("EndPointID"),
                        Frequency,
                        startTime,
                        EndTime,rs.getString(7));
                list.add(b);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get BusRoute by Id
//      public BusRoute getBusRouteById(int id) {
//        try {
//            String sql = "select * from [BusRoute] where [RouteID] = ?";
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, id);
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//                BusRoute u = new BusRoute();
//                u.setRouteID(rs.getInt(1));
//                u.setStartPointID(rs.getInt(2));
//                u.setEndPointID(rs.getInt(3));
//                u.setFrequency(rs.getTime(4));
//                u.setStartTime(rs.getTime(5));
//                u.setEndTime(rs.getTime(6));
//                
//                return u;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
    // tim Bus khi co RouteID
    public Bus getBusByRouteID(int RouteID) {
        String sql = """
                    SELECT [BusID]
                               ,[RouteID]
                               ,[BusNumber]
                           FROM [BusPRJ_Real].[dbo].[Bus]
                           where [RouteID]=?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Bus bus = new Bus();
                bus.setBusID(rs.getInt("BusID"));
                bus.setBusRoute(rs.getInt("RouteID"));
                bus.setBusNumber(rs.getString("BusNumber"));
                return bus;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // get BusRoute By RouteID
    public BusRoute getBusRouteByRouteID(int RouteID) {
        String sql = """
                     SELECT [RouteID]
                           ,[StartPointID]
                           ,[EndPointID]
                           ,[Frequency]
                           ,[StartTime]
                           ,[EndTime]
                           ,[RouteName]
                       FROM [BusPRJ_Real].[dbo].[BusRoute]
                       where RouteID=?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Time startTime = rs.getTime("StartTime");
                Time EndTime = rs.getTime("EndTime");
                Time Frequency = rs.getTime("Frequency");
                BusRoute b = new BusRoute(rs.getInt("RouteID"),
                        rs.getInt("StartPointID"),
                        rs.getInt("EndPointID"),
                        Frequency,
                        startTime,
                        EndTime, rs.getString("RouteName"));
                return b;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // insert bus
    public void insertBus(Bus u) {
        try {
            String sql = "insert into [Bus]([BusID],[RouteID],[BusNumber]) values(?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getBusID());
            st.setInt(2, u.getBusRoute());
            st.setString(3, u.getBusNumber());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // insert busroute
    public void insertBusRoute(BusRoute u) {
        try {
            String sql = "insert into [BusRoute]([RouteID],[StartPointID],[EndPointID],[Frequency],[StartTime],[EndTime],[RouteName]) values(?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getRouteID());
            st.setInt(2, u.getStartPointID());
            st.setInt(3, u.getEndPointID());
            st.setTime(4, u.getFrequency());
            st.setTime(5, u.getStartTime());
            st.setTime(6, u.getEndTime());
            st.setString(7, u.getName());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertRouteTransitPoint(RouteTransitPoint u) {
        try {
            String sql = "insert into [RouteTransitPoint]([RouteID],[TransitPointID],[Order],[ArrivalTime]) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getRouteID());
            st.setInt(2, u.getTransitPointID());
            st.setInt(3, u.getOrder());
            st.setTime(4, u.getArrivalTime());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // insert TransitPoint
    public void inserTransitPoint(TransitPoint u) {
        try {
            String sql = "insert into [TransitPoint]([TransitPointID],[Kind],[Name],[Location]) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getTransitPointID());
            st.setString(2, u.getKind());
            st.setString(3, u.getName());
            st.setString(4, u.getLocation());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //get all Bus
    public ArrayList<Bus> getAllBus() {
        ArrayList<Bus> listB = new ArrayList<>();
        String sql = "select * from [Bus]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setBusID(rs.getInt("BusID"));
                 bus.setBusRoute(rs.getInt(2));
                bus.setBusNumber(rs.getString("BusNumber"));
                listB.add(bus);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listB;
    }

    public ArrayList<RouteTransitPoint> getRouteTranstPointByRouteID(int RouteID) {
        ArrayList<RouteTransitPoint> list = new ArrayList<>();
        String sql = "select * from [RouteTransitPoint] where [RouteID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RouteTransitPoint rtb = new RouteTransitPoint();
                rtb.setRouteID(rs.getInt(1));
                rtb.setTransitPointID(rs.getInt(2));
                rtb.setOrder(rs.getInt(3));
                rtb.setArrivalTime(rs.getTime(4));
                list.add(rtb);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Bus getBusByBusID(int BusID) {
        try {
            String sql = "select * from [Bus] where [BusID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, BusID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Bus u = new Bus();
                u.setBusID(rs.getInt(1));
                u.setBusRoute(rs.getInt(2));
                u.setBusNumber(rs.getString(3));
                return u;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // delete bus
    public void deleteBus(int id) {
        try {
            String sql = "delete from [Bus] where BusID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // delete transitpoint
    public int deleteTransitpoint(int id) {
        try {
            String sql = "delete from [TransitPoint] where [TransitPointID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public void deleteRouteTransitpoint(int id) {
        try {
            String sql = "delete from [RouteTransitPoint] where [TransitPointID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBus(int id, int rid, String num) {
        try {
            String sql = " UPDATE [dbo].[Bus] SET [RouteID] = ?,[BusNumber] = ? WHERE [BusID]=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(3, id);
            st.setInt(1, rid);
            st.setString(2, num);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateTransitPoint(TransitPoint t) {
        try {
            String sql = " UPDATE [TransitPoint] SET [Kind] = ?,[Name] = ?,[Location] = ? WHERE [TransitPointID]=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(4, t.getTransitPointID());
            st.setString(1, t.getKind());
            st.setString(2, t.getName());
            st.setString(3, t.getLocation());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBusRoute(BusRoute t) {
        try {
            String sql = " UPDATE [BusRoute] SET [StartPointID] = ?,[EndPointID] = ?,[Frequency] = ?,[StartTime] = ?,[EndTime] = ?,[RouteName] = ? WHERE [RouteID]=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(7, t.getRouteID());
            st.setInt(1, t.getStartPointID());
            st.setInt(2, t.getEndPointID());
            st.setTime(3, t.getFrequency());
            st.setTime(4, t.getStartTime());
            st.setTime(5, t.getEndTime());
            st.setString(6, t.getName());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getRouteIDByTransitPointID(int TransitPointID) {
        try {
            String sql = "select [RouteID] from [RouteTransitPoint] where [TransitPointID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, TransitPointID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int RouteID = rs.getInt(1);

                return RouteID;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        DAO c = new DAO();
        ArrayList<BusRoute> list = c.getAllBusRoute();
        
        for (BusRoute bus : list) {
            System.out.println(bus);
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        try {
//            Date datef = sdf.parse("12:00:00");
//            Time timef = new Time(datef.getTime());
//            RouteTransitPoint rtp = new RouteTransitPoint(1, 2, 1, timef);
//            c.insertRouteTransitPoint(rtp);
//            RouteTransitPoint rtps = new RouteTransitPoint(1, 4, 1, timef);
//            c.insertRouteTransitPoint(rtps);
//        } catch (Exception e) {
//            System.out.println(e);
//        }

    }
    
    //////////////////////////////
    // tim tat ca TransitPoint
    public List<TransitPoint> getAllTransitPoint1() {
        List<TransitPoint> listTransitPoint = new ArrayList<>();
        String sql = "select * from TransitPoint";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TransitPoint t = new TransitPoint(rs.getInt("TransitPointID"),
                        rs.getString("Kind"),
                        rs.getString("Name"),
                        rs.getString("Location"));
                listTransitPoint.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listTransitPoint;
    }

    // tim User khi co username va password
    public User getUserByRole(String Username, String Password) {

        String sql = """
                     SELECT  [UserID]
                           ,[Username]
                           ,[Password]
                           ,[Role]
                       FROM [dbo].[User]
                     where Username=? and Password = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Username);
            st.setString(2, Password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"), rs.getString("role"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    
    // tim BusRoute khi co StartPointID va EndPointID
    public List<BusRoute> getBusRouteByLocation(int StartPointID, int EndPointID) {
        List<BusRoute> list = new ArrayList<>();
        String sql = """
                     SELECT *
                            FROM [BusPRJ_Real].[dbo].[BusRoute]
                            WHERE StartPointID = ? AND EndPointID = ?""";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, StartPointID);
            st.setInt(2, EndPointID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Time startTime = rs.getTime("StartTime");
                Time EndTime = rs.getTime("EndTime");
                Time Frequency = rs.getTime("Frequency");
                BusRoute b = new BusRoute(rs.getInt("RouteID"),
                        rs.getInt("StartPointID"),
                        rs.getInt("EndPointID"),
                        Frequency,
                        startTime,
                        EndTime, rs.getString("RouteName"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }   

   

    // tim RouteTransitPoint khi co RouteID
    public List<RouteTransitPoint> getRouteTransitPointByRouteID(int RouteID) {
        List<RouteTransitPoint> list = new ArrayList<>();
        String sql = """
                    SELECT [RouteID]
                             ,[TransitPointID]
                             ,[Order]
                             ,[ArrivalTime]
                         FROM [dbo].[RouteTransitPoint]
                         where RouteID = ? """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RouteTransitPoint stp = new RouteTransitPoint();
                stp.setRouteID(rs.getInt("RouteID"));
                stp.setTransitPointID(rs.getInt("TransitPointID"));
                stp.setOrder(rs.getInt("Order"));
                Time ArrivalTime = rs.getTime("ArrivalTime");
                stp.setArrivalTime(ArrivalTime);
                list.add(stp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    
    // tim RouteTransitPoint khi co TransitPointID
    public List<RouteTransitPoint> getRouteTransitPointByTransitPointID(int TransitPointID) {
        List<RouteTransitPoint> list = new ArrayList<>();
        String sql = """
                     SELECT *
                           FROM [dbo].[RouteTransitPoint]
                           where TransitPointID =?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, TransitPointID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RouteTransitPoint stp = new RouteTransitPoint();
                stp.setRouteID(rs.getInt("RouteID"));
                stp.setTransitPointID(rs.getInt("TransitPointID"));
                stp.setOrder(rs.getInt("Order"));
                Time ArrivalTime = rs.getTime("ArrivalTime");
                stp.setArrivalTime(ArrivalTime);
                list.add(stp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
   

}
