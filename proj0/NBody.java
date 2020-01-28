public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[num];
        for(int i=0; i<num; i++) {
            bodies[i] = new Body(in.readDouble(),in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());

        }
        return bodies;
    }

    public static void main(String[] args){

        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();

        /** Sets up the universe so it goes from
         * -100, -100 up to 100, 100 */
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();
        // use StdDraw library to draw the background

        int timeParse = 0;
        double[] xForce = new double[bodies.length];
        double[] yForce = new double[bodies.length];
        while(timeParse<T) {
            for(int i=0; i<bodies.length; i++) {
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i=0; i<bodies.length; i++) {
                bodies[i].update(dt,xForce[i],yForce[i]);
                bodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            StdDraw.clear();
            timeParse += dt;

        }

        StdDraw.show();


    }

}
