package com.mklachl.sopkom.model.dto;

/**
 * Klasa reprezentująca wiadomość.
 */
public class Message {

    /**
     * Treść wiadomości.
     */
    public String message;

    /**
     * Konstruktor domyślny.
     */
    public Message() {
    }

    /**
     * Konstruktor przyjmujący treść wiadomości.
     * @param message Treść wiadomości.
     */
    public Message(String message) {
        this.message = message;
    }

    /**
     * Ustawia treść wiadomości i zwraca obiekt Message.
     * @param message Treść wiadomości.
     * @return Obiekt Message.
     */
    public Message SetMessageInt(String message) {
        this.message = message;
        return this;
    }

    /**
     * Ustawia treść wiadomości i zwraca nowy obiekt Message.
     * @param message Treść wiadomości.
     * @return Nowy obiekt Message.
     */
    public static Message SetMessage(String message) {
        return new Message().SetMessageInt(message);
    }
}
