
abstract class Rute {

    protected Brett brett;
    private Kolonne kolonne;
    private Boks boks;
    private Rad rad;
    private int tall;
    protected Rute neste;
    private int grense;
    boolean skrivUt = true;
    
    
    Rute(Rad rad, Kolonne kol, Boks boks, int verdi, Brett brett) {
	this.boks = boks;
	kolonne = kol;
	this.rad = rad;
	tall = verdi;
	this.brett = brett;
	grense = brett.getGrense();
    }
    /*metoden finner et tall som passer i ruten og kaller saa paa neste rutes
      metode. Om den siste ruten finner et tall som passer, lages en kopi av
      brettet slik det ser ut da, som saa legges i sudokubeholderen*/
    public SudokuBeholder fyllUtRestenAvBrettet(SudokuBeholder loesninger) {
	for (int i = 1; i <= grense; i++) {
	    if (boks.tallPasser(i) && rad.tallPasser(i) && kolonne.tallPasser(i)) {
		settInnTall(i);
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
    
    //returnerer streng av tallet som er i ruten, bokstav om over 9
    public String getInnhold() {
	String retur = "";
	String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	if (this.tall > 9) {
	    retur = String.valueOf(alfabet.charAt(this.tall - 10));
	}
	else {
	    retur = String.valueOf(this.tall);
	}

	return retur;
    }
}