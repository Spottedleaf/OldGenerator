package ca.spottedleaf.oldgenerator.util;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;

import java.util.EnumSet;

public final class BlockConstants {

    /*
     * In order to avoid map lookups for converting from Material->NMS IBlockData we cache the block datas
     * here.
     */

    public static final BlockData AIR = Material.AIR.createBlockData();
    public static final BlockData SOURCE_WATER = Material.WATER.createBlockData();
    public static final BlockData SOURCE_LAVA = Material.LAVA.createBlockData();
    public static final BlockData STONE = Material.STONE.createBlockData();
    public static final BlockData DIRT = Material.DIRT.createBlockData();
    public static final BlockData GRASS_BLOCK = Material.GRASS_BLOCK.createBlockData();
    public static final BlockData SNOWY_GRASS_BLOCK = Material.GRASS_BLOCK.createBlockData();
    static {
        ((Snowable)SNOWY_GRASS_BLOCK).setSnowy(true);
    }
    public static final BlockData BEDROCK = Material.BEDROCK.createBlockData();
    public static final BlockData GRAVEL = Material.GRAVEL.createBlockData();
    public static final BlockData SAND = Material.SAND.createBlockData();
    public static final BlockData SANDSTONE = Material.SANDSTONE.createBlockData();
    public static final BlockData ICE = Material.ICE.createBlockData();
    public static final BlockData FERN = Material.FERN.createBlockData();
    public static final BlockData GRASS = Material.GRASS.createBlockData();
    public static final BlockData CLAY = Material.CLAY.createBlockData();
    public static final BlockData SPAWNER = Material.SPAWNER.createBlockData();
    public static final BlockData CHEST = Material.CHEST.createBlockData();
    public static final BlockData MOSSY_COBBLESTONE = Material.MOSSY_COBBLESTONE.createBlockData();
    public static final BlockData COBBLESTONE = Material.COBBLESTONE.createBlockData();
    public static final BlockData SUGAR_CANE = Material.SUGAR_CANE.createBlockData();
    public static final BlockData OAK_LEAVES = Material.OAK_LEAVES.createBlockData();
    public static final BlockData OAK_LOG = Material.OAK_LOG.createBlockData();
    public static final BlockData BIRCH_LEAVES = Material.BIRCH_LEAVES.createBlockData();
    public static final BlockData BIRCH_LOG = Material.BIRCH_LOG.createBlockData();
    public static final BlockData SPRUCE_LEAVES = Material.SPRUCE_LEAVES.createBlockData();
    public static final BlockData SPRUCE_LOG = Material.SPRUCE_LOG.createBlockData();
    public static final BlockData COAL_ORE = Material.COAL_ORE.createBlockData();
    public static final BlockData IRON_ORE = Material.IRON_ORE.createBlockData();
    public static final BlockData GOLD_ORE = Material.GOLD_ORE.createBlockData();
    public static final BlockData REDSTONE_ORE = Material.REDSTONE_ORE.createBlockData();
    public static final BlockData DIAMOND_ORE = Material.DIAMOND_ORE.createBlockData();
    public static final BlockData LAPIS_ORE = Material.LAPIS_ORE.createBlockData();
    public static final BlockData EMERALD_ORE = Material.EMERALD_ORE.createBlockData();
    public static final BlockData DANDELION = Material.DANDELION.createBlockData();
    public static final BlockData DEAD_BUSH = Material.DEAD_BUSH.createBlockData();
    public static final BlockData POPPY = Material.POPPY.createBlockData();
    public static final BlockData BROWN_MUSHROOM = Material.BROWN_MUSHROOM.createBlockData();
    public static final BlockData RED_MUSHROOM = Material.RED_MUSHROOM.createBlockData();
    public static final BlockData SNOW = Material.SNOW.createBlockData();
    public static final BlockData CACTUS = Material.CACTUS.createBlockData();
    public static final BlockData FIRE = Material.FIRE.createBlockData();
    public static final BlockData GLOWSTONE = Material.GLOWSTONE.createBlockData();
    public static final BlockData NETHERRACK = Material.NETHERRACK.createBlockData();
    public static final BlockData SOUL_SAND = Material.SOUL_SAND.createBlockData();

