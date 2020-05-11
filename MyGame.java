import java.awt.Graphics;
import java.awt.event.MouseEvent;


public class MyGame extends Game {

	private Particles particles;
	private boolean pressed;
	private int mx;
	private int my;



	public MyGame(){
		particles = new Particles();
	}

	@Override
	public void updateClass(){
		particles.update(pressed, mx, my);
	}

	@Override
	public void renderClass(Graphics g){
		renderBackground();
		particles.render(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

}
