package ca.spottedleaf.oldgenerator.generator.b173;

import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;
import java.util.EnumMap;
import java.util.EnumSet;

public final class LegacyUtil173 {

    public static final int WORLD_HEIGHT = 128;

    // Source code for b173 server can be found here: https://github.com/Bukkit/mc-dev/tree/1a792ed860ebe2c6d4c40c52f3bc7b9e0789ca23

    // NOTE: The following methods are supposed to mirror beta 1.7.3 behaviour! They are not guaranteed to mirror
    // NEW behaviour.

    private static final EnumSet<Material> REPLACABLE_MATERIALS = EnumSet.noneOf(Material.class);

    static {
        REPLACABLE_MATERIALS.addAll(BlockConstants.AIRS);
        REPLACABLE_MATERIALS.add(Material.WATER);
        REPLACABLE_MATERIALS.add(Material.LAVA);
        REPLACABLE_MATERIALS.add(Material.SNOW); // TODO SNOW_BLOCK?
        REPLACABLE_MATERIALS.add(Material.FIRE);
    }

    public static boolean Material_isReplacable(final Material material) {
        return REPLACABLE_MATERIALS.contains(material);
    }

    private static final EnumSet<Material> NON_BUILDABLE_MATERIALS = EnumSet.noneOf(Material.class);

    static {
        NON_BUILDABLE_MATERIALS.addAll(BlockConstants.AIRS);
        NON_BUILDABLE_MATERIALS.add(Material.FIRE);
        NON_BUILDABLE_MATERIALS.add(Material.WATER);
        NON_BUILDABLE_MATERIALS.add(Material.LAVA);
        NON_BUILDABLE_MATERIALS.add(Material.NETHER_PORTAL);
        NON_BUILDABLE_MATERIALS.add(Material.SNOW); // TODO SNOW_BLOCK?
        NON_BUILDABLE_MATERIALS.addAll(BlockConstants.BUTTONS);
        NON_BUILDABLE_MATERIALS.add(Material.REPEATER);
        NON_BUILDABLE_MATERIALS.add(Material.LADDER);
        NON_BUILDABLE_MATERIALS.add(Material.LEVER);
        NON_BUILDABLE_MATERIALS.add(Material.RAIL);
        NON_BUILDABLE_MATERIALS.addAll(BlockConstants.RAILS);
        NON_BUILDABLE_MATERIALS.add(Material.REDSTONE_WIRE);
        NON_BUILDABLE_MATERIALS.addAll(BlockConstants.GENERIC_TORCHS);
        NON_BUILDABLE_MATERIALS.add(Material.SUGAR_CANE);
        NON_BUILDABLE_MATERIALS.addAll(BlockConstants.FLOWERS);
        NON_BUILDABLE_MATERIALS.add(Material.RED_MUSHROOM);
        NON_BUILDABLE_MATERIALS.add(Material.BROWN_MUSHROOM);
        NON_BUILDABLE_MATERIALS.add(Material.DEAD_BUSH);
        NON_BUILDABLE_MATERIALS.add(Material.GRASS);
        NON_BUILDABLE_MATERIALS.add(Material.FERN);
        NON_BUILDABLE_MATERIALS.addAll(BlockConstants.SAPLINGS);
        NON_BUILDABLE_MATERIALS.add(Material.WHEAT);
    }

    public static boolean Material_isBuildable(final Material material) {
        return !NON_BUILDABLE_MATERIALS.contains(material);
    }

    private static final EnumSet<Material> Material_F = EnumSet.noneOf(Material.class);

    static {
        Material_F.add(Material.CACTUS);
        Material_F.add(Material.SNOW); // TODO SNOW_BLOCK?
        Material_F.add(Material.ICE);
        Material_F.add(Material.TNT);
        Material_F.add(Material.GLASS);
        Material_F.addAll(BlockConstants.LEAVES);
    }

    private static final EnumSet<Material> NOT_Block_b = EnumSet.noneOf(Material.class);

