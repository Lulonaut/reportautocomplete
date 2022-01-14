package de.lulonaut.reportautocomplete;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.Collections;
import java.util.List;

public class ReportCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "report";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("wdr");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return getListOfStringsMatchingLastWord(args, ReportAutoComplete.usernames);
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/report " + args[0]);
        } else {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/report");
        }
    }
}
