package ca.spottedleaf.oldgenerator.world;

import org.bukkit.HeightMap;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

public final class WorldBlockAccess implements BlockAccess {

    public final World world;

    public WorldBlockAccess(final World world) {
        this.world = world;
    }

    @Override
    public Material getType(final int x, final int y, final int z) {
        return this.world.getBlockAt(x, y, z).getType();
    }

    @Override
    public void setType(final int x, final int y, final int z, final Material material) {
        this.world.getBlockAt(x, y, z).setType(material);
    }

    @Override
    public void setType(final int x, final int y, final int z, final Material material, final boolean applyPhysics) {
        this.world.getBlockAt(x, y, z).setType(material, applyPhysics);
    }

    @Override
    public BlockData getBlockData(final int x, final int y, final int z) {
        return this.world.getBlockAt(x, y, z).getBlockData();
    }

    @Override
    public void setBlockData(final int x, final int y, final int z, final BlockData data) {
        this.world.getBlockAt(x, y, z).setBlockData(data);
    }

    @Override
    public void setBlockData(final int x, final int y, final int z, final BlockData data, final boolean applyPhysics) {
        this.world.getBlockAt(x, y, z).setBlockData(data, applyPhysics);
    }

    @Override
    public BlockState getBlockState(final int x, final int y, final int z) {
        return this.world.getBlockAt(x, y, z).getState();
    }

    @Override
    public byte getLightFromSky(final int x, final int y, final int z) {
        return this.world.getBlockAt(x, y, z).getLightFromSky();
    }

    @Override
    public byte getLightFromBlocks(final int x, final int y, final int z) {
        return this.world.getBlockAt(x, y, z).getLightFromBlocks();
    }

    @Override
    public byte getLightLevel(final int x, final int y, final int z) {
        return this.world.getBlockAt(x, y, z).getLightLevel();
    }

    @Override
    public int getHighestBlockYAt(final int x, final int z) {
        return this.world.getHighestBlockYAt(x, z);
    }

    @Override
    public int getHighestBlockYAt(final int x, final int z, final HeightMap heightMap) {
        return this.world.getHighestBlockYAt(x, z, heightMap);
    }

    @Override
    public int getMaxHeight() {
        return this.world.getMaxHeight();
    }
}
