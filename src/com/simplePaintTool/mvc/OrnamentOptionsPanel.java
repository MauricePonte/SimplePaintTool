package com.simplePaintTool.mvc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class OrnamentOptionsPanel extends JDialog {

    private final JPanel mainPanel;
    private JTextField txt_ornamentText;
    private JComboBox cmb_ornamentPos;
    private String[] options = {"top","bottom","left","right"};

    private JLabel lbl_ornamentText;
    private JLabel lblYcoordinate;

    private String ornamentText;
    private String ornamentPos;



    private boolean confirmed;



    public OrnamentOptionsPanel() {
        setModal(true);
        setResizable(false);
        setTitle("Ornament options");
        setBounds(100, 100, 440, 320);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        GridBagLayout gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};

        mainPanel.setLayout(gbl_mainPanel);
        {
            lbl_ornamentText = new JLabel("Enter text");
            GridBagConstraints gbc_lblOrnamentText= new GridBagConstraints();
            gbc_lblOrnamentText.insets = new Insets(0, 0, 5, 5);
            gbc_lblOrnamentText.gridx = 2;
            gbc_lblOrnamentText.gridy = 3;
            mainPanel.add(lbl_ornamentText, gbc_lblOrnamentText);
        }
        {
            txt_ornamentText = new JTextField();
            //lbl_ornamentText.setLabelFor(txt_ornamentText);
            GridBagConstraints gbc_txtXcoordinate = new GridBagConstraints();
            gbc_txtXcoordinate.anchor = GridBagConstraints.NORTH;
            gbc_txtXcoordinate.insets = new Insets(0, 0, 5, 5);
            gbc_txtXcoordinate.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtXcoordinate.gridx = 7;
            gbc_txtXcoordinate.gridy = 3;
            mainPanel.add(txt_ornamentText, gbc_txtXcoordinate);
            txt_ornamentText.setColumns(10);
        }
        {
            lblYcoordinate = new JLabel("Select position relative to selected shape");
            GridBagConstraints gbc_lblYcoordinate = new GridBagConstraints();
            gbc_lblYcoordinate.insets = new Insets(0, 0, 5, 5);
            gbc_lblYcoordinate.gridx = 2;
            gbc_lblYcoordinate.gridy = 5;
            mainPanel.add(lblYcoordinate, gbc_lblYcoordinate);
        }
        {
            cmb_ornamentPos = new JComboBox(options);
            lblYcoordinate.setLabelFor(cmb_ornamentPos);
            GridBagConstraints gbc_txtYcoordinate = new GridBagConstraints();
            gbc_txtYcoordinate.insets = new Insets(0, 0, 5, 5);
            gbc_txtYcoordinate.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtYcoordinate.gridx = 7;
            gbc_txtYcoordinate.gridy = 5;
            mainPanel.add(cmb_ornamentPos, gbc_txtYcoordinate);
        }


        {
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
            {
                JButton btnConfirm = new JButton("Confirm");
                btnConfirm.setBackground(Color.GREEN);
                btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnConfirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent click) {
                        if (txt_ornamentText.getText().isEmpty()) JOptionPane.showMessageDialog(getParent(), "Er moet tekst ingevuld worden!", "Error", JOptionPane.ERROR_MESSAGE);
                        else {
                            try {
                                ornamentText = txt_ornamentText.getText();
                                ornamentPos = cmb_ornamentPos.getSelectedItem().toString();

                                if(ornamentText.length() <= 0 ) JOptionPane.showMessageDialog(getParent(), "Er moet tekst ingevuld worden!", "Error", JOptionPane.ERROR_MESSAGE);
                                else {
                                    confirmed = true;
                                    setVisible(false);
                                    dispose();
                                }
                            } catch (NumberFormatException exception) {
                                JOptionPane.showMessageDialog(getParent(),"Er moet tekst ingevuld worden!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
                btnConfirm.setActionCommand("OK");
                buttonsPanel.add(btnConfirm);
                getRootPane().setDefaultButton(btnConfirm);
            }
            {
                JButton btnCancel = new JButton("Cancel");
                btnCancel.setBackground(Color.RED);
                btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnCancel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent click) {
                        setVisible(false);
                        dispose();
                    }
                });
                btnCancel.setActionCommand("Cancel");
                buttonsPanel.add(btnCancel);
            }
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getOrnamentText() {
        return ornamentText;
    }

    public String getOrnamentPos() {
        return ornamentPos;
    }

}

