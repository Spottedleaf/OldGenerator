package ca.spottedleaf.oldgenerator.world;

import org.bukkit.HeightMap;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;

public final class LimitedBlockAccess implements BlockAccess {

    private final ChunkGenerator.ChunkData[] chunks;
    private final int lowerX; // inclusive
    private final int lowerZ; // inclusive
    private final int upperX; // inclusive
    private final int upperZ; // inclusive
    private final int lengthX;

    public LimitedBlockAccess(final int lowerX, final int lowerZ, final int upperX, final int upperZ) {
        if (lowerX > upperX || lowerZ > upperZ) {
            throw new IllegalArgumentException("Lower must be less-than upper");
        }

        this.lowerX = lowerX;
        this.lowerZ = lowerZ;
        this.upperX = upperX;
        this.upperZ = upperZ;
        this.lengthX = upperX - lowerX + 1;

        this.chunks = new ChunkGenerator.ChunkData[this.lengthX * (upperZ - lowerZ + 1)];
    }

    public ChunkGenerator.ChunkData getChunk(final int chunkX, final int chunkZ) {
        if (chunkX < this.lowerX || chunkZ < this.lowerZ || chunkX > this.upperX || chunkZ > this.upperZ) {
            throw new IllegalStateException("Chunk [" + chunkX + "," + chunkZ + "] is outside region " + this.toString());
        }

        final int index = (chunkX - this.lowerX) + this.lengthX * (chunkZ - this.lowerZ);
        final ChunkGenerator.ChunkData ret = this.chunks[index];

        if (ret == null) {
            throw new IllegalStateException("Chunk [" + chunkX + "," + chunkZ + "] does not exist (null) in region " + this.toString());
        }

        return ret;
    }


    public void setChunk(final int chunkX, final int chunkZ, final ChunkGenerator.ChunkData chunk) {
        if (chunkX < this.lowerX || chunkZ < this.lowerZ || chunkX > this.upperX || chunkZ > this.upperZ) {
            throw new IllegalStateException("Chunk [" + chunkX + "," + chunkZ + "] is outside region " + this.toString());
        }

        final int index = (chunkX - this.lowerX) + this.lengthX * (chunkZ - this.lowerZ);
        final ChunkGenerator.ChunkData existing = this.chunks[index];

        if (existing != null) {
            throw new IllegalStateException("Chunk [" + chunkX + "," + chunkZ + "] already exists in region " + this.toString());
        }

        this.chunks[index] = chunk;
    }

    @Override
    public boolean isLoaded(final int chunkX, final int chunkZ) {
        return chunkX >= this.lowerX && chunkZ >= this.lowerZ && chunkX <= this.upperX && chunkZ <= this.upperZ;
    }

    @Override
    public Material getType(final int x, final int y, final int z) {
        return this.getChunk(x >> 4, z >> 4).getType(x, y, z);
    }

    @Override
    public void setType(final int x, final int y, final int z, final Material material) {
        this.getChunk(x >> 4, z >> 4).setBlock(x, y, z, material);
    }

    @Override
    public void setType(final int x, final int y, final int z, final Material material, final boolean applyPhysics) {
        this.getChunk(x >> 4, z >> 4).setBlock(x, y, z, material);
    }

    @Override
    public BlockData getBlockData(final int x, final int y, final int z) {
        return this.getChunk(x >> 4, z >> 4).getBlockData(x, y, z);
    }

    @Override
    public void setBlockData(final int x, final int y, final int z, final BlockData data) {
        this.getChunk(x >> 4, z >> 4).setBlock(x, y, z, data);
    }

    @Override
    public void setBlockData(final int x, final int y, final int z, final BlockData data, final boolean applyPhysics) {
        this.getChunk(x >> 4, z >> 4).setBlock(x, y, z, data);
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

    @Override
    public String toString() {
        return "Region[fromX=" + this.lowerX + ",toX=" + this.upperX + ",fromZ=" + this.lowerZ + ",toZ=" + this.upperZ + "]";
    }
}
