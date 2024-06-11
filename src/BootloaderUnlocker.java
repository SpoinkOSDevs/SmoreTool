import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BootloaderUnlocker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bootloader Unlocker");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JTextArea terminal = new JTextArea();
        terminal.setBackground(Color.BLACK);
        terminal.setForeground(Color.WHITE);
        terminal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        terminal.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JButton unlockBootloaderButton = new JButton("Unlock Bootloader");
        unlockBootloaderButton.setBackground(new Color(135, 206, 250)); // Light Blue color
        unlockBootloaderButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        unlockBootloaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process process = Runtime.getRuntime().exec("adb reboot bootloader");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        terminal.append(line + "\n");
                    }
                    process.waitFor();
                    terminal.append("Bootloader unlocked successfully!\n");
                } catch (Exception ex) {
                    terminal.append("Error unlocking bootloader: " + ex.getMessage() + "\n");
                }
            }
        });

        JButton flashButton = new JButton("Flash");
        flashButton.setBackground(new Color(50, 205, 50)); // Lime Green color
        flashButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        flashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process process = Runtime.getRuntime().exec("flashing_tool flash partition_name file_name");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        terminal.append(line + "\n");
                    }
                    process.waitFor();
                    terminal.append("Flashing completed successfully!\n");
                } catch (Exception ex) {
                    terminal.append("Error flashing: " + ex.getMessage() + "\n");
                }
            }
        });

        JButton rebootRecoveryButton = new JButton("Reboot to Recovery");
        rebootRecoveryButton.setBackground(new Color(255, 165, 0)); // Orange color
        rebootRecoveryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        rebootRecoveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process process = Runtime.getRuntime().exec("adb reboot recovery");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        terminal.append(line + "\n");
                    }
                    process.waitFor();
                    terminal.append("Rebooted to Recovery successfully!\n");
                } catch (Exception ex) {
                    terminal.append("Error rebooting to Recovery: " + ex.getMessage() + "\n");
                }
            }
        });

        JButton rebootBootloaderButton = new JButton("Reboot to Bootloader");
        rebootBootloaderButton.setBackground(new Color(255, 69, 0)); // Red-Orange color
        rebootBootloaderButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        rebootBootloaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process process = Runtime.getRuntime().exec("adb reboot bootloader");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        terminal.append(line + "\n");
                    }
                    process.waitFor();
                    terminal.append("Rebooted to Bootloader successfully!\n");
                } catch (Exception ex) {
                    terminal.append("Error rebooting to Bootloader: " + ex.getMessage() + "\n");
                }
            }
        });

        frame.add(unlockBootloaderButton);
        frame.add(flashButton);
        frame.add(rebootRecoveryButton);
        frame.add(rebootBootloaderButton);
        frame.add(new JScrollPane(terminal));

        frame.setVisible(true);
    }
}