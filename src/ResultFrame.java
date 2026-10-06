import java.awt.*;
import javax.swing.*;

public final class ResultFrame extends JDialog {
    public ResultFrame(JFrame owner, boolean success, String explanation, Runnable reviewEvidence) {
        super(owner, success ? "Case Solved" : "Case Failed", true);
        JPanel root = new JPanel(new BorderLayout(14, 14)); root.setBackground(new Color(20, 29, 42)); root.setBorder(BorderFactory.createEmptyBorder(28, 30, 24, 30));
        JLabel title = new JLabel(success ? "CASE SOLVED!" : "CASE FAILED!"); title.setForeground(success ? new Color(80, 220, 171) : new Color(255, 132, 119)); title.setFont(new Font("SansSerif", Font.BOLD, 27));
        JTextArea body = new JTextArea(explanation); body.setWrapStyleWord(true); body.setLineWrap(true); body.setEditable(false); body.setOpaque(false); body.setForeground(new Color(193, 205, 220)); body.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 9, 0)); actions.setOpaque(false);
        JButton review = new JButton("Review Evidence"); review.addActionListener(e -> { dispose(); reviewEvidence.run(); });
        JButton done = new JButton("Return to Case"); done.addActionListener(e -> dispose()); actions.add(review); actions.add(done);
        root.add(title, BorderLayout.NORTH); root.add(body, BorderLayout.CENTER); root.add(actions, BorderLayout.SOUTH);
        setContentPane(root); setSize(510, 250); setLocationRelativeTo(owner);
    }
}
