package gliders.oedada.ru;

import java.util.List;
import java.util.ArrayList;

import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.resources.Identifier;

public class ModAttachments {

    public static final AttachmentType<Boolean> IS_GLIDING = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath("gliders", "is_gliding"),
            builder -> builder
                    .initializer(() -> false)
                    .persistent(Codec.BOOL));
    public static final AttachmentType<List<Vec3>> Forces = AttachmentRegistry.create(
            Gliders.id("forces"),
            builder -> builder.initializer(() -> new ArrayList<>(List.of(new Vec3(0, 0, 0))))
                    .persistent(Vec3.CODEC.listOf()));

    public static void init() {
    }
}
