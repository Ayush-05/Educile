package myeducile.project.ayush.tutor;

public class Mybukings {


    private String name;
    private String desc;

    private String title;


    public Mybukings(String name, String desc, String title) {
        this.name = name;
        this.desc = desc;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
