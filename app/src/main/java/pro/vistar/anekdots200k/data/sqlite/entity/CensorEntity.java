package pro.vistar.anekdots200k.data.sqlite.entity;

/**
 * One Censor word item
 */
public class CensorEntity {
    private int id;
    private boolean is_system;
    private String word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean is_system() {
        return is_system;
    }

    public void setIs_system(boolean is_system) {
        this.is_system = is_system;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
