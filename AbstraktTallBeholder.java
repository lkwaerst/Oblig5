//-1 for tomme plasser
abstract class AbstraktTallBeholder {

    int[] tallSamling;

    AbstraktTallBeholder(int[] tall) {
	this.tallSamling = tall;
    }

    public void settInn(int tall) {
	for (int i = 0; i < tallSamling.length; i++) {
	    if (tallSamling[i] == -1) {
		tallSamling[i] = tall;
		return;
	    }
	}
    }

    public void taUt(int tall) {
	for (int i = 0; i < tallSamling.length; i++) {
	    if (tallSamling[i] == tall) {
		tallSamling[i] = -1;
		return;
	    }
	}
    }
    
    public boolean tallPasser(int tall) {
	for (int i = 0; i < tallSamling.length; i++) {
	    if (tallSamling[i] == tall) {
		return false;
	    }
	}
	return true;
    }
}