package by.epam.computergames.entity;

import by.epam.computergames.command.CommandName;

public class PictureDelivery extends AbstractEntity {
    private String idGame;
    private CommandName command;
    private String picture;

    public PictureDelivery() {
        command = CommandName.GET_GAME;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public CommandName getCommand() {
        return command;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}