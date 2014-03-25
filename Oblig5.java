class Oblig5 {
    public static void main(String[] args) {

	if (args.length > 0) {
	    Kontroll k = new Kontroll(args);
	    System.out.println("Ferdig");
	}
	else {
	    System.out.println("Husk filnavn");
	}
    }
}