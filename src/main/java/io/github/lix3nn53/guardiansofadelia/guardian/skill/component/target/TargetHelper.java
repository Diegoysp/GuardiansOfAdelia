package io.github.lix3nn53.guardiansofadelia.guardian.skill.component.target;

import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TargetHelper {

    public static LivingEntity getLookingTarget(LivingEntity source, float range, float tolerance) {
        List<Entity> list = source.getNearbyEntities(range, range, range);
        if (list.isEmpty()) return null;

        Vector facing = source.getLocation().getDirection();
        float fLengthSq = (float) facing.lengthSquared();

        LivingEntity target = null;
        float minDistance = 99999;
        for (Entity entity : list) {

            if (!isInFront(source, entity) || !(entity instanceof LivingEntity))
                continue;
            Vector relative = entity.getLocation().subtract(source.getLocation()).toVector();
            float dot = (float) relative.dot(facing);
            float rLengthSq = (float) relative.lengthSquared();
            float cosSquared = dot * dot / rLengthSq * fLengthSq;
            float sinSquared = 1.0f - cosSquared;
            float dSquared = rLengthSq * sinSquared;


            if (dSquared < tolerance) {
                // valid target
                float distance = (float) entity.getLocation().distanceSquared(source.getLocation());
                if (distance < minDistance) {
                    minDistance = distance;
                    target = (LivingEntity) entity;
                }
            }

        }
        return target;
    }

    public static boolean isInFront(Entity source, Entity recipient) {
        // Get the necessary vectors
        Vector facing = source.getLocation().getDirection();
        Vector relative = recipient.getLocation().subtract(source.getLocation()).toVector();

        // If the dot product is positive, the recipient is in front
        return facing.dot(relative) >= 0;
    }

    public static Location getOpenLocation(Location loc1, Location loc2, boolean throughWall) {
        if (loc1.getX() == loc2.getX() && loc1.getY() == loc2.getY() && loc1.getZ() == loc2.getZ()) {
            return loc1;
        }


        Vector slope = loc2.clone().subtract(loc1).toVector();
        int steps = (int) (slope.length() * 4.0D) + 1;
        slope.multiply(1.0D / steps);


        if (throughWall) {

            Location temp = loc2.clone();
            while (temp.getBlock().getType().isSolid() && steps > 0) {

                temp.subtract(slope);
                steps--;
            }
            temp.setX(temp.getBlockX() + 0.5D);
            temp.setZ(temp.getBlockZ() + 0.5D);
            temp.setY((temp.getBlockY() + 1));
            return temp;
        }


        Location temp = loc1.clone();
        while (!temp.getBlock().getType().isSolid() && steps > 0) {

            temp.add(slope);
            steps--;
        }
        temp.subtract(slope);
        temp.setX(temp.getBlockX() + 0.5D);
        temp.setZ(temp.getBlockZ() + 0.5D);
        temp.setY((temp.getBlockY() + 1));
        return temp;
    }

    /**
     * Gets entities nearby a location using a given radius
     *
     * @param loc    location centered around
     * @param radius radius to get within
     * @return nearby entities
     */
    public static List<LivingEntity> getNearbySphere(Location loc, float radius) {
        List<LivingEntity> result = new ArrayList<>();

        int minX = (int) (loc.getX() - radius) >> 4;
        int maxX = (int) (loc.getX() + radius) >> 4;
        int minZ = (int) (loc.getZ() - radius) >> 4;
        int maxZ = (int) (loc.getZ() + radius) >> 4;

        float radiusSquare = radius * radius;

        for (int i = minX; i <= maxX; i++)
            for (int j = minZ; j <= maxZ; j++)
                for (Entity entity : loc.getWorld().getChunkAt(i, j).getEntities())
                    if (entity instanceof LivingEntity && entity.getLocation().distanceSquared(loc) < radiusSquare)
                        result.add((LivingEntity) entity);

        return result;
    }

    /**
     * Gets entities nearby a location using a given radius
     *
     * @param loc    location centered around
     * @param radius radius to get within
     * @return nearby entities
     */
    public static List<Entity> getNearbySphereNonLiving(Location loc, float radius) {
        List<Entity> result = new ArrayList<>();

        int minX = (int) (loc.getX() - radius) >> 4;
        int maxX = (int) (loc.getX() + radius) >> 4;
        int minZ = (int) (loc.getZ() - radius) >> 4;
        int maxZ = (int) (loc.getZ() + radius) >> 4;

        float radiusSquare = radius * radius;

        for (int i = minX; i <= maxX; i++)
            for (int j = minZ; j <= maxZ; j++)
                for (Entity entity : loc.getWorld().getChunkAt(i, j).getEntities())
                    if (entity.getLocation().distanceSquared(loc) < radiusSquare)
                        result.add(entity);

        return result;
    }

    public static List<LivingEntity> getBoxTargets(World world, Vector b1, Vector b2, Vector b4, Vector t1, Vector t3) {
        Vector yLocal = t1.clone().subtract(b1);
        float yLength = (float) yLocal.length(); // size1 = np.linalg.norm(dir1)
        yLocal = yLocal.normalize(); // dir1 = dir1.divide(new Vector(size1, size1, size1));

        Vector xLocal = b2.clone().subtract(b1);
        float xLength = (float) xLocal.length();
        xLocal = xLocal.normalize();

        Vector zLocal = b4.clone().subtract(b1);
        float zLength = (float) zLocal.length();
        zLocal = zLocal.normalize();

        // cube3d_center = (b1 + t3)/2.0
        Vector center = b1.clone().add(t3).divide(new Vector(2, 2, 2));

        ArrayList<LivingEntity> result = new ArrayList<>();
        // dir_vec = points - cube3d_center
        // res1 = np.where( (np.absolute(np.dot(dir_vec, dir1)) * 2) > size1 )[0]
        // res2 = np.where( (np.absolute(np.dot(dir_vec, dir2)) * 2) > size2 )[0]
        // res3 = np.where( (np.absolute(np.dot(dir_vec, dir3)) * 2) > size3 )[0]

        // return list( set().union(res1, res2, res3) )
        Collection<Entity> nearbyEntities = world.getNearbyEntities(center.toLocation(world), xLength, yLength, zLength);

        for (Entity entity : nearbyEntities) {
            if (!(entity instanceof LivingEntity)) continue;
            BoundingBox boundingBox = entity.getBoundingBox();

            ArrayList<Vector> points = new ArrayList<>(); // points of bounding box of target entity

            points.add(boundingBox.getCenter());

            Vector max = boundingBox.getMax();
            points.add(max);

            Vector min = boundingBox.getMin();
            points.add(min);

            float height = (float) boundingBox.getHeight();
            points.add(min.clone().add(new Vector(0, height, 0)));
            points.add(max.clone().subtract(new Vector(0, height, 0)));

            float widthX = (float) boundingBox.getWidthX();
            float widthZ = (float) boundingBox.getWidthZ();
            points.add(min.clone().add(new Vector(widthX, 0, 0)));
            points.add(min.clone().add(new Vector(0, 0, widthZ)));

            points.add(max.clone().add(new Vector(widthX, 0, 0)));
            points.add(max.clone().add(new Vector(0, 0, widthZ)));

            for (Vector point : points) {
                boolean pointInsideBox = isPointInsideBox(point, center, xLocal, yLocal, zLocal, xLength, yLength, zLength);
                if (pointInsideBox) {
                    result.add((LivingEntity) entity);
                    break;
                }
            }
        }

        return result;
    }

    public static List<LivingEntity> getBoxTargets(World world, Vector[] cube, float length_x, float length_y, float length_z) {
        List<LivingEntity> result = new ArrayList<>();

        float highest_x = getHighest(cube, Axis.X);
        float highest_y = getHighest(cube, Axis.Y);
        float highest_z = getHighest(cube, Axis.Z);
        float lowest_x = getLowest(cube, Axis.X);
        float lowest_y = getLowest(cube, Axis.Y);
        float lowest_z = getLowest(cube, Axis.Z);

        Vector center = new Vector(lowest_x, lowest_y, lowest_z).add(new Vector(highest_x, highest_y, highest_z)).
                divide(new Vector(2, 2, 2));

        Collection<Entity> nearbyEntities = null;

        if (length_x > length_y && length_x > length_z) {
            nearbyEntities = world.getNearbyEntities(new Location(world, center.getX(), center.getY(), center.getZ()),
                    length_x * 2, length_x * 2, length_x * 2);
        } else if (length_y > length_x && length_y > length_z) {
            nearbyEntities = world.getNearbyEntities(new Location(world, center.getX(), center.getY(), center.getZ()),
                    length_y * 2, length_y * 2, length_y * 2);
        } else if (length_z > length_x && length_z > length_y) {
            nearbyEntities = world.getNearbyEntities(new Location(world, center.getX(), center.getY(), center.getZ()),
                    length_z * 2, length_z * 2, length_z * 2);
        }

        if (nearbyEntities != null) {
            for (Entity entity : nearbyEntities) {
                if (!(entity instanceof LivingEntity)) continue;
                BoundingBox boundingBox = entity.getBoundingBox();

                float maxX = (float) boundingBox.getMaxX();
                float maxY = (float) boundingBox.getMaxY();
                float maxZ = (float) boundingBox.getMaxZ();
                float minX = (float) boundingBox.getMinX();
                float minY = (float) boundingBox.getMinY();
                float minZ = (float) boundingBox.getMinZ();

                if (!overlaps(lowest_x, highest_x, minX, maxX)) {
                    continue; // NO INTERSECTION
                } else if (!overlaps(lowest_y, highest_y, minY, maxY)) {
                    continue; // NO INTERSECTION
                } else if (!overlaps(lowest_z, highest_z, minZ, maxZ)) {
                    continue; // NO INTERSECTION
                }

                result.add((LivingEntity) entity);
            }
        }

        return result;
    }

    public static float getHighest(Vector[] vectors, Axis axis) {
        float result = 0;

        if (axis.equals(Axis.X)) {
            result = (float) vectors[0].getX();
        } else if (axis.equals(Axis.Y)) {
            result = (float) vectors[0].getY();
        } else if (axis.equals(Axis.Z)) {
            result = (float) vectors[0].getZ();
        }

        for (Vector vector : vectors) {
            if (axis.equals(Axis.X)) {
                if (vector.getX() > result) result = (float) vector.getX();
            } else if (axis.equals(Axis.Y)) {
                if (vector.getY() > result) result = (float) vector.getY();
            } else if (axis.equals(Axis.Z)) {
                if (vector.getZ() > result) result = (float) vector.getZ();
            }
        }

        return result;
    }

    public static float getLowest(Vector[] vectors, Axis axis) {
        float result = 0;

        if (axis.equals(Axis.X)) {
            result = (float) vectors[0].getX();
        } else if (axis.equals(Axis.Y)) {
            result = (float) vectors[0].getY();
        } else if (axis.equals(Axis.Z)) {
            result = (float) vectors[0].getZ();
        }

        for (Vector vector : vectors) {
            if (axis.equals(Axis.X)) {
                if (vector.getX() < result) result = (float) vector.getX();
            } else if (axis.equals(Axis.Y)) {
                if (vector.getY() < result) result = (float) vector.getY();
            } else if (axis.equals(Axis.Z)) {
                if (vector.getZ() < result) result = (float) vector.getZ();
            }
        }

        return result;
    }

    public static boolean overlaps(float min1, float max1, float min2, float max2) {
        return isBetweenOrdered(min2, min1, max1) || isBetweenOrdered(min1, min2, max2);
    }

    public static boolean isBetweenOrdered(float val, float lowerBound, float upperBound) {
        return lowerBound <= val && val <= upperBound;
    }

    public static boolean isPointInsideBox(Vector point, Vector center, Vector xLocal, Vector yLocal, Vector zLocal, float xLength, float yLength, float zLength) {
        Vector v = point.clone().subtract(center); // direction vector from cube center to the target point

        float py = (float) (Math.abs(v.dot(yLocal)) * 2);
        float px = (float) (Math.abs(v.dot(xLocal)) * 2);
        float pz = (float) (Math.abs(v.dot(zLocal)) * 2);

        return px <= xLength && py <= yLength && pz <= zLength;
    }

    public static List<LivingEntity> getNearbyBox(Location loc, float radius) {
        List<LivingEntity> result = new ArrayList<>();

        int minX = (int) (loc.getX() - radius) >> 4;
        int maxX = (int) (loc.getX() + radius) >> 4;
        int minZ = (int) (loc.getZ() - radius) >> 4;
        int maxZ = (int) (loc.getZ() + radius) >> 4;

        for (int i = minX; i <= maxX; i++)
            for (int j = minZ; j <= maxZ; j++)
                for (Entity entity : loc.getWorld().getChunkAt(i, j).getEntities())
                    if (entity instanceof LivingEntity && boxDistance(entity.getLocation(), loc) < radius)
                        result.add((LivingEntity) entity);

        return result;
    }

    public static float boxDistance(Location loc1, Location loc2) {
        return (float) Math.max(Math.max(Math.abs(loc1.getX() - loc2.getX()), Math.abs(loc1.getY() - loc2.getY())), Math.abs(loc1.getZ() - loc2.getZ()));
    }

    public static List<LivingEntity> getConeTargets(LivingEntity source, float arc, float range) {
        List<LivingEntity> targets = new ArrayList<>();
        List<Entity> list = source.getNearbyEntities(range, range, range);
        if (arc <= 0.0D) return targets;


        Vector dir = source.getEyeLocation().getDirection();
        dir.setY(0);
        float cos = (float) Math.cos(arc * Math.PI / 180.0f);
        float cosSq = cos * cos;


        for (Entity entity : list) {

            if (entity instanceof LivingEntity) {


                if (arc >= 360.0D) {

                    targets.add((LivingEntity) entity);


                    continue;
                }

                Vector relative = entity.getLocation().subtract(source.getEyeLocation()).toVector();
                relative.setY(0);
                float dot = (float) relative.dot(dir);
                float value = dot * dot / (float) (relative.lengthSquared());
                if (arc < 180.0D && dot > 0.0D && value >= cosSq) {
                    targets.add((LivingEntity) entity);
                    continue;
                }
                if (arc >= 180.0D && (dot > 0.0D || dot <= cosSq)) targets.add((LivingEntity) entity);

            }
        }

        return targets;
    }
}
