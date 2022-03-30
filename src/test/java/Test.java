import io.github.lix3nn53.guardiansofadelia.database.DatabaseQueries;
import io.github.lix3nn53.guardiansofadelia.guardian.element.ElementType;

import java.sql.SQLException;
import java.util.*;

public class Test {

    public static String getRandomMonsterToSpawn(int darkness, List<String> monsterPool) {
        if (darkness < 0) {
            darkness = 1;
        }

        int size = monsterPool.size();
        float darknessPercent = darkness / 100f;

        int totalWeight = 0;
        List<Integer> weights = new ArrayList<>();
        for (int i = 1; i <= size; i++) { // start from 1 so first index has a percent and last one is 100
            float indexPercent = i / (float) size;
            float diff = Math.abs(indexPercent - darknessPercent);
            float diffReverse = 1 - diff;

            int weight = (int) (10 * diffReverse * diffReverse * diffReverse + 0.5); // to make differences between indexes larger
            System.out.println("weight " + i + ": " + weight);
            totalWeight += weight;
            weights.add(weight);
        }
        System.out.println("totalWeight: " + totalWeight);

        float randomWeight = (float) Math.random() * totalWeight;
        System.out.println("randomWeight: " + randomWeight);

        int weightCounter = 0;
        for (int i = 0; i <= size; i++) {
            weightCounter += weights.get(i);
            if (weightCounter >= randomWeight) {
                System.out.println("result: " + (i + 1));
                return monsterPool.get(i);
            }
        }

        return monsterPool.get(1);
    }

