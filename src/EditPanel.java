
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class EditPanel extends JPanel {
	JTextField edit=new JTextField(15);
	JButton addButton=new JButton("add");
	JLabel different=new JLabel("≥≠¿Ãµµ 1");
	JButton saveButton=new JButton("save");
	public EditPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new FlowLayout());
		add(edit);
		add(addButton);
		add(saveButton);
		add(different);
		saveButton.setEnabled(false);
	}	
}
