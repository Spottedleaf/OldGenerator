package ca.spottedleaf.oldgenerator.world;

import org.bukkit.HeightMap;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;

public final class ChunkDataBlockAccess implements BlockAccess {

    private final ChunkGenerator.ChunkData chunkData;

    public ChunkDataBlockAccess(final ChunkGenerator.ChunkData chunkData) {
        this.chunkData = chunkData;
    }

    private static void checkCoordinates(final int x, final int y, final int z) {
        final int xz = x | z;
        if ((xz & 15) != xz || (y & 255) != y) {
            throw new IllegalStateException("Out of range: [" + x + "," + y + "," + z + "]");
        }
    }

    @Override
    public Material getType(final int x, final int y, final int z) {
        checkCoordinates(x, y, z);
        return this.chunkData.getType(x, y, z);
    }

    @Override
    public void setType(final int x, final int y, final int z, final Material material) {
        checkCoordinates(x, y, z);
        this.chunkData.setBlock(x, y, z, material);
    }

    @Override
    public void setType(final int x, final int y, final int z, final Material material, final boolean applyPhysics) {
        checkCoordinates(x, y, z);
        this.chunkData.setBlock(x, y, z, material);
    }

    @Override
    public BlockData getBlockData(final int x, final int y, final int z) {
        checkCoordinates(x, y, z);
        return this.chunkData.getBlockData(x, y, z);
    }

    @Override
    public void setBlockData(final int x, final int y, final int z, final BlockData data) {
        checkCoordinates(x, y, z);
        this.chunkData.setBlock(x, y, z, data);
    }

    @Override
    public void setBlockData(final int x, final int y, final int z, final BlockData data, final boolean applyPhysics) {
        checkCoordinates(x, y, z);
        this.chunkData.setBlock(x, y, z, data);
    }

    @Override
    public BlockState getBlockState(final int x, final int y, final int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getLightFromSky(final int x, final int y, final int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getLightFromBlocks(final int x, final int y, final int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getLightLevel(final int x, final int y, final int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getHighestBlockYAt(final int x, final int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getHighestBlockYAt(final int x, final int z, final HeightMap heightMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMinHeight() {
        return 0;
    }

    @Override
    public int getMaxHeight() {
        return 127;
    }
}
