package io.github.meatwo310.optiaae;

import io.github.meatwo310.optiaae.config.ServerConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(OptiAAE.MODID)
public class OptiAAE {
    public static final String MODID = "optiaae";
    public OptiAAE() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
    }
}
