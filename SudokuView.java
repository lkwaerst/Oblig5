import javax.swing.*;
import java.awt.*;
import java.io.File;

class SudokuView {

    Brett brett;
    JFrame vindu;
    int[][] sudoku;
    int stoerrelse;
    JPanel visningsPanel;
    JPanel[] boksPaneler;
    JPanel kontrollPanel;
    
    SudokuView (int[][] sudoku, int boksHoeyde, int boksLengde) {
	this.sudoku = sudoku;
	stoerrelse = sudoku[0].length;
	brett = new Brett(sudoku, stoerrelse, boksLengde, boksHoeyde);

	vindu = new JFrame("sudoku");
	visningsPanel = new JPanel();
	visningsPanel.setLayout(new GridLayout(stoerrelse/boksHoeyde, stoerrelse/boksLengde ));
	visningsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

	/*lager paneler med grid layout for hver boks, putter riktige verdier inn i dem,
	  og legger dem inn i hovedpanelet*/
	boksPaneler = new JPanel[stoerrelse];
	int[][] boksArray = brett.boksTabell(sudoku);
	for (int i = 0; i < stoerrelse; i++) {
	    boksPaneler[i] = new JPanel();
	    boksPaneler[i].setLayout(new GridLayout(boksHoeyde, boksLengde));
	    boksPaneler[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
	    for (int j = 0; j < stoerrelse; j++) {
		JButton knapp = new JButton(String.valueOf(boksArray[i][j]));
		knapp.setEnabled(false);
		boksPaneler[i].add(knapp);
	    }
	    visningsPanel.add(boksPaneler[i]);
	}

	kontrollPanel = new JPanel(new FlowLayout());
	kontrollPanel.add(new JButton("Velg fil"));
	kontrollPanel.add(new JButton("<<"));
	kontrollPanel.add(new JButton(">>"));

	vindu.add(visningsPanel, BorderLayout.CENTER);
	vindu.add(kontrollPanel, BorderLayout.SOUTH);
	vindu.add(new JPanel(), BorderLayout.EAST);
	vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	vindu.setSize(800, 800);
	vindu.setVisible(true);

	velgFil();
    }

    public static void main (String[] args) {
	int teller = 1;
	int[][] a = new int[9][9];

	for (int i = 0; i < a[0].length; i++) {
	    for (int j = 0; j < a[0].length; j++) {
		a[i][j] = teller++;
	    }
	}

	SudokuView s = new SudokuView(a, 3, 3);
    }

    public void nySudoku(int[][] sudoku) {
	for (int i = 0; i < stoerrelse; i++) {
	    for (int j = 0; j < stoerrelse; j++) {
		JButton knapp = (JButton) boksPaneler[i].getComponent(j);
		knapp.setText(String.valueOf(sudoku[i][j]));
	    }
	}
    }

    public void velgFil() {
	JFileChooser filVelger = new JFileChooser();
	int retur = filVelger.showOpenDialog(vindu);
    }
}