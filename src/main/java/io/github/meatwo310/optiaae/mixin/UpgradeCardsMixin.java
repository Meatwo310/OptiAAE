package io.github.meatwo310.optiaae.mixin;

import io.github.meatwo310.optiaae.config.ServerConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pedroksl.advanced_ae.common.items.upgrades.UpgradeCards;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = UpgradeCards.class,
        remap = false // ←重要(n敗)
)
public class UpgradeCardsMixin {
    @Inject(method = "autoStock(" +
            "Lnet/minecraft/world/level/Level;" +
            "Lnet/minecraft/world/entity/player/Player;" +
            "Lnet/minecraft/world/item/ItemStack;" +
            ")Z", at = @At("HEAD"), cancellable = true)
    private static void autoStock(Level level, Player player, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (ServerConfig.AUTO_STOCK_TICKS.get() == 0 || level.getGameTime() % ServerConfig.AUTO_STOCK_TICKS.get() != 0) {
            cir.setReturnValue(false);
        }
    }
}
