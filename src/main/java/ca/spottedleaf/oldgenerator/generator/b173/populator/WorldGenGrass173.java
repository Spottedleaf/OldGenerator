package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import java.util.Random;

public class WorldGenGrass173 extends WorldGenerator173 {

    private final BlockData blockData;

    public WorldGenGrass173(BlockData i) {
        this.blockData = i;
    }

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        Material l;

        for (boolean flag = false; (BlockConstants.isAir(l = world.getType(centerX, centerY, centerZ)) || BlockConstants.isLeaves(l)) && centerY > 0; --centerY) {
            ;
        }

        for (int i1 = 0; i1 < 128; ++i1) {
            int j1 = centerX + random.nextInt(8) - random.nextInt(8);
            int k1 = centerY + random.nextInt(4) - random.nextInt(4);
            int l1 = centerZ + random.nextInt(8) - random.nextInt(8);


            if (BlockConstants.isAir(world.getType(j1, k1, l1)) && LegacyUtil173.BlockFlower_f(world, j1, k1, l1, this.blockData.getMaterial())) {
                world.setBlockData(j1, k1, l1, this.blockData, false);
            }
        }

        return true;
    }
}
