package Model;

public class SinhVien {
    private int ID;
    private String FullName;
    private float Mark;
    private  long Date;
    private String image;
    public SinhVien(Integer fullname, String mark){

    }
    public SinhVien(int ID , String FullName, float Mark, long Date, String image){
        this.ID = ID;
        this.FullName=FullName;
        this.Mark = Mark;
        this.Date = Date;
        this.image = image;
    }
    public SinhVien(int ID , String FullName, float Mark, long Date){
        this.ID = ID;
        this.FullName=FullName;
        this.Mark = Mark;
        this.Date = Date;

    }

    public SinhVien(String FullName, float Mark, long Date,String image) {
        this.FullName=FullName;
        this.Mark = Mark;
        this.Date = Date;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public String getFullName() {
        return FullName;
    }

    public float getMark() {return  Mark; }

    public long getDate() {return Date;}

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setMark(float mark){
        Mark = mark;
    }
    public void setDate(long date){
        Date = date;
    }
}