    public static final EnumSet<Material> AIRS = EnumSet.noneOf(Material.class);
    static {
        AIRS.add(Material.AIR);
        AIRS.add(Material.VOID_AIR);
        AIRS.add(Material.CAVE_AIR);
    }

    public static final EnumSet<Material> LIQUIDS = EnumSet.noneOf(Material.class);
    static {
        LIQUIDS.add(Material.WATER);
        LIQUIDS.add(Material.LAVA);
    }

    public static boolean isLiquid(final Material material) {
        return LIQUIDS.contains(material);
    }

    public static boolean isAir(final Material material) {
        return AIRS.contains(material);
    }

    public static final EnumSet<Material> SAPLINGS = EnumSet.noneOf(Material.class);
    static {
        SAPLINGS.add(Material.ACACIA_SAPLING);
        SAPLINGS.add(Material.BIRCH_SAPLING);
        SAPLINGS.add(Material.DARK_OAK_SAPLING);
        SAPLINGS.add(Material.JUNGLE_SAPLING);
        SAPLINGS.add(Material.SPRUCE_SAPLING);
        SAPLINGS.add(Material.OAK_SAPLING);
    }

    public static final EnumSet<Material> LEAVES = EnumSet.noneOf(Material.class);
    static {
        LEAVES.add(Material.ACACIA_LEAVES);
        LEAVES.add(Material.BIRCH_LEAVES);
        LEAVES.add(Material.DARK_OAK_LEAVES);
        LEAVES.add(Material.JUNGLE_LEAVES);
        LEAVES.add(Material.SPRUCE_LEAVES);
        LEAVES.add(Material.OAK_LEAVES);
    }

    public static boolean isLeaves(final Material material) {
        return LEAVES.contains(material);
    }

    public static final EnumSet<Material> WOODS = EnumSet.noneOf(Material.class);
    static {
        WOODS.add(Material.ACACIA_WOOD);
        WOODS.add(Material.BIRCH_WOOD);
        WOODS.add(Material.DARK_OAK_WOOD);
        WOODS.add(Material.JUNGLE_WOOD);
        WOODS.add(Material.SPRUCE_WOOD);
        WOODS.add(Material.OAK_WOOD);
    }

    public static boolean isWood(final Material material) {
        return WOODS.contains(material);
    }

    public static final EnumSet<Material> LOGS = EnumSet.noneOf(Material.class);
    static {
        LOGS.add(Material.ACACIA_LOG);
        LOGS.add(Material.BIRCH_LOG);
        LOGS.add(Material.DARK_OAK_LOG);
        LOGS.add(Material.JUNGLE_LOG);
        LOGS.add(Material.SPRUCE_LOG);
        LOGS.add(Material.OAK_LOG);
    }

    public static boolean isLog(final Material material) {
        return LOGS.contains(material);
    }

    public static final EnumSet<Material> BUTTONS = EnumSet.noneOf(Material.class);
    static {
        BUTTONS.add(Material.STONE_BUTTON);

        BUTTONS.add(Material.ACACIA_BUTTON);
        BUTTONS.add(Material.BIRCH_BUTTON);
        BUTTONS.add(Material.DARK_OAK_BUTTON);
        BUTTONS.add(Material.JUNGLE_BUTTON);
        BUTTONS.add(Material.SPRUCE_BUTTON);
        BUTTONS.add(Material.OAK_BUTTON);
    }

    public static boolean isButton(final Material material) {
        return BUTTONS.contains(material);
    }

    public static final EnumSet<Material> WOODEN_FENCES = EnumSet.noneOf(Material.class);
    static {
        WOODEN_FENCES.add(Material.ACACIA_FENCE);
        WOODEN_FENCES.add(Material.BIRCH_FENCE);
        WOODEN_FENCES.add(Material.DARK_OAK_FENCE);
        WOODEN_FENCES.add(Material.JUNGLE_FENCE);
        WOODEN_FENCES.add(Material.SPRUCE_FENCE);
        WOODEN_FENCES.add(Material.OAK_FENCE);
    }

