package shapes.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ShapeViewerFrame.java
 * Author: Tate Prodigalidad
 * Date: 2025-07-06
 *
 * Purpose:
 * This GUI application allows the user to select a 2D or 3D shape,
 * enter dimensions, then display the shape in a window.
 * The program calculates and displays the shape's area (2D) or volume (3D).
 */

public class ShapeViewerFrame extends JFrame {

    // GUI components
    private JComboBox<String> shapeSelector;       // Drop-down menu for selecting shape
    private JTextField dimension1Field;            // First dimension input
    private JTextField dimension2Field;            // Second dimension input
    private JLabel resultLabel;                    // Label to show computed result
    private ShapeDrawingPanel drawingPanel;        // Custom panel to draw shapes

    /**
     * Constructor: builds the frame, sets up layout and components.
     */
    public ShapeViewerFrame() {
        setTitle("Shape Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Create and configure top input panel
        JPanel inputPanel = new JPanel(new FlowLayout());

        // Drop-down for shape selection
        shapeSelector = new JComboBox<>(new String[] {
            "Circle", "Square", "Rectangle", "Triangle",
            "Sphere", "Cube", "Cone", "Cylinder", "Torus"
        });

        // Input fields for dimensions
        dimension1Field = new JTextField(5);
        dimension2Field = new JTextField(5);
        JButton drawButton = new JButton("Draw");

        // Add all components to input panel
        inputPanel.add(new JLabel("Shape:"));
        inputPanel.add(shapeSelector);
        inputPanel.add(new JLabel("Dimension 1:"));
        inputPanel.add(dimension1Field);
        inputPanel.add(new JLabel("Dimension 2:"));
        inputPanel.add(dimension2Field);
        inputPanel.add(drawButton);

        // Label at bottom to show result
        resultLabel = new JLabel("Area/Volume: ");

        // Custom drawing panel in center
        drawingPanel = new ShapeDrawingPanel();

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // Add listener for "Draw" button
        drawButton.addActionListener(e -> drawSelectedShape());
    }

    /**
     * Called when "Draw" button is clicked.
     * Parses inputs, passes them to the drawing panel, repaints panel, and updates result label.
     */
    private void drawSelectedShape() {
        try {
            String shape = (String) shapeSelector.getSelectedItem();
            double d1 = Double.parseDouble(dimension1Field.getText());
            double d2 = dimension2Field.getText().isEmpty() ? 0 : Double.parseDouble(dimension2Field.getText());

            // Pass values to drawing panel
            drawingPanel.setShapeToDraw(shape, d1, d2);
            double result = drawingPanel.getResult();
            resultLabel.setText("Area/Volume: " + result);

            // Trigger repaint
            drawingPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for dimensions.");
        }
    }

    /**
     * Entry point: starts the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeViewerFrame().setVisible(true));
    }
}
