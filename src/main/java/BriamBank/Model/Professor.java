/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author Miguel
 */
public class Professor {
    private String SENHA_Professor;
    private String EMAIL_Professor;
    private String Nome_Professor;
    private int RM_Professor;

    public Professor() {
    }

    public Professor(String SENHA_Professor, String EMAIL_Professor, String Nome_Professor, int RM_Professor) {
        this.SENHA_Professor = SENHA_Professor;
        this.EMAIL_Professor = EMAIL_Professor;
        this.Nome_Professor = Nome_Professor;
        this.RM_Professor = RM_Professor;
    }
    
    
    
    public String getSENHA_Professor() {
        return SENHA_Professor;
    }

    public void setSENHA_Professor(String SENHA_Professor) {
        this.SENHA_Professor = SENHA_Professor;
    }

    public String getEMAIL_Professor() {
        return EMAIL_Professor;
    }

    public void setEMAIL_Professor(String EMAIL_Professor) {
        this.EMAIL_Professor = EMAIL_Professor;
    }

    public String getNome_Professor() {
        return Nome_Professor;
    }

    public void setNome_Professor(String Nome_Professor) {
        this.Nome_Professor = Nome_Professor;
    }

    public int getRM_Professor() {
        return RM_Professor;
    }

    public void setRM_Professor(int RM_Professor) {
        this.RM_Professor = RM_Professor;
    }

    
    
    
}
