package ca.spottedleaf.oldgenerator.world;

import org.bukkit.HeightMap;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

public interface BlockAccess {

    public Material getType(final int x, final int y, final int z);

    public void setType(final int x, final int y, final int z, final Material material);

    public void setType(final int x, final int y, final int z, final Material material, final boolean applyPhysics);

    public BlockData getBlockData(final int x, final int y, final int z);

    public void setBlockData(final int x, final int y, final int z, final BlockData data);

    public void setBlockData(final int x, final int y, final int z, final BlockData data, final boolean applyPhysics);

    public BlockState getBlockState(final int x, final int y, final int z);

    public byte getLightFromSky(final int x, final int y, final int z);

    public byte getLightFromBlocks(final int x, final int y, final int z);

    public byte getLightLevel(final int x, final int y, final int z);

    public int getHighestBlockYAt(final int x, final int z);

    public int getHighestBlockYAt(final int x, final int z, final HeightMap heightMap);

    default int getTotalHeight() {
        return this.getMaxHeight() - this.getMinHeight() + 1;
    }

    public int getMinHeight();

    public int getMaxHeight();
}