    static {
        NOT_Block_b.addAll(BlockConstants.BEDS);
        NOT_Block_b.addAll(BlockConstants.BUTTONS);
        NOT_Block_b.add(Material.CACTUS);
        NOT_Block_b.add(Material.CAKE);
        NOT_Block_b.add(Material.REPEATER);
        NOT_Block_b.addAll(BlockConstants.DOORS);
        NOT_Block_b.addAll(BlockConstants.WOODEN_FENCES);
        NOT_Block_b.add(Material.FIRE);
        NOT_Block_b.addAll(BlockConstants.FLOWERS);
        NOT_Block_b.add(Material.WHEAT);
        NOT_Block_b.add(Material.DEAD_BUSH);
        NOT_Block_b.add(Material.GRASS);
        NOT_Block_b.add(Material.FERN);
        NOT_Block_b.add(Material.RED_MUSHROOM);
        NOT_Block_b.add(Material.BROWN_MUSHROOM);
        NOT_Block_b.addAll(BlockConstants.SAPLINGS);
        NOT_Block_b.add(Material.WATER);
        NOT_Block_b.add(Material.LAVA);
        NOT_Block_b.add(Material.LADDER);
        NOT_Block_b.add(Material.LEVER);
        NOT_Block_b.addAll(BlockConstants.RAILS);
        NOT_Block_b.add(Material.PISTON);
        NOT_Block_b.add(Material.STICKY_PISTON);
        NOT_Block_b.add(Material.PISTON_HEAD);
        NOT_Block_b.add(Material.MOVING_PISTON);
        NOT_Block_b.add(Material.NETHER_PORTAL);
        NOT_Block_b.addAll(BlockConstants.PRESSURE_PLATES);
        NOT_Block_b.add(Material.REDSTONE_WIRE);
        NOT_Block_b.add(Material.SUGAR_CANE);
        NOT_Block_b.addAll(BlockConstants.SIGNS);
        NOT_Block_b.add(Material.SNOW); // TODO SNOW_BLOCK?
        NOT_Block_b.add(Material.FARMLAND);
        NOT_Block_b.addAll(BlockConstants.WOODEN_STAIRS);
        // TODO slabs
        NOT_Block_b.addAll(BlockConstants.GENERIC_TORCHS);
        NOT_Block_b.addAll(BlockConstants.TRAP_DOORS);
        NOT_Block_b.add(Material.COBWEB);
    }

    public static boolean Block_b(final Material material) {
        return !NOT_Block_b.contains(material);
    }

    public static boolean World_e(final BlockAccess world, final int x, final int y, final int z) {
        final Material material = world.getType(x, y, z);
        if (BlockConstants.isAir(material)) {
            return false;
        }
        return (Material_F.contains(material) ? false : Material_isSolid(material)) && Block_b(material);
    }


    private static final EnumMap<Material, Integer> BlockFire_a = new EnumMap<>(Material.class);
    private static final EnumMap<Material, Integer> BlockFire_b = new EnumMap<>(Material.class);

    static {
        /*
        this.a(Block.WOOD.id, 5, 20);
        this.a(Block.FENCE.id, 5, 20);
        this.a(Block.WOOD_STAIRS.id, 5, 20);
        this.a(Block.LOG.id, 5, 5);
        this.a(Block.LEAVES.id, 30, 60);
        this.a(Block.BOOKSHELF.id, 30, 20);
        this.a(Block.TNT.id, 15, 100);
        this.a(Block.LONG_GRASS.id, 60, 100);
        this.a(Block.WOOL.id, 30, 60);
         */
        for (final Material wood : BlockConstants.WOODS) {
            BlockFire_a.put(wood, 5);
            BlockFire_b.put(wood, 20);
        }

        for (final Material fence : BlockConstants.WOODEN_FENCES) {
            BlockFire_a.put(fence, 5);
            BlockFire_b.put(fence, 20);
        }

        for (final Material stair : BlockConstants.WOODEN_STAIRS) {
            BlockFire_a.put(stair, 5);
            BlockFire_b.put(stair, 20);
        }

        for (final Material log : BlockConstants.LOGS) {
            BlockFire_a.put(log, 5);
            BlockFire_b.put(log, 5);
        }

        for (final Material leaves : BlockConstants.LEAVES) {
            BlockFire_a.put(leaves, 30);
            BlockFire_b.put(leaves, 60);
        }

        BlockFire_a.put(Material.BOOKSHELF, 30);
        BlockFire_b.put(Material.BOOKSHELF, 20);

        BlockFire_a.put(Material.TNT, 15);
        BlockFire_b.put(Material.TNT, 100);

        BlockFire_a.put(Material.GRASS, 60);
        BlockFire_b.put(Material.GRASS, 100);
        BlockFire_a.put(Material.FERN, 60);
        BlockFire_b.put(Material.FERN, 100);

        for (final Material wool : BlockConstants.WOOLS) {
            BlockFire_a.put(wool, 30);
            BlockFire_b.put(wool, 60);
        }
    }


