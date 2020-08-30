package io.github.lix3nn53.guardiansofadelia.quests.list.mainstory;

import io.github.lix3nn53.guardiansofadelia.jobs.gathering.GatheringManager;
import io.github.lix3nn53.guardiansofadelia.jobs.gathering.GatheringTool;
import io.github.lix3nn53.guardiansofadelia.jobs.gathering.GatheringToolTier;
import io.github.lix3nn53.guardiansofadelia.npc.QuestNPCManager;
import io.github.lix3nn53.guardiansofadelia.quests.Quest;
import io.github.lix3nn53.guardiansofadelia.quests.actions.GiveItemAction;
import io.github.lix3nn53.guardiansofadelia.quests.task.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class QuestsPhase3 {

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
        story.add("The darkness' effects on Forest of Mist");
        story.add("is killing magical beings.");
        story.add("Talk to Timberman Franky, he knows much");
        story.add("more about the forest.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(18);
        List<Task> tasks = new ArrayList<>();
        List<ItemStack> itemPrizes = new ArrayList<>();
        String startMsg = ChatColor.YELLOW + "Click" + ChatColor.BOLD + " Compass Icon " + ChatColor.RESET + ChatColor.YELLOW + "from menu-book and select your destination NPC.";
        Quest quest = new Quest(19, "Forest of Mist", story,
                startMsg, "Talk with Timberman Franky",
                "", tasks, itemPrizes, 11, 2400, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 32, 36);
    }

    private static void createQuestTwo() {
        List<String> story = new ArrayList<>();
        story.add("Franky can't gather woods because");
        story.add("of monsters. Get this axe and bring");
        story.add("him some woods.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(19);

        List<Task> tasks = new ArrayList<>();
        TaskGathering taskGathering = new TaskGathering(GatheringManager.getIngredient(1), 6);
        tasks.add(taskGathering);
        TaskGathering taskGathering2 = new TaskGathering(GatheringManager.getIngredient(1), 4);
        tasks.add(taskGathering2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2" + " for Franky";
        Quest quest = new Quest(20, "Woodcutting", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 12000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 36, 36);

        ItemStack itemStack = GatheringTool.AXE.getItemStack(GatheringToolTier.WOODEN);
        GiveItemAction giveItemAction = new GiveItemAction(itemStack);
        quest.addOnAcceptAction(giveItemAction);
    }

    private static void createQuestThree() {
        List<String> story = new ArrayList<>();
        story.add("Forest fairies fighting darkness");
        story.add("by themselves. Talk with the fairy in");
        story.add("middle of Forest of Mist.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(19);

        List<Task> tasks = new ArrayList<>();

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "Talk with Forest Fairy";
        Quest quest = new Quest(21, "Forest fairies", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 12000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 36, 37);
    }

    private static void createQuestFour() {
        List<String> story = new ArrayList<>();
        story.add("Help forest fairies on their battle");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(21);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.WHITE + "Archer Skeleton", 21);
        tasks.add(taskKill);
        TaskKill taskKill2 = new TaskKill(ChatColor.WHITE + "Fighter Skeleton", 21);
        tasks.add(taskKill2);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nTASK_PROGRESS_2\nThen talk back to the Forest Fairy";
        Quest quest = new Quest(22, "Skeleton hunt 1", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 24000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 37, 37);
    }

    private static void createQuestFive() {
        List<String> story = new ArrayList<>();
        story.add("Collect magical bones from");
        story.add("skeletons to find the source");
        story.add("of their magic!");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(21);

        List<Task> tasks = new ArrayList<>();
        List<String> nameOfMobsItemDropsFrom = new ArrayList<>();
        nameOfMobsItemDropsFrom.add(ChatColor.WHITE + "Archer Skeleton");
        nameOfMobsItemDropsFrom.add(ChatColor.WHITE + "Fighter Skeleton");
        nameOfMobsItemDropsFrom.add(ChatColor.WHITE + "Rogue Skeleton");
        nameOfMobsItemDropsFrom.add(ChatColor.WHITE + "Monk Skeleton");
        nameOfMobsItemDropsFrom.add(ChatColor.WHITE + "Mage Skeleton");
        TaskCollect taskCollect = new TaskCollect(nameOfMobsItemDropsFrom, 0.64, new ItemStack(Material.ACACIA_BOAT), 14);
        tasks.add(taskCollect);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nfrom skeletons then talk back to the Forest Fairy";
        Quest quest = new Quest(23, "Magical bones", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 24000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 37, 37);
    }

    private static void createQuestSix() {
        List<String> story = new ArrayList<>();
        story.add("Mage skeletons are too hard to handle");
        story.add("for forest fairies.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(21);

        List<Task> tasks = new ArrayList<>();
        TaskKill taskKill = new TaskKill(ChatColor.WHITE + "Mage Skeleton", 12);
        tasks.add(taskKill);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nthen talk back to the Forest Fairy";
        Quest quest = new Quest(24, "Skeleton hunt 2", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 24000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 37, 37);
    }

    private static void createQuestSeven() {
        List<String> story = new ArrayList<>();
        story.add("Thanks to your effort forest fairies");
        story.add("found the source of dark magic that");
        story.add("controls skeletons.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(20);
        requiredQuests.add(21);
        requiredQuests.add(22);
        requiredQuests.add(23);
        requiredQuests.add(24);
        List<Task> tasks = new ArrayList<>();

        TaskKill taskKill = new TaskKill(ChatColor.DARK_PURPLE + "Dark Magician Nimzuth", 1);
        tasks.add(taskKill);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "<Dungeon>\nTASK_PROGRESS_1\nthen talk to Forest Fairy";
        Quest quest = new Quest(25, "The source of dark magic", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 48000, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 37, 37);
    }

    private static void createQuestEight() {
        List<String> story = new ArrayList<>();
        story.add("Report back that you destroyed the");
        story.add("source of dark magic in Forest of Mist.");
        List<Integer> requiredQuests = new ArrayList<>();
        requiredQuests.add(25);
        List<Task> tasks = new ArrayList<>();

        Task task = new TaskInteract(36);
        tasks.add(task);

        List<ItemStack> itemPrizes = new ArrayList<>();
        String objectiveText = "TASK_PROGRESS_1\nthen report back to Sergent Armin.";
        Quest quest = new Quest(26, "Forest fairies are safe", story,
                "", objectiveText,
                "", tasks, itemPrizes, 11, 3200, 0, requiredQuests,
                Material.GRASS_BLOCK);
        QuestNPCManager.addQuest(quest, 37, 32);
    }
}
