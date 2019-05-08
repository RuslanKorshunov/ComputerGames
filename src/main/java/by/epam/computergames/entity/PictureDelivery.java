package by.epam.computergames.entity;

import by.epam.computergames.command.CommandEnum;

public class PictureDelivery
{
    private long id;
    private CommandEnum command;
    private String picture;

    public PictureDelivery()
    {
        command =CommandEnum.GET_GAME;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}