package myeducile.project.ayush.tutor;

public class Product {


    private int id;
    private String title;

    private String image;

    public Product(String title, String image) {

        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getImage() {
        return image;
    }

}
