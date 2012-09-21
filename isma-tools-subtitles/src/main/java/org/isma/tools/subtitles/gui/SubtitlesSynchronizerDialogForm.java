package org.isma.tools.subtitles.gui;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import org.isma.tools.subtitles.SubtitlesSynchronizer;
import org.isma.tools.subtitles.format.SubtitlesFormatFactory;
import org.isma.tools.subtitles.format.UnsupportedSubtitlesFormat;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SubtitlesSynchronizerDialogForm extends JDialog {
    private JPanel contentPane;
    private JButton synchronizeButton;
    private JTextField fileTextField;
    private JTextField delayTextField;
    private JButton browseButton;

    public SubtitlesSynchronizerDialogForm() throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        setTitle("Subtitles synchronizer");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(synchronizeButton);
        synchronizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    synchronize();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browse();
            }
        });


        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void synchronize() throws IOException, UnsupportedSubtitlesFormat {
        SubtitlesSynchronizer synchronizer = new SubtitlesSynchronizer();
        String subtitlesFilePath = fileTextField.getText();
        int delay = Integer.decode(delayTextField.getText());
        File synchroFile = synchronizer.doSynchro(subtitlesFilePath, delay);
        JOptionPane.showMessageDialog(this, synchroFile.getAbsolutePath() + " created", "Done", JOptionPane.INFORMATION_MESSAGE);

    }

    private void browse() {
        JFileChooser fileChooser = buildFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            fileTextField.setText(file.getAbsolutePath());
        }
    }

    private JFileChooser buildFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                try {
                    new SubtitlesFormatFactory().getFormat(f);
                    return true;
                } catch (UnsupportedSubtitlesFormat unsupportedSubtitlesFormat) {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                return "supported subtitles format";
            }
        });
        return fileChooser;
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        SubtitlesSynchronizerDialogForm dialog = new SubtitlesSynchronizerDialogForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
