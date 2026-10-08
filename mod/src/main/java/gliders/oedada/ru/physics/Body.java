package gliders.oedada.ru.physics;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;


public class Body {
    public Vec3 velocity;
    public float prevHeading;
    GlidersPhysModel gliderPhysModel;

    public Body(Vec3 initial_velocity, double mass, Vec3 lookPlayerVector, GliderInitialParameters gips) {
        this.velocity = initial_velocity;
        this.gliderPhysModel = new GlidersPhysModel(0, gips.WingArea(), mass, gips.initialVerticalInclinationControlAngel(),lookPlayerVector.normalize().cross(new Vec3(0, 1, 0)));
        // TODO: возможно из-за этого баг со срывом шеи
        this.prevHeading = (float) Math.toDegrees(Math.atan2(-this.velocity.x, this.velocity.z));
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

        Vec3 a = (L.add(D).scale(1.0/this.mass)).add(0, -Constants.g, 0);
        this.velocity = this.velocity.add(a.scale(delta_time));
        return this.velocity;
    }

}

record GliderInitialParameters(double WingArea, double initialVerticalInclinationControlAngel){}
