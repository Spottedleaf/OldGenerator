package ca.spottedleaf.oldgenerator.generator.b173;

import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenBigTree173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenForest173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenTaiga1173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenTaiga2173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenTrees173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenerator173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import java.util.Random;

public enum BiomeBase173 {

    RAINFOREST(Biome.JUNGLE) {
        @Override
        public WorldGenerator173 getTreeGenerator(Random random) {
            return random.nextInt(3) == 0 ? new WorldGenBigTree173() : new WorldGenTrees173();
        }
    },
    SWAMPLAND(Biome.SWAMP),
    SEASONAL_FOREST(Biome.FOREST),
    FOREST(Biome.FOREST) {
        @Override
        public WorldGenerator173 getTreeGenerator(Random random) {
            return random.nextInt(5) == 0 ? new WorldGenForest173() : (random.nextInt(3) == 0 ? new WorldGenBigTree173() : new WorldGenTrees173());
        }
    },
    SAVANNA(Biome.SAVANNA),
    SHRUBLAND(Biome.PLAINS),
    TAIGA(Biome.TAIGA) {
        @Override
        public WorldGenerator173 getTreeGenerator(Random random) {
            return (random.nextInt(3) == 0 ? new WorldGenTaiga1173() : new WorldGenTaiga2173());
        }
    },
    DESERT(Biome.DESERT, BlockConstants.SAND, BlockConstants.SAND),
    PLAINS(Biome.PLAINS),
    ICE_DESERT(Biome.DESERT, BlockConstants.SAND, BlockConstants.SAND),
    TUNDRA(Biome.SNOWY_TUNDRA),
    HELL(Biome.NETHER_WASTES),
    SKY(Biome.THE_VOID);

    public final Biome bukkitBiome;
    public final BlockData top;
    public final BlockData bottom;

    BiomeBase173(Biome bukkitBiome) {
        this(bukkitBiome, BlockConstants.GRASS_BLOCK, BlockConstants.DIRT);
    }

    BiomeBase173(Biome bukkitBiome, BlockData top, BlockData bottom) {
        this.bukkitBiome = bukkitBiome;
        this.top = top;
        this.bottom = bottom;
    }

    static final BiomeBase173[] LOOKUP = new BiomeBase173[64 * 64];

    static {
        for (int i = 0; i < 64; ++i) {
            for (int k = 0; k < 64; ++k) {
                LOOKUP[i + k * 64] = getByRainTempUncached((float)i / 63.0F, (float)k / 63.0F);
            }
        }
    }

    public static BiomeBase173 getByRainTempUncached(float f, float f1) {
        f1 *= f;
        return f < 0.1F ? TUNDRA : (f1 < 0.2F ? (f < 0.5F ? TUNDRA : (f < 0.95F ? SAVANNA : DESERT)) : (f1 > 0.5F && f < 0.7F ? SWAMPLAND : (f < 0.5F ? TAIGA : (f < 0.97F ? (f1 < 0.35F ? SHRUBLAND : FOREST) : (f1 < 0.45F ? PLAINS : (f1 < 0.9F ? SEASONAL_FOREST : RAINFOREST))))));
    }

    public static BiomeBase173 a(double temp, double rain) {
        int i = (int)(temp * 63.0D);
        int j = (int)(rain * 63.0D);

        return LOOKUP[i + j * 64];
    }

    public WorldGenerator173 getTreeGenerator(Random random) {
        return random.nextInt(10) == 0 ? new WorldGenBigTree173() : new WorldGenTrees173();
    }
}
