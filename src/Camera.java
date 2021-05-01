import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.Graphics2D;
import java.util.Set;

public class Camera {
	private double x;
	private double y;
	private double a;
	private double fov;
	private double scanlines;
	private double distance;
	
	public Camera(double x, double y, double a, double fov, double scanlines, double distance) {
		this.x = x;
		this.y = y;
		this.a = a;
		this.fov = fov;
		this.scanlines = scanlines;
		this.distance = distance;
	}
	
	public void turn(double angle) {
		this.a += angle;
	}
	
	public void move(double distance, Set<Surface> world) {
		double x2 = this.x + Math.cos(this.a + Math.PI / 4) * distance;
		double y2 = this.y + Math.sin(this.a + Math.PI / 4) * distance;
		
		for (Surface surface : world) {
			double s1_x = x2 - x;
			double s1_y = y2 - y;
			double s2_x = surface.x2 - surface.x1;
			double s2_y = surface.y2 - surface.y1;
			
			double s = (-s1_y * (x - surface.x1) + s1_x * (y - surface.y1)) / (-s2_x * s1_y + s1_x * s2_y); 
			double t = (s2_x * (y - surface.y1) - s2_y * (x - surface.x1)) / (-s2_x * s1_y + s1_x * s2_y); 
			
			if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {	
				return;
			}
		}
		
		this.x += Math.cos(this.a + Math.PI / 4) * distance;
		this.y += Math.sin(this.a + Math.PI / 4) * distance;
	}
	public void move2(double distance, Set<Surface> world) {
		double x2 = this.x + Math.cos(this.a - Math.PI / 4) * distance;
		double y2 = this.y + Math.sin(this.a - Math.PI / 4) * distance;
		
		for (Surface surface : world) {
			double s1_x = x2 - x;
			double s1_y = y2 - y;
			double s2_x = surface.x2 - surface.x1;
			double s2_y = surface.y2 - surface.y1;
			
			double s = (-s1_y * (x - surface.x1) + s1_x * (y - surface.y1)) / (-s2_x * s1_y + s1_x * s2_y); 
			double t = (s2_x * (y - surface.y1) - s2_y * (x - surface.x1)) / (-s2_x * s1_y + s1_x * s2_y); 
			
			if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {	
				return;
			}
		}
		
		this.x += Math.cos(this.a - Math.PI / 4) * distance;
		this.y += Math.sin(this.a - Math.PI / 4) * distance;
	}
	
	public void render(Graphics2D g, Set<Surface> world) {
		g.setColor(new Color(209, 164, 42));
		g.fillRect(0, 500, 1000, 500);
		g.setColor(new Color(66,239,245));
		g.fillRect(0, 0, 1000, 500);
		
		for (int i = 0; i < scanlines; i += 1) {
			double theta = a + fov / scanlines * i;
			double dx = x + distance * Math.cos(theta);
			double dy = y + distance * Math.sin(theta);
			
			ScanLine scanline = new ScanLine(x, y, dx, dy);
			Color smaple_color = null;
			double cadistance = Double.POSITIVE_INFINITY;
			
			for (Surface surface : world) {
				Optional<Point> p = scanline.intersect(surface);
				
				if (p.isPresent()) {
					double distance = Math.hypot(x - p.get().x, y - p.get().y);
					if (distance < cadistance) {
						cadistance = distance;
						smaple_color = surface.color;
					}
				}
			}
			
			if (smaple_color != null) {
				int height = (int) Math.round(100 / cadistance * 1000);
				
				g.setColor(smaple_color);
				g.fillRect((int) (1000 / scanlines * i), 500 - height / 2, (int) (Math.round(1000 / scanlines)), height);
			}
		}
	}
}


// _ 	      /\          /\          /\          /\          /\          /\          /\          /\        /\        /\        /\        _
// \\ 	     //\\        //\\        //\\        //\\        //\\        //\\        //\\        //\\      //\\      //\\      //\\      //
//  \\      //  \\      //  \\
//   \\    //    \\    //    \\    //    \\    //    \\    //    \\    //  \\    //  \\    //  \\    //  \\    //  \\    //  \\    //
//    \\  //      \\  //      \\  //      \\  //      \\  //      \\  //    \\  //    \\  //    \\  //    \\  //    \\  //    \\  //
//     \\//        \\//        \\//        \\//        \\//        \\//      \\//      \\//      \\//      \\//      \\//      \\//
//	    \/          \/          \/          \/          \/          \/        \/        \/        \/        \/        \/	     \/	