package gliders.oedada.ru;

import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class ModAttachments {

    public static final AttachmentType<Boolean> IS_GLIDING = AttachmentRegistry.create(
        Identifier.fromNamespaceAndPath("gliders", "is_gliding"),
        builder -> builder
            .initializer(() -> false)
            .persistent(Codec.BOOL)
            .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
    );

    public static void init() {}
}


