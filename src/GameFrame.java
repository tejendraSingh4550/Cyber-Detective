import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public final class GameFrame extends JFrame {
    private static final int TOTAL_CLUES = 5;
    private final Case gameCase = new Case();
    private final EvidenceManager evidence = new EvidenceManager();
    private final Map<String, java.util.List<Clue>> locationClues = new LinkedHashMap<>();
    private final Map<String, JButton> locationButtons = new LinkedHashMap<>();
    private final JLabel countLabel = new JLabel();
    private boolean caseSolved;

    public GameFrame() {
        super("CYBER DETECTIVE · The Missing Prototype");
        buildClues(); buildUi();
        setDefaultCloseOperation(EXIT_ON_CLOSE); setMinimumSize(new Dimension(900, 660)); setSize(1060, 760); setLocationRelativeTo(null);
    }
    private void buildClues() {
        addLocation("Office", new Clue("office-card", "Access card trace", "An employee access card was used at 10:41 PM, one minute before the security system went offline.", "Office"));
        addLocation("Computer Room", new Clue("computer-log", "Deleted project file", "A system audit log shows the prototype's tracking file was deleted from an administrator session at 10:39 PM.", "Computer Room"));
        addLocation("Parking Area", new Clue("parking-car", "Unscheduled departure", "A vehicle left the east gate at 10:45 PM. The plate is registered to a company systems administrator.", "Parking Area"));
        addLocation("Apartment", new Clue("apartment-cctv", "CCTV still", "A recovered camera frame shows a person in a blue jacket carrying a hard case toward the staff exit.", "Apartment"));
        locationClues.get("Office").add(new Clue("office-security", "Security override", "The building security system was disabled at 10:42 PM using a privileged maintenance override.", "Office"));
    }
    private void addLocation(String name, Clue clue) { locationClues.computeIfAbsent(name, ignored -> new ArrayList<>()).add(clue); }
    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(24, 22)); root.setBackground(new Color(13, 20, 31)); root.setBorder(new EmptyBorder(26, 34, 24, 34)); setContentPane(root);
        JPanel header = new JPanel(new BorderLayout()); header.setOpaque(false);
        JPanel brand = new JPanel(); brand.setOpaque(false); brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));
        JLabel kicker = label("INVESTIGATION DIVISION  /  CASE FILE 047", new Color(66, 204, 190), 11, Font.BOLD);
        JLabel title = label("CYBER DETECTIVE", Color.WHITE, 30, Font.BOLD);
        JLabel subtitle = label(gameCase.getTitle(), new Color(166, 183, 204), 17, Font.PLAIN);
        brand.add(kicker); brand.add(Box.createVerticalStrut(7)); brand.add(title); brand.add(Box.createVerticalStrut(3)); brand.add(subtitle);
        JPanel countCard = card(new BorderLayout(8, 4)); countCard.setPreferredSize(new Dimension(178, 82)); countCard.setBorder(new EmptyBorder(15, 18, 15, 18)); countLabel.setForeground(Color.WHITE); countLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); countCard.add(countLabel, BorderLayout.CENTER); JLabel small = label("EVIDENCE FOUND", new Color(139, 158, 180), 10, Font.BOLD); countCard.add(small, BorderLayout.NORTH);
        header.add(brand, BorderLayout.WEST); header.add(countCard, BorderLayout.EAST); root.add(header, BorderLayout.NORTH);
        JPanel center = new JPanel(new BorderLayout(0, 17)); center.setOpaque(false);
        JPanel summary = card(new BorderLayout()); summary.setBorder(new EmptyBorder(17, 20, 17, 20));
        JLabel intro = label("THE CASE", new Color(67, 204, 190), 10, Font.BOLD);
        JTextArea description = new JTextArea("A valuable technology prototype vanished from the Vantage Labs office during the night shift. The access logs have gaps, the camera archive is incomplete, and someone knew exactly where to hide their tracks. Investigate every location, connect the evidence, and identify the person responsible.");
        description.setWrapStyleWord(true); description.setLineWrap(true); description.setEditable(false); description.setOpaque(false); description.setForeground(new Color(196, 207, 221)); description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JPanel sum = new JPanel(new BorderLayout(0, 9)); sum.setOpaque(false); sum.add(intro, BorderLayout.NORTH); sum.add(description, BorderLayout.CENTER); summary.add(sum);
        JPanel lower = new JPanel(new GridLayout(1, 2, 18, 0)); lower.setOpaque(false);
        JPanel locations = card(new BorderLayout(0, 14)); locations.setBorder(new EmptyBorder(18, 20, 18, 20));
        JPanel locHead = new JPanel(new BorderLayout()); locHead.setOpaque(false); locHead.add(label("INVESTIGATION LOCATIONS", Color.WHITE, 14, Font.BOLD), BorderLayout.WEST); locHead.add(label("4 SECTORS", new Color(127, 148, 172), 10, Font.BOLD), BorderLayout.EAST); locations.add(locHead, BorderLayout.NORTH);
        JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10)); grid.setOpaque(false);
        addLocationButton(grid, "Office", "01   Office"); addLocationButton(grid, "Computer Room", "02   Computer Room"); addLocationButton(grid, "Parking Area", "03   Parking Area"); addLocationButton(grid, "Apartment", "04   Apartment"); locations.add(grid, BorderLayout.CENTER);
        lower.add(locations);
        JPanel actions = card(new BorderLayout(0, 14)); actions.setBorder(new EmptyBorder(18, 20, 18, 20)); actions.add(label("CASE TOOLS", Color.WHITE, 14, Font.BOLD), BorderLayout.NORTH);
        JPanel actionGrid = new JPanel(new GridLayout(3, 1, 0, 10)); actionGrid.setOpaque(false);
        JButton view = actionButton("◈   View Evidence", new Color(40, 55, 74)); view.addActionListener(e -> new EvidenceFrame(this, evidence.getClues(), TOTAL_CLUES).setVisible(true));
        JButton solve = actionButton("⚑   Solve Case", new Color(32, 133, 128)); solve.addActionListener(e -> solveCase());
        JButton restart = actionButton("↻   Restart Case", new Color(40, 55, 74)); restart.addActionListener(e -> restartCase());
        actionGrid.add(view); actionGrid.add(solve); actionGrid.add(restart); actions.add(actionGrid, BorderLayout.CENTER); lower.add(actions);
        center.add(summary, BorderLayout.NORTH); center.add(lower, BorderLayout.CENTER); root.add(center, BorderLayout.CENTER);
        JPanel footer = new JPanel(new BorderLayout()); footer.setOpaque(false); JLabel note = label("FIELD NOTES  •  Collect all five clues before making an accusation.", new Color(119, 139, 163), 11, Font.PLAIN); JButton exit = new JButton("Exit Game"); styleSecondary(exit); exit.addActionListener(e -> { int answer = JOptionPane.showConfirmDialog(this, "Exit the investigation?", "Exit Game", JOptionPane.YES_NO_OPTION); if (answer == JOptionPane.YES_OPTION) dispose(); }); footer.add(note, BorderLayout.WEST); footer.add(exit, BorderLayout.EAST); root.add(footer, BorderLayout.SOUTH);
        updateCount();
    }
    private void addLocationButton(JPanel parent, String key, String text) {
        JButton button = actionButton(text, new Color(31, 44, 61)); button.setHorizontalAlignment(SwingConstants.LEFT); button.addActionListener(e -> investigate(key)); locationButtons.put(key, button); parent.add(button);
    }
    private void investigate(String location) {
        if (!locationClues.containsKey(location) || locationButtons.get(location).isEnabled() == false) return;
        java.util.List<Clue> discovered = locationClues.get(location);
        for (Clue clue : discovered) evidence.add(clue);
        JButton button = locationButtons.get(location); button.setEnabled(false); button.setText("✓   " + location + "  ·  SECURED"); button.setBackground(new Color(35, 80, 76));
        updateCount(); new InvestigationPanel(this, location, discovered).setVisible(true);
        JOptionPane.showMessageDialog(this, discovered.size() > 1 ? "Location secured. " + discovered.size() + " clues added to evidence." : "Location secured. Clue added to evidence.", "Evidence secured", JOptionPane.INFORMATION_MESSAGE);
    }
    private void solveCase() {
        if (!evidence.hasAll(TOTAL_CLUES)) { JOptionPane.showMessageDialog(this, "You need to investigate all locations and collect all evidence before solving the case.", "Investigation incomplete", JOptionPane.WARNING_MESSAGE); return; }
        if (caseSolved) { JOptionPane.showMessageDialog(this, "This case is already solved. Restart the case to play again.", "Case closed", JOptionPane.INFORMATION_MESSAGE); return; }
        new SuspectSelectionFrame(this, gameCase.getSuspects(), this::showResult).setVisible(true);
    }
    private void showResult(Suspect selected) {
        boolean correct = selected != null && selected.getName().equals(gameCase.getCulprit().getName()); caseSolved = correct;
        String explanation = correct ? "The access card and privileged override point to someone who could manipulate building systems. The tracking file was deleted from an administrator session, and the getaway vehicle was registered to Neeraj Kapoor. The timeline links the system access, deletion, and departure. Neeraj Kapoor is the only suspect who fits the evidence." : "Your accusation does not fit the complete evidence trail. The access card, privileged override, deleted tracking file, and vehicle record still need to be considered together. Return to the evidence room and review the timeline before restarting the case.";
        new ResultFrame(this, correct, explanation, () -> new EvidenceFrame(this, evidence.getClues(), TOTAL_CLUES).setVisible(true)).setVisible(true);
    }
    private void restartCase() {
        evidence.clear(); caseSolved = false;
        for (Map.Entry<String, JButton> entry : locationButtons.entrySet()) { entry.getValue().setEnabled(true); entry.getValue().setText(String.format("%02d   %s", new ArrayList<>(locationButtons.keySet()).indexOf(entry.getKey()) + 1, entry.getKey())); entry.getValue().setBackground(new Color(31, 44, 61)); }
        updateCount();
    }
    private void updateCount() { countLabel.setText(evidence.size() + " / " + TOTAL_CLUES); }
    private JPanel card(LayoutManager layout) { JPanel panel = new JPanel(layout); panel.setBackground(new Color(22, 32, 46)); panel.setBorder(BorderFactory.createLineBorder(new Color(39, 55, 74))); return panel; }
    private JLabel label(String text, Color color, int size, int style) { JLabel label = new JLabel(text); label.setForeground(color); label.setFont(new Font("SansSerif", style, size)); return label; }
    private JButton actionButton(String text, Color color) { JButton button = new JButton(text); button.setFocusPainted(false); button.setForeground(new Color(229, 236, 244)); button.setBackground(color); button.setFont(new Font("SansSerif", Font.BOLD, 12)); button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(54, 71, 92)), new EmptyBorder(10, 13, 10, 13))); button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); return button; }
    private void styleSecondary(JButton button) { button.setFocusPainted(false); button.setForeground(new Color(189, 201, 216)); button.setBackground(new Color(30, 42, 58)); button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(54, 71, 92)), new EmptyBorder(7, 13, 7, 13))); }
}
