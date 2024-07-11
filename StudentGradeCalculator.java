import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator {
    private JFrame frame;
    private JTextField numSubjectsField;
    private JTextField[] marksFields;
    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel gradeLabel;

    public static void main(String[] args) {
        new StudentGradeCalculator().createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));

        JLabel numSubjectsLabel = new JLabel("Enter number of subjects:");
        numSubjectsField = new JTextField(5);
        inputPanel.add(numSubjectsLabel);
        inputPanel.add(numSubjectsField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        inputPanel.add(new JLabel("")); // empty label to align the button
        inputPanel.add(enterButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int numSubjects = Integer.parseInt(numSubjectsField.getText());
            marksFields = new JTextField[numSubjects];

            JPanel marksPanel = new JPanel();
            marksPanel.setLayout(new GridLayout(0, 2));

            for (int i = 0; i < numSubjects; i++) {
                JLabel label = new JLabel("Enter marks for subject " + (i + 1) + ":");
                JTextField field = new JTextField(5);
                marksFields[i] = field;
                marksPanel.add(label);
                marksPanel.add(field);
            }

            JButton calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(new CalculateButtonListener());
            marksPanel.add(new JLabel("")); // empty label to align the button
            marksPanel.add(calculateButton);

            frame.add(marksPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        }
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] marks = new int[marksFields.length];
            for (int i = 0; i < marksFields.length; i++) {
                marks[i] = Integer.parseInt(marksFields[i].getText());
            }

            int total = 0;
            for (int mark : marks) {
                total += mark;
            }
            double average = calculateAverage(marks);
            String grade = calculateGrade(average);

            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new GridLayout(0, 1));

            totalLabel = new JLabel("Total Marks: " + total);
            averageLabel = new JLabel("Average Percentage: " + String.format("%.2f", average) + "%");
            gradeLabel = new JLabel("Grade: " + grade);

            resultPanel.add(totalLabel);
            resultPanel.add(averageLabel);
            resultPanel.add(gradeLabel);

            frame.add(resultPanel, BorderLayout.SOUTH);
            frame.revalidate();
            frame.repaint();
        }
    }

    private double calculateAverage(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return (double) total / marks.length;
    }

    private String calculateGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}