    public static boolean BlockFire_b(final BlockAccess world, final int x, final int y, final int z) {
        return BlockFire_a.getOrDefault(world.getType(x, y, z), Integer.valueOf(-1)).intValue() > 0;
    }

    private static final EnumSet<Material> NOT_Block_a = EnumSet.noneOf(Material.class);

    static {
        NOT_Block_a.addAll(BlockConstants.BEDS);
        NOT_Block_a.addAll(BlockConstants.BUTTONS);
        NOT_Block_a.add(Material.CACTUS);
        NOT_Block_a.add(Material.CAKE);
        NOT_Block_a.add(Material.REPEATER);
        NOT_Block_a.addAll(BlockConstants.DOORS);
        NOT_Block_a.addAll(BlockConstants.WOODEN_FENCES);
        NOT_Block_a.add(Material.FIRE);
        NOT_Block_a.addAll(BlockConstants.FLOWERS);
        NOT_Block_a.add(Material.WHEAT);
        NOT_Block_a.add(Material.DEAD_BUSH);
        NOT_Block_a.add(Material.GRASS);
        NOT_Block_a.add(Material.FERN);
        NOT_Block_a.add(Material.RED_MUSHROOM);
        NOT_Block_a.add(Material.BROWN_MUSHROOM);
        NOT_Block_a.addAll(BlockConstants.SAPLINGS);
        NOT_Block_a.add(Material.WATER);
        NOT_Block_a.add(Material.LAVA);
        NOT_Block_a.add(Material.LADDER);
        NOT_Block_a.add(Material.LEVER);
        NOT_Block_a.addAll(BlockConstants.RAILS);
        NOT_Block_a.add(Material.PISTON);
        NOT_Block_a.add(Material.STICKY_PISTON);
        NOT_Block_a.add(Material.PISTON_HEAD);
        NOT_Block_a.add(Material.MOVING_PISTON);
        NOT_Block_a.add(Material.NETHER_PORTAL);
        NOT_Block_a.addAll(BlockConstants.PRESSURE_PLATES);
        NOT_Block_a.add(Material.REDSTONE_WIRE);
        NOT_Block_a.add(Material.SUGAR_CANE);
        NOT_Block_a.addAll(BlockConstants.SIGNS);
        NOT_Block_a.add(Material.SNOW); // TODO SNOW_BLOCK?
        NOT_Block_a.add(Material.FARMLAND);
        NOT_Block_a.addAll(BlockConstants.WOODEN_STAIRS);
        // TODO slabs
        NOT_Block_a.addAll(BlockConstants.GENERIC_TORCHS);
        NOT_Block_a.addAll(BlockConstants.TRAP_DOORS);
        NOT_Block_a.add(Material.COBWEB);

        // new additions
        NOT_Block_a.add(Material.GLASS);
        NOT_Block_a.add(Material.ICE);
        NOT_Block_a.add(Material.NETHER_PORTAL);
        //NOT_Block_a.addAll(BlockConstants.LEAVES); // overrides but always is true
        NOT_Block_a.add(Material.SPAWNER);
    }

    public static boolean Block_o(final Material material) {
        // As there is no block with air, mojang opted to use a boolean array to avoid NPE... And to decide that air should return false
        return !BlockConstants.isAir(material) && Block_a(material);
    }

    public static boolean Block_a(final Material material) {
        if (BlockConstants.isAir(material)) {
            throw new NullPointerException();
        }
        return !NOT_Block_a.contains(material);
    }

    public static boolean Material_isSolid(final Material material) {
        return Material_isBuildable(material);
    }

    public static boolean BlockChest_g(final BlockAccess world, final int x, final int y, final int z) {
        return world.getType(x, y, z) != Material.CHEST ? false : (world.getType(x - 1, y, z) == Material.CHEST ? true : (world.getType(x + 1, y, z) == Material.CHEST ? true : (world.getType(x, y, z - 1) == Material.CHEST ? true : world.getType(x, y, z + 1) == Material.CHEST)));
    }

