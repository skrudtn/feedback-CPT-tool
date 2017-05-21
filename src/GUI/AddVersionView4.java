package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.MainSystem;

public class AddVersionView4 extends JFrame implements ActionListener{

	private JPanel contentPane;
	JLabel[] rvLabels;
	JTextField[] constNameFields;
	JButton confirmBtn;
	JButton prevBtn;
	JPanel[] container;
	int btnLoc;
	private JPanel panel;

	
	//NewProjectView3 ���� ������
	ArrayList<String> rvName;
	ArrayList<Integer> constNum;
	
	int temp = 0;
	
	public AddVersionView4(ArrayList<String> rvName, ArrayList<Integer> constNum) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.rvName = rvName;
		this.constNum = constNum;
		
		
		panel = new JPanel();
		panel.setBounds(0, 10, 800, 300);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 10, 800, 337);
		contentPane.add(scrollPane);
		
		temp = numCount(constNum);
		rvLabels = new JLabel[temp];


		int line = 0;
		for(int i=0;i<rvName.size();i++){
			for(int j=0;j<constNum.get(i);j++){//�̰� �� �պ��� �ɵ�
				rvLabels[line] = new JLabel("RepreValue:" + (j+1) + " " + rvName.get(i) + "��");
				rvLabels[line].setBounds(10,10+(line*20), 95, 15);	
				line++;
			}
			//panel.add(cLabels[i]);
			
		}
		
		
		constNameFields = new JTextField[temp];
		for(int i=0;i<temp;i++){
			constNameFields[i] = new JTextField();
			constNameFields[i].getCursor();
			constNameFields[i].setColumns(15);
			constNameFields[i].setText("");
			//panel.add(cNameFields[i]);
		}
		
		
		
		container = new JPanel[temp];
		for(int i=0;i<temp;i++){
			container[i] = new JPanel();
			container[i].add(rvLabels[i]);
			container[i].add(constNameFields[i]);
			panel.add(container[i]);
		}
		panel.setLayout(new GridLayout(0, 1, 0, 0));


		prevBtn = new JButton("Prev");
		prevBtn.setBounds(0, 357, 200, 36);
		contentPane.add(prevBtn);
		prevBtn.addActionListener(this);
		
		confirmBtn = new JButton("OK!!");
		confirmBtn.setBounds(399, 357, 200, 36);
		contentPane.add(confirmBtn);
		confirmBtn.addActionListener(this);

		
		
	}

	//Ȯ�ι�ư ó��������� �����̶�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("bbb");
		if(e.getSource() == confirmBtn){
	
			for(int i=0;i<temp;i++){
				String name = constNameFields[i].getText();
				MainSystem.pm.reqCreateConstraints(name);
			}

			/*RM���� ������ �����ؾ���*/	//�׸���
			MainSystem.rm.calTestCaseNum(MainSystem.pm.reqGetLastVersion());
			
			MainSystem.gm.addVerisonPanel(MainSystem.pm.reqGetVersionListSize(), 0);
			MainSystem.gm.leaderMain.setCalNum( MainSystem.pm.reqGetVersion().getTestcaseNum() );
			MainSystem.gm.leaderMainRepaint();
			//MainSystem.reqShowView();
			
			MainSystem.gm.addVersionFrame1.dispose();
			MainSystem.gm.addVersionFrame2.dispose();
			MainSystem.gm.addVersionFrame3.dispose();
			MainSystem.gm.addVersionFrame4.dispose();
			
		}else if(e.getSource() == prevBtn){
			this.setVisible(false);
			MainSystem.gm.addVersionFrame3.setVisible(true);
		}
	}

	private int numCount(ArrayList<Integer> array){
		int sum = 0;
		for(int i=0;i<array.size();i++){
			sum += array.get(i);
		}
		return sum;
	}
	
}