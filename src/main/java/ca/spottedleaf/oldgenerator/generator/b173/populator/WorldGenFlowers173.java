package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.block.data.BlockData;
import java.util.Random;

public class WorldGenFlowers173 extends WorldGenerator173 {

    private final BlockData blockData;

    public WorldGenFlowers173(BlockData i) {
        this.blockData = i;
    }

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        for (int l = 0; l < 64; ++l) {
            int i1 = centerX + random.nextInt(8) - random.nextInt(8);
            int j1 = centerY + random.nextInt(4) - random.nextInt(4);
            int k1 = centerZ + random.nextInt(8) - random.nextInt(8);

            if (BlockConstants.isAir(world.getType(i1, j1, k1)) && LegacyUtil173.BlockFlower_f(world, i1, j1, k1, this.blockData.getMaterial())) {
                world.setBlockData(i1, j1, k1, this.blockData, false);
            }
        }

        return true;
    }
}
