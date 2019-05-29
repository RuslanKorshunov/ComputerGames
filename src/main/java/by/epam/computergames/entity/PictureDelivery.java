package by.epam.computergames.entity;

import by.epam.computergames.command.CommandConst;

public class PictureDelivery
{
    private long id;
    private CommandConst command;
    private String picture;

    public PictureDelivery()
    {
        command = CommandConst.GET_GAME;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CommandConst getCommand() {
        return command;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}