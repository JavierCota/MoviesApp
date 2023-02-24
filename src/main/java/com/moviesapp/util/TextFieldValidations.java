package com.moviesapp.util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextFieldValidations {

    public void validateStringChars(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char c = e.getKeyChar();

                if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c) || c == ':' || c == '\'' || c == ',' || c == '-') {
                    textField.setEditable(true);
                } else {
                    textField.setEditable(false);
                }
            }
        });
    }

    public void validateIntChars(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char c = e.getKeyChar();

                if (Character.isDigit(c) || Character.isISOControl(c)) {
                    textField.setEditable(true);
                } else {
                    textField.setEditable(false);
                }
            }
        });
    }

    public void validateDateChars(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char c = e.getKeyChar();

                if (Character.isDigit(c) || c == '-' || Character.isISOControl(c)) {
                    textField.setEditable(true);
                } else {
                    textField.setEditable(false);
                }
            }
        });
    }

    public void validateNameLength(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textField.getText().length() >= 50) {
                    e.consume();
                }
            }
        });
    }

    public void validateIntDateLength(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textField.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
    }

    public void validateTextFLength(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textField.getText().length() >= 30) {
                    e.consume();
                }
            }
        });
    }

    public void validateDescriptionLength(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (textField.getText().length() >= 300) {
                    e.consume();
                }
            }
        });
    }
}
