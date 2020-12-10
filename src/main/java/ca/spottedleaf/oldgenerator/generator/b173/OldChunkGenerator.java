package ca.spottedleaf.oldgenerator.generator.b173;

import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.generator.ChunkGenerator;

public interface OldChunkGenerator {

    public void populateChunk(BlockAccess blockAccess, int chunkX, int chunkZ);

    public void generateUnpopulatedChunkData(ChunkGenerator.ChunkData chunkData, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biomes);

}
