package ntc.tfnautica.naurobopulse.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin({ClientPlayerEntity.class})
public class ClientPlayerEntityMixin {
	@Inject(method = {"tickMovement"}, at = {@At("HEAD")})
	private void cancelJumpInWater(CallbackInfo ci) {

		MinecraftClient client = MinecraftClient.getInstance();
		PlayerEntity player = client.player;
		if(player.isCreative() || player.isSpectator()) return;
		if (player.isTouchingWater() || player.isSubmergedInWater()) {
			if (client.options.jumpKey.isPressed()) {
				client.options.jumpKey.setPressed(false);

			}
			if(player.isSprinting() || client.options.sprintKey.isPressed()) {
				player.setSprinting(false);
				client.options.sprintKey.setPressed(false);
			}
		}
	}

}