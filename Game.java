import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public abstract class Game implements Runnable, MouseListener, MouseMotionListener {

	private JFrame jframe;

	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	private Graphics g;
	private boolean running;
	private Thread t;
	public static final int FPS = 30;
	public static final long ONE_NANO = 1_000_000_000;
	public static final long LIMIT = ONE_NANO / FPS;
	private BufferStrategy bs;
	private Canvas canvas;



	public Game() {

		jframe = new JFrame();
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);

		canvas = new Canvas();
		Dimension d = new Dimension(WIDTH, HEIGHT);
		canvas.setPreferredSize(d);
		canvas.setMinimumSize(d);
		canvas.setMaximumSize(d);

		jframe.add(BorderLayout.CENTER, canvas);
		jframe.pack();

		canvas.createBufferStrategy(2);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		jframe.setIgnoreRepaint(true);
		canvas.setFocusable(false);

		t = new Thread(this);
	}

	public void start(){
		running = true;
		jframe.setVisible(true);
		t.start();
	}

	private void update(){
		updateClass();
	}

	public abstract void updateClass();

	private void render(){
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		renderClass(g);
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public abstract void renderClass(Graphics g);

	@Override
	public void run(){
		long ini;
		long end;
		long delta;
		long wait;
		while (running) {
			ini = System.nanoTime();
			update();
			render();
			end = System.nanoTime();
			delta = end - ini;
			wait = LIMIT - delta;
			if (wait > 0) {
				try{
					wait = wait/1_000_000;
					Thread.sleep(wait);
				} catch(Exception ex){}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	public void renderBackground(){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

}
