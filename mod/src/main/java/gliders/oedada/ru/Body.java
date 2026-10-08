package gliders.oedada.ru;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;



public class Body {
    public Vec3 velocity;
    float mass;
    float area;
    Vec3 wind_velocity;
    float alpha;
    float dt;
    public Vec3 b;
    public float sigma;
    public float prevHeading;
    public float alphaCmd;   // задаёт пилот

    public Body(Vec3 velocity, Vec3 b, float mass, float area, Vec3 wind_velocity, float alphaCmd, float dt, float sigma) {
        this.velocity = velocity;
        this.mass = mass;
        this.area = area;
        this.wind_velocity = wind_velocity;
        this.alphaCmd = alphaCmd;
        this.alpha = alphaCmd;
        this.dt = dt;
        this.b = b;
        this.sigma = sigma;
        this.prevHeading = (float) Math.toDegrees(Math.atan2(-this.velocity.x, this.velocity.z));
    }

    public void set_wind(Vec3 wind_velocity){
        this.wind_velocity = wind_velocity;
    }

    public float get_new_yaw(float yRot){
        if (Math.hypot(this.velocity.x, this.velocity.z) > 0.05) {          // при почти вертикальном полёте курс не определён
            float heading = (float) Math.toDegrees(Math.atan2(-this.velocity.x, this.velocity.z));
            float delta = Mth.wrapDegrees(heading - this.prevHeading);
            this.prevHeading = heading;

            float yaw = yRot + delta;
            float rel = Mth.wrapDegrees(yaw - heading);
            rel = Mth.clamp(rel, -90f, 90f);
            float newYaw = heading + rel;
            return newYaw;
        }
        else {
            return yRot;
        }
    }

    public Vec3 update(){
        for(int i = 0; i < 10; i++) {
            step(this.dt/10);
        }
        return this.velocity;
    }

    private Vec3 step(float delta_time){
        Vec3 va = velocity.subtract(wind_velocity);
        Vec3 ev = va.normalize();
        this.b = this.b.subtract(ev.scale(b.dot(ev))).normalize();
        double d = sigma*delta_time;
        this.b = this.b.scale(Math.cos(d)).add(ev.cross(this.b).scale(Math.sin(d)));
        double q = 0.5 * Constants.density*Math.pow(va.length(), 2)*this.area;
        double CLcmd   = Math.max(Constants.a * (alphaCmd - Constants.alpha0), 0.1);
        double vTrim   = Math.sqrt(2 * mass * Constants.g / (Constants.density * area * CLcmd));
        double eps     = va.length() / vTrim - 1;
        double alphaEq = alphaCmd - Constants.K_speed * eps;
        alphaEq = Math.max(0.017, Math.min(0.30, alphaEq));
        alpha += (alphaEq - alpha) * delta_time / Constants.tau_alpha;
        double w = 1/(1+Math.pow(Math.E, -(this.alpha - Constants.a_s)/Constants.da));
        double CL_norm = Constants.a*(this.alpha-Constants.alpha0);
        double CL = (1-w)*(CL_norm) + w*(1.2*Math.sin(this.alpha)*Math.cos(this.alpha));
        double CD = (1-w)*(Constants.CD0 + Constants.part_of_CL_in_CD*CL_norm*CL_norm) + w*(1.2*Math.pow(Math.sin(this.alpha), 2));
        Vec3 l = this.b.cross(ev);
        Vec3 L = l.scale(q*CL);
        Vec3 D = ev.reverse().scale(q*CD);
        Vec3 a = (L.add(D).scale(1.0/this.mass)).add(0, -Constants.g, 0);
        this.velocity = this.velocity.add(a.scale(delta_time));
        return this.velocity;
    }

}
