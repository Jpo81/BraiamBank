package BriamBank.Model.componentes;

import BriamBank.View.GrupoMenu;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JCardTurma extends JPanel {

    private JLabel lblTurmaTitulo;
    private JLabel lblNomeTurma;
    private JLabel lblCurso;
    
    private JPanel painelColorido;
    private String turma;

    private Color corNormal = new Color(0, 123, 255); // Azul principal
    private Color corHover = new Color(0, 153, 255); // Azul hover

    public JCardTurma() {
        initComponents();
    }

    public JCardTurma(String nomeTurma, String nomeCurso, int idTurma) {
        this.turma = nomeTurma;
        initComponents();
        setTurma(nomeTurma);
        setCurso(nomeCurso);
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(200, 250));
        setLayout(new BorderLayout());

        
        painelColorido = new JPanel();
        painelColorido.setBackground(corNormal);
        painelColorido.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelColorido.setLayout(new GridBagLayout()); // centraliza o título
        painelColorido.setBorder(BorderFactory.createLineBorder(corNormal.darker(), 1, true));

        lblTurmaTitulo = new JLabel("Turma");
        lblTurmaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTurmaTitulo.setForeground(Color.WHITE);

        painelColorido.add(lblTurmaTitulo);

        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        lblNomeTurma = new JLabel("Turma A");
        lblNomeTurma.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNomeTurma.setForeground(new Color(33, 33, 33));

        lblCurso = new JLabel("Curso");
        lblCurso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCurso.setForeground(new Color(100, 100, 100));

        infoPanel.add(lblNomeTurma);
        infoPanel.add(Box.createVerticalStrut(5)); // espaçamento
        infoPanel.add(lblCurso);

        add(painelColorido, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        
        painelColorido.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GrupoMenu grupoMenu = new GrupoMenu(turma);
                grupoMenu.setVisible(true);
                System.out.println(turma);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(JCardTurma.this);
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                painelColorido.setBackground(corHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painelColorido.setBackground(corNormal);
            }
        });
    }

    public void setTurma(String nome) {
        lblTurmaTitulo.setText(nome);
        lblNomeTurma.setText(nome);
    }

    public void setCurso(String curso) {
        lblCurso.setText(curso);
    }

    public void setCorPainel(Color cor) {
        this.corNormal = cor;
        this.corHover = cor.brighter();
        painelColorido.setBackground(corNormal);
        painelColorido.setBorder(BorderFactory.createLineBorder(cor.darker(), 1, true));
    }
}
