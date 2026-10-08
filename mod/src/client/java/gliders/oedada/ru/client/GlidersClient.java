package gliders.oedada.ru.client;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.platform.InputConstants;

import gliders.oedada.ru.GlideState;
import gliders.oedada.ru.GliderPayload;
import gliders.oedada.ru.ModAttachments;
import gliders.oedada.ru.physics.Body;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class GlidersClient implements ClientModInitializer {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("gliders");
    private static KeyMapping glideKey;
    private static KeyMapping rightKey;
    private static KeyMapping leftKey;
    private static KeyMapping upKey;
    private static KeyMapping downKey;

    private boolean isKeyPressed(KeyMapping km) {
        return km.isDown();
    }

    private KeyMapping createKeyMapping(String name, int key, KeyMapping.Category category) {
        return KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.gliders.%s".formatted(name),
                InputConstants.Type.KEYBOARD,
                key,
                category));
    }

    @Override
    public void onInitializeClient() {
        //from claude
        ModelLayerRegistry.registerModelLayer(GliderModel.LAYER, GliderModel::createBodyLayer);

        LivingEntityRenderLayerRegistrationCallback.EVENT.register(
            (entityType, renderer, helper, context) -> {
                if (renderer instanceof AvatarRenderer<?> avatarRenderer) {
                    helper.register(new GliderLayer(
                        (RenderLayerParent<AvatarRenderState, PlayerModel>) avatarRenderer,
                        context.getModelSet()));
                }
            });

        //from claude
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath("gliders", "main"));
        // TODO: разобраться R, работает всегда, а Z никогда, почему-то
        glideKey = createKeyMapping("glide", InputConstants.KEY_Z, category);
        leftKey = createKeyMapping("left", InputConstants.KEY_A, category);
        rightKey = createKeyMapping("right", InputConstants.KEY_D, category);
        upKey = createKeyMapping("up", InputConstants.KEY_S, category);
        downKey = createKeyMapping("down", InputConstants.KEY_W, category);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            LocalPlayer player = client.player;
            if (player == null) {
                return;
            }
            while (glideKey.consumeClick()) {
                Boolean is_gliding = player.getAttachedOrCreate(ModAttachments.is_gliding);
                if (!is_gliding) {
                    if (checkStartCondition(player)) {
                        onStartGliding(player);
                        is_gliding = !is_gliding;
                    }
                } else {
                    onEndGliding(player);
                    is_gliding = !is_gliding;
                }
                player.setAttached(ModAttachments.is_gliding, is_gliding);

                ClientPlayNetworking.send(new GliderPayload(is_gliding));
            }
            Boolean is_gliding = player.getAttachedOrCreate(ModAttachments.is_gliding);
            if (is_gliding) {
                whileGliding(player);
            }
        });
    }

    private boolean isNotSolid(LocalPlayer p, double x, double y, double z) {
        BlockPos pos = BlockPos.containing(x, y, z);
        Block block = p.level().getBlockState(pos).getBlock();
        List<Block> PASSABLE_BLOCKS = List.of(
                // Воздух
                Blocks.AIR,
                Blocks.CAVE_AIR,
                Blocks.VOID_AIR,

                // Вода
                Blocks.WATER,
                Blocks.BUBBLE_COLUMN,

                // Листва
                Blocks.OAK_LEAVES,
                Blocks.SPRUCE_LEAVES,
                Blocks.BIRCH_LEAVES,
                Blocks.JUNGLE_LEAVES,
                Blocks.ACACIA_LEAVES,
                Blocks.DARK_OAK_LEAVES,
                Blocks.MANGROVE_LEAVES,
                Blocks.CHERRY_LEAVES,
                Blocks.AZALEA_LEAVES,
                Blocks.FLOWERING_AZALEA_LEAVES,
                Blocks.PALE_OAK_LEAVES);
        return PASSABLE_BLOCKS.contains(block);
    }

    private boolean checkStartCondition(LocalPlayer p) {
        Vec3 look = p.getLookAngle();
        double xDir, yDir, zDir;
        xDir = Math.signum(look.x);
        yDir = -1;
        zDir = Math.signum(look.z);
        double x = p.position().x;
        for (int i = 0; i < 3; i++) {
            x += xDir;
            double y = p.position().y;
            for (int j = 0; j < 3; j++) {
                y += yDir;
                double z = p.position().z;
                for (int u = 0; u < 3; u++) {
                    z += zDir;
                    if (!isNotSolid(p, x, y, z)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkWhileCondition(LocalPlayer p) {
        return isNotSolid(p, p.position().x, p.position().y - 1, p.position().z);
    }

    private void control(Body b) {
        boolean up = isKeyPressed(upKey);
        boolean down = isKeyPressed(downKey);
        boolean right = isKeyPressed(rightKey);
        boolean left = isKeyPressed(leftKey);

        if (up)
            b.alphaCmd += 0.008f;
        else if (down)
            b.alphaCmd -= 0.004f;
        else
            b.alphaCmd += (0.083 - b.alphaCmd) * 0.1; // кнопки отпущены, возврат
        b.alphaCmd = (float) Math.max(0.017, Math.min(0.30, b.alphaCmd));

        b.sigma = 0.06f * (float) Math.asin(b.b.y); // возврат к ровному
        if (right)
            b.sigma += 0.025f; // правый
        if (left)
            b.sigma -= 0.025f; // левый

    }

    private void rotateLook(LocalPlayer p, Body b) {
        float newYaw = b.get_new_yaw(p.getYRot());
        p.setYRot(newYaw);
        p.yRotO = newYaw; // чтобы не дёргалось при интерполяции
        p.setYBodyRot(b.prevHeading); // тело смотрит по курсу
        p.setYHeadRot(newYaw);

    }

    public void whileGliding(LocalPlayer p) {
        if (!checkWhileCondition(p)) {
            onEndGliding(p);
            p.setAttached(ModAttachments.is_gliding, false);
            ClientPlayNetworking.send(new GliderPayload(false));
            return;
        }
        Body b = GlideState.body;
        control(b);
        rotateLook(p, b);

        // TODO: доделать коллизии
        if (p.verticalCollision)
            b.velocity = new Vec3(b.velocity.x, b.velocity.y, b.velocity.z);

        p.setDeltaMovement(b.update());
        p.resetFallDistance();
    }

    public static void onEndGliding(LocalPlayer p) {
        p.setNoGravity(false);
    }

    public static void onStartGliding(LocalPlayer p) {
        LOGGER.error("started");
        Vec3 look = p.getLookAngle();
        Vec3 v = p.getDeltaMovement();
        Vec3 b = look.cross(new Vec3(0, 1, 0));
        Vec3 flat = new Vec3(look.x, 0, look.z).normalize();
        // Vec3 v = flat.scale(0.523).add(0, -0.057, 0);
        GlideState.set_body(new Body(v, b, 70f, 17f, new Vec3(0, 0, 0), 0.0833f, 1, 0));
        p.setNoGravity(true);
    }
}
