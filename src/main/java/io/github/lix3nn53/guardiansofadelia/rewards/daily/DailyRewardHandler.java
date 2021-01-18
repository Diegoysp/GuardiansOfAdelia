package io.github.lix3nn53.guardiansofadelia.rewards.daily;

import io.github.lix3nn53.guardiansofadelia.guardian.GuardianData;
import io.github.lix3nn53.guardiansofadelia.guardian.GuardianDataManager;
import io.github.lix3nn53.guardiansofadelia.utilities.DateUtils;
import io.github.lix3nn53.guardiansofadelia.utilities.InventoryUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DailyRewardHandler {

    private static final ItemStack[] itemPrizes = new ItemStack[7]; //A week is 7 days

    public static void setReward(int day, ItemStack itemStack) {
        if (day < 1 || day > 7) {
            throw new IllegalArgumentException();
        }

        itemPrizes[day - 1] = itemStack;
    }

    public static ItemStack[] getRewards() {
        return itemPrizes;
    }

    public static int getCurrentIndex() {
        return DateUtils.getDayOfTheWeek();
    }

    public static int getIndexOfDate(LocalDate date) {
        if (date == null) {
            return 0;
        }

        boolean dateInCurrentWeek = DateUtils.isDateInCurrentWeek(date);

        if (!dateInCurrentWeek) { //return first index if date is older than current week
            return 0;
        }

        return date.get(ChronoField.DAY_OF_WEEK);
    }

    public static void giveReward(Player player) {
        boolean hasGuardianData = GuardianDataManager.hasGuardianData(player);

        if (!hasGuardianData) return;

        GuardianData guardianData = GuardianDataManager.getGuardianData(player);

        DailyRewardInfo dailyRewardInfo = guardianData.getDailyRewardInfo();

        boolean canGetReward = canGetReward(dailyRewardInfo);

        if (!canGetReward) return;

        int index = DateUtils.getDayOfTheWeek() - 1;
        ItemStack itemPrize = itemPrizes[index];

        if (itemPrize == null) return;

        dailyRewardInfo.setLastObtainDate(LocalDate.now());
        InventoryUtils.giveItemToPlayer(player, itemPrize);
    }

    private static boolean canGetReward(DailyRewardInfo dailyRewardInfo) {
        LocalDate lastPrizeDate = dailyRewardInfo.getLastObtainDate();

        int indexOfDate = getIndexOfDate(lastPrizeDate);

        //get day of the week for current day
        int dayOfTheWeek = DateUtils.getDayOfTheWeek();

        return indexOfDate == dayOfTheWeek - 1; //is yesterday
    }
}
