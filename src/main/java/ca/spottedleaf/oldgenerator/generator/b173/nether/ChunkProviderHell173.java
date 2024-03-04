package ca.spottedleaf.oldgenerator.generator.b173.nether;

import ca.spottedleaf.oldgenerator.generator.b173.BiomeBase173;
import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.generator.b173.MapGenBase173;
import ca.spottedleaf.oldgenerator.generator.b173.OldChunkGenerator;
import ca.spottedleaf.oldgenerator.generator.b173.noise.NoiseGeneratorOctaves173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenFire173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenFlowers173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenHellLava173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenLightStone1173;
import ca.spottedleaf.oldgenerator.generator.b173.populator.WorldGenLightStone2173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;
import java.util.Random;

public class ChunkProviderHell173 implements OldChunkGenerator {

    private final Random random;
    private final NoiseGeneratorOctaves173 terrainNoise2Generator;
    private final NoiseGeneratorOctaves173 terrainNoise3Generator;
    private final NoiseGeneratorOctaves173 terrainNoise1Generator;
    private final NoiseGeneratorOctaves173 sandAndGravelNoiseGenerator;
    private final NoiseGeneratorOctaves173 heightNoiseGenerator;
    private final NoiseGeneratorOctaves173 terrainNoise4Generator;
    private final NoiseGeneratorOctaves173 terrainNoise5Generator;

    private final World world;
    private final WorldChunkManagerHell173 worldChunkManager;

