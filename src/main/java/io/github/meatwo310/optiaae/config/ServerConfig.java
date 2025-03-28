package io.github.meatwo310.optiaae.config;

import io.github.meatwo310.optiaae.OptiAAE;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OptiAAE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue AUTO_STOCK_TICKS = BUILDER
            .comment("Ticks between Auto Stock upgrade checks. " +
                    "Smaller values make it more responsive, and larger values greatly improve performance. " +
                    "Set to 0 completely disables the upgrade functionality.")
            .defineInRange("autoStockTicks", 20, 0, 20);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