    public static void main(String[] args) throws InterruptedException, SQLException {

        final HashMap<ElementType, Integer> elementTypeToValue = new HashMap<>();

        elementTypeToValue.put(ElementType.AIR, 100);
        elementTypeToValue.put(ElementType.EARTH, 300);
        elementTypeToValue.put(ElementType.LIGHTNING, 90);
        elementTypeToValue.put(ElementType.FIRE, 200);
        elementTypeToValue.put(ElementType.WATER, 150);

        // Create a list from elements of HashMap
        List<Map.Entry<ElementType, Integer>> list = new LinkedList<>(elementTypeToValue.entrySet());

        // Sort the list
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        // put data from sorted list to hashmap
        HashMap<ElementType, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<ElementType, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        for (Map.Entry<ElementType, Integer> en : temp.entrySet()) {
            System.out.println("element: " + en.getKey() + " value: " + en.getValue());
        }


        /*int darkness = 40;

        ArrayList<String> monsterPool = new ArrayList<>();
        monsterPool.add("1");
        monsterPool.add("2");
        monsterPool.add("3");
        monsterPool.add("4");
        monsterPool.add("5");
        monsterPool.add("6");
        monsterPool.add("7");
        monsterPool.add("8");
        monsterPool.add("9");
        monsterPool.add("10");

        getRandomMonsterToSpawn(darkness, monsterPool);*/

        /*for (int levelD = 1; levelD <= 90; levelD++) {
            // Use i as normal here
            int damageItem = StatUtils.getDamageItem(levelD);
            int healthItem = StatUtils.getHealthItem(levelD);
            System.out.println("level: " + levelD + " damage: " + damageItem + " hp: " + healthItem);
        }*/

        /*int size = 5;
        int maxLevel = 10;
        for (int darkness = 1; darkness < 100; darkness += 5) {
            int bound = (int) ((darkness / 100d) * size) + 1;

            System.out.println("-------- darkness: " + darkness);
            System.out.println("bound: " + bound);

            int i = 0;
            if (bound > 1) {
                i = GuardiansOfAdelia.RANDOM.nextInt(bound);
            }
            System.out.println("i: " + i);

            int level = (int) ((darkness / 100d) * maxLevel) + 1;
            System.out.println("level: " + level);
        }*/

        /*int reqLevel = 4;
        float price = Math.max(1, Math.pow(reqLevel, 1.2) / 4);

        ItemTier itemTier = ItemTier.COMMON;
        price = price * itemTier.getBonusMultiplier();

        int enchantLevel = 0;
        price = price * EnchantManager.getSellGuiMultiplier(enchantLevel);

        System.out.println("price: " + price);
        System.out.println("price: " + (int) (price + 0.5) * 1);*/

        /*int playerLevel = 2;
        int shareCount = 1;
        for (int mobLevel = 1; mobLevel < 91; mobLevel++) {
            if (mobLevel == 0) mobLevel = 1;

            int exp = (int) (2 + Math.round(10 * Math.pow(mobLevel, 2) / 16) + 0.5);

            if (playerLevel > 9) { //do not reduce exp according to player level before level 10
                if (playerLevel > mobLevel) {
                    exp = (int) (exp * (Math.pow(mobLevel, 1.2) / Math.pow(playerLevel, 1.2)) + 0.5);
                } else {
                    exp = (int) (exp * (Math.pow(playerLevel, 1.2) / Math.pow(mobLevel, 1.2)) + 0.5);
                }
            }

            //Share
            if (shareCount > 1) {
                float expMultiplier = 1 - (0.1 * shareCount);

                exp = (int) (exp * expMultiplier + 0.5);
            }

            if (exp == 0) exp = 1;
            System.out.println("mobLevel: " + mobLevel + "exp: " + exp);
        }

        int totalMaxMana = 100;
        int currentMana = 100;
        if (currentMana > totalMaxMana) {
            currentMana = totalMaxMana;
        }

        float ratio = (float) currentMana / totalMaxMana;
        int foodLevel = (int) (19 * ratio + 0.5);

        if (currentMana > 0) {
            if (foodLevel <= 0) {
                foodLevel = 1;
            }
        } else {
            foodLevel = 0;
        }

        System.out.println("foodLevel: " + foodLevel);*/

        /*
        Vector vector = new Vector(8, 8, 8);

        Vector[] cubeCorners = new Vector[8];

        float length_x = vector.getX() / 2.0;
        float length_y = vector.getY() / 2.0;
        float length_z = vector.getZ() / 2.0;

        Vector centerVector = new Vector(0, 0, 0);
        System.out.println("CUBE DRAW: " + vector.getX() + ", " + vector.getY() + ", " + vector.getZ());
        System.out.println("centerVector (before translation): " + centerVector);

        cubeCorners[0] = centerVector.clone().add(new Vector(-length_x, -length_y, -length_z));
        cubeCorners[1] = centerVector.clone().add(new Vector(length_x, -length_y, -length_z));
        cubeCorners[2] = centerVector.clone().add(new Vector(length_x, length_y, -length_z));
        cubeCorners[3] = centerVector.clone().add(new Vector(-length_x, length_y, -length_z));
        cubeCorners[4] = centerVector.clone().add(new Vector(-length_x, -length_y, length_z));
        cubeCorners[5] = centerVector.clone().add(new Vector(length_x, -length_y, length_z));
        cubeCorners[6] = centerVector.clone().add(new Vector(length_x, length_y, length_z));
        cubeCorners[7] = centerVector.clone().add(new Vector(-length_x, length_y, length_z));

        // Rotate
        RotationHelper.rotateYawPitch(cubeCorners, 45, 0);

        Vector translateVector = new Vector(length_x, length_y, length_z);

        for (int i = 0; i < 8; i++) {
            Vector point = cubeCorners[i];
            MatrixHelper.translate(point, translateVector);
        }

        Vector b1 = cubeCorners[0].clone();
        System.out.println("b1: " + b1);
        Vector b2 = cubeCorners[1].clone();
        System.out.println("b2: " + b2);
        Vector b4 = cubeCorners[4].clone();
        System.out.println("b4: " + b4);
        Vector t1 = cubeCorners[3].clone();
        System.out.println("t1: " + t1);
        Vector t3 = cubeCorners[6].clone();
        System.out.println("t3: " + t3);

        Vector yLocal = t1.clone().subtract(b1);
        float yLength = yLocal.length(); // size1 = np.linalg.norm(dir1)
        yLocal = yLocal.normalize(); // dir1 = dir1.divide(new Vector(size1, size1, size1));

        Vector xLocal = b2.clone().subtract(b1);
        float xLength = xLocal.length();
        xLocal = xLocal.normalize();

        Vector zLocal = b4.clone().subtract(b1);
        float zLength = zLocal.length();
        zLocal = zLocal.normalize();

        // cube3d_center = (b1 + t3)/2.0
        Vector center = b1.clone().add(t3).divide(new Vector(2, 2, 2));
        System.out.println("center calculated (after translation): " + center);

        ArrayList<Vector> result = new ArrayList<>();
        // dir_vec = points - cube3d_center
        // res1 = np.where( (np.absolute(np.dot(dir_vec, dir1)) * 2) > size1 )[0]
        // res2 = np.where( (np.absolute(np.dot(dir_vec, dir2)) * 2) > size2 )[0]
        // res3 = np.where( (np.absolute(np.dot(dir_vec, dir3)) * 2) > size3 )[0]

        // return list( set().union(res1, res2, res3) )

        for (Vector corner : cubeCorners) {
            System.out.println("cubeCorners: " + corner);
        }

        Vector[] points = new Vector[8];
        points[0] = new Vector(0.1, 0.1, 0.1);
        points[1] = new Vector(0.0, 0.0, 0.0);
        points[2] = new Vector(8, 8, 8);
        points[3] = new Vector(4, 4, 4);
        points[4] = new Vector(-5, -4, -6);
        points[5] = new Vector(-5, -4, -6);
        points[6] = new Vector(-5, -4, -6);
        points[7] = new Vector(-5, -4, -6);

        for (Vector target : points) {
            Vector v = target.clone().subtract(center); // direction vector from cube center to the target point

            float py = Math.abs(v.dot(yLocal)) * 2;
            float px = Math.abs(v.dot(xLocal)) * 2;
            float pz = Math.abs(v.dot(zLocal)) * 2;

            if (px <= xLength && py <= yLength && pz <= zLength) {
                result.add(target);
            }
        }

        System.out.println("result: " + result);*/

        /*for (GearLevel gearLevel : GearLevel.values()) {
            System.out.println("-----------" + gearLevel.toString());
            int minNumberOfAttr = 5;
            int minNumberOfElements = 5;
            int minAttrValue = gearLevel.getMinStatValue(true, false);
            int maxAttrValue = gearLevel.getMaxStatValue(true, false);
            int minElemValue = gearLevel.getMinStatValue(true, true);
            int maxElemValue = gearLevel.getMaxStatValue(true, true);
            System.out.println("min attr: " + minAttrValue);
            System.out.println("max attr: " + maxAttrValue);
            System.out.println("min element: " + minElemValue);
            System.out.println("max element: " + maxElemValue);

            StatPassive statPassive = new StatPassive(minAttrValue, maxAttrValue, minNumberOfAttr, minElemValue, maxElemValue, minNumberOfElements);
            // StatPassive statPassive = new StatPassive(15, 100, 2, 50, 250, 3);

            for (AttributeType attributeType : AttributeType.values()) {
                int attributeValue = statPassive.getAttributeValue(attributeType);
                if (attributeValue == 0) continue;
                System.out.println(attributeType.name() + ": " + attributeValue);
            }
            for (ElementType elementType : ElementType.values()) {
                int attributeValue = statPassive.getElementValue(elementType);
                if (attributeValue == 0) continue;
                System.out.println(elementType.name() + ": " + attributeValue);
            }
        }*/
        /*for (int requiredLevel = 0; requiredLevel <= 99; requiredLevel += 10) {
            GearLevel gearLevel = GearLevel.getGearLevel(requiredLevel);
            System.out.println("requiredLevel: " + requiredLevel + "GearLevel: " + gearLevel);
        }

        for (int requiredLevel = 0; requiredLevel <= 90; requiredLevel += 10) {
            System.out.println("REQUIRED LEVEL " + requiredLevel);
            WeaponSet weaponSet = new WeaponSet("requiredLevel" + requiredLevel, requiredLevel, 0);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1 * 0.4);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1 * 0.6);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1 * 0.8);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1 * 1);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1.4 * 0.4);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1.4 * 0.6);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1.4 * 0.8);
            System.out.println("weaponSet damage: " + weaponSet.getElementDamage(WeaponGearType.WAND) * 1.4 * 1);
            ArmorSet armorSet = new ArmorSet("requiredLevel" + requiredLevel, requiredLevel);
            int totalHp = 0;
            int totalArmor = 0;
            for (ArmorSlot armorSlot : ArmorSlot.values()) {
                totalHp += armorSet.getHealth(armorSlot, ArmorGearType.FEATHER_ARMOR);
                totalArmor += armorSet.getDefense(armorSlot, ArmorGearType.FEATHER_ARMOR);
            }
            System.out.println("armorSet health: " + totalHp);
            float defenseReduction = StatUtils.getDefenseReduction(totalArmor);
            System.out.println("armorSet defense: " + totalArmor + " - " + "(" + new DecimalFormat("##.##").format((1.0 - defenseReduction) * 100) + "% reduction)");
            ShieldSet shieldSet = new ShieldSet("requiredLevel" + requiredLevel, requiredLevel, 0);
            System.out.println("shieldSet health: " + shieldSet.getHealth(ShieldGearType.SHIELD));
        }*/

        /*float angle = 30;

        float[][] rotationY = MatrixHelper.rotationY(angle);

        System.out.println("rotationY: " + Arrays.deepToString(rotationY));

        float[][] location = new float[][]{new float[]{5}, new float[]{5}, new float[]{5}};

        System.out.println("location: " + Arrays.deepToString(location));

        float[][] result = MatrixHelper.multiplyMatrices(rotationY, location);

        System.out.println("result: " + Arrays.deepToString(result));
        System.out.println("result x: " + result[0][0]);
        System.out.println("result y: " + result[1][0]);
        System.out.println("result z: " + result[2][0]);

        float[][] translation = MatrixHelper.translate(result[0][0], result[1][0], result[2][0], new Vector(0, 10, 0));


        System.out.println("translation: " + Arrays.deepToString(translation));*/




        /*GearSet gearSet = new GearSet("Tutorial", 2);
        GearSet gearSet1 = new GearSet("Tutorial", 2);
        GearSet gearSet2 = new GearSet("Tutorial", 2);

        System.out.println("equals: " + gearSet.equals(gearSet1));
        System.out.println("hash1: " + gearSet1.hashCode());
        System.out.println("hash2: " + gearSet2.hashCode());*/

        /*int stepCount = 5;
        for (int ticksRun = 0; ticksRun <= 100; ticksRun++) {
            boolean doesDivide = ticksRun % 4 == 0;
            if (doesDivide) {
                System.out.println("Tick: " + ticksRun);

                int currentStep = ticksRun / 4;

                System.out.println("Step: " + currentStep);

                if (currentStep < stepCount) {
                    System.out.println("NextStep");
                } else {
                    System.out.println("Finish");
                    break;
                }
            } else {
                System.out.println("Skip tick: " + ticksRun);
            }
        }*/
        /*for (int mobLevel = 1; mobLevel <= 10; mobLevel++) {
            for (int playerLevel = 1; playerLevel <= 20; playerLevel++) {
                System.out.println("mobLevel: " + mobLevel);
                System.out.println("playerLevel: " + playerLevel);
                if (mobLevel == 0) mobLevel = 1;

                int exp = (int) (2 + Math.round(10 * Math.pow(mobLevel, 2) / 16) + 0.5);
                System.out.println(exp);

                if (playerLevel > 9) {
                    if (playerLevel > mobLevel) {
                        exp = (int) (exp * (Math.pow(mobLevel, 1.2) / Math.pow(playerLevel, 1.2)) + 0.5);
                    } else {
                        exp = (int) (exp * (Math.pow(playerLevel, 1.2) / Math.pow(mobLevel, 1.2)) + 0.5);
                    }
                }
                //Share
                int shareCount = 1;
                if (shareCount > 1) {
                    float expMultiplier = 1 - (0.1 * shareCount);

                    exp = (int) (exp * expMultiplier + 0.5);
                }

                if (exp == 0) exp = 1;
                System.out.println(exp);
            }
        }*/
        /*LocalDate localDate = LocalDate.now().with(ChronoField.DAY_OF_MONTH, 7);
        boolean dateInCurrentWeek = DateUtils.isDateInCurrentWeek(localDate);

        LocalDate firstDayOfTheWeek = DateUtils.getFirstDayOfTheWeek();
        System.out.println("firstDayOfTheWeek: " + firstDayOfTheWeek);
        int dayOfTheWeek = DateUtils.getDayOfTheWeek();
        System.out.println("dayOfTheWeek: " + dayOfTheWeek);
        System.out.println("dateInCurrentWeek: " + dateInCurrentWeek);
        boolean sameDay = DateUtils.isSameDay(LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), LocalDate.now());
        System.out.println("sameDay: " + sameDay);*/


        /*LocalDate now = LocalDate.now();
        String currentDateString = now.toString();
        System.out.println(currentDateString);

        for (int i = 0; i < 15; i++) {
            int rpgClassRandom = rand.nextInt(7);
            RPGClass rpgClass = RPGClass.values()[rpgClassRandom + 1]; //+1 to ignore NO_CLASS

            // Obtain a number between [0 - 2].
            int dropType = rand.nextInt(3);

            System.out.println(rpgClass.toString());
            System.out.println("dropType: " + dropType);
        }*/
        /*MySocketServer server = new MySocketServer("localhost", 9092);

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();*/
        /*int value = 1000;
        int z = (int) ((value * MULTIPLIER) + 0.5);
        System.out.println(z);
        System.out.println((int) ((z / MULTIPLIER) + 0.5));
        String currentName = ChatPalette.GOLD + "Test";
        System.out.println(currentName);
        String stripColor = ChatColor.stripColor(currentName);
        System.out.println(stripColor);*/
        /*List<Float> downRatesForLevel = new ArrayList<>();
        downRatesForLevel.add(0.75);
        downRatesForLevel.add(0.6);
        downRatesForLevel.add(0.45);
        downRatesForLevel.add(0.325);
        downRatesForLevel.add(0.2);
        downRatesForLevel.add(0.1);
        downRatesForLevel.add(0.05);
        downRatesForLevel.add(0.02);
        downRatesForLevel.add(0.01);

        int value = 640;
        for (float rate : downRatesForLevel) {
            System.out.println(value * rate);
        }*/
    }

