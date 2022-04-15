package com.tp3.dto;

import lombok.Data;

@Data
public class DtoChoix {
    private String choix;
    private String critere;
    private String data;

    public DtoChoix(String choix, String critere, String data) {
        this.choix = choix;
        this.critere = critere;
        this.data = data;
    }

    public DtoChoix(){
    };
}
