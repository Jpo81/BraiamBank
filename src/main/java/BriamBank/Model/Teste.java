/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

import BriamBank.Controller.ConProfessor;
import BriamBank.Model.componentes.MenuLateral;
import BriamBank.View.AlunoList;
import BriamBank.View.CadAluno;
import BriamBank.View.Home;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

/**
 *
 * @author Miguel
 */
public class Teste {
    public static void main(String[] args) {
        ConProfessor conProfessor = new ConProfessor();
        Professor prof = new Professor();
        prof.setRM_Professor(44444);
        prof.setSENHA_Professor("123@Aa12");
        conProfessor.logar(prof);
        
        Professor profa = Session.getInstancia().getProfessorLogado();
        
        System.out.println(profa.getRM_Professor());
        
        try {
            // Configura FlatLaf estilo macOS claro
            FlatMacLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Teste.class.getName())
                    .log(java.util.logging.Level.SEVERE, "Falha ao inicializar FlatLaf", ex);
        }
        
        Home novaTela = new Home();
        novaTela.setVisible(true);
        
        
    }
    
    
}