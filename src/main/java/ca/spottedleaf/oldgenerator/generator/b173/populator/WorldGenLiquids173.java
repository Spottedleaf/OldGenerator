package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import java.util.Random;

public class WorldGenLiquids173 extends WorldGenerator173 {

    private final BlockData blockData;

    public WorldGenLiquids173(BlockData i) {
        this.blockData = i;
    }

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        if (world.getType(centerX, centerY + 1, centerZ) != Material.STONE) {
            return false;
        } else if (world.getType(centerX, centerY - 1, centerZ) != Material.STONE) {
            return false;
        } else if (!BlockConstants.isAir(world.getType(centerX, centerY, centerZ)) && world.getType(centerX, centerY, centerZ) != Material.STONE) {
            return false;
        } else {
            int l = 0;

            if (world.getType(centerX - 1, centerY, centerZ) == Material.STONE) {
                ++l;
            }

            if (world.getType(centerX + 1, centerY, centerZ) == Material.STONE) {
                ++l;
            }

            if (world.getType(centerX, centerY, centerZ - 1) == Material.STONE) {
                ++l;
            }

            if (world.getType(centerX, centerY, centerZ + 1) == Material.STONE) {
                ++l;
            }

            int i1 = 0;

            if (BlockConstants.isAir(world.getType(centerX - 1, centerY, centerZ))) {
                ++i1;
            }

            if (BlockConstants.isAir(world.getType(centerX + 1, centerY, centerZ))) {
                ++i1;
            }

            if (BlockConstants.isAir(world.getType(centerX, centerY, centerZ - 1))) {
                ++i1;
            }

            if (BlockConstants.isAir(world.getType(centerX, centerY, centerZ + 1))) {
                ++i1;
            }

            if (l == 3 && i1 == 1) {
                world.setBlockData(centerX, centerY, centerZ, this.blockData, true); // want physics here so the water flows
            }

            return true;
        }
    }
}
