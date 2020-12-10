package ca.spottedleaf.oldgenerator.generator.b173.sky;

import ca.spottedleaf.oldgenerator.generator.b173.BiomeBase173;
import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.generator.b173.MapGenBase173;
import ca.spottedleaf.oldgenerator.generator.b173.OldChunkGenerator;
import ca.spottedleaf.oldgenerator.generator.b173.nether.WorldChunkManagerHell173;
import ca.spottedleaf.oldgenerator.generator.b173.noise.NoiseGeneratorOctaves173;
import ca.spottedleaf.oldgenerator.generator.b173.overworld.MapGenCaves173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenCactus173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenClay173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenDungeons173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenFlowers173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenLakes173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenLiquids173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenMinable173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenPumpkin173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenReed173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenerator173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.HeightMap;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;
import java.util.Random;

public class ChunkProviderSky173 implements OldChunkGenerator {

    private final Random random;

    private final NoiseGeneratorOctaves173 terrainNoise2Generator;
    private final NoiseGeneratorOctaves173 terrainNoise3Generator;
    private final NoiseGeneratorOctaves173 terrainNoise1Generator;
    private final NoiseGeneratorOctaves173 sandAndGravelNoiseGenerator;
    private final NoiseGeneratorOctaves173 stoneNoiseGenerator;
    private final NoiseGeneratorOctaves173 terrainNoise4Generator;
    private final NoiseGeneratorOctaves173 terrainNoise5Generator;
    private final NoiseGeneratorOctaves173 treeCountNoise;

    private final World world;
    private final WorldChunkManagerHell173 worldChunkManager;

