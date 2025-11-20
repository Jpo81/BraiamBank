/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author Miguel
 */
public class Aluno {
    private String EMAIL_Aluno;
    private int ID_Aluno;
    private String DATA_NASC_Aluno;
    private String NOME_Aluno;
    private int COD_Turma;
    private int RM_Aluno;

    public Aluno() {
    }

    public Aluno(String EMAIL_Aluno, int ID_Aluno, String DATA_NASC_Aluno, String NOME_Aluno, int COD_Turma, int RM_Aluno) {
        this.EMAIL_Aluno = EMAIL_Aluno;
        this.ID_Aluno = ID_Aluno;
        this.DATA_NASC_Aluno = DATA_NASC_Aluno;
        this.NOME_Aluno = NOME_Aluno;
        this.COD_Turma = COD_Turma;
        this.RM_Aluno = RM_Aluno;
    }
    
    
    
    public String getEMAIL_Aluno() {
        return EMAIL_Aluno;
    }

    public void setEMAIL_Aluno(String EMAIL_Aluno) {
        this.EMAIL_Aluno = EMAIL_Aluno;
    }

    public int getID_Aluno() {
        return ID_Aluno;
    }

    public void setID_Aluno(int ID_Aluno) {
        this.ID_Aluno = ID_Aluno;
    }

    public String getDATA_NASC_Aluno() {
        return DATA_NASC_Aluno;
    }

    public void setDATA_NASC_Aluno(String DATA_NASC_Aluno) {
        this.DATA_NASC_Aluno = DATA_NASC_Aluno;
    }

    public String getNOME_Aluno() {
        return NOME_Aluno;
    }

    public void setNOME_Aluno(String NOME_Aluno) {
        this.NOME_Aluno = NOME_Aluno;
    }

    public int getCOD_Turma() {
        return COD_Turma;
    }

    public void setCOD_Turma(int COD_Turma) {
        this.COD_Turma = COD_Turma;
    }

    public int getRM_Aluno() {
        return RM_Aluno;
    }

    public void setRM_Aluno(int RM_Aluno) {
        this.RM_Aluno = RM_Aluno;
    }
    
    
}
