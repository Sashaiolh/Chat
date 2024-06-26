package com.mecheniy.chat.utilities;

import com.mecheniy.chat.Chat;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;

public class MessageFunctions {



    public static void broadcastMessageGlobal(MinecraftServer server, Component message) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            player.sendSystemMessage(message);

        }
    }
    public static void broadcastMessageLocal(ServerPlayer serverPlayer, Component message){
        MinecraftServer server = serverPlayer.getServer();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (MessageFunctions.compareCoordinateDistance(serverPlayer.getOnPos(), player.getOnPos()) <= Chat.ForgeBeautifulChatEvent.rangeTalk
            && player.getLevel() == serverPlayer.getLevel()){
                player.sendSystemMessage(message);
            }
        }
    }

    public static void broadcastMcSkillMessage(ServerPlayer serverPlayer, String string){
        Component finalMessage = Component.literal(  "§8[§6" + ChatConfig.chatName.get() + "§8]: " + "§7" + string);
        serverPlayer.sendSystemMessage(finalMessage);
    }

    public static double compareCoordinateDistance(BlockPos playerPos1, BlockPos playerPos2){
        double x = (double) Math.abs(playerPos1.getX() - playerPos2.getX());
        double y = (double) Math.abs(playerPos1.getY() - playerPos2.getY());
        double z = (double) Math.abs(playerPos1.getZ() - playerPos2.getZ());
        return x + y + z;
    }


}
