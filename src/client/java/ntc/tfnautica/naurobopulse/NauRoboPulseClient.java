package ntc.tfnautica.naurobopulse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.util.Identifier;
import ntc.tfnautica.naurobopulse.render.renderCustomOverlay;

public class NauRoboPulseClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudElementRegistry.addFirst(Identifier.of(NauRoboPulse.MOD_ID, "robot_hud"), renderCustomOverlay.getInstance());
	}
}