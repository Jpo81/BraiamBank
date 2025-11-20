/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author LABINFO
 */
public class Turma {
    private int ID_Turma;
    private int ANO_Turma;
    private int COD_Professor;
    private String GRUPO_Turma;
    private String CURSO_Turma;
    private String PERIODO_Turma;
    private String NOME_Turma;

    public Turma() {
    }

    public Turma(int ID_Turma, int ANO_Turma, int COD_Professor, String GRUPO_Turma, String CURSO_Turma, String PERIODO_Turma, String NOME_Turma) {
        this.ID_Turma = ID_Turma;
        this.ANO_Turma = ANO_Turma;
        this.COD_Professor = COD_Professor;
        this.GRUPO_Turma = GRUPO_Turma;
        this.CURSO_Turma = CURSO_Turma;
        this.PERIODO_Turma = PERIODO_Turma;
        this.NOME_Turma = NOME_Turma;
    }
    
    

    public int getID_Turma() {
        return ID_Turma;
    }

    public void setID_Turma(int ID_Turma) {
        this.ID_Turma = ID_Turma;
    }

    public int getANO_Turma() {
        return ANO_Turma;
    }

    public void setANO_Turma(int ANO_Turma) {
        this.ANO_Turma = ANO_Turma;
    }

    public int getCOD_Professor() {
        return COD_Professor;
    }

    public void setCOD_Professor(int COD_Professor) {
        this.COD_Professor = COD_Professor;
    }

    public String getGRUPO_Turma() {
        return GRUPO_Turma;
    }

    public void setGRUPO_Turma(String GRUPO_Turma) {
        this.GRUPO_Turma = GRUPO_Turma;
    }

    public String getCURSO_Turma() {
        return CURSO_Turma;
    }

    public void setCURSO_Turma(String CURSO_Turma) {
        this.CURSO_Turma = CURSO_Turma;
    }

    public String getPERIODO_Turma() {
        return PERIODO_Turma;
    }

    public void setPERIODO_Turma(String PERIODO_Turma) {
        this.PERIODO_Turma = PERIODO_Turma;
    }

    public String getNOME_Turma() {
        return NOME_Turma;
    }

    public void setNOME_Turma(String NOME_Turma) {
        this.NOME_Turma = NOME_Turma;
    }
    
    
    
}
