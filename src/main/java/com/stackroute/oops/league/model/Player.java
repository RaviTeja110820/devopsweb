package com.stackroute.oops.league.model;

import java.io.Serializable;

/**
 * This class contains five fields playerId,playerName,password,yearExpr and teamTitle
 * It is a subclass of Comparable interface
 */
public class Player implements Serializable,Comparable<Player> {


    private String playerId;
    private String playerName;
    private String password;
    private int yearExpr;
    private String teamTitle;

    //Parameterized Constructor to initialize all properties

    public Player(String playerId, String playerName, String password, int yearExpr) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.password = password;
        this.yearExpr = yearExpr;
    }



    /* Getter and setter*/

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYearExpr() {
        return yearExpr;
    }

    public void setYearExpr(int yearExpr) {
        this.yearExpr = yearExpr;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    /**
     * This overridden method should return player details in below format
     * Player{playerId=xxxxx, playerName=xxxxxx, yearExpr=xxxxxx,teamTitle=xxxxxxxx}-> if teamTitle is set
     * Player{playerId=xxxxx, playerName=xxxxxx, yearExpr=xxxxxx}-> if teamTitle is not set
     */

    @Override
    public String toString() {
        if (teamTitle == null || teamTitle.isEmpty())
            return "Player{" +
                    "playerId='" + playerId +
                    ", playerName='" + playerName +
                    ", password='" + password +
                    ", yearExpr='" + yearExpr +
                    '}';
        else
            return "Player{" +
                    "playerId='" + playerId +
                    ", playerName='" + playerName +
                    ", password='" + password +
                    ", yearExpr='" + yearExpr +
                    ", teamTitle='" + teamTitle +
                    '}';
    }


    //compareTo player object based on playerId

    @Override
    public int compareTo(Player o) {
        return playerId.compareTo(o.playerId);
    }
}
