public class Body {
    public static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(
                Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }

    public double calcForceExertedBy(Body b) {
        double distance = this.calcDistance(b);
        return G * this.mass * b.mass / Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - this.xxPos;
        double distance = calcDistance(b);
        return calcForceExertedBy(b) * dx / distance;
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - this.yyPos;
        double distance = calcDistance(b);
        return calcForceExertedBy(b) * dy / distance;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double netForceX = 0;
        for (Body b : allBodys) {
            if (!b.equals(this)) {
                netForceX += this.calcForceExertedByX(b);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double netForceY = 0;
        for (Body b : allBodys) {
            if (!b.equals(this)) {
                netForceY += this.calcForceExertedByY(b);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

}
