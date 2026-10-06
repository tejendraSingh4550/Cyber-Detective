import java.util.*;

public final class Case {
    private final String title = "The Missing Prototype";
    private final List<Suspect> suspects;
    private final Suspect culprit;

    public Case() {
        suspects = Collections.unmodifiableList(Arrays.asList(
            new Suspect("Rahul Sharma", "Software Engineer", "Built a key component of the prototype and stayed late to finish a patch."),
            new Suspect("Aman Verma", "Security Officer", "Managed the night shift and had routine access to the building controls."),
            new Suspect("Vikram Mehta", "Project Manager", "Oversaw the prototype schedule and had a meeting off-site that evening."),
            new Suspect("Neeraj Kapoor", "System Administrator", "Maintained access systems and workstation logs across the office.")
        ));
        culprit = suspects.get(3);
    }
    public String getTitle() { return title; }
    public List<Suspect> getSuspects() { return suspects; }
    public Suspect getCulprit() { return culprit; }
}
