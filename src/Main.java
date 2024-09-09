import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField subjectCountField;
    private JPanel courseInputsPanel;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JTextField[] hourFields;
    private JTextField[] pointFields;

    public Main() {
        // Setting up the GUI window
        setTitle("GPA Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Application title
        JLabel title = new JLabel("حساب المعدل التراكمي", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setOpaque(true);
        title.setBackground(new Color(70, 130, 180));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Subject count input
        JPanel subjectCountPanel = new JPanel();
        JLabel subjectCountLabel = new JLabel("أدخل عدد المواد: ");
        subjectCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subjectCountField = new JTextField(5);
        subjectCountField.setFont(new Font("Arial", Font.PLAIN, 16));
        subjectCountPanel.add(subjectCountLabel);
        subjectCountPanel.add(subjectCountField);
        add(subjectCountPanel, BorderLayout.NORTH);

        // Panel to hold course input fields
        courseInputsPanel = new JPanel();
        courseInputsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(courseInputsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel to hold calculate button and result label
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Calculate button
        calculateButton = new JButton("احسب المعدل التراكمي");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(30, 144, 255));
        calculateButton.setForeground(Color.WHITE);
        bottomPanel.add(calculateButton, BorderLayout.NORTH);

        // Result label
        resultLabel = new JLabel("المعدل التراكمي: ", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(new Color(25, 25, 112));
        bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event listener for the subject count input
        subjectCountField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCourseInputFields();
            }
        });

        // Event listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGPA();
            }
        });
    }

    private void createCourseInputFields() {
        try {
            int subjectCount = Integer.parseInt(subjectCountField.getText());
            courseInputsPanel.removeAll(); // Clear previous input fields if any

            hourFields = new JTextField[subjectCount];
            pointFields = new JTextField[subjectCount];

            for (int i = 0; i < subjectCount; i++) {
                JLabel hourLabel = new JLabel("عدد الساعات لمادة " + (i + 1) + ": ");
                hourFields[i] = new JTextField(5);

                JLabel pointLabel = new JLabel("النقاط لمادة " + (i + 1) + ": ");
                pointFields[i] = new JTextField(5);

                courseInputsPanel.add(hourLabel);
                courseInputsPanel.add(hourFields[i]);
                courseInputsPanel.add(pointLabel);
                courseInputsPanel.add(pointFields[i]);
            }

            // Refresh the panel to show new fields
            courseInputsPanel.revalidate();
            courseInputsPanel.repaint();

        } catch (NumberFormatException ex) {
            resultLabel.setText("يرجى إدخال عدد صحيح للمواد.");
        }
    }

    private void calculateGPA() {
        try {
            int subjectCount = Integer.parseInt(subjectCountField.getText());

            double totalPoints = 0;
            int totalHours = 0;

            for (int i = 0; i < subjectCount; i++) {
                int hours = Integer.parseInt(hourFields[i].getText());
                double points = Double.parseDouble(pointFields[i].getText());

                totalPoints += points * hours;
                totalHours += hours;
            }

            double gpa = totalPoints / totalHours;
            resultLabel.setText(String.format("المعدل التراكمي الخاص بك هو: %.2f", gpa));
        } catch (Exception ex) {
            resultLabel.setText("يرجى التأكد من إدخال البيانات بشكل صحيح.");
        }
    }

    public static void main(String[] args) {
        Main window = new Main();
        window.setVisible(true);
    }
}
