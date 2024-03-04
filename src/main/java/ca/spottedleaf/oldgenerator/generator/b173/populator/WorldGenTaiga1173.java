package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import java.util.Random;

public class WorldGenTaiga1173 extends WorldGenerator173 {

    public WorldGenTaiga1173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        int l = random.nextInt(5) + 7;
        int i1 = l - random.nextInt(2) - 3;
        int j1 = l - i1;
        int k1 = 1 + random.nextInt(j1 + 1);
        boolean flag = true;

        if (centerY >= (world.getMinHeight() + 1) && centerY + l + 1 <= (world.getMaxHeight() + 1)) {
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            Material type;

            for (l1 = centerY; l1 <= centerY + 1 + l && flag; ++l1) {
                boolean flag1 = true;

                if (l1 - centerY < i1) {
                    l2 = 0;
                } else {
                    l2 = k1;
                }

                for (i2 = centerX - l2; i2 <= centerX + l2 && flag; ++i2) {
                    for (j2 = centerZ - l2; j2 <= centerZ + l2 && flag; ++j2) {
                        if (l1 >= world.getMinHeight() && l1 < (world.getMaxHeight() + 1)) {
                            type = world.getType(i2, l1, j2);
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
                    l2 = 0;

                    for (i2 = centerY + l; i2 >= centerY + i1; --i2) {
                        for (j2 = centerX - l2; j2 <= centerX + l2; ++j2) {
                            k2 = j2 - centerX;

                            for (int i3 = centerZ - l2; i3 <= centerZ + l2; ++i3) {
                                int j3 = i3 - centerZ;

                                if ((Math.abs(k2) != l2 || Math.abs(j3) != l2 || l2 <= 0) && !LegacyUtil173.Block_o(world.getType(j2, i2, i3))) {
                                    world.setBlockData(j2, i2, i3, BlockConstants.SPRUCE_LEAVES, false);
                                }
                            }
                        }

                        if (l2 >= 1 && i2 == centerY + i1 + 1) {
                            --l2;
                        } else if (l2 < k1) {
                            ++l2;
                        }
                    }

                    for (i2 = 0; i2 < l - 1; ++i2) {
                        type = world.getType(centerX, centerY + i2, centerZ);
                        if (BlockConstants.isAir(type) || BlockConstants.isLeaves(type)) {
                            world.setBlockData(centerX, centerY + i2, centerZ, BlockConstants.SPRUCE_LOG, false);
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
