package checker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Checker extends JFrame {

    private JPanel mainPanel;
    private JButton submitButton;
    private JButton addStudentButton;
    private JTextField studentNameField;
    private JPanel studentListPanel;
    private HashMap<String, JCheckBox> studentCheckBoxes;

    public Checker() {
        setTitle("Attendance Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null); // Center the window

        mainPanel = new JPanel(new BorderLayout());

        // Student list panel (scrollable)
        studentListPanel = new JPanel();
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(studentListPanel);

        studentCheckBoxes = new HashMap<>();

        // Initial students
        String[] students = {"Jhonlord", "Kenneth", "Hyacinth"};
        for (String student : students) {
            addStudentToPanel(student);
        }

        // Bottom panel (add field + buttons)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        studentNameField = new JTextField();
        studentNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new AddStudentListener());

        submitButton = new JButton("Submit Attendance");
        submitButton.addActionListener(new SubmitListener());

        bottomPanel.add(new JLabel("New Student Name:"));
        bottomPanel.add(studentNameField);
        bottomPanel.add(addStudentButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(submitButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void addStudentToPanel(String name) {
        if (!studentCheckBoxes.containsKey(name)) {
            JCheckBox checkBox = new JCheckBox(name);
            studentCheckBoxes.put(name, checkBox);
            studentListPanel.add(checkBox);
            studentListPanel.revalidate(); // Refresh layout
            studentListPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Student already exists.");
        }
    }

    private class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = studentNameField.getText().trim();
            if (!name.isEmpty()) {
                addStudentToPanel(name);
                studentNameField.setText(""); // Clear input
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a student name.");
            }
        }
    }

    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Attendance Record:");
            for (String student : studentCheckBoxes.keySet()) {
                boolean present = studentCheckBoxes.get(student).isSelected();
                System.out.println(student + ": " + (present ? "Present" : "Absent"));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Checker());
    }
}

