class Test {
    public static void main(String[] args) {
	int[][] ja = new int[2][2];
	ja[0][0] = 0;
	ja[0][1] = 1;
	ja[1][0] = 2;
	ja[1][1] = 3;


	int[][] flippedArray = new int[2][2];
	for (int i = 0; i < 2; i++) {
	    for (int j = 0; j < 2; j++) {
		flippedArray[i][j] = ja[j][i];
	    }
	}

	int[] radEn = flippedArray[0];
	int[] radTo = flippedArray[1];

	for (int i : radEn) {
	    System.out.print(i);
	}
	System.out.println();
	for (int i : radTo) {
	    System.out.print(i);
	}
    }
}