    public static boolean BlockTorch_g(final BlockAccess world, final int x, final int y, final int z) {
        return World_e(world, x, y, z) || BlockConstants.isWoodenFence(world.getType(x, y, z));
    }

    public static boolean BlockFlower_c(final Material type, final Material param) {
        if (type == Material.WHEAT) {
            return param == Material.FARMLAND;
        }
        if (type == Material.DEAD_BUSH) {
            return param == Material.SAND;
        }
        if (type == Material.RED_MUSHROOM || type == Material.BROWN_MUSHROOM) {
            return Block_o(param);
        }

        return param == Material.GRASS_BLOCK || param == Material.DIRT || param == Material.FARMLAND;
    }

    public static boolean BlockFlower_f(final BlockAccess world, final int x, final int y, final int z, final Material type) {
        if (type == Material.RED_MUSHROOM || type == Material.BROWN_MUSHROOM) {
            return y >= 0 && y < WORLD_HEIGHT ? world.getLightLevel(x, y, z) < 13 && BlockFlower_c(type, world.getType(x, y - 1, z)) : false;
        }

        // default
        // we can always assume the chunk is loaded when calling
        final Material material = world.getType(x, y - 1, z);
        return BlockFlower_c(type, material);
    }

    public static boolean BlockCactus_f(final BlockAccess world, final int x, final int y, final int z) {
        if (Material_isBuildable(world.getType(x - 1, y, z))) {
            return false;
        } else if (Material_isBuildable(world.getType(x + 1, y, z))) {
            return false;
        } else if (Material_isBuildable(world.getType(x, y, z - 1))) {
            return false;
        } else if (Material_isBuildable(world.getType(x, y, z + 1))) {
            return false;
        } else {
            Material l = world.getType(x, y - 1, z);

            return l == Material.CACTUS || l == Material.SAND;
        }
    }

