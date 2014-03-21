import java.util.*;
import java.io.*;

class Kontroll {
    int[][] utgangspunkt;
    int lengde;
    int boksHoeyde;
    int boksLengde;

    Kontroll() {
	try {
	    lesFil("fil.txt");
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	for (int i = 0; i < lengde; i++) {
	    for(int j = 0; j < lengde; j++) {
		System.out.printf("%2d", utgangspunkt[i][j]);
	    }
	    System.out.println();
	}
	Brett b = new Brett(utgangspunkt, lengde, boksLengde, boksHoeyde); 
	int[][] ff = new int[9][9];
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		ff[i][j] = -1;
	    }
	}
	b = new Brett(ff, 9, 3, 3);	    	    
	SudokuBeholder s = b.finnLoesninger();
	System.out.println("Antall loesninger: " + s.getAntallLoesninger());
    }

    //fyller ut utgangspunkt[] med verdier slik at den representerer sudokuen i filen
    public void lesFil(String filnavn) throws Exception {
	Scanner les = new Scanner(new File(filnavn));
	
	//finner dimensjonene til tabellen
	boksHoeyde = les.nextInt();	
	boksLengde = les.nextInt();
	lengde = boksHoeyde * boksLengde;
	utgangspunkt = new int[lengde][lengde];
	while (les.hasNext()) {
	    nesteTall(Integer.parseInt(les.next().replace(".", "-1")));
	}
    }

    //setter neste tall inn paa riktig plass i arrayen
    public void nesteTall(int tall) {
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
}
	

	
