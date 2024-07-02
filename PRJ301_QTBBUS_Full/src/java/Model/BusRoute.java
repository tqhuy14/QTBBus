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
public class BusRoute {
    private int RouteID;
    private int StartPointID;
    private int EndPointID;
    private Time Frequency;
    private Time StartTime;
    private Time EndTime;
    private String Name;

    public BusRoute() {
    }

    public BusRoute(int RouteID, int StartPointID, int EndPointID, Time Frequency, Time StartTime, Time EndTime, String Name) {
        this.RouteID = RouteID;
        this.StartPointID = StartPointID;
        this.EndPointID = EndPointID;
        this.Frequency = Frequency;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Name = Name;
    }

    public int getRouteID() {
        return RouteID;
    }

    public void setRouteID(int RouteID) {
        this.RouteID = RouteID;
    }

    public int getStartPointID() {
        return StartPointID;
    }

    public void setStartPointID(int StartPointID) {
        this.StartPointID = StartPointID;
    }

    public int getEndPointID() {
        return EndPointID;
    }

    public void setEndPointID(int EndPointID) {
        this.EndPointID = EndPointID;
    }

    public Time getFrequency() {
        return Frequency;
    }

    public void setFrequency(Time Frequency) {
        this.Frequency = Frequency;
    }

    public Time getStartTime() {
        return StartTime;
    }

    public void setStartTime(Time StartTime) {
        this.StartTime = StartTime;
    }

    public Time getEndTime() {
        return EndTime;
    }

    public void setEndTime(Time EndTime) {
        this.EndTime = EndTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "BusRoute{" + "RouteID=" + RouteID + ", StartPointID=" + StartPointID + ", EndPointID=" + EndPointID + ", Frequency=" + Frequency + ", StartTime=" + StartTime + ", EndTime=" + EndTime + ", Name=" + Name + '}';
    }

    

    
    
}