    private double[] terrainNoise;
    private double[] sandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] stoneNoise = new double[256];
    private double[] terrainNoise1;
    private double[] terrainNoise2;
    private double[] terrainNoise3;
    private double[] terrainNoise4;
    private double[] terrainNoise5;
    private double[] snowNoise;

    private final MapGenBase173 caveGenerator = new MapGenCaves173();

    private BiomeBase173[] biomeNoiseCache;

    public ChunkProviderSky173(World world, long seed) {
        this.world = world;
        this.worldChunkManager = new WorldChunkManagerHell173(BiomeBase173.SKY, 0.5, 0.0);
        this.random = new Random(seed);
        this.terrainNoise2Generator = new NoiseGeneratorOctaves173(this.random, 16);
        this.terrainNoise3Generator = new NoiseGeneratorOctaves173(this.random, 16);
        this.terrainNoise1Generator = new NoiseGeneratorOctaves173(this.random, 8);
        this.sandAndGravelNoiseGenerator = new NoiseGeneratorOctaves173(this.random, 4);
        this.stoneNoiseGenerator = new NoiseGeneratorOctaves173(this.random, 4);
        this.terrainNoise4Generator = new NoiseGeneratorOctaves173(this.random, 10);
        this.terrainNoise5Generator = new NoiseGeneratorOctaves173(this.random, 16);
        this.treeCountNoise = new NoiseGeneratorOctaves173(this.random, 8);
    }

    public void generateBareTerrain(int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData, BiomeBase173[] biomeCache,
                                    double[] temperatures) {
        byte b0 = 2;
        int k = b0 + 1;
        byte b1 = 33;
        int l = b0 + 1;

        this.terrainNoise = this.generateTerrainNoise(this.terrainNoise, chunkX * b0, 0, chunkZ * b0, k, b1, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 32; ++k1) {
                    double d0 = 0.25D;
                    double d1 = this.terrainNoise[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
                    double d2 = this.terrainNoise[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
                    double d3 = this.terrainNoise[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
                    double d4 = this.terrainNoise[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
                    double d5 = (this.terrainNoise[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
                    double d6 = (this.terrainNoise[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
                    double d7 = (this.terrainNoise[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
                    double d8 = (this.terrainNoise[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 4; ++l1) {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 8; ++i2) {
                            int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + l1;
                            short short1 = 128;
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 8; ++k2) {
                                BlockData blockData = BlockConstants.AIR;

                                if (d15 > 0.0D) {
                                    blockData = BlockConstants.STONE;
                                }

                                LegacyUtil173.setBlockData(chunkData, j2, blockData);
                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    // turns base terrain into the biome dependent terrain
    public void generateBiomeTerrain(int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData, BiomeBase173[] biomeCache) {
        double d0 = 0.03125D;

        this.sandNoise = this.sandAndGravelNoiseGenerator.generateNoise(this.sandNoise, (double) (chunkX * 16), (double) (chunkZ * 16), 0.0D, 16, 16, 1, d0, d0, 1.0D);
        this.gravelNoise = this.sandAndGravelNoiseGenerator.generateNoise(this.gravelNoise, (double) (chunkX * 16), 109.0134D, (double) (chunkZ * 16), 16, 1, 16, d0, 1.0D, d0);
        this.stoneNoise = this.stoneNoiseGenerator.generateNoise(this.stoneNoise, (double) (chunkX * 16), (double) (chunkZ * 16), 0.0D, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeBase173 biomebase = biomeCache[k + l * 16];
                int i1 = (int) (this.stoneNoise[k + l * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
                int j1 = -1;
                BlockData b0 = biomebase.top;
                BlockData b1 = biomebase.bottom;

                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = (l * 16 + k) * 128 + k1;
                    Material b2 = LegacyUtil173.getType(chunkData, l1);

                    if (BlockConstants.isAir(b2)) {
                        j1 = -1;
                    } else if (b2 == Material.STONE) {
                        if (j1 == -1) {
                            if (i1 <= 0) {
                                b0 = BlockConstants.AIR;
                                b1 = BlockConstants.STONE;
                            }

                            j1 = i1;
                            if (k1 >= 0) {
                                LegacyUtil173.setBlockData(chunkData, l1, b0);
                            } else {
                                LegacyUtil173.setBlockData(chunkData, l1, b1);
                            }
                        } else if (j1 > 0) {
                            --j1;
                            LegacyUtil173.setBlockData(chunkData, l1, b1);
                            if (j1 == 0 && b1 == BlockConstants.SAND) {
                                j1 = this.random.nextInt(4);
                                b1 = BlockConstants.SANDSTONE;
                            }
                        }
                    }
                }
            }
        }
    }

    private double[] generateTerrainNoise(double[] noise, int fromX, int fromY, int fromZ, int xLen, int yLen, int zLen) {
        if (noise == null) {
            noise = new double[xLen * yLen * zLen];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;
        double[] adouble1 = this.worldChunkManager.temperature;
        double[] adouble2 = this.worldChunkManager.rain;

        this.terrainNoise4 = this.terrainNoise4Generator.generateNoise(this.terrainNoise4, fromX, fromZ, xLen, zLen, 1.121D, 1.121D, 0.5D);
        this.terrainNoise5 = this.terrainNoise5Generator.generateNoise(this.terrainNoise5, fromX, fromZ, xLen, zLen, 200.0D, 200.0D, 0.5D);
        d0 *= 2.0D;
        this.terrainNoise1 = this.terrainNoise1Generator.generateNoise(this.terrainNoise1, (double) fromX, (double) fromY, (double) fromZ, xLen, yLen, zLen, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.terrainNoise2 = this.terrainNoise2Generator.generateNoise(this.terrainNoise2, (double) fromX, (double) fromY, (double) fromZ, xLen, yLen, zLen, d0, d1, d0);
        this.terrainNoise3 = this.terrainNoise3Generator.generateNoise(this.terrainNoise3, (double) fromX, (double) fromY, (double) fromZ, xLen, yLen, zLen, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / xLen;

        for (int j2 = 0; j2 < xLen; ++j2) {
            int k2 = j2 * i2 + i2 / 2;

            for (int l2 = 0; l2 < zLen; ++l2) {
                int i3 = l2 * i2 + i2 / 2;
                double d2 = adouble1[k2 * 16 + i3];
                double d3 = adouble2[k2 * 16 + i3] * d2;
                double d4 = 1.0D - d3;

                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (this.terrainNoise4[l1] + 256.0D) / 512.0D;

                d5 *= d4;
                if (d5 > 1.0D) {
                    d5 = 1.0D;
                }

                double d6 = this.terrainNoise5[l1] / 8000.0D;

                if (d6 < 0.0D) {
                    d6 = -d6 * 0.3D;
                }

                d6 = d6 * 3.0D - 2.0D;
                if (d6 > 1.0D) {
                    d6 = 1.0D;
                }

                d6 /= 8.0D;
                d6 = 0.0D;
                if (d5 < 0.0D) {
                    d5 = 0.0D;
                }

                d5 += 0.5D;
                d6 = d6 * (double) yLen / 16.0D;
                ++l1;
                double d7 = (double) yLen / 2.0D;

                for (int j3 = 0; j3 < yLen; ++j3) {
                    double d8 = 0.0D;
                    double d9 = ((double) j3 - d7) * 8.0D / d5;

                    if (d9 < 0.0D) {
                        d9 *= -1.0D;
                    }

                    double d10 = this.terrainNoise2[k1] / 512.0D;
                    double d11 = this.terrainNoise3[k1] / 512.0D;
                    double d12 = (this.terrainNoise1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d12 < 0.0D) {
                        d8 = d10;
                    } else if (d12 > 1.0D) {
                        d8 = d11;
                    } else {
                        d8 = d10 + (d11 - d10) * d12;
                    }

                    d8 -= 8.0D;
                    byte b0 = 32;
                    double d13;

                    if (j3 > yLen - b0) {
                        d13 = (double) ((float) (j3 - (yLen - b0)) / ((float) b0 - 1.0F));
                        d8 = d8 * (1.0D - d13) + -30.0D * d13;
                    }

                    b0 = 8;
                    if (j3 < b0) {
                        d13 = (double) ((float) (b0 - j3) / ((float) b0 - 1.0F));
                        d8 = d8 * (1.0D - d13) + -30.0D * d13;
                    }

                    noise[k1] = d8;
                    ++k1;
                }
            }
        }

        return noise;
    }

    @Override
    public void generateUnpopulatedChunkData(ChunkGenerator.ChunkData chunkData, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biome) {
        this.random.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
        this.biomeNoiseCache = this.worldChunkManager.getBiomeNoise(this.biomeNoiseCache, chunkX * 16, chunkZ * 16, 16, 16);
        for (int z = 0; z <= 15; ++z) {
            for (int x = 0; x <= 15; ++x) {
                BiomeBase173 b = this.biomeNoiseCache[z | (x << 4)];
                biome.setBiome(x, z, b.bukkitBiome);
            }
        }

        this.generateBareTerrain(chunkX, chunkZ, chunkData, this.biomeNoiseCache, this.worldChunkManager.temperature);
        this.generateBiomeTerrain(chunkX, chunkZ, chunkData, this.biomeNoiseCache);

        this.caveGenerator.generate(this.world, chunkX, chunkZ, chunkData);
    }

    @Override
    public void populateChunk(BlockAccess blockAccess, int chunkX, int chunkZ) {
        int k = chunkX * 16;
        int l = chunkZ * 16;
        BiomeBase173 biomebase = this.worldChunkManager.getBiome(k + 16, l + 16);

        this.random.setSeed(this.world.getSeed());
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;

        this.random.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.world.getSeed());
        double d0 = 0.25D;
        int k1;
        int l1;
        int i2;

        if (this.random.nextInt(4) == 0) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = this.random.nextInt(128);
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenLakes173(BlockConstants.SOURCE_WATER)).populate(blockAccess, this.random, k1, l1, i2);
        }

        if (this.random.nextInt(8) == 0) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = this.random.nextInt(this.random.nextInt(120) + 8);
            i2 = l + this.random.nextInt(16) + 8;
            if (l1 < 64 || this.random.nextInt(10) == 0) {
                (new WorldGenLakes173(BlockConstants.SOURCE_LAVA)).populate(blockAccess, this.random, k1, l1, i2);
            }
        }

        int j2;

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.random.nextInt(16) + 8;
            i2 = this.random.nextInt(128);
            j2 = l + this.random.nextInt(16) + 8;
            (new WorldGenDungeons173()).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 10; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(128);
            j2 = l + this.random.nextInt(16);
            (new WorldGenClay173(32)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(128);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.DIRT, 32)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 10; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(128);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.GRAVEL, 32)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(128);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.COAL_ORE, 16)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(64);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.IRON_ORE, 8)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 2; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(32);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.GOLD_ORE, 8)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(16);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.REDSTONE_ORE, 7)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 1; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(16);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.DIAMOND_ORE, 7)).populate(blockAccess, this.random, l1, i2, j2);
        }

        for (k1 = 0; k1 < 1; ++k1) {
            l1 = k + this.random.nextInt(16);
            i2 = this.random.nextInt(16) + this.random.nextInt(16);
            j2 = l + this.random.nextInt(16);
            (new WorldGenMinable173(BlockConstants.LAPIS_ORE, 6)).populate(blockAccess, this.random, l1, i2, j2);
        }

        d0 = 0.5D;
        k1 = (int) ((this.treeCountNoise.generateNoiseForCoordinate((double) k * d0, (double) l * d0) / 8.0D + this.random.nextDouble() * 4.0D + 4.0D) / 3.0D);
        l1 = 0;
        if (this.random.nextInt(10) == 0) {
            ++l1;
        }

        if (biomebase == BiomeBase173.FOREST) {
            l1 += k1 + 5;
        }

        if (biomebase == BiomeBase173.RAINFOREST) {
            l1 += k1 + 5;
        }

        if (biomebase == BiomeBase173.SEASONAL_FOREST) {
            l1 += k1 + 2;
        }

        if (biomebase == BiomeBase173.TAIGA) {
            l1 += k1 + 5;
        }

        if (biomebase == BiomeBase173.DESERT) {
            l1 -= 20;
        }

        if (biomebase == BiomeBase173.TUNDRA) {
            l1 -= 20;
        }

        if (biomebase == BiomeBase173.PLAINS) {
            l1 -= 20;
        }

        int k2;

        for (i2 = 0; i2 < l1; ++i2) {
            j2 = k + this.random.nextInt(16) + 8;
            k2 = l + this.random.nextInt(16) + 8;
            WorldGenerator173 worldgenerator = biomebase.getTreeGenerator(this.random);

            worldgenerator.scale(1.0D, 1.0D, 1.0D);
            worldgenerator.populate(blockAccess, this.random, j2, LegacyUtil173.getHighestBlockYAt(this.world, j2, k2), k2);
        }

        int l2;

        for (i2 = 0; i2 < 2; ++i2) {
            j2 = k + this.random.nextInt(16) + 8;
            k2 = this.random.nextInt(128);
            l2 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers173(BlockConstants.DANDELION)).populate(blockAccess, this.random, j2, k2, l2);
        }

        if (this.random.nextInt(2) == 0) {
            i2 = k + this.random.nextInt(16) + 8;
            j2 = this.random.nextInt(128);
            k2 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers173(BlockConstants.POPPY)).populate(blockAccess, this.random, i2, j2, k2);
        }

        if (this.random.nextInt(4) == 0) {
            i2 = k + this.random.nextInt(16) + 8;
            j2 = this.random.nextInt(128);
            k2 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers173(BlockConstants.BROWN_MUSHROOM)).populate(blockAccess, this.random, i2, j2, k2);
        }

        if (this.random.nextInt(8) == 0) {
            i2 = k + this.random.nextInt(16) + 8;
            j2 = this.random.nextInt(128);
            k2 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers173(BlockConstants.RED_MUSHROOM)).populate(blockAccess, this.random, i2, j2, k2);
        }

        for (i2 = 0; i2 < 10; ++i2) {
            j2 = k + this.random.nextInt(16) + 8;
            k2 = this.random.nextInt(128);
            l2 = l + this.random.nextInt(16) + 8;
            (new WorldGenReed173()).populate(blockAccess, this.random, j2, k2, l2);
        }

        if (this.random.nextInt(32) == 0) {
            i2 = k + this.random.nextInt(16) + 8;
            j2 = this.random.nextInt(128);
            k2 = l + this.random.nextInt(16) + 8;
            (new WorldGenPumpkin173()).populate(blockAccess, this.random, i2, j2, k2);
        }

        i2 = 0;
        if (biomebase == BiomeBase173.DESERT) {
            i2 += 10;
        }

        int i3;

        for (j2 = 0; j2 < i2; ++j2) {
            k2 = k + this.random.nextInt(16) + 8;
            l2 = this.random.nextInt(128);
            i3 = l + this.random.nextInt(16) + 8;
            (new WorldGenCactus173()).populate(blockAccess, this.random, k2, l2, i3);
        }

        for (j2 = 0; j2 < 50; ++j2) {
            k2 = k + this.random.nextInt(16) + 8;
            l2 = this.random.nextInt(this.random.nextInt(120) + 8);
            i3 = l + this.random.nextInt(16) + 8;
            (new WorldGenLiquids173(BlockConstants.SOURCE_WATER)).populate(blockAccess, this.random, k2, l2, i3);
        }

        for (j2 = 0; j2 < 20; ++j2) {
            k2 = k + this.random.nextInt(16) + 8;
            l2 = this.random.nextInt(this.random.nextInt(this.random.nextInt(112) + 8) + 8);
            i3 = l + this.random.nextInt(16) + 8;
            (new WorldGenLiquids173(BlockConstants.SOURCE_LAVA)).populate(blockAccess, this.random, k2, l2, i3);
        }

        this.snowNoise = this.worldChunkManager.createNoise(this.snowNoise, k + 8, l + 8, 16, 16);

        for (j2 = k + 8; j2 < k + 8 + 16; ++j2) {
            for (k2 = l + 8; k2 < l + 8 + 16; ++k2) {
                l2 = j2 - (k + 8);
                i3 = k2 - (l + 8);
                int j3 = this.world.getHighestBlockYAt(j2, k2, HeightMap.MOTION_BLOCKING) + 1; // TODO - make sure spigot doesn't break this again // TODO make sure this is the heightmap we want on update
                double d1 = this.snowNoise[l2 * 16 + i3] - (double) (j3 - 64) / 64.0D * 0.3D;

                Material below;

                if (d1 < 0.5D && j3 > 0 && j3 < 128 && blockAccess.getType(j2, j3, k2).isAir() && LegacyUtil173.Material_isSolid((below = blockAccess.getType(j2, j3 - 1, k2))) && below != Material.ICE) {
                    blockAccess.setBlockData(j2, j3, k2, BlockConstants.SNOW, false);
                    if (below == Material.GRASS_BLOCK) {
                        blockAccess.setBlockData(j2, j3 - 1, k2, BlockConstants.SNOWY_GRASS_BLOCK, false);
                    }
                }
            }
        }
    }
}