    public static boolean Block_canPlace(final BlockAccess world, final int x, final int y, final int z, final Material material) {
        if (BlockConstants.isButton(material)) {
            return World_e(world, x - 1, y, z) ? true : (World_e(world, x + 1, y, z) ? true : (World_e(world, x, y, z - 1) ? true : World_e(world, x, y, z + 1)));
        }
        if (material == Material.CACTUS) {
            return !Material_isReplacable(world.getType(x, y, z)) ? false : BlockCactus_f(world, x, y, z);
        }
        if (material == Material.CAKE) {
            return !Material_isReplacable(world.getType(x, y, z)) ? false : Material_isBuildable(world.getType(x, y - 1, z));
        }
        if (material == Material.CHEST) {
            int l = 0;

            if (world.getType(x - 1, y, z) == Material.CHEST) {
                ++l;
            }

            if (world.getType(x + 1, y, z) == Material.CHEST) {
                ++l;
            }

            if (world.getType(x, y, z - 1) == Material.CHEST) {
                ++l;
            }

            if (world.getType(x, y, z + 1) == Material.CHEST) {
                ++l;
            }

            return l > 1 ? false : (BlockChest_g(world, x - 1, y, z) ? false : (BlockChest_g(world, x + 1, y, z) ? false : (BlockChest_g(world, x, y, z - 1) ? false : !BlockChest_g(world, x, y, z + 1))));
        }
        if (material == Material.REPEATER) {
            return !World_e(world, x, y - 1, z) ? false : Material_isReplacable(world.getType(x, y, z));
        }
        if (BlockConstants.isDoor(material)) {
            return y >= (WORLD_HEIGHT - 1) ? false : World_e(world, x, y - 1, z) && Material_isReplacable(world.getType(x, y, z)) && Material_isReplacable(world.getType(x, y + 1, z));
        }
        if (material == Material.FIRE) {
            if (World_e(world, x, y - 1, z)) {
                return true;
            }

            return BlockFire_b(world, x + 1, y, z) ? true : (BlockFire_b(world, x - 1, y, z) ? true : (BlockFire_b(world, x, y - 1, z) ? true : (BlockFire_b(world, x, y + 1, z) ? true : (BlockFire_b(world, x, y, z - 1) ? true : BlockFire_b(world, x, y, z + 1)))));
        }
        if (BlockConstants.isFlower(material)) {
            if (!Material_isReplacable(world.getType(x, y, z))) {
                return false;
            }
            final Material below = world.getType(x, y - 1, z);
            return below == Material.GRASS_BLOCK || below == Material.DIRT || below == Material.FARMLAND;
        }
        if (material == Material.LADDER) {
            return World_e(world, x - 1, y, z) ? true : (World_e(world, x + 1, y, z) ? true : (World_e(world, x, y, z - 1) ? true : World_e(world, x, y, z + 1)));
        }
        if (material == Material.LEVER) {
            return World_e(world, x - 1, y, z) ? true : (World_e(world, x + 1, y, z) ? true : (World_e(world, x, y, z - 1) ? true : (World_e(world, x, y, z + 1) ? true : World_e(world, x, y - 1, z))));
        }
        // locked_chest no longer exists.
        if (BlockConstants.isRail(material)) {
            return World_e(world, x, y - 1, z);
        }
        if (material == Material.PISTON_HEAD || material == Material.MOVING_PISTON) {
            return false;
        }
        if (BlockConstants.isPressurePlate(material)) {
            return World_e(world, x, y - 1, z);
        }
        if (material == Material.CARVED_PUMPKIN) {
            return Material_isReplacable(world.getType(x, y, z)) && World_e(world, x, y - 1, z);
        }
        if (material == Material.REDSTONE_WIRE) {
            return World_e(world, x, y - 1, z);
        }
        if (material == Material.SUGAR_CANE) {
            final Material below = world.getType(x, y - 1, z);

            return below == Material.SUGAR_CANE ? true : (below != Material.GRASS_BLOCK && below != Material.DIRT ? false : (world.getType(x - 1, y - 1, z) == Material.WATER ? true : (world.getType(x + 1, y - 1, z) == Material.WATER ? true : (world.getType(x, y - 1, z - 1) == Material.WATER ? true : world.getType(x, y - 1, z + 1) == Material.WATER))));
        }
        if (material == Material.SNOW) { // TODO SNOW_BLOCK?
            final Material below = world.getType(x, y - 1, z);
            return !BlockConstants.isAir(below) && Block_a(below) ? Material_isSolid(below) : false;
        }
        // stairs maps to either Material.WOOD or Material.COBBLESTONE, depending on the type of the stairs.
        // both do not override canPlace, so they aren't checked here (as they enter the default case)
        if (BlockConstants.isGenericTorch(material)) {
            return World_e(world, x - 1, y, z) ? true : (World_e(world, x + 1, y, z) ? true : (World_e(world, x, y, z - 1) ? true : (World_e(world, x, y, z + 1) ? true : BlockTorch_g(world, x, y - 1, z))));
        }


        // default
        return Material_isReplacable(world.getType(x, y, z));
    }

    /*
     * For reference, Chunks used to store block data as a single byte in a giant array. Indexing was done as follows:
     * index = x << 11 | z << 7 | y where x and z are in [0, 15] and y is in [0, 127]
     */

    // helper functions to easily convert code using the old byte[] access for chunk data

    public static Material getType(final ChunkGenerator.ChunkData chunkData, final int index) {
        return chunkData.getType(index >>> 11, index & 127, (index >>> 7) & 15);
    }

    public static BlockData getBlockData(final ChunkGenerator.ChunkData chunkData, final int index) {
        return chunkData.getBlockData(index >>> 11, index & 127, (index >>> 7) & 15);
    }

    public static void setBlockData(final ChunkGenerator.ChunkData chunkData, final int index, final BlockData blockData) {
        chunkData.setBlock(index >>> 11, index & 127, (index >>> 7) & 15, blockData);
    }

    // due to very questionable decisions by spigot, the getHighestBlockYAt function was changed to return
    // the actual block y, after returning the block y + 1 for about 9 years. Move the correction here just in case
    // spigot breaks it again, so we can account for it easily.
    public static int getHighestBlockYAt(final World world, final int x, final int z) {
        return world.getHighestBlockYAt(x, z) + 1;
    }

    public static boolean isFallingBlock(final Material material) {
        return material == Material.SAND || material == Material.GRAVEL;
    }
}