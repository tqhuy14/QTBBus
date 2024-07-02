/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Time;
/**
 *
 * @author V
 */
public class RouteTransitPoint {
    private int RouteID;
    private int TransitPointID;
    private int Order;
    private Time ArrivalTime;

    public RouteTransitPoint() {
    }

    public RouteTransitPoint(int RouteID, int TransitPointID, int Order, Time ArrivalTime) {
        this.RouteID = RouteID;
        this.TransitPointID = TransitPointID;
        this.Order = Order;
        this.ArrivalTime = ArrivalTime;
    }

    public int getRouteID() {
        return RouteID;
    }

    public void setRouteID(int RouteID) {
        this.RouteID = RouteID;
    }

    public int getTransitPointID() {
        return TransitPointID;
    }

    public void setTransitPointID(int TransitPointID) {
        this.TransitPointID = TransitPointID;
    }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int Order) {
        this.Order = Order;
    }

    public Time getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(Time ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    @Override
    public String toString() {
        return "RouteTransitPoint{" + "RouteID=" + RouteID + ", TransitPointID=" + TransitPointID + ", Order=" + Order + ", ArrivalTime=" + ArrivalTime + '}';
    }

    
    
}
