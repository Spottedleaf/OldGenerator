package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import java.util.Random;

public class WorldGenTaiga2173 extends WorldGenerator173 {

    public WorldGenTaiga2173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        int l = random.nextInt(4) + 6;
        int i1 = 1 + random.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + random.nextInt(2);
        boolean flag = true;

        if (centerY >= (world.getMinHeight() + 1) && centerY + l + 1 <= (world.getMaxHeight() + 1)) {
            int l1;
            int i2;
            int j2;
            int k2;
            Material type;

            for (l1 = centerY; l1 <= centerY + 1 + l && flag; ++l1) {
                boolean flag1 = true;

                if (l1 - centerY < i1) {
                    k2 = 0;
                } else {
                    k2 = k1;
                }

                for (i2 = centerX - k2; i2 <= centerX + k2 && flag; ++i2) {
                    for (int l2 = centerZ - k2; l2 <= centerZ + k2 && flag; ++l2) {
                        if (l1 >= world.getMinHeight() && l1 < (world.getMaxHeight() + 1)) {
                            type = world.getType(i2, l1, l2);
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
                if ((type == Material.GRASS_BLOCK || type == Material.DIRT) && centerY < (world.getMaxHeight() + 1) - l - 1) {
                    world.setBlockData(centerX, centerY - 1, centerZ, BlockConstants.DIRT, false);
                    k2 = random.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;

                    int i3;
                    int j3;

                    for (j2 = 0; j2 <= j1; ++j2) {
                        j3 = centerY + l - j2;

                        for (i3 = centerX - k2; i3 <= centerX + k2; ++i3) {
                            int k3 = i3 - centerX;

                            for (int l3 = centerZ - k2; l3 <= centerZ + k2; ++l3) {
                                int i4 = l3 - centerZ;

                                if ((Math.abs(k3) != k2 || Math.abs(i4) != k2 || k2 <= 0) && !LegacyUtil173.Block_o(world.getType(i3, j3, l3))) {
                                    world.setBlockData(i3, j3, l3, BlockConstants.SPRUCE_LEAVES, false);
                                }
                            }
                        }

                        if (k2 >= i2) {
                            k2 = b0;
                            b0 = 1;
                            ++i2;
                            if (i2 > k1) {
                                i2 = k1;
                            }
                        } else {
                            ++k2;
                        }
                    }

                    j2 = random.nextInt(3);

                    for (j3 = 0; j3 < l - j2; ++j3) {
                        type = world.getType(centerX, centerY + j3, centerZ);
                        if (BlockConstants.isAir(type) || BlockConstants.isLeaves(type)) {
                            world.setBlockData(centerX, centerY + j3, centerZ, BlockConstants.SPRUCE_LOG, false);
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
