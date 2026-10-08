package gliders.oedada.ru;

import gliders.oedada.ru.physics.Body;

public class GlideState {
    public static Body body;
    public static float prevHeading = 0;

    public static void set_body(Body new_body){
        body = new_body;
    }

    public static void set_prevHeading(float ph){
        prevHeading = ph;
    }

}
