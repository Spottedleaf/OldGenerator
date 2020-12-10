package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import java.util.Random;

public class WorldGenFire173 extends WorldGenerator173 {

    public WorldGenFire173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        for (int l = 0; l < 64; ++l) {
            int i1 = centerX + random.nextInt(8) - random.nextInt(8);
            int j1 = centerY + random.nextInt(4) - random.nextInt(4);
            int k1 = centerZ + random.nextInt(8) - random.nextInt(8);

            if (BlockConstants.isAir(world.getType(i1, j1, k1)) && world.getType(i1, j1 - 1, k1) == Material.NETHERRACK) {
                world.setBlockData(i1, j1, k1, BlockConstants.FIRE, false);
            }
        }

        return true;
    }
}
