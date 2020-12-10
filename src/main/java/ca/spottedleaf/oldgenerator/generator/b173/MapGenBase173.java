package ca.spottedleaf.oldgenerator.generator.b173;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import java.util.Random;

public class MapGenBase173 {
    protected final int offset = 8;
    protected final Random random = new Random();

    public MapGenBase173() {}

    public void generate(World world, int cx, int cz, ChunkGenerator.ChunkData chunkData) {
        int k = this.offset;

        this.random.setSeed(world.getSeed());
        long l = this.random.nextLong() / 2L * 2L + 1L;
        long i1 = this.random.nextLong() / 2L * 2L + 1L;

        for (int j1 = cx - k; j1 <= cx + k; ++j1) {
            for (int k1 = cz - k; k1 <= cz + k; ++k1) {
                this.random.setSeed((long) j1 * l + (long) k1 * i1 ^ world.getSeed());
                this.generate(world, j1, k1, cx, cz, chunkData);
            }
        }
    }

    protected void generate(World world, int i, int j, int k, int l, ChunkGenerator.ChunkData chunkData) {}

}
