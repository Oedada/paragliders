package gliders.oedada.ru.client;

import com.mojang.blaze3d.platform.InputConstants;

import gliders.oedada.ru.DashPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
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
            category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (dashKey.consumeClick()) {
                ClientPlayNetworking.send(new DashPayload());
            }
        });
    }
}
