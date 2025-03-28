package io.github.meatwo310.optiaae.event;

import io.github.meatwo310.optiaae.command.OptiAAECommands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandsRegisterer {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        OptiAAECommands.register(event.getDispatcher());
    }
}
