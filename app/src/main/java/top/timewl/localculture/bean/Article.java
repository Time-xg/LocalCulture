package top.timewl.localculture.bean;

public class Article {
    private int id;
    private String author;
    private String content;
    private String image;
    private String title;
    private String creat_time;
    private String update_time;

    public Article(){

    }
    public Article(int id, String author, String content, String image, String title, String creat_time, String update_time) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.image = image;
        this.title = title;
        this.creat_time = creat_time;
        this.update_time = update_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public String getUpdate_time() {
        return update_time;
    }
}
