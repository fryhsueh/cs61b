import java.util.ArrayList;
import java.util.Arrays;

public class NBody {
    /**get radius from given file, which is the second double */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }


    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();

        Body[] bodies = new Body[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
        }

        return bodies;
    }

    public static void drawBackGround(double radius) {
        StdDraw.setScale(radius * -1, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
    }

    public static void drawBodies(Body[] bodies) {
        for (Body b : bodies) {
            b.draw();
        }
    }
    
    public static void main(String[] args) {

        if (args.length > 0) {
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = args[2];
            double radius = readRadius(filename);
            Body[] bodies = readBodies(filename);

            // better draw performence
            StdDraw.enableDoubleBuffering();

            int size = bodies.length;

            double[] xForces = new double[size];
            double[] yForces = new double[size];

            double time = 0;


            // draw loop
            while (time < T) {
                //calculate the nex x and y forces for each Body
                for (int i = 0; i < size; i++) {
                    xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                    yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                }

                //update each Body's position, velocity, and acceleration
                for (int i = 0; i < size; i++) {
                    bodies[i].update(dt, xForces[i], yForces[i]);
                }

                StdDraw.clear();
                drawBackGround(radius);
                drawBodies(bodies);

                StdDraw.show();
                StdDraw.pause(10);
                time += dt;
            }
        }
       
    }
        
}
