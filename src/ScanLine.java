import java.util.Optional;
import java.awt.Point;


public class ScanLine {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	public ScanLine(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Optional<Point> intersect(Surface surface) {
		double s1_x = x2 - x1;
		double s1_y = y2 - y1;
		double s2_x = surface.x2 - surface.x1;
		double s2_y = surface.y2 - surface.y1;
		
		double s = (-s1_y * (x1 - surface.x1) + s1_x * (y1 - surface.y1)) / (-s2_x * s1_y + s1_x * s2_y); 
		double t = (s2_x * (y1 - surface.y1) - s2_y * (x1 - surface.x1)) / (-s2_x * s1_y + s1_x * s2_y); 
		
		if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
			int x = (int) (x1 + (t * s1_x));
			int y = (int) (y1 + (t * s1_y));
					
			return Optional.of(new Point(x, y));
		}
		else {
			return Optional.empty();
		}
	}
}

