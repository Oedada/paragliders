package gliders.oedada.ru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class Gliders implements ModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("MyMod");

	@Override
	public void onInitialize() {
		ModAttachments.init();

		// Пакет клиент → сервер
		PayloadTypeRegistry.serverboundPlay().register(DashPayload.TYPE, DashPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(DashPayload.TYPE, (payload, context) -> {
			ServerPlayer player = context.player();
			Boolean is_gliding = player.getAttachedOrCreate(ModAttachments.IS_GLIDING);
			if (!is_gliding) {
				onStartGliding(player);
			} else {
				onEndGliding(player);
			}
			player.setAttached(ModAttachments.IS_GLIDING, !is_gliding);
		});
		ServerTickEvents.START_SERVER_TICK.register(server -> {
			for (ServerPlayer p : server.getPlayerList().getPlayers()) {
				if (p.getAttachedOrCreate(ModAttachments.IS_GLIDING)) {
					whileGliding(p);
				}
			}
		});
	}

	public static void whileGliding(ServerPlayer p) {

	}

	public static void onEndGliding(ServerPlayer p) {
		onStartGliding(p);
	}

	public static void onStartGliding(ServerPlayer p) {
		Vec3 look = p.getLookAngle();
		p.setDeltaMovement(look.scale(1.5)); // 1.5 блока/тик — сильный рывок
		p.connection.send(new ClientboundSetEntityMotionPacket(p.getId(), look.scale(1.5)));
		p.resetFallDistance();

	}
}
