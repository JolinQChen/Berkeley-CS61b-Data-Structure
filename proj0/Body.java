public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    double mass;
    String imgFileName;
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        // a copy
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b2) {
        double res = 0.0;
        res = Math.pow(this.xxPos - b2.xxPos,2) + Math.pow(this.yyPos - b2.yyPos,2);
        res = Math.pow(res,0.5);
        return res;
    }

    public double calcForceExertedBy(Body b) {
        double G = 6.67 * Math.pow(10,-11);
        double dist = this.calcDistance(b);
        double res = G * b.mass * this.mass / Math.pow(dist,2);
        return res;
    }
    public double calcForceExertedByX(Body b) {

        double force = this.calcForceExertedBy(b);
        double dist = this.calcDistance(b);
        if(dist == 0) return 0;
        double res = force*(b.xxPos-this.xxPos)/dist;
        return res;
    }
    public double calcForceExertedByY(Body b) {
        double force = this.calcForceExertedBy(b);
        double dist = this.calcDistance(b);
        if(dist == 0) return 0;
        double res = force*(b.yyPos-this.yyPos)/dist;
        return res;
    }

    public  double calcNetForceExertedByX(Body[] bodies) {
        double res = 0;
        for(Body body : bodies) {
            res += this.calcForceExertedByX(body);
        }
        return res;
    }
    public  double calcNetForceExertedByY(Body[] bodies) {
        double res = 0;
        for(Body body : bodies) {
            res += this.calcForceExertedByY(body);
        }
        return res;
    }
    public void update(double dt, double xForce, double yForce) {
        double accX = xForce/this.mass;
        double accY = yForce/this.mass;
        this.xxVel = this.xxVel + accX * dt;
        this.yyVel = this.yyVel + accY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        //StdDraw.picture(0, 0, "/Users/jolinchen/Documents/courses/cs61b/skeleton-sp19-master/proj0/images/" + this.imgFileName);
    }

}