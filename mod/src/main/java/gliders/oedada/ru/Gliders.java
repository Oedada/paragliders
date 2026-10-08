package gliders.oedada.ru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.Identifier;

public class Gliders implements ModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("MyMod");
	private static final String MOD_ID = "gliders";

	@Override
	public void onInitialize() {
		ModAttachments.init();
		ModItems.initialize();

		PayloadTypeRegistry.serverboundPlay().register(GliderPayload.TYPE, GliderPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(GliderPayload.TYPE, (payload, context) -> {
			if (!payload.is_gliding()) {
				context.player().resetFallDistance();
				LOGGER.info("not gliding");
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

}
