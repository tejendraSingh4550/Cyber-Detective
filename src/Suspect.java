public final class Suspect {
    private final String name;
    private final String role;
    private final String description;

    public Suspect(String name, String role, String description) {
        this.name = name;
        this.role = role;
        this.description = description;
    }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getDescription() { return description; }
    @Override public String toString() { return name + " — " + role; }
}
