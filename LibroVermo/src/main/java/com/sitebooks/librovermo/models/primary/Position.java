package com.sitebooks.librovermo.models.primary;
import jakarta.persistence.*;

@Entity
@Table(name = "Position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID_Position;

    @Column(name = "Position_Name", nullable = false, unique = true)
    private String position_Name;

    public Position(){}
    public Position(long ID_Position, String positionName) {
        this.ID_Position = ID_Position;
        this.position_Name = positionName;
    }

    public long getID_Position() {
        return ID_Position;
    }

    public void setID_Position(long ID_Position) {
        this.ID_Position = ID_Position;
    }

    public String getPositionName() {
        return position_Name;
    }

    public void setPositionName(String positionName) {
        this.position_Name = positionName;
    }
}