    private static float expFormula(int level) {
        return 10 + Math.round(5 * Math.pow(level, 3) / 4);
    }

    private static void asd() {
        DatabaseQueries.createTables();

        List<Integer> playersInGame = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            playersInGame.add(i);
        }

        for (int i = 4; i < playersInGame.size(); i+=4) {
            List<Integer> players = playersInGame.subList(i - 4, i);
            System.out.println(players);
        }

        int armor = 4000;

        //dmg reduction formula
        float reduction = (1 - (armor / (armor + 3000f)));

        System.out.println(reduction);

        int damage = 100;

        System.out.println(damage * reduction);

        int count = 1;
        for (int i = 1; i <= 210; i+=20) {
            float pow = (float) (Math.pow(i, 1f / 2f) / 2.5f * Math.pow(i, 2f));
            int result = (int) (pow + 0.5);
            System.out.println(count + ": " + result);
            count++;
        }

        //exp formula
        List<Integer> monstersToKill = new ArrayList<>();
        monstersToKill.add(30);
        monstersToKill.add(80);
        monstersToKill.add(110);
        monstersToKill.add(150);
        monstersToKill.add(180);
        monstersToKill.add(230);
        monstersToKill.add(260);
        monstersToKill.add(290);
        monstersToKill.add(320);
        monstersToKill.add(360);
        monstersToKill.add(400);
        monstersToKill.add(450);
        monstersToKill.add(500);
        monstersToKill.add(550);
        monstersToKill.add(600);
        monstersToKill.add(680);
        monstersToKill.add(750);
        monstersToKill.add(820);
        monstersToKill.add(900);

