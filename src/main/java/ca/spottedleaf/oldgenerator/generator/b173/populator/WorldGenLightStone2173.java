package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import java.util.Random;

public class WorldGenLightStone2173 extends WorldGenerator173 {

    // mfw same as lightstone1
    public WorldGenLightStone2173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        if (!BlockConstants.isAir(world.getType(centerX, centerY, centerZ))) {
            return false;
        } else if (world.getType(centerX, centerY + 1, centerZ) != Material.NETHERRACK) {
            return false;
        } else {
            world.setBlockData(centerX, centerY, centerZ, BlockConstants.GLOWSTONE, false);

            for (int l = 0; l < 1500; ++l) {
                int i1 = centerX + random.nextInt(8) - random.nextInt(8);
                int j1 = centerY - random.nextInt(12);
                int k1 = centerZ + random.nextInt(8) - random.nextInt(8);

                if (BlockConstants.isAir(world.getType(i1, j1, k1))) {
                    int l1 = 0;

                    for (int i2 = 0; i2 < 6; ++i2) {
                        Material j2 = Material.AIR;

                        if (i2 == 0) {
                            j2 = world.getType(i1 - 1, j1, k1);
                        }

                        if (i2 == 1) {
                            j2 = world.getType(i1 + 1, j1, k1);
                        }

                        if (i2 == 2) {
                            j2 = world.getType(i1, j1 - 1, k1);
                        }

                        if (i2 == 3) {
                            j2 = world.getType(i1, j1 + 1, k1);
                        }

                        if (i2 == 4) {
                            j2 = world.getType(i1, j1, k1 - 1);
                        }

                        if (i2 == 5) {
                            j2 = world.getType(i1, j1, k1 + 1);
                        }

                        if (j2 == Material.GLOWSTONE) {
                            ++l1;
                        }
                    }

                    if (l1 == 1) {
                        world.setBlockData(i1, j1, k1, BlockConstants.GLOWSTONE, false);
                    }
                }
            }

            return true;
        }
    }
}
