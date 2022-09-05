package object;

public class ItemPL {
    private String pos;
    private String club;
    private String match;
    private String gd;
    private String pts;

    public ItemPL(){
    }

    public ItemPL(String pos, String club, String match, String gd, String pts) {
        this.pos = pos;
        this.club = club;
        this.match = match;
        this.gd = gd;
        this.pts = pts;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getPts() {
        return pts;
    }

    public String getGd() {
        return gd;
    }

    public void setGd(String gd) {
        this.gd = gd;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }
}
