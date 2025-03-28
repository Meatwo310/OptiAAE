package io.github.meatwo310.optiaae.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.github.meatwo310.optiaae.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class OptiAAECommands {
    static final Component PLACEHOLDER = Component
            .literal("[OptiAAE] ")
            .withStyle(ChatFormatting.YELLOW);

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("optiaae")
                .then(Commands.literal("autoStockTicks")
                        .executes(OptiAAECommands::getAutoStockTicks)
                        .then(Commands.argument("ticks", IntegerArgumentType.integer(0, Integer.MAX_VALUE))
                                .requires(ctx -> ctx.hasPermission(2))
                                .executes(OptiAAECommands::setAutoStockTicks)
                        )
                )
        );
    }

    private static int setAutoStockTicks(CommandContext<CommandSourceStack> ctx) {
        int ticks = IntegerArgumentType.getInteger(ctx, "ticks");
        ServerConfig.AUTO_STOCK_TICKS.set(ticks);

        MutableComponent message;
        if (ticks == 0) {
            message = Component
                    .literal("Auto Stock Card is now ")
                    .withStyle(ChatFormatting.WHITE)
                    .append(Component
                            .literal("DISABLED")
                            .withStyle(ChatFormatting.RED)
                    );
        } else {
            message = Component
                    .literal("Auto Stock Card is now working every ")
                    .withStyle(ChatFormatting.WHITE)
                    .append(Component
                            .literal(ticks + " ticks")
                            .withStyle(ChatFormatting.GREEN)
                    );
        }

        ctx.getSource().sendSuccess(
                () -> PLACEHOLDER.copy().append(message),
                true
        );

        return Command.SINGLE_SUCCESS;
    }

    private static int getAutoStockTicks(CommandContext<CommandSourceStack> ctx) {
        int ticks = ServerConfig.AUTO_STOCK_TICKS.get();

        MutableComponent message;
        if (ticks == 0) {
            message = Component
                    .literal("Auto Stock Card is currently ")
                    .withStyle(ChatFormatting.WHITE)
                    .append(Component
                            .literal("DISABLED")
                            .withStyle(ChatFormatting.RED)
                    );
        } else {
            message = Component
                    .literal("Auto Stock Card is working every ")
                    .withStyle(ChatFormatting.WHITE)
                    .append(Component
                            .literal(ticks + " ticks")
                            .withStyle(ChatFormatting.GREEN)
                    );
        }

        ctx.getSource().sendSuccess(
                () -> PLACEHOLDER.copy().append(message),
                false
        );

        return Command.SINGLE_SUCCESS;
    }
}