    public static boolean isWoodenFence(final Material material) {
        return WOODEN_FENCES.contains(material);
    }

    public static final EnumSet<Material> WOODEN_STAIRS = EnumSet.noneOf(Material.class);
    static {
        WOODEN_STAIRS.add(Material.ACACIA_STAIRS);
        WOODEN_STAIRS.add(Material.BIRCH_STAIRS);
        WOODEN_STAIRS.add(Material.DARK_OAK_STAIRS);
        WOODEN_STAIRS.add(Material.JUNGLE_STAIRS);
        WOODEN_STAIRS.add(Material.SPRUCE_STAIRS);
        WOODEN_STAIRS.add(Material.OAK_STAIRS);
    }

    public static boolean isWoodenStair(final Material material) {
        return WOODEN_STAIRS.contains(material);
    }

    public static final EnumSet<Material> FLOWERS = EnumSet.noneOf(Material.class);
    static {
        FLOWERS.add(Material.DANDELION);
        FLOWERS.add(Material.POPPY);
        FLOWERS.add(Material.BLUE_ORCHID);
        FLOWERS.add(Material.ALLIUM);
        FLOWERS.add(Material.AZURE_BLUET);
        FLOWERS.add(Material.RED_TULIP);
        FLOWERS.add(Material.ORANGE_TULIP);
        FLOWERS.add(Material.WHITE_TULIP);
        FLOWERS.add(Material.PINK_TULIP);
        FLOWERS.add(Material.OXEYE_DAISY);
        FLOWERS.add(Material.CORNFLOWER);
        FLOWERS.add(Material.LILY_OF_THE_VALLEY);
    }

    public static boolean isFlower(final Material material) {
        return FLOWERS.contains(material);
    }

    public static final EnumSet<Material> RAILS = EnumSet.noneOf(Material.class);
    static {
        RAILS.add(Material.RAIL);
        RAILS.add(Material.ACTIVATOR_RAIL);
        RAILS.add(Material.DETECTOR_RAIL);
        RAILS.add(Material.POWERED_RAIL);
    }

    public static boolean isRail(final Material material) {
        return RAILS.contains(material);
    }

    public static final EnumSet<Material> WOOLS = EnumSet.noneOf(Material.class);
    static {
        WOOLS.add(Material.BLACK_WOOL);
        WOOLS.add(Material.BLUE_WOOL);
        WOOLS.add(Material.BROWN_WOOL);
        WOOLS.add(Material.CYAN_WOOL);
        WOOLS.add(Material.GRAY_WOOL);
        WOOLS.add(Material.GREEN_WOOL);
        WOOLS.add(Material.LIGHT_BLUE_WOOL);
        WOOLS.add(Material.LIGHT_GRAY_WOOL);
        WOOLS.add(Material.LIME_WOOL);
        WOOLS.add(Material.MAGENTA_WOOL);
        WOOLS.add(Material.ORANGE_WOOL);
        WOOLS.add(Material.PINK_WOOL);
        WOOLS.add(Material.PURPLE_WOOL);
        WOOLS.add(Material.RED_WOOL);
        WOOLS.add(Material.WHITE_WOOL);
        WOOLS.add(Material.YELLOW_WOOL);
    }

    public static boolean isWool(final Material material) {
        return WOOLS.contains(material);
    }

    public static final EnumSet<Material> DOORS = EnumSet.noneOf(Material.class);
    static {
        DOORS.add(Material.ACACIA_DOOR);
        DOORS.add(Material.BIRCH_DOOR);
        DOORS.add(Material.DARK_OAK_DOOR);
        DOORS.add(Material.JUNGLE_DOOR);
        DOORS.add(Material.SPRUCE_DOOR);
        DOORS.add(Material.OAK_DOOR);

        DOORS.add(Material.IRON_DOOR);
    }

