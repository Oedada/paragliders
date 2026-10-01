package gliders.oedada.ru;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record GliderPayload(Boolean is_gliding) implements CustomPacketPayload {
	public static final Identifier GLIDER_PAYLOAD_ID = Gliders.id("glider");
    public static final Type<GliderPayload> TYPE =
        new Type<>(GLIDER_PAYLOAD_ID);
    public static final StreamCodec<FriendlyByteBuf, GliderPayload> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, GliderPayload::is_gliding, GliderPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
