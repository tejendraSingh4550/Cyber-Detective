import java.util.*;

public final class EvidenceManager {
    private final LinkedHashMap<String, Clue> found = new LinkedHashMap<>();
    public boolean add(Clue clue) { return clue != null && found.putIfAbsent(clue.getId(), clue) == null; }
    public List<Clue> getClues() { return Collections.unmodifiableList(new ArrayList<>(found.values())); }
    public int size() { return found.size(); }
    public boolean hasAll(int count) { return size() >= count; }
    public void clear() { found.clear(); }
}
