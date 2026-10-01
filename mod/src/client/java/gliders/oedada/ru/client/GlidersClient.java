package gliders.oedada.ru.client;

import com.mojang.blaze3d.platform.InputConstants;

import gliders.oedada.ru.GliderPayload;
import net.minecraft.world.phys.Vec3;
import gliders.oedada.ru.ModAttachments;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;

public class GlidersClient implements ClientModInitializer {
    private static KeyMapping dashKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath("gliders", "main"));

        dashKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.gliders.dash",
                InputConstants.Type.KEYBOARD,
                InputConstants.KEY_R,
                category));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            LocalPlayer player = client.player;
            if (player == null) {
                return;
            }
            while (dashKey.consumeClick()) {
                Boolean is_gliding = player.getAttachedOrCreate(ModAttachments.IS_GLIDING);
                if (!is_gliding) {
                    onStartGliding(player);
                } else {
                    onEndGliding(player);
                }
                is_gliding = !is_gliding;
                player.setAttached(ModAttachments.IS_GLIDING, is_gliding);

                ClientPlayNetworking.send(new GliderPayload(is_gliding));
            }
            Boolean is_gliding = player.getAttachedOrCreate(ModAttachments.IS_GLIDING);
            if (is_gliding) {
                whileGliding(player);
            }
        });
    }

    public static void whileGliding(LocalPlayer p) {
        p.addDeltaMovement(new Vec3(0, 0.08, 0));

    }

    public static void onEndGliding(LocalPlayer p) {
        onStartGliding(p);
    }

    public static void onStartGliding(LocalPlayer p) {
    }
}
