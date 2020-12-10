package ca.spottedleaf.oldgenerator.generator.b173;

import ca.spottedleaf.oldgenerator.generator.b173.nether.ChunkProviderHell173;
import ca.spottedleaf.oldgenerator.generator.b173.overworld.ChunkProviderGenerate173;
import ca.spottedleaf.oldgenerator.generator.b173.sky.ChunkProviderSky173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.WorldBlockAccess;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.WeakHashMap;

public final class Beta173ChunkGenerator extends ChunkGenerator {

    private final WeakHashMap<World, ArrayDeque<ChunkProviderGenerate173>> cachedOverworldGenerators = new WeakHashMap<>();
    private final WeakHashMap<World, ArrayDeque<ChunkProviderHell173>> cachedNetherGenerators = new WeakHashMap<>();
    private final WeakHashMap<World, ArrayDeque<ChunkProviderSky173>> cachedSkyGenerators = new WeakHashMap<>();

    public final boolean isSkyLands;

    public Beta173ChunkGenerator(final boolean isSkyLands) {
        this.isSkyLands = isSkyLands;
    }

    public ChunkProviderGenerate173 getOverworldGenerator(World world) {
        ChunkProviderGenerate173 ret;
        synchronized (this.cachedOverworldGenerators) {
            ret = this.cachedOverworldGenerators.computeIfAbsent(world, (key) -> new ArrayDeque<>()).pollFirst();
        }

        if (ret == null) {
            ret = new ChunkProviderGenerate173(world, world.getSeed());
        }

        return ret;
    }

    public void returnOverworldGenerator(ChunkProviderGenerate173 generator, World world) {
        synchronized (this.cachedOverworldGenerators) {
            this.cachedOverworldGenerators.get(world).addLast(generator);
        }
    }

    public ChunkProviderHell173 getNetherGenerator(World world) {
        ChunkProviderHell173 ret;
        synchronized (this.cachedNetherGenerators) {
            ret = this.cachedNetherGenerators.computeIfAbsent(world, (key) -> new ArrayDeque<>()).pollFirst();
        }

        if (ret == null) {
            ret = new ChunkProviderHell173(world, world.getSeed());
        }

        return ret;
    }

    public void returnNetherGenerator(ChunkProviderHell173 generator, World world) {
        synchronized (this.cachedNetherGenerators) {
            this.cachedNetherGenerators.get(world).addLast(generator);
        }
    }

    public ChunkProviderSky173 getSkyGenerator(World world) {
        ChunkProviderSky173 ret;
        synchronized (this.cachedSkyGenerators) {
            ret = this.cachedSkyGenerators.computeIfAbsent(world, (key) -> new ArrayDeque<>()).pollFirst();
        }

        if (ret == null) {
            ret = new ChunkProviderSky173(world, world.getSeed());
        }

        return ret;
    }

    public void returnSkyGenerator(ChunkProviderSky173 generator, World world) {
        synchronized (this.cachedSkyGenerators) {
            this.cachedSkyGenerators.get(world).addLast(generator);
        }
    }


    @Override
    public boolean isParallelCapable() {
        return true;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData ret = this.createChunkData(world);
        switch (world.getEnvironment()) {
            case NORMAL: {
                if (!this.isSkyLands) {
                    ChunkProviderGenerate173 generator = this.getOverworldGenerator(world);
                    try {
                        generator.generateUnpopulatedChunkData(ret, x, z, biome);
                    } finally {
                        this.returnOverworldGenerator(generator, world);
                    }
                    return ret;
                } else {
                    ChunkProviderSky173 generator = this.getSkyGenerator(world);
                    try {
                        generator.generateUnpopulatedChunkData(ret, x, z, biome);
                    } finally {
                        this.returnSkyGenerator(generator, world);
                    }
                    return ret;
                }
            }
            case NETHER: {
                ChunkProviderHell173 generator = this.getNetherGenerator(world);
                try {
                    generator.generateUnpopulatedChunkData(ret, x, z, biome);
                } finally {
                    this.returnNetherGenerator(generator, world);
                }
                return ret;
            }
            default:
                throw new IllegalArgumentException("Invalid environment: " + world.getEnvironment());
        }
    }

    public void runPopulators(World world, Chunk chunk) {
        switch (world.getEnvironment()) {
            case NORMAL: {
                if (!this.isSkyLands) {
                    ChunkProviderGenerate173 generator = this.getOverworldGenerator(world);
                    try {
                        generator.populateChunk(new WorldBlockAccess(world), chunk.getX(), chunk.getZ()); // TODO limited access
                    } finally {
                        this.returnOverworldGenerator(generator, world);
                    }
                    return;
                } else {
                    ChunkProviderSky173 generator = this.getSkyGenerator(world);
                    try {
                        generator.populateChunk(new WorldBlockAccess(world), chunk.getX(), chunk.getZ()); // TODO limited access
                    } finally {
                        this.returnSkyGenerator(generator, world);
                    }
                    return;
                }
            }
            case NETHER: {
                ChunkProviderHell173 generator = this.getNetherGenerator(world);
                try {
                    generator.populateChunk(new WorldBlockAccess(world), chunk.getX(), chunk.getZ()); // TODO limited access
                } finally {
                    this.returnNetherGenerator(generator, world);
                }
                return;
            }
            default:
                throw new IllegalArgumentException("Invalid environment: " + world.getEnvironment());
        }
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        if (world.getEnvironment() == World.Environment.THE_END) {
            throw new IllegalStateException("Invalid environment: " + world.getEnvironment());
        }
        int x = 0;
        int z = 0;

        while (!this.canSpawn(world, x, z)) {
            x += random.nextInt(64) - random.nextInt(64);
            z += random.nextInt(64) - random.nextInt(64);
        }

        return new Location(world, x, 64.0, z);
    }

    @Override
    public boolean canSpawn(World world, int x, int z) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            int y;
            for (y = 63; !world.getBlockAt(x, y + 1, z).isEmpty(); ++y) {}

            if (!this.isSkyLands) {
                return world.getBlockAt(x, y, z).getType() == Material.SAND;
            } else {
                final Material type = world.getBlockAt(x, y, z).getType();
                return !BlockConstants.isAir(type) && LegacyUtil173.Material_isSolid(type);
            }
        } else { // assume nether
            int y;
            for(y = 63; !world.getBlockAt(x, y + 1, z).isEmpty(); ++y) {}

            Material type = world.getBlockAt(x, y, z).getType();
            return type != Material.BEDROCK && (type != Material.AIR && LegacyUtil173.Block_o(type));
        }
    }
}
