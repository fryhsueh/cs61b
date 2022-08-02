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
    
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

    }
}
