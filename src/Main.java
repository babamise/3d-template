import java.awt.Color;
import java.awt.Frame;
import java.awt.image.BufferStrategy;
import java.util.Set;
import java.util.HashSet;
import java.awt.Graphics2D;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame("yey");
		
		frame.setSize(1000, 1000);
		frame.setBackground(Color.cyan);
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		
		Camera camera = new Camera(0, 0, 0, 1.5, 130, 2000);
		Set<Surface> world = new HashSet();
		
		world.add(new Surface(442,-442,442,442,Color.red));
		world.add(new Surface(442,442,-442,442,Color.green));
		world.add(new Surface(-442,442,-442,-442,Color.red));
		world.add(new Surface(-442,-442,442,-442,Color.green));
	// pillar 1
		world.add(new Surface(100,100,110,100,Color.red));
		world.add(new Surface(110,100,110,110,Color.green));
		world.add(new Surface(110,110,100,110,Color.red));
		world.add(new Surface(100,110,100,100,Color.green));
		//pillar 2
		world.add(new Surface(150,100,160,100,Color.red));
		world.add(new Surface(160,100,160,110,Color.green));
		world.add(new Surface(160,110,150,110,Color.red));
		world.add(new Surface(150,110,150,100,Color.green));
		//pillar 3
		world.add(new Surface(100,150,110,150,Color.red));
		world.add(new Surface(110,150,110,160,Color.green));
		world.add(new Surface(110,160,100,160,Color.red));
		world.add(new Surface(100,160,100,150,Color.green));
		//pillar 4
		world.add(new Surface(150,150,160,150,Color.red));
		world.add(new Surface(160,150,160,160,Color.green));
		world.add(new Surface(160,160,150,160,Color.red));
		world.add(new Surface(150,160,150,150,Color.green));
		
		world.add(new Surface(-100,100,-210,100,Color.red));
		world.add(new Surface(-210,100,-210,110,Color.green));
		world.add(new Surface(-210,110,-100,110,Color.red));
		world.add(new Surface(-100,110,-100,100,Color.green));
		
		world.add(new Surface(-200,-200,-170,-170,Color.blue));
		world.add(new Surface(-170,-170,-170,-140,Color.white));
		world.add(new Surface(-170,-140,-200,-110,Color.blue));
		world.add(new Surface(-200,-200,-230,-200,Color.white));
		world.add(new Surface(-230,-200,-260,-170,Color.blue));
		world.add(new Surface(-260,-170,-442,-442,Color.white));
		world.add(new Surface(-170,-140,-200,-442,Color.white));
		BufferStrategy butter = frame.getBufferStrategy();
		while (true) {
			Graphics2D g = (Graphics2D) butter.getDrawGraphics();

			camera.render(g, world);
			
			
			if (Input2.isWPressed()) {
				camera.move(0.5, world);
			}
			if (Input2.isSPressed()) {
				camera.move(-0.5, world);
			}
			if (Input2.isAPressed()) {
				camera.move2(0.5, world);
			}
			if (Input2.isDPressed()) {
				camera.move2(-0.5, world);
			}
			if (Input2.isQPressed()) {
				camera.turn(0.005);
			}
			if (Input2.isEPressed()) {
				camera.turn(-0.005);
			}
			
			g.dispose();
			butter.show();
		} 
	} 
}
