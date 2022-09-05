package object;

public class ItemLive {
    private String team1,team2;
    private String img1,img2;
    private String time;

    public ItemLive(){}

    public ItemLive(String team1, String team2, String img1, String img2, String time) {
        this.team1 = team1;
        this.team2 = team2;
        this.img1 = img1;
        this.img2 = img2;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

}
