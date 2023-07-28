package Core;

public class Material {
    public String title;
    public String content;
    public String url;

    public Material(String title){
        this.title = title;
        this.url = "https://yandex.kz/" + title;
    }
}
