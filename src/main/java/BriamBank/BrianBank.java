/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package BriamBank;

import BriamBank.Model.Teste;
import BriamBank.View.Home;
import BriamBank.View.Login;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.ImageIcon;

/**
 *
 * @author LABINFO
 */
public class BrianBank {

    public static void main(String[] args) {
        try {
            // Configura FlatLaf estilo macOS claro
            FlatMacLightLaf.setup();

            Login novaTela = new Login();
            ImageIcon icon = new ImageIcon(BrianBank.class.getResource("/assets/icons/bblogo.png"));
            

            // Seta o ícone no JFrame
            novaTela.setIconImage(icon.getImage());
            novaTela.setVisible(true);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Teste.class.getName())
                    .log(java.util.logging.Level.SEVERE, "Falha ao inicializar FlatLaf", ex);
        }

    }
}
