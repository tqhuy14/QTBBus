/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author V
 */
public class Bus {
    private int BusID;
    private int BusRoute;
    private String BusNumber;

    public Bus() {
    }

    public Bus(int BusID, int BusRoute, String BusNumber) {
        this.BusID = BusID;
        this.BusRoute = BusRoute;
        this.BusNumber = BusNumber;
    }

    public int getBusID() {
        return BusID;
    }

    public void setBusID(int BusID) {
        this.BusID = BusID;
    }

    public int getBusRoute() {
        return BusRoute;
    }

    public void setBusRoute(int BusRoute) {
        this.BusRoute = BusRoute;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String BusNumber) {
        this.BusNumber = BusNumber;
    }

    @Override
    public String toString() {
        return "Bus{" + "BusID=" + BusID + ", BusRoute=" + BusRoute + ", BusNumber=" + BusNumber + '}';
    }

    

    
}
