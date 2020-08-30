package io.github.lix3nn53.guardiansofadelia.quests.list.mainstory;

import io.github.lix3nn53.guardiansofadelia.npc.QuestNPCManager;
import io.github.lix3nn53.guardiansofadelia.quests.Quest;
import io.github.lix3nn53.guardiansofadelia.quests.task.Task;
import io.github.lix3nn53.guardiansofadelia.quests.task.TaskCollect;
import io.github.lix3nn53.guardiansofadelia.quests.task.TaskKill;
import io.github.lix3nn53.guardiansofadelia.quests.task.TaskReach;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class QuestsPhase5 {

    public static void createQuests() {
        createQuestOne();
        createQuestTwo();
        createQuestThree();
        createQuestFour();
        createQuestFive();
        createQuestSix();
        createQuestSeven();
        createQuestEight();
    }

    private static void createQuestOne() {
        List<String> story = new ArrayList<>();
        story.add("Vikings are fighting with ghosts");
        story.add("of pirates to regain their freedom");
        story.add("at the sea.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(34);
        List<Task> tasks = new ArrayList<>();
        List<ItemStack> itemPrizes = new ArrayList<>();
        String startMsg = ChatColor.YELLOW + "Click" + ChatColor.BOLD + " Compass Icon " + ChatColor.RESET + ChatColor.YELLOW + "from menu-book and select your destination NPC.";
        Quest quest = new Quest(35, "Sea of Greed", story,
                startMsg, "Go to viking village and talk with Sailor Skamkel",
                "", tasks, itemPrizes, 21, 9000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 39, 40);
    }

    private static void createQuestTwo() {
        List<String> story = new ArrayList<>();
        story.add("Shooter and fighter pirates are");
        story.add("weakest of them. Lets see if you can handle?");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(35);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.DARK_AQUA + "Shooter Pirate", 35);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.DARK_AQUA + "Fighter Pirate", 35);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nThen talk back to Sailor Skamkel";
        Quest quest = new Quest(36, "Pirate hunt 1", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 90000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 40);
    }

    private static void createQuestThree() {
        List<String> story = new ArrayList<>();
        story.add("Drowned pirates are so strong under");
        story.add("the sea. We need to get rid of them.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(35);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.DARK_AQUA + "Drowned Pirate", 27);
        tasks.add(taskKill);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nThen talk back to Sailor Skamkel";
        Quest quest = new Quest(37, "Pirate hunt 2", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 90000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 40);
    }

    private static void createQuestFour() {
        List<String> story = new ArrayList<>();
        story.add("There is a ship wreck in Sea of Greed.");
        story.add("There might be some valuable treasure.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(35);

        List<Task> tasks = new ArrayList<>();
        Location reachLocation = new Location(Bukkit.getWorld("world"), -2257.5, 40.5, 3631.5);
        TaskReach taskReach = new TaskReach(reachLocation, Material.GOLD_BLOCK);
        tasks.add(taskReach);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nThen talk back to Sailor Skamkel";
        Quest quest = new Quest(38, "Treasure hunt", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 90000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 40);
    }

    private static void createQuestFive() {
        List<String> story = new ArrayList<>();
        story.add("All of the pirates are wearing the");
        story.add("same hat. Collect some pirate hats to");
        story.add("find the reason behind it.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(35);

        List<Task> tasks = new ArrayList<>();
        List<String> nameOfMobsItemDropsFrom = new ArrayList<>();
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_AQUA + "Shooter Pirate");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_AQUA + "Fighter Pirate");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_AQUA + "Sharpshooter Pirate");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_AQUA + "Duel Master Pirate");
        TaskCollect taskCollect = new TaskCollect(nameOfMobsItemDropsFrom, 0.64, new ItemStack(Material.ACACIA_BOAT), 20);
        tasks.add(taskCollect);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nfrom pirates then talk back to Sailor Skamkel";
        Quest quest = new Quest(39, "Pirate hats", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 90000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 40);
    }

    private static void createQuestSix() {
        List<String> story = new ArrayList<>();
        story.add("There are some high ranked pirates on");
        story.add("those ships. We must capture them.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(35);
        List<Task> tasks = new ArrayList<>();

        TaskKill taskKill = new TaskKill(ChatColor.DARK_AQUA + "Sharpshooter Pirate", 24);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.DARK_AQUA + "Duel Master Pirate", 24);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nthen talk to Sailor Skamkel";
        Quest quest = new Quest(40, "Pirate hunt 3", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 90000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 40);
    }

    private static void createQuestSeven() {
        List<String> story = new ArrayList<>();
        story.add("We found the location of their captain's");
        story.add("ship. The key for our freedom must be there.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(36);
        requiredQuests.add(37);
        requiredQuests.add(38);
        requiredQuests.add(39);
        requiredQuests.add(40);
        List<Task> tasks = new ArrayList<>();

        TaskKill taskKill = new TaskKill(ChatColor.DARK_AQUA + "Captain Barbaros", 1);
        tasks.add(taskKill);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "<Dungeon>\nTASK_PROGRESS_1\nthen talk to Sailor Skamkel";
        Quest quest = new Quest(41, "The captain's soul", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 190000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 40);
    }

    private static void createQuestEight() {
        List<String> story = new ArrayList<>();
        story.add("Report back that vikings rule Sea of Greed once again.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(41);
        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Report back to Captain Lenna that vikings rule Sea of Greed once again";
        Quest quest = new Quest(42, "Sea of Greed report", story,
                "", objectiveText,
                "", tasks, itemPrizes, 21, 15000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 40, 39);
    }
}
