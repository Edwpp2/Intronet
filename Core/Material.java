package Core;

import java.io.Serializable;

public class Material implements Serializable {
    public String title;
    public String content;
    public String url;

    public Material(String title){
        this.title = title;
        this.url = "https://yandex.kz/" + title;
    }
}
