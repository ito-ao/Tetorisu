import java.util.Arrays;

public class TetriMino{
	
	int[][][] minos;
	int color;
	int initX=99,initY=99,initW,initH;

	
	static final int BLOCK_SIZE = 25;

	TetriMino(int[][][] minos, int color){
		this.minos = minos;
		this.color = color;

		Arrays.stream(minos).forEach(e -> Arrays.stream(e).forEach(intarr ->{
			for ( int i = 0;i < intarr.length;i++ ) {
				if ( intarr[i] == 1 ) {
					intarr[i] = color;
				}
			}
		}));

		
		int r=0,b=0;
		for ( int i = 0;i < minos[0].length;i++ ) {
			for ( int j = 0;j < minos[0][i].length;j++ ) {
				if ( minos[0][i][j] != 0 ) {
					initX = Math.min(initX, j);
					initY = Math.min(initY, i);
					r = Math.max(r, j);
					b = Math.max(b, i);
				}
			}
		}
		initW = r - initX;
		initH = b - initY;
	}
}
