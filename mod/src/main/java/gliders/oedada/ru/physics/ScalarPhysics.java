package gliders.oedada.ru.physics;

public class ScalarPhysics {
}

record liftAndDragForces(double lift, double drag) {
}

class GliderScalarPhysics {
    static double newAlpha(double alphaCmd, double mass, double airVelocityScale, double area, double deltaTime) {
        double CLcmd = Math.max(Constants.a * (alphaCmd - Constants.alpha0), 0.1);
        double vTrim = Math.sqrt(2 * mass * Constants.g / (Constants.density * area * CLcmd));
        double eps = airVelocityScale / vTrim - 1;
        double alphaEq = alphaCmd - Constants.K_speed * eps;
        double alpha = Math.max(0.017, Math.min(0.30, alphaEq));
        return alpha;
    }

    static double aerodynamicForce(double airVelocityScale, double area) {
        return 0.5 * Constants.density * Math.pow(airVelocityScale, 2) * area;
    }

    static liftAndDragForces liftAndDragForce(double alpha, double aerodynamicForceScale){
        double w = 1/(1+Math.pow(Math.E, -(alpha - Constants.a_s)/Constants.da));
        double CL_norm = Constants.a*(alpha-Constants.alpha0);
        double CL = (1-w)*(CL_norm) + w*(1.2*Math.sin(alpha)*Math.cos(alpha));
        double CD = (1-w)*(Constants.CD0 + Constants.part_of_CL_in_CD*CL_norm*CL_norm) + w*(1.2*Math.pow(Math.sin(alpha), 2));
        return new liftAndDragForces(CL*aerodynamicForceScale, CD*aerodynamicForceScale);
    }
}
