/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author Miguel
 */
public class Session {
    private static Session instancia;
    
    private Professor professorLogado;
    
    public Session() {}

    public static Session getInstancia() {
        if (instancia == null) {
            instancia = new Session();
        }
        return instancia;
        
        
    }

    public Professor getProfessorLogado() {
        return professorLogado;
    }

    public void setProfessorLogado(Professor professorlogado) {
        this.professorLogado = professorlogado;
    }
    
    public void limparSessao() {
        this.professorLogado = null;
    }

    
    
}
