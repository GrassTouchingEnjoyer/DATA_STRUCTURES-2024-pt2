package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loading extends JFrame {
    private JProgressBar progressBar;
    private JButton startButton;

    public loading() {
        setTitle("Loading Bar GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 150);
        setLocationRelativeTo(null);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        startLoading();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(progressBar);


        add(panel);
    }

    public void startLoading() {
        startButton.setEnabled(false);
        Thread loadingThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100); // Simulating a loading process
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setValue(i);
                }
                startButton.setEnabled(true);
            }
        });
        loadingThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                loading loadingGUI = new loading();
                loadingGUI.setVisible(true);
            }
        });
    }
}