/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.handler;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hp
 */
@Getter
@Setter
public class RessourceError {

    public RessourceError(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
    private int status;
    private String message;
    private long timeStamp;
}
