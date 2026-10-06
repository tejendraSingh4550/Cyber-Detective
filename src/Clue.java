public final class Clue {
    private final String id;
    private final String title;
    private final String detail;
    private final String location;

    public Clue(String id, String title, String detail, String location) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.location = location;
    }
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDetail() { return detail; }
    public String getLocation() { return location; }
    @Override public String toString() { return title + " — " + detail; }
}
