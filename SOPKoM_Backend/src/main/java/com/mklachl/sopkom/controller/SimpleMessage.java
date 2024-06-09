package com.mklachl.sopkom.controller;

/**
 * Klasa SimpleMessage służy do przechowywania prostych wiadomości tekstowych.
 */
public class SimpleMessage {

    /** Wiadomość tekstowa */
    public String message;

    /** Konstruktor domyślny */
    public SimpleMessage() {
        super();
    }

    /**
     * Konstruktor inicjalizujący wiadomość
     * @param message wiadomość do ustawienia
     */
    public SimpleMessage(String message) {
        super();
        this.message = message;
    }

    /**
     * Pobiera wiadomość
     * @return wiadomość
     */
    public String getMessage() {
        return message;
    }

    /**
     * Ustawia wiadomość
     * @param message wiadomość do ustawienia
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
