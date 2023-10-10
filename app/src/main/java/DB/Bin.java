package DB;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bins")
public class Bin {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String type;
    private String location;
    private String capacity;

    public Bin(String type, String location, String capacity) {
        this.type = type;
        this.location = location;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public String getCapacity(){return capacity;}
    public void setCapacity(String capacity) {this.capacity = capacity;}
}
