import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Particles {

	private ArrayList<Particle> particles;

	public Particles() {
		particles = new ArrayList<Particle>();
	}

	public void update(boolean pressed, int x, int y) {
		if (pressed) {
			particles.add(new Particle(x, y));
		}
		for (int i = (particles.size() - 1); i >= 0; i--) {
			particles.get(i).update();
			if (particles.get(i).isReadyToDel()) {
				particles.remove(i);
			}
		}
	}

	public void render(Graphics g) {
		for (int i = (particles.size() - 1); i >= 0; i--) {
			particles.get(i).render(g);
		}
		g.setColor(Color.GREEN);
		g.drawString("Particles: " + particles.size(), 20, 20);
	}


}