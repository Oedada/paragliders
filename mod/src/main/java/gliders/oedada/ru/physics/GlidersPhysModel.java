package gliders.oedada.ru.physics;
import net.minecraft.world.phys.Vec3;

record GliderControls(double deltaAlphaCmd, double deltaSigma){}

public class GlidersPhysModel implements PhysModel<GliderControls> {
    double sigma;
    double area;
    double mass;
    double alpha;
    Vec3 b;
    public GlidersPhysModel(double sigma, double area, double mass, double alpha, Vec3 b) {
        this.sigma = sigma;
        this.area = area;
        this.mass = mass;
        this.alpha = alpha;
        this.b = b;
    }

    @Override
    public Vec3 update(GliderControls ctrls, Environment env, Vec3 velocity){
        Vec3 va = velocity.subtract(env.wind_velocity());
        // вычисление скаляров
        double d = sigma*env.delta_time();
        double af = GliderScalarPhysics.aerodynamicForce(va.length(), area);
        double alphaEq = GliderScalarPhysics.newAlpha(ctrls.deltaAlphaCmd(), mass, va.length(), area, env.delta_time());
        this.alpha += (alphaEq - alpha) * env.delta_time() / Constants.tau_alpha;
        liftAndDragForces ldf = GliderScalarPhysics.liftAndDragForce(this.alpha, af);
        // Вычисление векторов
        Vec3 ev = va.normalize();
        this.b = this.b.subtract(ev.scale(b.dot(ev))).normalize();
        this.b = this.b.scale(Math.cos(d)).add(ev.cross(this.b).scale(Math.sin(d)));
        Vec3 l = this.b.cross(ev);
        Vec3 L = l.scale(ldf.lift());
        Vec3 D = ev.reverse().scale(ldf.drag());
        return L.add(D);
    }
}
