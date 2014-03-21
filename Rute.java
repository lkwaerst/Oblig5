abstract class Rute {
    protected Brett brett;
    private Kolonne kolonne;
    private Boks boks;
    private Rad rad;
    private int tall;
    protected Rute neste;
    private int grense;;
    
    
    Rute(Rad rad, Kolonne kol, Boks boks, int verdi, Brett brett) {
	this.boks = boks;
	kolonne = kol;
	this.rad = rad;
	tall = verdi;
	this.brett = brett;
	grense = brett.getGrense();
    }
    //proever foerst aa finne lovlig verdi
    public SudokuBeholder fyllUtRestenAvBrettet(SudokuBeholder loesninger) {
	for (int i = 1; i <= grense; i++) {
	    //setter inn tallet i bokser osv om det passer
	    if (boks.tallPasser(i) && rad.tallPasser(i) && kolonne.tallPasser(i)) {
		settInnTall(i);
		//her lagres en ny løsning 
		if (neste == null) {
		    loesninger.add(brett.copy());
		}
		else {
		    neste.fyllUtRestenAvBrettet(loesninger);
		}
		//proever neste tall
		taUtTall(i);
	    }
	}
	//proevd alle tall
	return loesninger;
    }
    
    public void setNeste(Rute r) {
	neste = r;
    }
    
    public void skriv() {
	System.out.print(tall + " ");
	if (neste != null) {
	    neste.skriv();
	}
    }

    public void settInnTall(int tall) {
	this.tall = tall;
	boks.settInn(tall);
	rad.settInn(tall);
	kolonne.settInn(tall);
    }

    public void taUtTall(int tall) {
	this.tall = 0;
	boks.taUt(tall);
	rad.taUt(tall);
	kolonne.taUt(tall);
    }
    
    public int hentTall() {
	return tall;
    }
}