
package Dal;


import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class userDAO extends DBContext{
    
    //get all Employee
    public ArrayList<User> getAllEmployee() {
        ArrayList<User> listE = new ArrayList<>();
        String sql = "select * from [User] where Role = 'Employee'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                listE.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listE;
    }
    
    public User getUserById(int id) {
        try {
            String sql = "select * from [User] where UserID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setRole(rs.getString(4));
                return u;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void insertUser(User u) {
        try {
            String sql = "insert into [User]([UserID],[Username],[Password],[Role]) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getUserID());
            st.setString(2, u.getUsername());
            st.setString(3, u.getPassword());
            st.setString(4, u.getRole());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateUser(User u) {
        try {
            String sql = " UPDATE [dbo].[User] SET [Username] = ?,[Password] = ? WHERE [UserID]=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            st.setInt(3, u.getUserID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteEmployee(int id) {
        try {
            String sql = "delete from [User] where UserID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteRouteTransitPoint(int RouteID) {
        try {
            String sql = "delete from [RouteTransitPoint] where [RouteID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     public void deleteBusByRouteID(int RouteID) {
        try {
            String sql = "delete from [Bus] where [RouteID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteBusRoute(int RouteID) {
        try {
            String sql = "delete from [BusRoute] where [RouteID] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RouteID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     
    public static void main(String[] args) {
        userDAO cDao = new userDAO();
//        ArrayList<User> list = cDao.getAllEmployee();
//        for (User u : list) {
//            System.out.println(u);
//        }
        System.out.println("");
    }
}
