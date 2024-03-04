package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import java.util.Random;

public class WorldGenForest173 extends WorldGenerator173 {

    public WorldGenForest173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        int l = random.nextInt(3) + 5;
        boolean flag = true;

        if (centerY >= (world.getMinHeight() + 1) && centerY + l + 1 <= (world.getMaxHeight() + 1)) {
            int i1;
            int j1;
            int k1;
            int l1;
            Material type;

            for (i1 = centerY; i1 <= centerY + 1 + l; ++i1) {
                byte b0 = 1;

                if (i1 == centerY) {
                    b0 = 0;
                }

                if (i1 >= centerY + 1 + l - 2) {
                    b0 = 2;
                }

                for (j1 = centerX - b0; j1 <= centerX + b0 && flag; ++j1) {
                    for (k1 = centerZ - b0; k1 <= centerZ + b0 && flag; ++k1) {
                        if (i1 >= world.getMinHeight() && i1 < (world.getMaxHeight() + 1)) {
                            type = world.getType(j1, i1, k1);
                            if (!BlockConstants.isAir(type) && !BlockConstants.isLeaves(type)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                type = world.getType(centerX, centerY - 1, centerZ);
                if ((type == Material.GRASS_BLOCK || type == Material.DIRT) && centerY < 128 - l - 1) {
                    world.setBlockData(centerX, centerY - 1, centerZ, BlockConstants.DIRT, false);

                    int i2;

                    for (i2 = centerY - 3 + l; i2 <= centerY + l; ++i2) {
                        j1 = i2 - (centerY + l);
                        k1 = 1 - j1 / 2;

                        for (l1 = centerX - k1; l1 <= centerX + k1; ++l1) {
                            int j2 = l1 - centerX;

                            for (int k2 = centerZ - k1; k2 <= centerZ + k1; ++k2) {
                                int l2 = k2 - centerZ;

                                if ((Math.abs(j2) != k1 || Math.abs(l2) != k1 || random.nextInt(2) != 0 && j1 != 0) && !LegacyUtil173.Block_o(world.getType(l1, i2, k2))) {
                                    world.setBlockData(l1, i2, k2, BlockConstants.BIRCH_LEAVES, false);
                                }
                            }
                        }
                    }

                    for (i2 = 0; i2 < l; ++i2) {
                        type = world.getType(centerX, centerY + i2, centerZ);
                        if (BlockConstants.isAir(type) || BlockConstants.isLeaves(type)) {
                            world.setBlockData(centerX, centerY + i2, centerZ, BlockConstants.BIRCH_LOG, false);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