        for (int lvl = 0; lvl <=90; lvl++) {
            //float exp = x * lvl * lvl + y * lvl + z;
            float exp = expFormula(lvl);
            System.out.println("Level " + lvl + " TotalExp: " + exp);
        }

        for (int lvl = 0; lvl <=90; lvl+=5) {
            //float exp = x * lvl * lvl + y * lvl + z;
            float exp = expFormula(lvl);
            int i = (int) (lvl / 5 + 0.5);
            System.out.println("Level " + lvl + " MobKill: " + monstersToKill.get(i));
        }

        for (int lvl = 0; lvl <=90; lvl++) {
            //float exp = x * lvl * lvl + y * lvl + z;
            float exp = expFormula(lvl);
            int i = (int) (lvl / 5 + 0.5);
            System.out.println("Level " + lvl + " ExpPerMob: " + exp / monstersToKill.get(i));
        }

        for (int lvl = 0; lvl <=90; lvl+=5) {
            //float exp = x * lvl * lvl + y * lvl + z;
            float exp = expFormula(lvl);
            int i = (int) (lvl / 5 + 0.5);
            System.out.println("Level " + lvl + " ExpPerMob: " + exp / monstersToKill.get(i));
        }
    }

    private static void printClassStats() {
        HashMap<String, Integer> classToMaxHP = new HashMap<>();
        HashMap<String, Integer> classToMaxMP = new HashMap<>();
        HashMap<String, Integer> classToMaxDMG = new HashMap<>();
        HashMap<String, Integer> classToMaxMDMG = new HashMap<>();
        HashMap<String, Integer> classToMaxDEF = new HashMap<>();
        HashMap<String, Integer> classToMaxMDEF = new HashMap<>();

        List<String> classes = new ArrayList<>();
        classes.add("knight");
        classes.add("paladin");
        classes.add("warrior");
        classes.add("rogue");
        classes.add("archer");
        classes.add("mage");
        classes.add("monk");
        classes.add("hunter");

        for (String clas : classes) {
            float maxhp = 20000;
            float maxmp = 20000;
            float maxdmg = 2000;
            float maxmdmg = 2000;
            float maxdef = 2000;
            float maxmdef = 2000;

            float hp = 5;
            float mp = 5;
            float dmg = 5;
            float mdmg = 5;
            float def = 5;
            float mdef = 5;
            if (clas.equals("knight")) {
                hp = 5;
                mp = 2;
                dmg = 3;
                mdmg = 1;
                def = 5;
                mdef = 2;
            } else if (clas.equals("paladin")) {
                hp = 4;
                mp = 4;
                dmg = 2;
                mdmg = 1;
                def = 3;
                mdef = 4;
            } else if (clas.equals("warrior")) {
                hp = 4;
                mp = 2;
                dmg = 6;
                mdmg = 1;
                def = 3;
                mdef = 2;
            } else if (clas.equals("rogue")) {
                hp = 3;
                mp = 3;
                dmg = 5;
                mdmg = 2;
                def = 3;
                mdef = 2;
            } else if (clas.equals("archer")) {
                hp = 2;
                mp = 4;
                dmg = 4;
                mdmg = 3;
                def = 3;
                mdef = 2;
            } else if (clas.equals("mage")) {
                hp = 2;
                mp = 5;
                dmg = 1;
                mdmg = 5;
                def = 2;
                mdef = 2;
            } else if (clas.equals("monk")) {
                hp = 4;
                mp = 3;
                dmg = 4;
                mdmg = 2;
                def = 3;
                mdef = 2;
            } else if (clas.equals("hunter")) {
                hp = 3;
                mp = 4;
                dmg = 4;
                mdmg = 2;
                def = 3;
                mdef = 2;
            }

            classToMaxHP.put(clas, (int) (maxhp / 5 * hp));
            classToMaxMP.put(clas, (int) (maxmp / 5 * mp));
            classToMaxDMG.put(clas, (int) (maxdmg / 5 * dmg));
            classToMaxMDMG.put(clas, (int) (maxmdmg / 5 * mdmg));
            classToMaxDEF.put(clas, (int) (maxdef / 5 * def));
            classToMaxMDEF.put(clas, (int) (maxmdef / 5 * mdef));
        }

        List<Float> downRatesForLevel = new ArrayList<>();
        downRatesForLevel.add(0.75f);
        downRatesForLevel.add(0.6f);
        downRatesForLevel.add(0.45f);
        downRatesForLevel.add(0.325f);
        downRatesForLevel.add(0.2f);
        downRatesForLevel.add(0.125f);
        downRatesForLevel.add(0.05f);
        downRatesForLevel.add(0.02f);
        downRatesForLevel.add(0.005f);

        for (String key : classes) {
            int levelCount = 90;
            Integer hp = classToMaxHP.get(key);
            System.out.println(key + " level " + levelCount + " hp: " + hp);
            Integer mp = classToMaxMP.get(key);
            System.out.println(key + " level " + levelCount + " mp: " + mp);
            Integer dmg = classToMaxDMG.get(key);
            System.out.println(key + " level " + levelCount + " dmg: " + dmg);
            Integer mdmg = classToMaxMDMG.get(key);
            System.out.println(key + " level " + levelCount + " mdmg: " + mdmg);
            Integer def = classToMaxDEF.get(key);
            System.out.println(key + " level " + levelCount + " def: " + def);
            Integer mdef = classToMaxMDEF.get(key);
            System.out.println(key + " level " + levelCount + " mdef: " + mdef);
            for (float rate : downRatesForLevel) {
                levelCount -= 10;
                System.out.println(key + " level " + levelCount + " hp: " + hp * rate);
                System.out.println(key + " level " + levelCount + " mp: " + mp * rate);
                System.out.println(key + " level " + levelCount + " dmg: " + dmg * rate);
                System.out.println(key + " level " + levelCount + " mdmg: " + mdmg * rate);
                System.out.println(key + " level " + levelCount + " def: " + def * rate);
                System.out.println(key + " level " + levelCount + " mdef: " + mdef * rate);
            }
        }
    }

    private void printArmorStats() {
        HashMap<String, Integer> classToMaxHP = new HashMap<>();
        HashMap<String, Integer> classToMaxMP = new HashMap<>();
        HashMap<String, Integer> classToMaxDMG = new HashMap<>();
        HashMap<String, Integer> classToMaxMDMG = new HashMap<>();
        HashMap<String, Integer> classToMaxDEF = new HashMap<>();
        HashMap<String, Integer> classToMaxMDEF = new HashMap<>();

        List<String> classes = new ArrayList<>();
        classes.add("knight");
        classes.add("paladin");
        classes.add("warrior");
        classes.add("rogue");
        classes.add("archer");
        classes.add("mage");
        classes.add("monk");
        classes.add("hunter");

        for (String clas : classes) {
            float maxhp = 20000;
            float maxmp = 20000;
            float maxdmg = 2000;
            float maxmdmg = 2000;
            float maxdef = 2000;
            float maxmdef = 2000;

            float hp = 5;
            float mp = 5;
            float dmg = 5;
            float mdmg = 5;
            float def = 5;
            float mdef = 5;
            if (clas.equals("knight")) {
                hp = 5;
                mp = 2;
                dmg = 3;
                mdmg = 1;
                def = 5;
                mdef = 2;
            } else if (clas.equals("paladin")) {
                hp = 4;
                mp = 4;
                dmg = 2;
                mdmg = 1;
                def = 3;
                mdef = 4;
            } else if (clas.equals("warrior")) {
                hp = 4;
                mp = 2;
                dmg = 6;
                mdmg = 1;
                def = 3;
                mdef = 2;
            } else if (clas.equals("rogue")) {
                hp = 3;
                mp = 4;
                dmg = 5;
                mdmg = 2;
                def = 2;
                mdef = 2;
            } else if (clas.equals("archer")) {
                hp = 2;
                mp = 4;
                dmg = 4;
                mdmg = 3;
                def = 3;
                mdef = 2;
            } else if (clas.equals("mage")) {
                hp = 2;
                mp = 5;
                dmg = 1;
                mdmg = 5;
                def = 2;
                mdef = 2;
            } else if (clas.equals("monk")) {
                hp = 4;
                mp = 3;
                dmg = 4;
                mdmg = 2;
                def = 3;
                mdef = 2;
            } else if (clas.equals("hunter")) {
                hp = 3;
                mp = 4;
                dmg = 4;
                mdmg = 2;
                def = 3;
                mdef = 2;
            }

            classToMaxHP.put(clas, (int) (maxhp / 5 * hp));
            classToMaxMP.put(clas, (int) (maxmp / 5 * mp));
            classToMaxDMG.put(clas, (int) (maxdmg / 5 * dmg));
            classToMaxMDMG.put(clas, (int) (maxmdmg / 5 * mdmg));
            classToMaxDEF.put(clas, (int) (maxdef / 5 * def));
            classToMaxMDEF.put(clas, (int) (maxmdef / 5 * mdef));
        }

        List<Float> downRatesForLevel = new ArrayList<>();
        downRatesForLevel.add(0.75f);
        downRatesForLevel.add(0.6f);
        downRatesForLevel.add(0.45f);
        downRatesForLevel.add(0.325f);
        downRatesForLevel.add(0.2f);
        downRatesForLevel.add(0.125f);
        downRatesForLevel.add(0.05f);
        downRatesForLevel.add(0.02f);

        for (String key : classes) {
            int levelCount = 90;
            Integer hp = classToMaxHP.get(key);
            Integer def = classToMaxDEF.get(key);
            Integer mdef = classToMaxMDEF.get(key);
            if (key.equals("knight") || key.equals("paladin")) {
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot hp: " + (hp) / 14 * 2);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot def: " + (def) / 14 * 2);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot mdef: " + (mdef) / 14 * 2);

                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest hp: " + (hp) / 14 * 4);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest def: " + (def) / 14 * 4);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest mdef: " + (mdef) / 14 * 4);

                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg & shield hp: " + (hp) / 14 * 3);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg & shield def: " + (def) / 14 * 3);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg & shield mdef: " + (mdef) / 14 * 3);
            } else {
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot hp: " + (hp) / 11 * 2);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot def: " + (def) / 11 * 2);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot mdef: " + (mdef) / 11 * 2);

                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest hp: " + (hp) / 11 * 4);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest def: " + (def) / 11 * 4);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest mdef: " + (mdef) / 11 * 4);

                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg hp: " + (hp) / 11 * 3);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg def: " + (def) / 11 * 3);
                System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg mdef: " + (mdef) / 11 * 3);
            }
            for (float rate : downRatesForLevel) {
                levelCount -= 10;
                if (key.equals("knight") || key.equals("paladin")) {
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot hp: " + (hp * rate) / 14 * 2);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot def: " + (def * rate) / 14 * 2);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot mdef: " + (mdef * rate) / 14 * 2);

                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest hp: " + (hp * rate) / 14 * 4);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest def: " + (def * rate) / 14 * 4);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest mdef: " + (mdef * rate) / 14 * 4);

                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg & shield hp: " + (hp * rate) / 14 * 3);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg & shield def: " + (def * rate) / 14 * 3);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg & shield mdef: " + (mdef * rate) / 14 * 3);
                } else {
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot hp: " + (hp * rate) / 11 * 2);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot def: " + (def * rate) / 11 * 2);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " helmet & boot mdef: " + (mdef * rate) / 11 * 2);

                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest hp: " + (hp * rate) / 11 * 4);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest def: " + (def * rate) / 11 * 4);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " chest mdef: " + (mdef * rate) / 11 * 4);

                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg hp: " + (hp * rate) / 11 * 3);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg def: " + (def * rate) / 11 * 3);
                    System.out.println(key + " level " + (levelCount - 10) + "~" + levelCount + " leg mdef: " + (mdef * rate) / 11 * 3);
                }
            }
        }
    }
}
