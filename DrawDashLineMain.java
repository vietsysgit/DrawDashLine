/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Draws;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author brian
 */
public class DrawDashLineMain extends JFrame {

    public DrawDashLineMain() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Basic strokes");
        setSize(280, 270);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                DrawDashLineMain ex = new DrawDashLineMain();
                ex.setVisible(true);
            }
        });
    }
}
