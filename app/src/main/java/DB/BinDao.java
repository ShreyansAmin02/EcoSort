package DB;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface BinDao {
    @Insert
    void insert(Bin bin);
    @Update
    void update(Bin bin);
    @Delete
    void delete(Bin bin);
    @Query("SELECT * FROM bins")
    LiveData<List<Bin>>getAllBins();

}
