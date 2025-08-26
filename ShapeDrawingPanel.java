package shapes.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Custom JPanel to draw selected shape and calculate area or volume.
 */
public class ShapeDrawingPanel extends JPanel {
    private String shape;    // Shape name
    private double d1, d2;   // Dimensions
    private double result;   // Calculated area or volume

    // Set shape type and dimensions, and calculate result
    public void setShapeToDraw(String shape, double d1, double d2) {
        this.shape = shape;
        this.d1 = d1;
        this.d2 = d2;

        // Perform shape-specific area or volume calculation
        switch (shape) {
            case "Circle": result = Math.PI * d1 * d1; break;
            case "Square": result = d1 * d1; break;
            case "Rectangle": result = d1 * d2; break;
            case "Triangle": result = 0.5 * d1 * d2; break;
            case "Sphere": result = (4.0 / 3.0) * Math.PI * Math.pow(d1, 3); break;
            case "Cube": result = Math.pow(d1, 3); break;
            case "Cone": result = (1.0 / 3.0) * Math.PI * d1 * d1 * d2; break;
            case "Cylinder": result = Math.PI * d1 * d1 * d2; break;
            case "Torus": result = (2 * Math.PI * d2) * (2 * Math.PI * d1); break;
        }
    }

    // Return computed result
    public double getResult() {
        return result;
    }

    // Draw shape based on selected type and dimensions
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Drawing: " + shape, 20, 20);
        if (shape == null) return;

        // Basic 2D shape drawing
        switch (shape) {
            case "Circle":
                g.drawOval(100, 100, (int)d1 * 2, (int)d1 * 2);
                break;
            case "Square":
                g.drawRect(100, 100, (int)d1, (int)d1);
                break;
            case "Rectangle":
                g.drawRect(100, 100, (int)d1, (int)d2);
                break;
            case "Triangle":
                g.drawLine(100, 200, 100 + (int)d1, 200);
                g.drawLine(100, 200, 100, 200 - (int)d2);
                g.drawLine(100 + (int)d1, 200, 100, 200 - (int)d2);
                break;

            // Simple 3D shape sketches
            case "Sphere":
                g.drawOval(100, 100, 100, 100);            // Outer circle
                g.drawOval(125, 100, 50, 100);             // Inner ellipse
                g.drawString("(3D Sphere)", 110, 95);
                break;

            case "Cube":
                g.drawRect(100, 100, 80, 80);              // Front face
                g.drawRect(120, 120, 80, 80);              // Back face
                g.drawLine(100, 100, 120, 120);
                g.drawLine(180, 100, 200, 120);
                g.drawLine(100, 180, 120, 200);
                g.drawLine(180, 180, 200, 200);
                g.drawString("(3D Cube)", 110, 95);
                break;

            case "Cone":
                g.drawOval(100, 180, 80, 20);              // Base ellipse
                g.drawLine(100, 190, 140, 100);            // Left side
                g.drawLine(180, 190, 140, 100);            // Right side
                g.drawString("(3D Cone)", 110, 95);
                break;

            case "Cylinder":
                g.drawOval(100, 100, 80, 20);              // Top ellipse
                g.drawLine(100, 110, 100, 190);            // Left edge
                g.drawLine(180, 110, 180, 190);            // Right edge
                g.drawOval(100, 180, 80, 20);              // Bottom ellipse
                g.drawString("(3D Cylinder)", 110, 95);
                break;

            case "Torus":
                g.drawOval(100, 100, 100, 100);            // Outer ring
                g.drawOval(125, 125, 50, 50);              // Inner ring
                g.drawString("(3D Torus)", 110, 95);
                break;
        }
    }
}