    public static boolean isDoor(final Material material) {
        return DOORS.contains(material);
    }

    public static final EnumSet<Material> PRESSURE_PLATES = EnumSet.noneOf(Material.class);
    static {
        PRESSURE_PLATES.add(Material.STONE_PRESSURE_PLATE);

        PRESSURE_PLATES.add(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);

        PRESSURE_PLATES.add(Material.ACACIA_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Material.BIRCH_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Material.DARK_OAK_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Material.JUNGLE_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Material.SPRUCE_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Material.OAK_PRESSURE_PLATE);
    }

    public static boolean isPressurePlate(final Material material) {
        return PRESSURE_PLATES.contains(material);
    }

    public static final EnumSet<Material> TRAP_DOORS = EnumSet.noneOf(Material.class);
    static {
        TRAP_DOORS.add(Material.IRON_TRAPDOOR);

        TRAP_DOORS.add(Material.ACACIA_TRAPDOOR);
        TRAP_DOORS.add(Material.BIRCH_TRAPDOOR);
        TRAP_DOORS.add(Material.DARK_OAK_TRAPDOOR);
        TRAP_DOORS.add(Material.JUNGLE_TRAPDOOR);
        TRAP_DOORS.add(Material.SPRUCE_TRAPDOOR);
        TRAP_DOORS.add(Material.OAK_TRAPDOOR);
    }

    public static boolean isTrapDoor(final Material material) {
        return TRAP_DOORS.contains(material);
    }

    public static final EnumSet<Material> GENERIC_TORCHS = EnumSet.noneOf(Material.class);
    static {
        GENERIC_TORCHS.add(Material.TORCH);
        GENERIC_TORCHS.add(Material.WALL_TORCH);
        GENERIC_TORCHS.add(Material.REDSTONE_TORCH);
        GENERIC_TORCHS.add(Material.REDSTONE_WALL_TORCH);
    }

    public static boolean isGenericTorch(final Material material) {
        return GENERIC_TORCHS.contains(material);
    }

    public static final EnumSet<Material> BEDS = EnumSet.noneOf(Material.class);
    static {
        BEDS.add(Material.RED_BED);
        BEDS.add(Material.BLACK_BED);
        BEDS.add(Material.BLUE_BED);
        BEDS.add(Material.BROWN_BED);
        BEDS.add(Material.CYAN_BED);
        BEDS.add(Material.GRAY_BED);
        BEDS.add(Material.GREEN_BED);
        BEDS.add(Material.LIGHT_BLUE_BED);
        BEDS.add(Material.LIGHT_GRAY_BED);
        BEDS.add(Material.LIME_BED);
        BEDS.add(Material.MAGENTA_BED);
        BEDS.add(Material.ORANGE_BED);
        BEDS.add(Material.PINK_BED);
        BEDS.add(Material.PURPLE_BED);
        BEDS.add(Material.WHITE_BED);
        BEDS.add(Material.YELLOW_BED);
    }

    public static boolean isBed(final Material material) {
        return BEDS.contains(material);
    }

    public static final EnumSet<Material> SIGNS = EnumSet.noneOf(Material.class);
    static {
        SIGNS.add(Material.ACACIA_SIGN);
        SIGNS.add(Material.BIRCH_SIGN);
        SIGNS.add(Material.DARK_OAK_SIGN);
        SIGNS.add(Material.JUNGLE_SIGN);
        SIGNS.add(Material.SPRUCE_SIGN);
        SIGNS.add(Material.OAK_SIGN);

        SIGNS.add(Material.ACACIA_WALL_SIGN);
        SIGNS.add(Material.BIRCH_WALL_SIGN);
        SIGNS.add(Material.DARK_OAK_WALL_SIGN);
        SIGNS.add(Material.JUNGLE_WALL_SIGN);
        SIGNS.add(Material.SPRUCE_WALL_SIGN);
        SIGNS.add(Material.OAK_WALL_SIGN);
    }

    public static boolean isSign(final Material material) {
        return SIGNS.contains(material);
    }
}
