package Core;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Serializable {
    public String title;
    public String url;

    public Material(String title){
        this.title = title;
        this.url = "https://yandex.kz/" + title;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material material)) return false;
        return title.equals(material.title) && url.equals(material.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }
}
