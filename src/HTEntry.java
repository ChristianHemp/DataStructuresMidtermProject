public class HTEntry {
    String key;
    Object value;
    boolean deleted;

    public HTEntry(String key, Object value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }
}
