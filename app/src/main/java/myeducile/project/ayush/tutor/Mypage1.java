package myeducile.project.ayush.tutor;

public class Mypage1 {
        private String id;
        private String title;
        private String shortdesc;
        private String rating;
        private String price1;
        private String price2;
        private int image1;
        private int image2;
        private String cost;


        public Mypage1(String id, String title, String shortdesc, String rating, String price1,String price2, int image1,int image2,String cost) {
            this.id = id;
            this.title = title;
            this.shortdesc = shortdesc;
            this.rating = rating;
            this.price1 = price1;
            this.price2=price2;
            this.image1 = image1;
            this.image2=image2;
            this.cost=cost;

        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getShortdesc() {
            return shortdesc;
        }

        public String getRating() {
            return rating;
        }

        public String getPrice1() {
            return price1;
        }
    public String getPrice2() {
        return price2;
    }

        public int getImage1() {
            return image1;
        }
    public int getImage2() {
        return image2;
    }
    public String getCost() {
        return cost;
    }




}


