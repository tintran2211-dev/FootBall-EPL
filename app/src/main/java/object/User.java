package object;
//tạo một lớp user
public class User {
    private String sourceID;
    private String name;
    private String detailUrl;

    public User(){ }

    public User(String sourceID, String name, String detailUrl) {
        this.sourceID = sourceID;
        this.name = name;
        this.detailUrl = detailUrl;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
