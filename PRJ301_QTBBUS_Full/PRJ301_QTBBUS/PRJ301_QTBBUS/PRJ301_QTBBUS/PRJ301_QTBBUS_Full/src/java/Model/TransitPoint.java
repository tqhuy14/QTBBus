/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author V
 */
public class TransitPoint {
    private int TransitPointID;
    private String Kind;
    private String Name;
    private String Location;

    public TransitPoint() {
    }

    public TransitPoint(int TransitPointID, String Kind, String Name, String Location) {
        this.TransitPointID = TransitPointID;
        this.Kind = Kind;
        this.Name = Name;
        this.Location = Location;
    }

    public int getTransitPointID() {
        return TransitPointID;
    }

    public void setTransitPointID(int TransitPointID) {
        this.TransitPointID = TransitPointID;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String Kind) {
        this.Kind = Kind;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return "TransitPoint{" + "TransitPointID=" + TransitPointID + ", Kind=" + Kind + ", Name=" + Name + ", Location=" + Location + '}';
    }

    
    
}
