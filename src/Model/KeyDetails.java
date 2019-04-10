package Model;
public class KeyDetails {
    int keyNO;
    String id;
    Boolean direction;

    public KeyDetails(int no, String id, boolean direction) {
        keyNO = no;
        this.id = id;
        this.direction = direction;
    }

    public int getKeyNO() {
        return keyNO;
    }

    public String getId() {
        return id;
    }

    public Boolean getDirection() {
        return direction;
    }
}
