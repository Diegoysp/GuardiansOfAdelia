package io.github.lix3nn53.guardiansofadelia.commands;

import io.github.lix3nn53.guardiansofadelia.minigames.MiniGameManager;
import io.github.lix3nn53.guardiansofadelia.party.Party;
import io.github.lix3nn53.guardiansofadelia.party.PartyInvite;
import io.github.lix3nn53.guardiansofadelia.party.PartyManager;
import io.github.lix3nn53.guardiansofadelia.utilities.ChatPalette;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandParty implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!command.getName().equals("party")) {
            return false;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (MiniGameManager.isInMinigame(player)) {
                player.sendMessage(ChatPalette.RED + "You can't use party commands in minigames");
                return false;
            }
            if (args.length < 1) {
                player.sendMessage(ChatPalette.YELLOW + "/party invite <player>");
                player.sendMessage(ChatPalette.YELLOW + "/party leave");
                player.sendMessage(ChatPalette.YELLOW + "/party kick <player>");
                //player.sendMessage(ChatPalette.YELLOW + "/party leader <player>");
            } else if (args[0].equalsIgnoreCase("invite")) {
                if (args.length == 2) {
                    if (MiniGameManager.isInMinigame(player)) {
                        player.sendMessage(ChatPalette.RED + "You can't invite players to a minigame party!");
                        return false;
                    }
                    Player receiver = Bukkit.getPlayer(args[1]);
                    if (receiver != null && receiver != sender) {
                        String senderTitle = ChatPalette.BLUE_LIGHT + "Sent party invitation";
                        String receiverMessage = ChatPalette.BLUE_LIGHT + sender.getName() + " invites you to party";
                        String receiverTitle = ChatPalette.BLUE_LIGHT + "Received party invitation";
                        PartyInvite partyInvite = new PartyInvite(player, receiver, senderTitle, receiverMessage, receiverTitle);
                        partyInvite.send();
                    } else {
                        player.sendMessage(ChatPalette.RED + "You can't invite yourself!");
                    }
                }
            } else if (args[0].equalsIgnoreCase("leave")) {
                if (PartyManager.inParty(player)) {
                    Party party = PartyManager.getParty(player);
                    party.leave(player);
                }
            } else if (args[0].equalsIgnoreCase("kick")) {
                if (args.length == 2) {
                    if (!args[1].equalsIgnoreCase(player.getName())) {
                        Player receiver = Bukkit.getPlayer(args[1]);
                        if (receiver != null) {
                            if (PartyManager.inParty(player)) {
                                Party party = PartyManager.getParty(player);
                                party.kickMember(player, receiver);
                            }
                        }
                    }
                }
            }/* else if (args[0].equalsIgnoreCase("leader")) {
                if (args.length == 2) {
                    if (!args[1].equalsIgnoreCase(player.getName())) {
                        Player receiver = Bukkit.getPlayer(args[1]);
                        if (receiver != null) {
                            if (PartyManager.inParty(player)) {
                                Party party = PartyManager.getParty(player);
                                party.setNewLeader(player, receiver);
                            }
                        }
                    }
                }
            }*/
            // If the player (or console) uses our command correct, we can return true
            return true;
        }
        return false;
    }
}
