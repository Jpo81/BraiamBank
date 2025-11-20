/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Miguel
 */
import BriamBank.Controller.ConProfessor;
import BriamBank.Model.Professor;
import java.util.Vector;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ConProfessorTest {
    /*
    @Test
    public void testeLoginValido() {
        ConProfessor controller = new ConProfessor();
        Professor prof = new Professor();

        prof.setRM_Professor(44444);
        prof.setSENHA_Professor("A12345");

        Professor resultado = controller.logar(prof);
        if (resultado != null) {
            System.out.println(String.format(
                    "Nome: %s RM: %d Email: %s Senha: %s",
                    resultado.getNome_Professor(),
                    resultado.getRM_Professor(),
                    resultado.getEMAIL_Professor(),
                    resultado.getSENHA_Professor()
            ));
        } else {
            System.out.println("Login inválido");
        }
    }
    
    @Test
    public void testeRetornarTurmas() {
        ConProfessor controller = new ConProfessor();
        Professor prof = new Professor();
        
        Se
        Vector<Vector> turmas = controller.retornar_turmas(prof);
        if (turmas != null && !turmas.isEmpty()) {
            System.out.println("Turmas encontradas:");
            for (Vector linha : turmas) {
                System.out.println(String.format(
                        "Ano: %s | Cod_Prof: %s | Curso: %s | Grupo: %s | ID: %s | Nome: %s | Período: %s",
                        linha.get(0), linha.get(1), linha.get(2),
                        linha.get(3), linha.get(4), linha.get(5), linha.get(6)
                ));
            }
        } else {
            System.out.println("Nenhuma turma encontrada para esse professor.");
        }
    }

    @Test
    public void testeCadastrarProfessor() {
        ConProfessor controller = new ConProfessor();
        Professor novo = new Professor();

        novo.setRM_Professor(545454);
        novo.setNome_Professor("Carlos BriamBank.Model.Teste");
        novo.setEMAIL_Professor("carlos.teste@briambank.com");
        novo.setSENHA_Professor("Senha123");

        boolean resultado = controller.cadastrar(novo);

        if (resultado) {
            System.out.println("Cadastro realizado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar o professor.");
        }
    }

    @Test
    public void testeEditarProfessor() {
        ConProfessor controller = new ConProfessor();
        Professor prof = new Professor();

        prof.setRM_Professor(54545);
        prof.setNome_Professor("Carlos Editado");
        prof.setEMAIL_Professor("carlos.editado@briambank.com");
        prof.setSENHA_Professor("NovaSenha456");

        boolean resultado = controller.editar(prof);

        if (resultado) {
            System.out.println("Professor atualizado com sucesso!");
        } else {
            System.out.println("Nenhum professor encontrado para editar.");
        }
    }

    @Test
    public void testeExcluirProfessor() {
        ConProfessor controller = new ConProfessor();

        int rmExcluir = 545454;

        boolean resultado = controller.excluir(rmExcluir);

        if (resultado) {
            System.out.println("Professor excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir o professor.");
        }
    }*/
}
