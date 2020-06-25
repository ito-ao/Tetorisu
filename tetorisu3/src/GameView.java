import java.util.ArrayList;
import java.util.Collections;

import com.nompor.gtk.GTK;
import com.nompor.gtk.GTKColor;
import com.nompor.gtk.GTKFont;
import com.nompor.gtk.GTKView;
import com.nompor.gtk.draw.GTKGraphics;

public class GameView implements GTKView {


	GTKColor clr = GTK.createIntColor(0,80,150);
	GTKColor clr2 = GTK.createIntColor(0,0,0);
	GTKColor clr3 = GTK.createIntColor(255,255,255);

	GTKFont f1 = GTK.createFont(15);
	GTKFont f2 = GTK.createFont(40);

	//繝輔ぅ繝ｼ繝ｫ繝峨�ｮ蠎�縺�
	final int HEIGHT = 20;
	final int WIDTH = 10;
	//繝�繝舌ャ繧ｰ逕ｨ縺ｫ驟榊�怜ｮ｣險�縺ｯ逶ｴ譖ｸ縺阪↓縺励※縺�繧�
	int[][] fields = {
			 {0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}//5
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}//10
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}//15
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0,0}//20
	};
	
	static final int RED = 1;
	static final int SKYBLUE = 2;
	static final int BLUE = 3;
	static final int YELLOW = 4;
	static final int GREEN = 5;
	static final int ORANGE = 6;
	static final int PINK = 7;

	final int exox = GTK.getWidth() / 2 - 250;
	final int exoy = GTK.getHeight() / 2 - 300;
	final int ox = 125 + exox;
	final int oy = 50 + exoy;

	ArrayList<TetriMino> nextMinos = new ArrayList<>();
	ArrayList<TetriMino> minos = new ArrayList<>();
	TetriMino currentMino = null;
	TetriMino hold = null;
	int x,y;
	int rotate;
	int fallFrame;
	int highFallFrame;
	int moveFrame;
	boolean isHardDropMode;
	boolean isAlreadyHold;
	boolean isGameOver;

	public GameView() {
		//螳夂ｾｩ縺励◆繝溘ヮ縺ｮ遞ｮ鬘槭ｒ繝ｪ繧ｹ繝医↓逋ｻ骭ｲ縺励※縺�縺�

		//I
		minos.add(new TetriMino(new int[][][] {
			{
				 {0,0,0,0}
				,{1,1,1,1}
				,{0,0,0,0}
				,{0,0,0,0}
			}
			,{
				 {0,1,0,0}
				,{0,1,0,0}
				,{0,1,0,0}
				,{0,1,0,0}
			}
		}, SKYBLUE));

		//L
		minos.add(new TetriMino(new int[][][] {
			{
				 {0,0,0,0}
				,{0,1,1,1}
				,{0,1,0,0}
				,{0,0,0,0}
			}
			,{
				 {0,0,0,0}
				,{0,1,1,0}
				,{0,0,1,0}
				,{0,0,1,0}
			}
			,{
				 {0,0,0,0}
				,{0,0,1,0}
				,{1,1,1,0}
				,{0,0,0,0}
			}
			,{
				 {0,1,0,0}
				,{0,1,0,0}
				,{0,1,1,0}
				,{0,0,0,0}
			}
		}, ORANGE));

		//J
		minos.add(new TetriMino(new int[][][] {
			{
				 {0,0,0,0}
				,{0,1,0,0}
				,{0,1,1,1}
				,{0,0,0,0}
			}
			,{
				 {0,0,0,0}
				,{0,1,1,0}
				,{0,1,0,0}
				,{0,1,0,0}
			}
			,{
				 {0,0,0,0}
				,{1,1,1,0}
				,{0,0,1,0}
				,{0,0,0,0}
			}
			,{
				 {0,0,1,0}
				,{0,0,1,0}
				,{0,1,1,0}
				,{0,0,0,0}
			}
		}, BLUE));

		//T
		minos.add(new TetriMino(new int[][][] {
			{
				 {1,1,1}
				,{0,1,0}
				,{0,0,0}
			}
			,{
				 {0,1,0}
				,{1,1,0}
				,{0,1,0}
			}
			,{
				 {0,1,0}
				,{1,1,1}
				,{0,0,0}
			}
			,{
				 {0,1,0}
				,{0,1,1}
				,{0,1,0}
			}
		}, PINK));

		//O
		minos.add(new TetriMino(new int[][][] {
			{
				 {1,1}
				,{1,1}
			}
		}, YELLOW));

		//S
		minos.add(new TetriMino(new int[][][] {
			{
				 {0,1,1}
				,{1,1,0}
				,{0,0,0}
			}
			,{
				 {1,0,0}
				,{1,1,0}
				,{0,1,0}
			}
		}, RED));

		//Z
		minos.add(new TetriMino(new int[][][] {
			{
				 {1,1,0}
				,{0,1,1}
				,{0,0,0}
			}
			,{
				 {0,0,1}
				,{0,1,1}
				,{0,1,0}
			}
			,{
				 {0,0,0}
				,{0,1,1}
				,{1,1,0}
			}
			,{
				 {1,0,0}
				,{1,1,0}
				,{0,1,0}
			}
		}, GREEN));
		
		
		//V
//		minos.add(new TetriMino(new int [][][] {
//			{
//				 {0,0,0,0,0}
//				,{0,1,0,1,0}
//				,{0,0,1,0,0}			
//			}
//			,{
//				 {0,0,0,1,0}
//				,{0,0,1,0,0}
//				,{0,0,0,1,0}
//				
//			}
//			,{
//				 {0,0,0,0,0}
//				,{0,0,1,0,0}
//				,{0,1,0,1,0}
//				
//			}
//			,{
//				 {0,1,0,0,0}
//				,{0,0,1,0,0}
//				,{0,1,0,0,0}
//			}
//		},RED));
			
		Collections.shuffle(minos);
		minos.stream().forEach(nextMinos::add);
		Collections.shuffle(minos);
		minos.stream().forEach(nextMinos::add);

		currentMino = nextMinos.remove(0);

		x = 4;
		y = 0;
	}

	public void process() {
		Background.update();

		if ( currentMino == null ) return;

		if ( isGameOver ) {

			//迚ｹ螳壹�ｮ繧ｭ繝ｼ謚ｼ荳九〒繧ｿ繧､繝医Ν縺ｫ謌ｻ繧�
			if ( KeyAction.isActive(KeyAction.R_ROTATE) || KeyAction.isActive(KeyAction.HOLD) ) {
				GTK.changeViewDefaultAnimation(new TitleView());
			}
			return;
		}

		int yy = y;
		int xx = x;
		int rr = rotate;

		//繝上�ｼ繝峨ラ繝ｭ繝�繝�
		if ( KeyAction.isActive(KeyAction.HARDDROP) || isHardDropMode ) {
			isHardDropMode = true;
			fallFrame = 0;
			yy++;
		} else {

			boolean isDown =  KeyAction.isDown(KeyAction.DOWN);

			//蜿ｳ蝗櫁ｻ｢
			if ( KeyAction.isActive(KeyAction.R_ROTATE) ) {
				rr++;
			}
			//蟾ｦ蝗櫁ｻ｢
			else if ( KeyAction.isActive(KeyAction.L_ROTATE) ) {
				if (rr > 0) {
					rr--;
				} else {
					rr = currentMino.minos.length - 1;
				}
			}
			//蟾ｦ遘ｻ蜍�
			else if ( KeyAction.isActive(KeyAction.LEFT) ) {
				xx--;
				if ( moveFrame != 0 ) {
					moveFrame = 0;
				}
			}
			//蜿ｳ遘ｻ蜍�
			else if ( KeyAction.isActive(KeyAction.RIGHT) ) {
				xx++;
				if ( moveFrame != 0 ) {
					moveFrame = 0;
				}
			}
			//蟾ｦ謚ｼ縺礼ｶ壹￠
			else if ( KeyAction.isDown(KeyAction.LEFT) ) {
				moveFrame--;
				if ( moveFrame <= -12 ) {
					if ( moveFrame % 2 == 0 ) {
						xx--;
					}
				}
			}
			//蜿ｳ謚ｼ縺礼ｶ壹￠
			else if ( KeyAction.isDown(KeyAction.RIGHT) ) {
				moveFrame++;
				if ( moveFrame >= 12 ) {
					if ( moveFrame % 2 == 0 ) {
						xx++;
					}
				}
			}
			//HOLD
			else if ( KeyAction.isActive(KeyAction.HOLD) && !isAlreadyHold ) {
				if ( hold != null ) {
					nextMinos.add(0, hold);
				}
				isAlreadyHold = true;
				hold = currentMino;
				nextInit();
				return;
			}
			else {
				//荳狗ｧｻ蜍�
				if ( isDown ) {
					if ( highFallFrame == 0 ) {
						yy++;
					}
					highFallFrame++;
					if ( highFallFrame > 2 ) {
						highFallFrame = 0;
					}
					fallFrame = 0;
				}
			}
			if ( !isDown ) {
				highFallFrame = 0;
			}
		}

		//閾ｪ逕ｱ關ｽ荳九�ｮ蜃ｦ逅�
		fallFrame++;
		if ( fallFrame >= 60 ) {
			fallFrame = 0;
			if ( yy+1 >= fields.length ) {
				yy = 0;
			} else {
				yy++;
			}
		}

		//y縺悟刈邂励＆繧後◆縺ｨ縺阪�ｯ驟咲ｽｮ螳御ｺ�蛻､螳�
		int[][][] minos = currentMino.minos;
		int r = rr % minos.length;
		boolean isUpdate = true;
		label:
		for ( int i = 0;i < minos[r].length;i++ ) {
			for ( int j = 0;j < minos[r][i].length;j++ ) {
				if ( minos[r][i][j] != 0 ) {
					int fx = xx + j;
					int fy = yy + i;
					if ( fy >= HEIGHT || fx < 0 || fx >= WIDTH || fields[fy][fx] != 0 ) {
						isUpdate = false;
						break label;
					}
				}
			}
		}

		//蠎ｧ讓呎峩譁ｰ蜃ｦ逅�
		if (isUpdate) {
			x = xx;
			y = yy;
			rotate = rr;
		} else {
			//遘ｻ蜍穂ｸ崎�ｽ縺ｧy縺悟刈邂励＆繧後◆縺ｨ縺阪�ｯ驟咲ｽｮ螳御ｺ�蛻､螳�
			if ( yy > y && xx == x && rotate == rr ) {
				for ( int i = 0;i < minos[r].length;i++ ) {
					for ( int j = 0;j < minos[r][i].length;j++ ) {
						int fx = x + j;
						int fy = y + i;
						if ( minos[r][i][j] != 0 ) fields[fy][fx] = currentMino.color;
					}
				}

				//繝輔ぅ繝ｼ繝ｫ繝峨ｒ繝√ぉ繝�繧ｯ縺励�∵純縺｣縺溘Λ繧､繝ｳ繧貞炎髯､縺吶ｋ
				for ( int i = 0;i < fields.length;i++ ) {
					int cnt = 0;
					//繝悶Ο繝�繧ｯ縺檎ｽｮ縺九ｌ縺ｦ縺�繧区焚繧偵き繧ｦ繝ｳ繝�
					for ( int j = 0;j < fields[i].length;j++ ) {
						if ( fields[i][j] == 0 ) {
							break;
						}
						cnt++;
					}
					//繝ｩ繧､繝ｳ縺梧純縺｣縺ｦ縺�縺溘ｉ蜑企勁縺励※荳翫↓縺ゅｋ繝�繝医Μ繝溘ヮ繧偵☆縺ｹ縺ｦ荳�谿ｵ荳九∈縺壹ｉ縺�
					if ( cnt == WIDTH ) {
						//繝ｩ繧､繝ｳ蜑企勁
						for ( int j = 0;j < fields[i].length;j++ ) {
							fields[i][j] = 0;
						}

						//荳翫↓縺ゅｋ繝�繝医Μ繝溘ヮ繧偵☆縺ｹ縺ｦ荳�谿ｵ荳九∈縺壹ｉ縺�
						for ( int k = i - 1;k >= 0;k-- ) {
							for ( int j = 0;j < fields[i].length;j++ ) {
								fields[k+1][j] = fields[k][j];
							}
						}
					}
				}

				nextInit();
				isAlreadyHold = false;
			}
		}
		if ( isHardDropMode ) {
			process();
		}
	}

	private void nextInit() {
		isHardDropMode = false;
		highFallFrame = 0;
		fallFrame = 0;
		rotate = 0;
		x = 4;
		y = 0;
		currentMino = nextMinos.remove(0);

		//蜃ｺ迴ｾ莠亥ｮ壹ユ繝医Μ繝溘ヮ縺梧ｸ帙▲縺ｦ縺阪◆繧峨す繝｣繝�繝輔Ν縺励※谺｡縺ｮ荳�蟾｡蛻�繧呈諺蜈･
		if ( nextMinos.size() < 7 ) {
			Collections.shuffle(this.minos);
			this.minos.stream().forEach(nextMinos::add);
		}

		//1蛻礼岼縺�2蛻礼岼縺ｫ繝悶Ο繝�繧ｯ縺後≠縺｣縺滓凾轤ｹ縺ｧ繧ｲ繝ｼ繝�繧ｪ繝ｼ繝舌�ｼ縺ｫ縺吶ｋ
		for ( int i = 0;i < 2;i++ ) {
			for ( int j = 0;j < fields[0].length;j++ ) {
				if ( fields[i][j] != 0 ) {
					isGameOver = true;
				}
			}
		}
	}


	@Override
	public void draw(GTKGraphics g) {
		if ( currentMino == null ) return;
		g.setFont(f1);

		//閭梧勹鬘槭�ｮ謠冗判
		Background.draw(g);
		g.setColor(clr);
		g.fillRect(exox,exoy,500,600);

		//繝輔ぅ繝ｼ繝ｫ繝峨�ｮ謠冗判
		g.setColor(clr2);
		g.fillRect(ox,oy,250,500);
		for ( int i = 0;i < fields.length;i++ ) {
			for ( int j = 0;j < fields[i].length;j++ ) {
				b_draw_idx(g,j,i,fields[i][j]);
			}
		}

		//HOLD鬆伜沺縺ｮ謠冗判
		g.setColor(clr3);
		g.drawString("HOLD", exox+10,exoy+80);
		g.setColor(clr2);
		g.fillRect(exox+10,exoy+100,100,80);
		if ( hold != null ) {
			for ( int i = 0;i < hold.minos[0].length;i++ ) {
				for ( int j = 0;j < hold.minos[0][i].length;j++ ) {
					if ( hold.minos[0][i][j] != 0 ) {
						b_draw(
								g
								,j*TetriMino.BLOCK_SIZE+exox+10+50-(hold.initW+1)*TetriMino.BLOCK_SIZE/2-hold.initX*TetriMino.BLOCK_SIZE
								,i*TetriMino.BLOCK_SIZE+exoy+100+40-(hold.initH+1)*TetriMino.BLOCK_SIZE/2-hold.initY*TetriMino.BLOCK_SIZE
								,hold.color
						);
					}
				}
			}
		}

		//NEXT鬆伜沺縺ｮ謠冗判
		g.setColor(clr3);
		g.drawString("NEXT", exox+390,exoy+80);
		g.setColor(clr2);
		for ( int k = 0;k < 4;k++ ) {
			int nextXOff = exox+390;
			int nextYOff = exoy+100+100*k;
			g.fillRect(nextXOff,nextYOff,100,80);
			TetriMino mino = nextMinos.get(k);
			int[][][] minos = mino.minos;
			for ( int i = 0;i < minos[0].length;i++ ) {
				for ( int j = 0;j < minos[0][i].length;j++ ) {
					if ( minos[0][i][j] != 0 ) {
						b_draw(
								g
								,j*TetriMino.BLOCK_SIZE+nextXOff+50-(mino.initW+1)*TetriMino.BLOCK_SIZE/2-mino.initX*TetriMino.BLOCK_SIZE
								,i*TetriMino.BLOCK_SIZE+nextYOff+40-(mino.initH+1)*TetriMino.BLOCK_SIZE/2-mino.initY*TetriMino.BLOCK_SIZE
								,mino.color
						);
					}
				}
			}
		}

		//迴ｾ蝨ｨ關ｽ荳倶ｸｭ縺ｮ繝溘ヮ縺ｮ謠冗判
		int[][][] minos = currentMino.minos;
		int r = rotate % minos.length;
		for ( int i = 0;i < minos[r].length;i++ ) {
			for ( int j = 0;j < minos[r][i].length;j++ ) {
				if ( minos[rotate % minos.length][i][j] != 0 ) {
					b_draw_idx(g,x+j,y+i,currentMino.color);
				}
			}
		}

		//繧ｲ繝ｼ繝�繧ｪ繝ｼ繝舌�ｼ謠冗判
		if ( isGameOver ) {
			g.setColor(clr2);
			g.setAlpha(0.5);
			g.fillRect(ox,oy,250,500);
			g.setColor(clr3);
			g.setAlpha(1);
			String tx = "GAMEOVER";
			g.setFont(f2);
			int w = (int) g.stringWidth(tx);
			int h = (int) g.stringHeight(tx);
			g.drawString(tx, ox+125-w/2, oy+250-h/2);
		}

	}

	//繝悶Ο繝�繧ｯ謠冗判
	private void b_draw_idx(GTKGraphics g, int x, int y, int color) {
		b_draw(g, x*TetriMino.BLOCK_SIZE+ox, y*TetriMino.BLOCK_SIZE+oy,color);
	}

	private void b_draw(GTKGraphics g, int x, int y, int color) {
		switch(color) {
		case RED:g.drawImage(Resource.b_red, x, y);break;
		case SKYBLUE:g.drawImage(Resource.b_skyblue, x, y);break;
		case BLUE:g.drawImage(Resource.b_blue, x, y);break;
		case YELLOW:g.drawImage(Resource.b_yellow, x, y);break;
		case GREEN:g.drawImage(Resource.b_green, x, y);break;
		case ORANGE:g.drawImage(Resource.b_orange, x, y);break;
		case PINK:g.drawImage(Resource.b_pink, x, y);break;
		}
	}
}
