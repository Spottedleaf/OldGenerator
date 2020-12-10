package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import java.util.Random;

public class WorldGenReed173 extends WorldGenerator173 {

    public WorldGenReed173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        for (int l = 0; l < 20; ++l) {
            int i1 = centerX + random.nextInt(4) - random.nextInt(4);
            int j1 = centerY;
            int k1 = centerZ + random.nextInt(4) - random.nextInt(4);

            if (BlockConstants.isAir(world.getType(i1, centerY, k1)) && (world.getType(i1 - 1, centerY - 1, k1) == Material.WATER || world.getType(i1 + 1, centerY - 1, k1) == Material.WATER || world.getType(i1, centerY - 1, k1 - 1) == Material.WATER || world.getType(i1, centerY - 1, k1 + 1) == Material.WATER)) {
                int l1 = 2 + random.nextInt(random.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2) {
                    if (LegacyUtil173.Block_canPlace(world, i1, j1 + i2, k1, Material.SUGAR_CANE)) { // f just calls canPlace
                        world.setBlockData(i1, j1 + i2, k1, BlockConstants.SUGAR_CANE, false);
                    }
                }
            }
        }

        return true;
    }
}
