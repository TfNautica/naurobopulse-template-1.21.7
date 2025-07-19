package ntc.tfnautica.naurobopulse.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import ntc.tfnautica.naurobopulse.NauRoboPulse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InGameHud.class})
public class InGameHudMixin {

    private static final Identifier CENTER_TEXTURE = Identifier.of(NauRoboPulse.MOD_ID, "textures/gui/center.png");

    private static final Identifier BOTTOM_LEFT_TEXTURE = Identifier.of(NauRoboPulse.MOD_ID, "textures/gui/bottom_left.png");

    private static final Identifier TOP_RIGHT_TEXTURE = Identifier.of(NauRoboPulse.MOD_ID, "textures/gui/top_right.png");

    @Inject(method = {"render"}, at = {@At("TAIL")})
    public void render(DrawContext context, RenderTickCounter renderTickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if(client.world == null || client.player == null) return;
        ClientPlayerEntity player = client.player;

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int iconSize = 128;
        int bottomLeftX = 10;
        int bottomLeftY = screenHeight - iconSize - 10;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, BOTTOM_LEFT_TEXTURE, bottomLeftX, bottomLeftY, 0.0F, 0.0F, iconSize, iconSize, iconSize, iconSize);
        int topRightX = screenWidth - iconSize - 10;
        int topRightY = 10;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TOP_RIGHT_TEXTURE, topRightX, topRightY, 0.0F, 0.0F, iconSize, iconSize, iconSize, iconSize);
        long timeOfDay = client.world.getTimeOfDay() % 24000L;
        int hours = (int)((timeOfDay / 1000L + 6L) % 24L);
        int minutes = (int)(timeOfDay % 1000L * 60L / 1000L);
        String timeString = String.format("Время: %02d:%02d", new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes) });
        context.drawText(client.textRenderer, timeString, 10, 50, -11882801, true);

        int location_X = (int) player.getX();
        int location_Y = (int) player.getY();
        int location_Z = (int) player.getZ();

        String location = "X: " + location_X + " " + "Y: " + location_Y + " " + "Z: " + location_Z;
        context.drawText(client.textRenderer, location, 10, 60, -11882801, true);

    }
}
