import java.awt.*;
import java.util.List;
import javax.swing.*;

public final class EvidenceFrame extends JDialog {
    public EvidenceFrame(JFrame owner, List<Clue> clues, int total) {
        super(owner, "Evidence Room", true);
        JPanel root = new JPanel(new BorderLayout(14, 14)); root.setBackground(new Color(20, 29, 42)); root.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));
        JLabel title = new JLabel("Evidence Room"); title.setForeground(Color.WHITE); title.setFont(new Font("SansSerif", Font.BOLD, 23));
        JLabel sub = new JLabel(clues.size() + " of " + total + " evidence items secured"); sub.setForeground(new Color(150, 167, 188)); sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JPanel head = new JPanel(new GridLayout(2, 1, 0, 5)); head.setOpaque(false); head.add(title); head.add(sub); root.add(head, BorderLayout.NORTH);
        DefaultListModel<String> model = new DefaultListModel<>();
        if (clues.isEmpty()) model.addElement("No evidence secured yet. Investigate a location to begin.");
        else for (Clue clue : clues) model.addElement("  " + clue.getTitle() + "   ·   " + clue.getLocation() + "\n      " + clue.getDetail());
        JList<String> list = new JList<>(model); list.setBackground(new Color(27, 38, 53)); list.setForeground(new Color(220, 229, 239)); list.setFont(new Font("SansSerif", Font.PLAIN, 13)); list.setFixedCellHeight(68); list.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
        JScrollPane scroll = new JScrollPane(list); scroll.setBorder(BorderFactory.createLineBorder(new Color(49, 65, 84))); scroll.getViewport().setBackground(new Color(27, 38, 53)); root.add(scroll, BorderLayout.CENTER);
        JButton close = new JButton("Close"); close.addActionListener(e -> dispose()); close.setFocusPainted(false); root.add(close, BorderLayout.SOUTH);
        setContentPane(root); setSize(620, 400); setLocationRelativeTo(owner);
    }
}
