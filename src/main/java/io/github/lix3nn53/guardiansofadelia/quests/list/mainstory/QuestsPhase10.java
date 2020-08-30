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

public class QuestsPhase10 {

    public static void createQuests() {
        createQuestOne();
        createQuestTwo();
        createQuestThree();
        createQuestFour();
        createQuestFive();
        createQuestSix();
        createQuestSeven();
        createQuestEight();
        createQuestNine();
        createQuestTen();
        createQuestEleven();
        createQuestTwelve();
        createQuestThirteen();
    }

    private static void createQuestOne() {
        List<String> story = new ArrayList<>();
        story.add("Archangel wants you to complete");
        story.add("a challenge. Go and repair the flag");
        story.add("of Alberstol Ruins.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(79);

        List<Task> tasks = new ArrayList<>();
        Location reachLocation = new Location(Bukkit.getWorld("world"), -4742.5, 140.5, 2151.5);
        TaskReach taskReach = new TaskReach(reachLocation, Material.EMERALD_BLOCK);
        tasks.add(taskReach);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String startMsg = ChatColor.YELLOW + "Click" + ChatColor.BOLD + " Compass Icon " + ChatColor.RESET + ChatColor.YELLOW + "from menu-book and select your destination NPC.";
        Quest quest = new Quest(80, "ggggggggggggggggg", story,
                startMsg, "TASK_PROGRESS_1\nThen talk back to Archangel",
                "", tasks, itemPrizes, 64, 220000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestTwo() {
        List<String> story = new ArrayList<>();
        story.add("Creatures of darkness deforming the");
        story.add("lands of Adelia. We have to stop them");
        story.add("before it's too late.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(80);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.DARK_PURPLE + "Void", 91);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.LIGHT_PURPLE + "Phantom", 91);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nThen talk back to Archangel";
        Quest quest = new Quest(81, "Darkness hunt", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 420000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestThree() {
        List<String> story = new ArrayList<>();
        story.add("Villagers who lost to their desires,");
        story.add("lost their souls and became pillagers.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(80);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.DARK_PURPLE + "Fighter Pillager", 91);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.DARK_PURPLE + "Rogue Pillager", 91);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nThen talk back to Archangel";
        Quest quest = new Quest(82, "Pillager hunt 1", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 420000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestFour() {
        List<String> story = new ArrayList<>();
        story.add("Villagers who lost to their desires,");
        story.add("lost their souls and became pillagers.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(80);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.DARK_PURPLE + "Mage Pillager", 91);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.DARK_PURPLE + "Shaman Pillager", 91);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nThen talk back to Archangel";
        Quest quest = new Quest(83, "Pillager hunt 2", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 420000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestFive() {
        List<String> story = new ArrayList<>();
        story.add("Temple of Spirit is conquered by Aleesia.");
        story.add("We need to regain control of the temple");
        story.add("to use the portal to Aleesia's Castle.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(80);

        List<Task> tasks = new ArrayList<>();
        Location reachLocation = new Location(Bukkit.getWorld("world"), -4375.5, 41.5, 1576.5);
        TaskReach taskReach = new TaskReach(reachLocation, Material.REDSTONE_BLOCK);
        tasks.add(taskReach);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nThen talk back to Archangel";
        Quest quest = new Quest(84, "Temple of Spirit", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 420000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestSix() {
        List<String> story = new ArrayList<>();
        story.add("Collect dark souls from pillagers");
        story.add("so Archangel can find their sources.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(80);

        List<Task> tasks = new ArrayList<>();
        List<String> nameOfMobsItemDropsFrom = new ArrayList<>();
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_PURPLE + "Fighter Pillager");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_PURPLE + "Commander Pillager");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_PURPLE + "Mage Pillager");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_PURPLE + "Rogue Pillager");
        nameOfMobsItemDropsFrom.add(ChatColor.DARK_PURPLE + "Shaman Pillager");
        TaskCollect taskCollect = new TaskCollect(nameOfMobsItemDropsFrom, 0.64, new ItemStack(Material.ACACIA_BOAT), 91);
        tasks.add(taskCollect);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nfrom pillagers then talk back to Archangel";
        Quest quest = new Quest(85, "Dark souls", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 420000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestSeven() {
        List<String> story = new ArrayList<>();
        story.add("Villagers who lost to their desires,");
        story.add("lost their souls and became pillagers.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(80);
        List<Task> tasks = new ArrayList<>();

        TaskKill taskKill = new TaskKill(ChatColor.DARK_PURPLE + "Commander Pillager", 91);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.YELLOW + "Ravager", 91);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nthen talk back to Archangel";
        Quest quest = new Quest(86, "Pillager hunt 3", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 420000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 49);
    }

    private static void createQuestEight() {
        List<String> story = new ArrayList<>();
        story.add("Talk with Eohr in Temple of Elements to");
        story.add("get ready for the last battle.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(81);
        requiredQuests.add(82);
        requiredQuests.add(83);
        requiredQuests.add(84);
        requiredQuests.add(85);
        requiredQuests.add(86);
        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Talk with Eohr in Temple of Elements";
        Quest quest = new Quest(87, "Temple of Elements", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 55000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 50);
    }

    private static void createQuestNine() {
        List<String> story = new ArrayList<>();
        story.add("The battle to decide Adelia's fate.");
        story.add("Let the power of the elements be with you!");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(87);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.DARK_PURPLE + "Aleesia", 1);
        tasks.add(taskKill);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTime to face the nightmare!";
        Quest quest = new Quest(88, "Facing the nightmare", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 8608000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 50, 50);
    }

    private static void createQuestTen() {
        List<String> story = new ArrayList<>();
        story.add("Bring back the light to Alberstol Ruins.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(88);
        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Bring the light of elements to Archangel";
        Quest quest = new Quest(89, "Bring back the light to Alberstol Ruins", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 55000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 50, 49);
    }

    private static void createQuestEleven() {
        List<String> story = new ArrayList<>();
        story.add("Bring back the light to Uruga.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(89);
        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Bring the light of elements to Commander Erwin";
        Quest quest = new Quest(90, "Bring back the light to Uruga", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 55000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 49, 42);
    }

    private static void createQuestTwelve() {
        List<String> story = new ArrayList<>();
        story.add("Bring back the light to Elderine.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(90);
        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Bring the light of elements to Captain Lenna";
        Quest quest = new Quest(91, "Bring back the light to Elderine", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 55000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 42, 39);
    }

    private static void createQuestThirteen() {
        List<String> story = new ArrayList<>();
        story.add("Bring back the light to Roumen.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(91);
        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Bring the light of elements to Sergeant Armin";
        Quest quest = new Quest(92, "Bring back the light to Roumen", story,
                "", objectiveText,
                "", tasks, itemPrizes, 64, 55000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 39, 32);
    }
}
