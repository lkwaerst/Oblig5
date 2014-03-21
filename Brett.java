class Brett {

    private Rute [][] ruter;
    private Boks[] bokser;
    private Kolonne[] kolonner;
    private Rad[] rader;
    private int stoerrelse; //lengde og bredde
    private int boksLengde;
    private int boksHoeyde;

    Brett(int[][] info, int stoerrelse, int boksLengde, int boksHoeyde) {
	ruter = new Rute[stoerrelse][stoerrelse];
	bokser = new Boks[stoerrelse];
	kolonner = new Kolonne[stoerrelse];
	rader = new Rad[stoerrelse];
	this.stoerrelse = stoerrelse;
	this.boksLengde = boksLengde;
	this.boksHoeyde = boksHoeyde;


	//lager rader
	for (int i = 0; i < stoerrelse; i++) {
	    rader[i] = new Rad(info[i]);
	}
	
	//snur om paa arrayen for aa lage kolonner
	int[][] snuddInfo  = snuTabell(info);
	for (int i = 0; i < stoerrelse; i++) {
	    kolonner[i] = new Kolonne(snuddInfo[i]);
	}
	
	//forvrenger arrayen og lager bokser
	int[][] boksArray = boksTabell(info);
	for (int i = 0; i < stoerrelse; i++) {
	    bokser[i] = new Boks(boksArray[i]);
	}

	//test
	// System.out.println("\n\n");
	// for (int i = 0; i < stoerrelse; i++) {
	//     for (int j = 0; j < stoerrelse; j++) {
	// 	System.out.printf("%2d", boksArray[i][j]);
	//     }
	//     System.out.println();
	// }
	
	
	//lager rute objekter
	Rute forrigeRute = new AapenRute(rader[0], kolonner[0], finnBoks(0,0), this); //forsvinner etterhvert
	for (int i = 0; i < stoerrelse; i++) {
	    for (int j = 0; j < stoerrelse; j++) {
		if (info[i][j] == -1) {
		    ruter[i][j] = new AapenRute(rader[i], kolonner[j], finnBoks(i, j), this);	
		}
		else {
		    ruter[i][j] = new UtfyltRute(rader[i], kolonner[j], finnBoks(i, j), info[i][j], this);
		}
		//nestepekere
		forrigeRute.setNeste(ruter[i][j]);
		forrigeRute = ruter[i][j];
	    }
	}
	
    }

    public int[][] snuTabell(int[][] array) {
	int[][] nyArray = new int[stoerrelse][stoerrelse];
	for (int i = 0; i < stoerrelse; i++) {
	    for (int j = 0; j < stoerrelse; j++) {
		nyArray[i][j] = array[j][i];
	    }
	}
	return nyArray;
    }
    
    //snur tabellen paa en maate som gjoer at hver rad blir informasjonen om en boks
    public int[][] boksTabell(int[][] array) {
	int[][] nyArray = new int[stoerrelse][stoerrelse];
	int boksTellerx = 0;
	int boksTellery = 0;

	for (int a = 0; a < stoerrelse/boksHoeyde; a++) {
	    for (int b = 0; b < stoerrelse/boksLengde; b++) {
		for (int i = 0; i < boksHoeyde; i++) {
		    for (int j = 0; j < boksLengde; j++) {
			nyArray [boksTellery][boksTellerx] = array[(boksHoeyde*a)+i][(boksLengde*b)+j];
			boksTellerx++;	
		    }
		}
		boksTellery++;
		boksTellerx = 0;	
	    }
	}
	return nyArray;
    }

    public Boks finnBoks(int i, int j) {
	int boksRad = i/boksHoeyde;
	int boksKolonne = j/boksLengde;
	int bokserPerRad = stoerrelse/boksLengde;
	int boksNr = boksRad * bokserPerRad + boksKolonne;
	return bokser[boksNr];
    }

    public Brett copy() {
	int[][] oppdatertInfo = new int[stoerrelse][stoerrelse];
	for (int i = 0; i < stoerrelse; i++) {
	    for (int j = 0; j < stoerrelse; j++) {
		oppdatertInfo[i][j] = ruter[i][j].hentTall();
	    }
	}
	return new Brett(oppdatertInfo, stoerrelse, boksLengde, boksHoeyde);
    }
		

    public void skriv() {
	System.out.println("Her er et brett: ");
	ruter[0][0].skriv(); 
    } 

    public SudokuBeholder finnLoesninger() {
	return ruter[0][0].fyllUtRestenAvBrettet(new SudokuBeholder());
    }

    public int getGrense() {
	return stoerrelse;
    }
}