import java.awt.*;
import java.util.List;
import javax.swing.*;

public final class SuspectSelectionFrame extends JDialog {
    public SuspectSelectionFrame(JFrame owner, List<Suspect> suspects, java.util.function.Consumer<Suspect> onAccuse) {
        super(owner, "Identify the Suspect", true);
        JPanel root = new JPanel(new BorderLayout(14, 14)); root.setBackground(new Color(20, 29, 42)); root.setBorder(BorderFactory.createEmptyBorder(24, 26, 22, 26));
        JLabel title = new JLabel("Who took the prototype?"); title.setForeground(Color.WHITE); title.setFont(new Font("SansSerif", Font.BOLD, 22));
        JLabel hint = new JLabel("Review the evidence, then make your accusation."); hint.setForeground(new Color(153, 169, 189));
        JPanel top = new JPanel(new GridLayout(2, 1, 0, 5)); top.setOpaque(false); top.add(title); top.add(hint); root.add(top, BorderLayout.NORTH);
        JPanel choices = new JPanel(); choices.setOpaque(false); choices.setLayout(new BoxLayout(choices, BoxLayout.Y_AXIS)); ButtonGroup group = new ButtonGroup(); java.util.Map<JRadioButton, Suspect> mapping = new java.util.LinkedHashMap<>();
        for (Suspect suspect : suspects) {
            JRadioButton radio = new JRadioButton(suspect.getName() + "  ·  " + suspect.getRole()); radio.setOpaque(true); radio.setBackground(new Color(31, 43, 59)); radio.setForeground(new Color(230, 236, 244)); radio.setFont(new Font("SansSerif", Font.BOLD, 14)); radio.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14)); radio.setAlignmentX(Component.LEFT_ALIGNMENT); radio.setMaximumSize(new Dimension(500, 48));
            group.add(radio); choices.add(radio); choices.add(Box.createVerticalStrut(8)); mapping.put(radio, suspect);
        }
        root.add(choices, BorderLayout.CENTER);
        JButton accuse = new JButton("Accuse Suspect"); accuse.setBackground(new Color(42, 176, 162)); accuse.setForeground(Color.WHITE); accuse.setFont(new Font("SansSerif", Font.BOLD, 13)); accuse.setFocusPainted(false);
        accuse.addActionListener(e -> { for (java.util.Map.Entry<JRadioButton, Suspect> entry : mapping.entrySet()) if (entry.getKey().isSelected()) { onAccuse.accept(entry.getValue()); dispose(); return; } JOptionPane.showMessageDialog(this, "Select a suspect before making your accusation.", "No suspect selected", JOptionPane.WARNING_MESSAGE); });
        root.add(accuse, BorderLayout.SOUTH); setContentPane(root); setSize(540, 390); setLocationRelativeTo(owner);
    }
}
