package model;

import java.util.HashMap;
import java.util.List;

public interface Model {

    List<HashMap<String, Object>> getAll();
    void insert(HashMap<String, Object> params);
    List<HashMap<String, Object>> getWithUid(int UID);
    List<HashMap<String, Object>> getWithBid(int BID);
    void delete(int ID);
    void update(HashMap<String, Object> params);
    List<HashMap<String, Object>> getWithUsername(String username);
    default HashMap<String, Object> deepCopy(HashMap<String, Object> original) {
        return new HashMap<>(original);
    }
}
