package by.epam.computergames.entity;

import by.epam.computergames.command.CommandName;

public class PictureDelivery {
    private long id;
    private CommandName command;
    private String picture;

    public PictureDelivery() {
        command = CommandName.GET_GAME;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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