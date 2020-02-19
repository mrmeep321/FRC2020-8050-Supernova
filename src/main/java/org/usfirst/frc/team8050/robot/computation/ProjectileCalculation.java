package org.usfirst.frc.team8050.robot.computation;

public class ProjectileCalculation {
	private double ground, wall, diagonal, angleGround, angleWall;
	private double[] quadratic;
	
	public void ProjectileAlignment(double ground, double wall, double height) {
		this.ground = ground;
		this.wall = wall-height;
		this.diagonal = Math.sqrt(Math.pow(ground, 2) + Math.pow(wall, 2));
		
		this.angleGround = Math.asin(ground/diagonal);
		this.angleWall = Math.asin(wall/diagonal);
	}
	
	/*
	 * differentiate(a, b) - Calculates derivative of a function with the model "y=(a(x-h)^2)+k".
	 * @return returns derivative of function with vertex model in the form of a double h that can be used as hx
	 */
	
	public double[] differentiate(double a, double h) {
		a *= 2;
		return new double[] {a, h};
	}
	
	public double[] differentiate() {
		double[] quad = quadraticConversion();
		return differentiate(quad[0], quad[1]);
	}
	
	public double[] quadraticConversion(double[] firingPoint, double[] vertex) {
		/*
		 * Vertex model - y=a(x-h)^2+k
		 */
		
		double a, b, c;
		double x = firingPoint[0];
		double y = firingPoint[1];
		double h = vertex[0];
		double k = vertex[1];
		
		//Equation for a - (y-k)/(x-h)^2
		a = ((y-k)/Math.pow((x-h), 2));
		
		double[] full = new double[]{a, h, k};
		
		this.quadratic = full;
		
		return full;
	}
	
	public double[] quadraticConversion() {
		return quadraticConversion(new double[] {0,0}, new double[] {ground, wall});
	}
	
	public double getTangentLine(double[] quadratic) {
		double[] quad = quadraticConversion();
		double[] derivative = differentiate();
		double tangentSlope = derivative[0]*-derivative[1];
		
		return tangentSlope;
	}
	
	public double getTangentLine() {
		quadraticConversion();
		return getTangentLine(quadratic);
	}
	
	public double[] getAngleVelocityPair() {
		double[] quadratic = quadraticConversion();
		double tangentSlope = getTangentLine();
		
		//y=mx
		double height = tangentSlope*ground;
		
		double[] exponentiated = new double[] {1, -quadratic[1]*2, Math.pow(quadratic[1], 2)};
		
		for(int i = 0 ; i < exponentiated.length; i++) {
			exponentiated[i] *= quadratic[0];
		}
		
		exponentiated[2] += quadratic[2];
		
		System.out.println("Standard form - " + exponentiated[0] + "x^2+" + exponentiated[1] + "x+" + exponentiated[2]);
		
		double tangentLength = Math.sqrt(Math.pow(ground, 2)+Math.pow(height, 2));
		
		double angle = Math.asin(height/tangentLength)*(180/Math.PI);
		
		double velocity = getVelocityInitial(angle);
		
		System.out.println();
		
		return new double[] {angle, velocity};
	}
	
	public double getZeroes(double[] quadratic) {
		/*
		 * X Zero equation - X = SQRT(-k/a) + h
		 */
		
		double a = quadratic[0];
		double h = quadratic[1];
		double k = quadratic[2];
		
		return Math.sqrt(-k/a) + h;
	}
	
	public double getVelocityInitial(double angle) {
		double x = getZeroes(this.quadraticConversion());
		
		double a = Math.toRadians(angle);
		
		System.out.println(a + " " + angle);
		
		double y = 0;
		
		/*
		 * Initial velocity equation - X - X of final zero, Y - Y of final zero, A - Angle
		 * V = SQRT((-16X^2)/(cos^2A(y-xtanA)))
		 */
		
		
		
		double v = Math.sqrt((-16*Math.pow(x, 2))/(Math.pow(Math.cos(a), 2)*(y-(x*Math.tan(a)))));
		
		return v;
	}
	
	public double[] getReformedQuadratic() {
		double tangentSlope = getTangentLine();
		double height = tangentSlope*ground;
		double tangentLength = Math.sqrt(Math.pow(ground, 2)+Math.pow(height, 2));
		
		double angle = Math.asin(height/tangentLength)*(180/Math.PI);	
		
		double[] v = new double[] {-16, getVelocityInitial(angle)};
		
		return v;
	}
}