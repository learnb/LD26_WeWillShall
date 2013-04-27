//package net.umc.ludumdare.tools;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//
//import net.umc.ludumdare.data.DataService;
//
//@SuppressWarnings("serial")
//public class ManageResources extends JFrame {
//
//	private static JTextField txtResourceID, txtPath;
//	private static JComboBox<String> cmbType;
//	
//	private JButton btnAddResource;
//	
//	public ManageResources() {
//		super("Resource Manager");
//		this.setBounds(200,200,290,180);
//		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLayout(null);
//		
//		JLabel label1 = new JLabel("Resource ID");
//		label1.setBounds(10, 10, 100, 20);
//		
//		txtResourceID = new JTextField();
//		txtResourceID.setBounds(120, 10, 150, 20);
//		
//		JLabel label2 = new JLabel("Type");
//		label2.setBounds(10, 40, 100, 20);
//		
//		cmbType = new JComboBox<String>();
//		cmbType.setBounds(120, 40, 150, 20);
//		cmbType.addItem("IMAGE");
//		cmbType.addItem("SOUND");
//		
//		JLabel label3 = new JLabel("Path");
//		label3.setBounds(10, 70, 100, 20);
//		
//		txtPath = new JTextField();
//		txtPath.setBounds(120, 70, 150, 20);
//		
//		btnAddResource = new JButton("Add Resource");
//		btnAddResource.setBounds(65, 120, 150, 20);
//		btnAddResource.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				DataService.insertResource(txtResourceID.getText(), cmbType.getSelectedItem().toString(), txtPath.getText());
//			}
//			
//		});
//		
//		add(label1);
//		add(txtResourceID);
//		add(label3);
//		add(cmbType);
//		add(label2);
//		add(txtPath);
//		add(btnAddResource);
//		
//		repaint();
//	}
//	
//	public static void main(String[] args) {
//		new ManageResources();
//	}
//
//}
