import java.util.*;
import java.io.*;

class Kontroll {

    SudokuBeholder loesninger;
    Brett oppgave;
    int[][] utgangspunkt;
    int lengde;
    int boksHoeyde;
    int boksLengde;

    Kontroll(String[] filnavn) {
	try {
	    lesFil(filnavn[0]);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	
	oppgave = new Brett(utgangspunkt, lengde, boksLengde, boksHoeyde);
	loesninger = oppgave.finnLoesninger();
	//test
	// int[][] ff = new int[9][9];
	// for (int i = 0; i < 9; i++) {
	//     for (int j = 0; j < 9; j++) {
	// 	ff[i][j] = -1;
	//     }
	// }
	// b = new Brett(ff, 9, 3, 3);	    	    
	// SudokuBeholder s = b.finnLoesninger();
	// System.out.println("Antall loesninger: " + s.getAntallLoesninger());

	try {
	    skrivTilFil(filnavn[1]);
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    skrivTilTerminal();
	}
	catch (FileNotFoundException e) {
	    System.out.println("Fant ikke srivefila");
	}
	catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println("Antall loesninger: " + loesninger.getAntLoesninger());
    }

    //fyller ut utgangspunkt[] med verdier slik at den representerer sudokuen i filen
    private void lesFil(String filnavn) throws Exception {
	Scanner les = new Scanner(new File(filnavn));
	
	//finner dimensjonene til tabellen
	boksHoeyde = les.nextInt();	
	boksLengde = les.nextInt();
	lengde = boksHoeyde * boksLengde;
	utgangspunkt = new int[lengde][lengde];
	while (les.hasNext()) {
	    nesteTall(les.next());
	}
    }

    //setter neste tall inn paa riktig plass i arrayen
     private void nesteTall(String info) {
	int tall;
	info = info.replace(".", "-1");
	String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	CharSequence c = info;
	if (alfabet.contains(c)) {
	    tall = alfabet.indexOf(info) + 10;
	}
	else {
	    tall = Integer.parseInt(info);
	}

	boolean ferdig = false;
	for (int i = 0; i < lengde && !ferdig; i++) {
	    for (int j = 0; j < lengde && !ferdig; j++) {
		if (utgangspunkt[i][j] == 0) {
		    utgangspunkt [i][j] = tall;
		    ferdig = true;
		    
		}
	    }
	}
    }

    private void skrivTilFil(String filnavn) throws Exception {
	PrintWriter skriv = new PrintWriter(new FileWriter(new File(filnavn)));
	boolean kortVersjon = loesninger.getAntLoesninger() > 5;
	
	for (Brett b : loesninger) {
	    b.skrivTilFil(skriv, kortVersjon);
	    skriv.println("\n");
	}
	skriv.close();
    }

    private void skrivTilTerminal() {
	boolean kortVersjon = loesninger.getAntLoesninger() > 5;
	for (Brett b : loesninger) {
	    b.skrivTilTerminal(kortVersjon);
	    System.out.println("\n");
	}
    }
}
	

	
