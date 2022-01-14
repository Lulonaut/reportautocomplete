package de.lulonaut.reportautocomplete;

import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;

@Mod(modid = "reportautocomplete", name = "ReportAutoComplete", version = "1.0", clientSideOnly = true)
public class ReportAutoComplete {
    public static HashSet<String> usernames = new HashSet<>();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new ReportCommand());
    }


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        //Find out the username of the messenger
        String message = StringUtils.stripControlCodes(event.message.getFormattedText());
        int indexColon = message.indexOf(':');
        if (indexColon == -1) //not a player message
            return;
        int indexEndBracket = message.indexOf(']');

        if (message.startsWith("[")) { //remove rank part from the String
            message = message.substring(indexEndBracket + 1).trim();
        } else if (!event.message.getUnformattedText().startsWith("§7")) {
            return;
        }
        indexColon = message.indexOf(':');
        usernames.add(message.trim().substring(0, indexColon));
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        usernames.clear();
    }

}
