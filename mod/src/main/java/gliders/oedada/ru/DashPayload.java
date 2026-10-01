package gliders.oedada.ru;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DashPayload() implements CustomPacketPayload {
    public static final Type<DashPayload> TYPE =
        new Type<>(Identifier.fromNamespaceAndPath("gliders", "dash"));
    public static final StreamCodec<FriendlyByteBuf, DashPayload> CODEC =
        StreamCodec.unit(new DashPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
