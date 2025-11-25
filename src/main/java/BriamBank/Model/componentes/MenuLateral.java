/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model.componentes;

/**
 *
 * @author Miguel
 */
import BriamBank.Model.Session;
import BriamBank.View.MenuCad;
import BriamBank.View.Home;
import BriamBank.View.JustCad;
import BriamBank.View.Login;
import BriamBank.View.MenuTurma;
import BriamBank.View.MudarPontos;
import BriamBank.View.RelatorioGerar;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

/*

Passando pra deixar o agradecimento ao https://github.com/DJ-Raven

 */
public class MenuLateral extends JPanel {

    private JButton btnInicio;
    private JButton btnPontos;
    private JButton btnJustificativas;
    private JButton btnRelatorio;
    private JButton btnCadastrar;
    private JButton btnSair;

    public MenuLateral() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("BraiamBank");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblTitulo);
        add(Box.createVerticalStrut(25));

        add(criarTituloSecao("Descubra"));

        btnInicio = criarBotao("Início", "/assets/icons/home.png", e -> {
            try {

                MenuTurma menuTurma = new MenuTurma();
                menuTurma.setLocationRelativeTo(null);
                menuTurma.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            }
        });
        add(btnInicio);

        btnPontos = criarBotao("Pontos", "/assets/icons/carteira.png", e -> {
            try {
                MudarPontos mudarPontos = new MudarPontos();
                mudarPontos.setLocationRelativeTo(null);
                mudarPontos.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            }
        });
        add(btnPontos);

        add(Box.createVerticalStrut(20));

        add(criarTituloSecao("Gestão"));

        btnJustificativas = criarBotao("Justificativas", "/assets/icons/menu.png", e -> {
            try {
                JustCad justCad = new JustCad();
                justCad.setLocationRelativeTo(null);
                justCad.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            }
        });
        add(btnJustificativas);

        btnRelatorio = criarBotao("Relatório", "/assets/icons/doc.png", e -> {
            try {
                RelatorioGerar relatorioGerar = new RelatorioGerar();
                relatorioGerar.setLocationRelativeTo(null);
                relatorioGerar.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            }
        });
        add(btnRelatorio);

        btnCadastrar = criarBotao("Gerenciar", "/assets/icons/mais.png", e -> {
            try {
                MenuCad cadMenu = new MenuCad();
                cadMenu.setLocationRelativeTo(null);
                cadMenu.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            }
        });
        add(btnCadastrar);

        add(Box.createVerticalGlue());
        
        
        
        btnSair = criarBotao("Sair", "/assets/icons/exit.png", e -> {
            int op = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente deslogar ?",
                    "Confirmar saída",
                    JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {
                try {
                Session.getInstancia().limparSessao();
                Login login = new Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro então o programa sera fechado por segurança" );
                    System.exit(0);
                }
            }
        });
        add(btnSair);
    }

    

    private JLabel criarTituloSecao(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(100, 100, 100));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 8, 0));
        return label;
    }

    private JButton criarBotao(String texto, String iconePath, java.awt.event.ActionListener acao) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        btn.setBackground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            btn.setIcon(new ImageIcon(getClass().getResource(iconePath)));
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: " + iconePath);
        }

        // Hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(245, 245, 245));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
            }
        });

        btn.addActionListener(acao);

        return btn;
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Menu Lateral Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(new MenuLateral(), BorderLayout.WEST);
        frame.setVisible(true);
    }

}
