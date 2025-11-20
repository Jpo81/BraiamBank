/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author Miguel
 */
public class Justificativa {
    private  int ID_Justificativa;
    private String DESC_Justificativa;
    private String MOTIVO_Justificativa;
    private Double QTD_Justificativa;
    private String DATE_Justificativa;
    private Boolean STATUS_Just;

    public Boolean getSTATUS_Just() {
        return STATUS_Just;
    }

    public void setSTATUS_Just(Boolean STATUS_Just) {
        this.STATUS_Just = STATUS_Just;
    }

    public Justificativa() {
    }

    public Justificativa(int ID_Justificativa, String DESC_Justificativa, String MOTIVO_Justificativa, Double QTD_Justificativa, String DATE_Justificativa) {
        this.ID_Justificativa = ID_Justificativa;
        this.DESC_Justificativa = DESC_Justificativa;
        this.MOTIVO_Justificativa = MOTIVO_Justificativa;
        this.QTD_Justificativa = QTD_Justificativa;
        this.DATE_Justificativa = DATE_Justificativa;
    }
    
    

    public int getID_Justificativa() {
        return ID_Justificativa;
    }

    public void setID_Justificativa(int ID_Justificativa) {
        this.ID_Justificativa = ID_Justificativa;
    }

    public String getDESC_Justificativa() {
        return DESC_Justificativa;
    }

    public void setDESC_Justificativa(String DESC_Justificativa) {
        this.DESC_Justificativa = DESC_Justificativa;
    }

    public String getMOTIVO_Justificativa() {
        return MOTIVO_Justificativa;
    }

    public void setMOTIVO_Justificativa(String MOTIVO_Justificativa) {
        this.MOTIVO_Justificativa = MOTIVO_Justificativa;
    }

    public Double getQTD_Justificativa() {
        return QTD_Justificativa;
    }

    public void setQTD_Justificativa(Double QTD_Justificativa) {
        this.QTD_Justificativa = QTD_Justificativa;
    }

    public String getDATE_Justificativa() {
        return DATE_Justificativa;
    }

    public void setDATE_Justificativa(String DATE_Justificativa) {
        this.DATE_Justificativa = DATE_Justificativa;
    }
    
    
}
