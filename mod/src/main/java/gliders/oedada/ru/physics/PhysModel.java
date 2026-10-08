package gliders.oedada.ru.physics;

import net.minecraft.world.phys.Vec3;
record Environment(Vec3 wind_velocity, double delta_time){}

public interface PhysModel<C> {
    Vec3 update(C controls, Environment env, Vec3 velocity);

}
