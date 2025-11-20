/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author Miguel
 */
public class Pontos {
    private Double QTD_Pontos;
    private int COD_Aluno;
    private int ID_Pontos;
    private int COD_Justificativa;
    private String NOME_Aluno;

    public Pontos(Double QTD_Pontos, int COD_Aluno, int ID_Pontos, int COD_Justificativa) {
        this.QTD_Pontos = QTD_Pontos;
        this.COD_Aluno = COD_Aluno;
        this.ID_Pontos = ID_Pontos;
        this.COD_Justificativa = COD_Justificativa;
    }

    public Pontos() {
    }
    
    

    public Double getQTD_Pontos() {
        return QTD_Pontos;
    }

    public void setQTD_Pontos(Double QTD_Pontos) {
        this.QTD_Pontos = QTD_Pontos;
    }

    public int getCOD_Aluno() {
        return COD_Aluno;
    }

    public void setCOD_Aluno(int COD_Aluno) {
        this.COD_Aluno = COD_Aluno;
    }

    public int getID_Pontos() {
        return ID_Pontos;
    }

    public void setID_Pontos(int ID_Pontos) {
        this.ID_Pontos = ID_Pontos;
    }

    public int getCOD_Justificativa() {
        return COD_Justificativa;
    }

    public void setCOD_Justificativa(int COD_Justificativa) {
        this.COD_Justificativa = COD_Justificativa;
    }
    
    public String getNOME_Aluno() {
        return NOME_Aluno;
    }

    public void setNOME_Aluno(String NOME_Aluno) {
        this.NOME_Aluno = NOME_Aluno;
    }
}
