import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;

class SudokuView implements ActionListener {

    Kontroll kontroll;
    Brett brett;
    JFrame vindu;
    int[][] sudoku;
    int stoerrelse;
    JPanel visningsPanel;
    JPanel[] boksPaneler;
    JPanel kontrollPanel;
    JButton nesteLoesningKnapp;
    JButton forrigeLoesningKnapp;
    JButton velgFilKnapp;
    
    SudokuView (int[][] sudoku, int boksHoeyde, int boksLengde, Kontroll k) {
	kontroll = k;
	this.sudoku = sudoku;
	stoerrelse = sudoku[0].length;
	brett = new Brett(sudoku, stoerrelse, boksLengde, boksHoeyde);

	vindu = new JFrame("sudoku");
	visningsPanel = new JPanel();
	visningsPanel.setLayout(new GridLayout(stoerrelse/boksHoeyde, stoerrelse/boksLengde ));
	visningsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

	/*lager paneler med grid layout for hver boks, putter riktige verdier inn i dem,
	  og legger dem inn i visningspanelet*/
	boksPaneler = new JPanel[stoerrelse];
	int[][] boksArray = brett.boksTabell(sudoku);
	for (int i = 0; i < stoerrelse; i++) {
	    boksPaneler[i] = new JPanel();
	    boksPaneler[i].setLayout(new GridLayout(boksHoeyde, boksLengde));
	    boksPaneler[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
	    
	    //lager knapper til hvert bokspanel
	    for (int j = 0; j < stoerrelse; j++) {
		JButton knapp = new JButton(String.valueOf(boksArray[i][j]));
		knapp.setFont(new Font("Helvetica", 100, 150));
		knapp.setEnabled(false);
		boksPaneler[i].add(knapp);
	    }
	    visningsPanel.add(boksPaneler[i]);
	}

	//Lager knapper med actionlisteners, og legger dem inn i et kontrollpanel
	kontrollPanel = new JPanel(new FlowLayout());
	forrigeLoesningKnapp = new JButton("<<");
	forrigeLoesningKnapp.addActionListener(this);
	velgFilKnapp = new JButton("Velg fil");
	velgFilKnapp.addActionListener(this);
	nesteLoesningKnapp = new JButton(">>");
	nesteLoesningKnapp.addActionListener(this);
	kontrollPanel.add(nesteLoesningKnapp);
	kontrollPanel.add(forrigeLoesningKnapp);
	kontrollPanel.add(velgFilKnapp);

	
	vindu.add(visningsPanel, BorderLayout.CENTER);
	vindu.add(kontrollPanel, BorderLayout.SOUTH);
	vindu.add(new JPanel(), BorderLayout.EAST);
	vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	vindu.setSize(50000, 50000);
	vindu.setVisible(true);
	malBokser();

    }

    public void nySudoku(int[][] sudoku) {
	for (int i = 0; i < stoerrelse; i++) {
	    for (int j = 0; j < stoerrelse; j++) {
		JButton knapp = (JButton) boksPaneler[i].getComponent(j);
		knapp.setText(String.valueOf(sudoku[i][j]));
	    }
	}
    }

    public static File velgFil() {
	JFileChooser filVelger = new JFileChooser();
	int retur = filVelger.showOpenDialog(null);
	return filVelger.getSelectedFile();
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == nesteLoesningKnapp) {
	    kontroll.visNesteLoesning();
	}
	else if (e.getSource() == forrigeLoesningKnapp) {
	    kontroll.visForrigeLoesning();
	}
    }

    private void malBokser() {
	Color[] farger = {Color.white, Color.blue, Color.red};
	int teller = 0;
	for (Component c : visningsPanel.getComponents()) {
	    c.setBackground(Color.blue);
	    JPanel panel = (JPanel) c;
	    for (Component knapp : panel.getComponents()) {
		knapp.setBackground(farger[teller % 3]);
		teller++;
		
	    }
	}
    }
}