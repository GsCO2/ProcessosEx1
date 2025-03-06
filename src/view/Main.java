package view;
import javax.swing.JOptionPane;
import controller.RedesController;

public class Main {
	public static void main(String[] args) {
		RedesController rc = new RedesController();
		int opc = 0;
		while(opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Configuração de IP\n2 - Tempo médio do ping\n9 - FIM"));
			switch(opc) {
			case 1:
				rc.ip();
				break;
			case 2:
				rc.ping();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "FIM");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida");
				break;
			}
		}
	}
}
