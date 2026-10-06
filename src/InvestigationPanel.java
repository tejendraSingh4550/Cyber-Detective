import java.awt.*;
import java.util.List;
import javax.swing.*;

public final class InvestigationPanel extends JDialog {
    public InvestigationPanel(JFrame owner, String location, List<Clue> clues) {
        super(owner, "Investigation · " + location, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBorder(BorderFactory.createEmptyBorder(24, 26, 22, 26));
        root.setBackground(new Color(20, 29, 42));
        JLabel eyebrow = new JLabel("FIELD INVESTIGATION  /  " + location.toUpperCase());
        eyebrow.setForeground(new Color(70, 203, 190)); eyebrow.setFont(new Font("SansSerif", Font.BOLD, 11));
        JLabel title = new JLabel("Evidence secured"); title.setForeground(Color.WHITE); title.setFont(new Font("SansSerif", Font.BOLD, 23));
        JPanel header = new JPanel(new GridLayout(2, 1, 0, 5)); header.setOpaque(false); header.add(eyebrow); header.add(title);
        JPanel entries = new JPanel(); entries.setOpaque(false); entries.setLayout(new BoxLayout(entries, BoxLayout.Y_AXIS));
        for (Clue clue : clues) {
            JPanel card = new JPanel(new BorderLayout(8, 5)); card.setBackground(new Color(31, 43, 59));
            card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(53, 69, 89)), BorderFactory.createEmptyBorder(13, 15, 13, 15)));
            JLabel name = new JLabel(clue.getTitle()); name.setForeground(Color.WHITE); name.setFont(new Font("SansSerif", Font.BOLD, 14));
            JTextArea desc = new JTextArea(clue.getDetail()); desc.setWrapStyleWord(true); desc.setLineWrap(true); desc.setEditable(false); desc.setOpaque(false); desc.setForeground(new Color(181, 194, 211)); desc.setFont(new Font("SansSerif", Font.PLAIN, 13));
            card.add(name, BorderLayout.NORTH); card.add(desc, BorderLayout.CENTER); card.setMaximumSize(new Dimension(480, 100));
            entries.add(card); entries.add(Box.createVerticalStrut(10));
        }
        JButton close = new JButton("Continue investigation"); close.setFocusPainted(false); close.setBackground(new Color(42, 176, 162)); close.setForeground(Color.WHITE); close.setFont(new Font("SansSerif", Font.BOLD, 13)); close.addActionListener(e -> dispose());
        root.add(header, BorderLayout.NORTH); root.add(entries, BorderLayout.CENTER); root.add(close, BorderLayout.SOUTH);
        setContentPane(root); pack(); setSize(520, Math.max(240, 180 + clues.size() * 112)); setLocationRelativeTo(owner);
    }
}
