package ntc.tfnautica.naurobopulse.mixin.client;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerEntity.class})
public abstract class PlayerEntityMixin {

    @Inject(method = {"updateSwimming"}, at = {@At("HEAD")}, cancellable = true)
    private void preventSwimming(CallbackInfo ci) {
        ci.cancel();
    }


    @Inject(method = {"interact"}, at = {@At("TAIL")}, cancellable = true)
    private void preventRide(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (entity instanceof BoatEntity)
            cir.setReturnValue(ActionResult.FAIL);
    }
}