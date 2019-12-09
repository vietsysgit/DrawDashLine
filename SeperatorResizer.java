/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelResizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SeperatorResizer extends JFrame {

    private JPanel mainPanel = new JPanel();

    ResizablePanel firstPanel;
    ResizablePanel secondPanel;
    ResizablePanel thirdPanel;

    // inner class DragBar: bar to drag&drop for panel resize
    class DragBar extends JSeparator
            implements MouseListener,
            MouseMotionListener {

        private Point pointPressed;
        private Point pointReleased;
        private JSeparator seperator;

        public DragBar() {
            creeatDragBar(this);
        }

        public void creeatDragBar(JSeparator seperator) {
            this.seperator = seperator;

            this.seperator.setBackground(Color.red);
            this.seperator.setOpaque(true);
            this.seperator.setPreferredSize(new Dimension(5, 10));
            this.seperator.setVisible(true);
            this.seperator.addMouseListener(this);
            this.seperator.addMouseMotionListener(this);
        }

        private void updateCursor(boolean on) {
            if (on) {
                setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            } else {
                setCursor(null);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            pointPressed = e.getLocationOnScreen();
            System.out.println(pointPressed);
            updateCursor(true);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
            mouseReleased(e);
            pointPressed = e.getLocationOnScreen();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pointReleased = e.getLocationOnScreen();
            int deltaY = pointReleased.y - pointPressed.y;
            
            System.out.println("deltaY: "+deltaY);

            Point sepLocation = seperator.getLocation();
            Dimension size = seperator.getSize();
            size.height += deltaY;

            int yy = firstPanel.getHeight()+deltaY;
            
            if (yy < 50) {
                yy = 50;
            }
                
            seperator.setBounds(sepLocation.x, yy, size.width, 10);
            firstPanel.setBounds(firstPanel.getLocation().x, firstPanel.getLocation().y, firstPanel.getWidth(),yy);
            firstPanel.setPreferredSize(new Dimension(firstPanel.getWidth(), yy));
            pointPressed = null;
            pointReleased = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            updateCursor(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }//end: inner class DragBar

    //inner class ResizablePanel
    class ResizablePanel extends JPanel {

        public ResizablePanel() {
            setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
            setMinimumSize(new java.awt.Dimension(300, 80));
            setPreferredSize(new java.awt.Dimension(300, 130));
            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);
            this.setVisible(true);
            JLabel myLabel = new JLabel("Test label");
            myLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(myLabel, BorderLayout.CENTER);
        }
    }// end inner class ResizablePanel

    public SeperatorResizer() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(100, 200));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        firstPanel = new ResizablePanel();
        secondPanel = new ResizablePanel();
        thirdPanel = new ResizablePanel();

        DragBar firstBar = new DragBar();
        DragBar secondBar = new DragBar();

        mainPanel.add(firstPanel);
        mainPanel.add(firstBar);
        mainPanel.add(secondPanel);
        mainPanel.add(secondBar);
        mainPanel.add(thirdPanel);
        getContentPane().add(mainPanel);
        pack();
    }

    public static void main(String args[]) {
        new SeperatorResizer().setVisible(true);
    }
}
