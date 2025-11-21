/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author Miguel
 */

//Essa classe funciona como uma sesão que guarda tds os dados do professor logado
public class Session {
    
    // Atributo que guarda a existencia de uma sessão
    private static Session instancia;
    
    // Atributo que armazena o objeto do professor q ta logado
    private Professor professorLogado;
    
    //Construtor vazio
    public Session() {}
    // Construtor com parametro
    public static Session getInstancia() {
        
        // se instancia igual null cria uma instancia
        if (instancia == null) {
            instancia = new Session();
        }
        //retorna instancia
        return instancia;
        
        
    }   
    // retorna o onj professor
    public Professor getProfessorLogado() {
        return professorLogado;
    }
    //Seta o obj professor
    public void setProfessorLogado(Professor professorlogado) {
        this.professorLogado = professorlogado;
    }
    //Limpa a sessão
    public void limparSessao() {
        this.professorLogado = null;
    }

    
    
}
