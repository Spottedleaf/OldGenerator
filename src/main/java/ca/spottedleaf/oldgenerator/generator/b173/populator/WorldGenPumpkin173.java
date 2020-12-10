package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import java.util.Random;

public class WorldGenPumpkin173 extends WorldGenerator173 {

    private static final BlockData[] RANDOM_CARVED_PUMPKIN_FACING;
    static {
        final BlockFace[] facings = new BlockFace[] {
                BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST
        };

        RANDOM_CARVED_PUMPKIN_FACING = new BlockData[facings.length];
        for (int i = 0, len = facings.length; i < len; ++i) {
            final Directional data = (Directional)Material.CARVED_PUMPKIN.createBlockData();
            data.setFacing(facings[i]);

            RANDOM_CARVED_PUMPKIN_FACING[i] = data;
        }
    }

    public static BlockData getRandomCarvedPumpkin(final Random random) {
        return RANDOM_CARVED_PUMPKIN_FACING[random.nextInt(RANDOM_CARVED_PUMPKIN_FACING.length)];
    }

    public static BlockData getRandomCarvedPumpkinClone(final Random random) {
        return RANDOM_CARVED_PUMPKIN_FACING[random.nextInt(RANDOM_CARVED_PUMPKIN_FACING.length)].clone();
    }

    public WorldGenPumpkin173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        for (int l = 0; l < 64; ++l) {
            int i1 = centerX + random.nextInt(8) - random.nextInt(8);
            int j1 = centerY + random.nextInt(4) - random.nextInt(4);
            int k1 = centerZ + random.nextInt(8) - random.nextInt(8);

            if (BlockConstants.isAir(world.getType(i1, j1, k1)) && world.getType(i1, j1 - 1, k1) == Material.GRASS_BLOCK && LegacyUtil173.Block_canPlace(world, i1, j1, k1, Material.PUMPKIN)) {
                world.setBlockData(i1, j1, k1, getRandomCarvedPumpkin(random), false);
            }
        }

        return true;
    }
}
