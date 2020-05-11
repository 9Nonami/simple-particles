import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Particle {

	private final int speedX;
	private final int speedY;
	private int x;
	private int y;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;

	public Particle(int x, int y) {
		this.x = x;
		this.y = y;
		speedX = ((new Random().nextInt(10)) - 5);
		speedY = ((new Random().nextInt(10)) + 1);
	}

	public void update() {
		x += speedX;
		y -= speedY;
	}

	public void render(Graphics g) {
		int red = new Random().nextInt(256);
		int green = new Random().nextInt(256);
		int blue = new Random().nextInt(256);
		g.setColor(new Color(red, green, blue));
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

	public boolean isReadyToDel() {
		return ((x + WIDTH) < 0) || (x > Game.WIDTH) || ((y + HEIGHT) < 0);
	}

}