    private double[] terrainNoise;
    private double[] soulSandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] heightNoise = new double[256];
    private double[] terrainNoise1;
    private double[] terrainNoise2;
    private double[] terrainNoise3;
    private double[] terrainNoise4;
    private double[] terrainNoise5;

    private final MapGenBase173 caveGenerator = new MapGenCavesHell173();

    public ChunkProviderHell173(World world, long seed) {
        this.world = world;
        this.worldChunkManager = new WorldChunkManagerHell173(BiomeBase173.HELL, 1.0D, 0.0D);
        this.random = new Random(seed);
        this.terrainNoise2Generator = new NoiseGeneratorOctaves173(this.random, 16);
        this.terrainNoise3Generator = new NoiseGeneratorOctaves173(this.random, 16);
        this.terrainNoise1Generator = new NoiseGeneratorOctaves173(this.random, 8);
        this.sandAndGravelNoiseGenerator = new NoiseGeneratorOctaves173(this.random, 4);
        this.heightNoiseGenerator = new NoiseGeneratorOctaves173(this.random, 4);
        this.terrainNoise4Generator = new NoiseGeneratorOctaves173(this.random, 10);
        this.terrainNoise5Generator = new NoiseGeneratorOctaves173(this.random, 16);
    }

    public void generateBareTerrain(int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
        byte b0 = 4;
        byte b1 = 32;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;

        this.terrainNoise = this.generateTerrainNoise(this.terrainNoise, chunkX * b0, 0, chunkZ * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    double d0 = 0.125D;
                    double d1 = this.terrainNoise[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.terrainNoise[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.terrainNoise[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.terrainNoise[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.terrainNoise[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.terrainNoise[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.terrainNoise[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.terrainNoise[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            short short1 = 128;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 4; ++k2) {
                                BlockData l2 = BlockConstants.AIR;

                                if (k1 * 8 + l1 < b1) {
                                    l2 = BlockConstants.SOURCE_LAVA;
                                }

                                if (d15 > 0.0D) {
                                    l2 = BlockConstants.NETHERRACK;
                                }

                                LegacyUtil173.setBlockData(chunkData, j2, l2);
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

    public void generateBiomeTerrain(int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
        byte b0 = 64;
        double d0 = 0.03125D;

        this.soulSandNoise = this.sandAndGravelNoiseGenerator.generateNoise(this.soulSandNoise, (double) (chunkX * 16), (double) (chunkZ * 16), 0.0D, 16, 16, 1, d0, d0, 1.0D);
        this.gravelNoise = this.sandAndGravelNoiseGenerator.generateNoise(this.gravelNoise, (double) (chunkX * 16), 109.0134D, (double) (chunkZ * 16), 16, 1, 16, d0, 1.0D, d0);
        this.heightNoise = this.heightNoiseGenerator.generateNoise(this.heightNoise, (double) (chunkX * 16), (double) (chunkZ * 16), 0.0D, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                boolean flag = this.soulSandNoise[k + l * 16] + this.random.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[k + l * 16] + this.random.nextDouble() * 0.2D > 0.0D;
                int i1 = (int) (this.heightNoise[k + l * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
                int j1 = -1;
                BlockData b1 = BlockConstants.NETHERRACK;
                BlockData b2 = BlockConstants.NETHERRACK;

                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = (l * 16 + k) * 128 + k1;

                    if (k1 >= 127 - this.random.nextInt(5)) {
                        LegacyUtil173.setBlockData(chunkData, l1, BlockConstants.BEDROCK);
                    } else if (k1 <= 0 + this.random.nextInt(5)) {
                        LegacyUtil173.setBlockData(chunkData, l1, BlockConstants.BEDROCK);
                    } else {
                        Material b3 = LegacyUtil173.getType(chunkData, l1);;

                        if (BlockConstants.isAir(b3)) {
                            j1 = -1;
                        } else if (b3 == Material.NETHERRACK) {
                            if (j1 == -1) {
                                if (i1 <= 0) {
                                    b1 = BlockConstants.AIR;
                                    b2 = BlockConstants.NETHERRACK;
                                } else if (k1 >= b0 - 4 && k1 <= b0 + 1) {
                                    b1 = BlockConstants.NETHERRACK;
                                    b2 = BlockConstants.NETHERRACK;
                                    if (flag1) {
                                        b1 = BlockConstants.GRAVEL;
                                    }

                                    if (flag1) {
                                        b2 = BlockConstants.NETHERRACK;
                                    }

                                    if (flag) {
                                        b1 = BlockConstants.SOUL_SAND;
                                    }

                                    if (flag) {
                                        b2 = BlockConstants.SOUL_SAND;
                                    }
                                }

                                if (k1 < b0 && b1 == BlockConstants.AIR) {
                                    b1 = BlockConstants.SOURCE_LAVA;
                                }

                                j1 = i1;
                                if (k1 >= b0 - 1) {
                                    LegacyUtil173.setBlockData(chunkData, l1, b1);
                                } else {
                                    LegacyUtil173.setBlockData(chunkData, l1, b2);
                                }
                            } else if (j1 > 0) {
                                --j1;
                                LegacyUtil173.setBlockData(chunkData, l1, b2);
                            }
                        }
                    }
                }
            }
        }
    }

    private double[] generateTerrainNoise(double[] into, int startX, int startY, int startZ, int lenX, int lenY, int lenZ) {
        if (into == null) {
            into = new double[lenX * lenY * lenZ];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;

        this.terrainNoise4 = this.terrainNoise4Generator.generateNoise(this.terrainNoise4, (double) startX, (double) startY, (double) startZ, lenX, 1, lenZ, 1.0D, 0.0D, 1.0D);
        this.terrainNoise5 = this.terrainNoise5Generator.generateNoise(this.terrainNoise5, (double) startX, (double) startY, (double) startZ, lenX, 1, lenZ, 100.0D, 0.0D, 100.0D);
        this.terrainNoise1 = this.terrainNoise1Generator.generateNoise(this.terrainNoise1, (double) startX, (double) startY, (double) startZ, lenX, lenY, lenZ, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.terrainNoise2 = this.terrainNoise2Generator.generateNoise(this.terrainNoise2, (double) startX, (double) startY, (double) startZ, lenX, lenY, lenZ, d0, d1, d0);
        this.terrainNoise3 = this.terrainNoise3Generator.generateNoise(this.terrainNoise3, (double) startX, (double) startY, (double) startZ, lenX, lenY, lenZ, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[lenY];

        int i2;

        for (i2 = 0; i2 < lenY; ++i2) {
            adouble1[i2] = Math.cos((double) i2 * 3.141592653589793D * 6.0D / (double) lenY) * 2.0D;
            double d2 = (double) i2;

            if (i2 > lenY / 2) {
                d2 = (double) (lenY - 1 - i2);
            }

            if (d2 < 4.0D) {
                d2 = 4.0D - d2;
                adouble1[i2] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (i2 = 0; i2 < lenX; ++i2) {
            for (int j2 = 0; j2 < lenZ; ++j2) {
                double d3 = (this.terrainNoise4[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.terrainNoise5[l1] / 8000.0D;

                if (d5 < 0.0D) {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;
                if (d5 < 0.0D) {
                    d5 /= 2.0D;
                    if (d5 < -1.0D) {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                } else {
                    if (d5 > 1.0D) {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * (double) lenY / 16.0D;
                ++l1;

                for (int k2 = 0; k2 < lenY; ++k2) {
                    double d6 = 0.0D;
                    double d7 = adouble1[k2];
                    double d8 = this.terrainNoise2[k1] / 512.0D;
                    double d9 = this.terrainNoise3[k1] / 512.0D;
                    double d10 = (this.terrainNoise1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D) {
                        d6 = d8;
                    } else if (d10 > 1.0D) {
                        d6 = d9;
                    } else {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;
                    double d11;

                    if (k2 > lenY - 4) {
                        d11 = (double) ((float) (k2 - (lenY - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    if ((double) k2 < d4) {
                        d11 = (d4 - (double) k2) / 4.0D;
                        if (d11 < 0.0D) {
                            d11 = 0.0D;
                        }

                        if (d11 > 1.0D) {
                            d11 = 1.0D;
                        }

                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    into[k1] = d6;
                    ++k1;
                }
            }
        }

        return into;
    }

    @Override
    public void generateUnpopulatedChunkData(ChunkGenerator.ChunkData chunkData, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biome) {
        this.random.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);

        this.generateBareTerrain(chunkX, chunkZ, chunkData);
        this.generateBiomeTerrain(chunkX, chunkZ, chunkData);
        this.caveGenerator.generate(this.world, chunkX, chunkZ, chunkData);

        for (int z = 0; z <= 15; ++z) {
            for (int x = 0; x <= 15; ++x) {
                biome.setBiome(x, z, Biome.NETHER_WASTES);
            }
        }
    }

    @Override
    public void populateChunk(BlockAccess blockAccess, int chunkX, int chunkZ) {
        int k = chunkX * 16;
        int l = chunkZ * 16;

        int i1;
        int j1;
        int k1;
        int l1;

        // Note: Beta 173 nether generation never actually sets the random seed when generating!
        // This code is added by us so that nether generation is consistent between runs.
        // We can never truly replicate populator randomness thanks to this bug, but at least we can
        // make it consistent now.
        this.random.setSeed(this.world.getSeed());
        this.random.setSeed((long) chunkX * (this.random.nextLong() / 2L * 2L + 1L) + (long) chunkZ * (this.random.nextLong() / 2L * 2L + 1L) ^ this.world.getSeed());

        for (i1 = 0; i1 < 8; ++i1) {
            j1 = k + this.random.nextInt(16) + 8;
            k1 = (this.random.nextInt(120 - blockAccess.getMinHeight()) + blockAccess.getMinHeight()) + 4;
            l1 = l + this.random.nextInt(16) + 8;
            (new WorldGenHellLava173(BlockConstants.SOURCE_LAVA)).populate(blockAccess, this.random, j1, k1, l1);
        }

        i1 = this.random.nextInt(this.random.nextInt(10) + 1) + 1;

        int i2;

        for (j1 = 0; j1 < i1; ++j1) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = (this.random.nextInt(120 - blockAccess.getMinHeight()) + blockAccess.getMinHeight()) + 4;
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenFire173()).populate(blockAccess, this.random, k1, l1, i2);
        }

        i1 = this.random.nextInt(this.random.nextInt(10) + 1);

        for (j1 = 0; j1 < i1; ++j1) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = (this.random.nextInt(120 - blockAccess.getMinHeight()) + blockAccess.getMinHeight()) + 4;
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenLightStone2173()).populate(blockAccess, this.random, k1, l1, i2);
        }

        for (j1 = 0; j1 < 10; ++j1) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = this.random.nextInt(blockAccess.getMaxHeight() + 1 - blockAccess.getMinHeight()) + blockAccess.getMinHeight();
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenLightStone1173()).populate(blockAccess, this.random, k1, l1, i2);
        }

        if (this.random.nextInt(1) == 0) {
            j1 = k + this.random.nextInt(16) + 8;
            k1 = this.random.nextInt(blockAccess.getMaxHeight() + 1 - blockAccess.getMinHeight()) + blockAccess.getMinHeight();
            l1 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers173(BlockConstants.BROWN_MUSHROOM)).populate(blockAccess, this.random, j1, k1, l1);
        }

        if (this.random.nextInt(1) == 0) {
            j1 = k + this.random.nextInt(16) + 8;
            k1 = this.random.nextInt(blockAccess.getMaxHeight() + 1 - blockAccess.getMinHeight()) + blockAccess.getMinHeight();
            l1 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers173(BlockConstants.RED_MUSHROOM)).populate(blockAccess, this.random, j1, k1, l1);
        }
    }
}
