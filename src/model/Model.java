package model;

import java.util.HashMap;
import java.util.List;

public interface Model {

    public List<HashMap<String, Object>> getAll();
    public void insert(HashMap<String, Object> params);
    public List<HashMap<String, Object>> getWithUid(int UID);
    public List<HashMap<String, Object>> getWithBid(int BID);
    public void delete(int ID);
    public void update(HashMap<String, Object> params);
    public List<HashMap<String, Object>> getWithUsername(String username);
    default HashMap<String, Object> deepCopy(HashMap<String, Object> original) {
        return new HashMap<>(original);
    }
}