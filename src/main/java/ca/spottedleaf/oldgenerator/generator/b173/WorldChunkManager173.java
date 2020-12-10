package ca.spottedleaf.oldgenerator.generator.b173;

import ca.spottedleaf.oldgenerator.generator.b173.noise.NoiseGeneratorOctaves2173;
import org.bukkit.World;
import java.util.Random;

public class WorldChunkManager173 {
    private NoiseGeneratorOctaves2173 e;
    private NoiseGeneratorOctaves2173 f;
    private NoiseGeneratorOctaves2173 g;
    public double[] temperature;
    public double[] rain;
    public double[] c;
    public BiomeBase173[] d;

    protected WorldChunkManager173() {}

    public WorldChunkManager173(World world) {
        this.e = new NoiseGeneratorOctaves2173(new Random(world.getSeed() * 9871L), 4);
        this.f = new NoiseGeneratorOctaves2173(new Random(world.getSeed() * 39811L), 4);
        this.g = new NoiseGeneratorOctaves2173(new Random(world.getSeed() * 543321L), 2);
    }

    public BiomeBase173 getBiome(int blockX, int blockZ) {
        return this.getBiomeData(blockX, blockZ, 1, 1)[0];
    }

    public BiomeBase173[] getBiomeData(int i, int j, int k, int l) {
        this.d = this.getBiomeNoise(this.d, i, j, k, l);
        return this.d;
    }

    public double[] createNoise(double[] into, int startX, int startZ, int sizeX, int sizeZ) {
        if (into == null || into.length < sizeX * sizeZ) {
            into = new double[sizeX * sizeZ];
        }

        into = this.e.a(into, (double) startX, (double) startZ, sizeX, sizeZ, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        this.c = this.g.a(this.c, (double) startX, (double) startZ, sizeX, sizeZ, 0.25D, 0.25D, 0.5882352941176471D);
        int i1 = 0;

        for (int j1 = 0; j1 < sizeX; ++j1) {
            for (int k1 = 0; k1 < sizeZ; ++k1) {
                double d0 = this.c[i1] * 1.1D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (into[i1] * 0.15D + 0.7D) * d2 + d0 * d1;

                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if (d3 < 0.0D) {
                    d3 = 0.0D;
                }

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                into[i1] = d3;
                ++i1;
            }
        }

        return into;
    }

    public BiomeBase173[] getBiomeNoise(BiomeBase173[] into, int startX, int startZ, int sizeX, int sizeZ) {
        if (into == null || into.length < sizeX * sizeZ) {
            into = new BiomeBase173[sizeX * sizeZ];
        }

        this.temperature = this.e.a(this.temperature, (double) startX, (double) startZ, sizeX, sizeX, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        this.rain = this.f.a(this.rain, (double) startX, (double) startZ, sizeX, sizeX, 0.05000000074505806D, 0.05000000074505806D, 0.3333333333333333D);
        this.c = this.g.a(this.c, (double) startX, (double) startZ, sizeX, sizeX, 0.25D, 0.25D, 0.5882352941176471D);
        int i1 = 0;

        for (int j1 = 0; j1 < sizeX; ++j1) {
            for (int k1 = 0; k1 < sizeZ; ++k1) {
                double d0 = this.c[i1] * 1.1D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (this.temperature[i1] * 0.15D + 0.7D) * d2 + d0 * d1;

                d1 = 0.0020D;
                d2 = 1.0D - d1;
                double d4 = (this.rain[i1] * 0.15D + 0.5D) * d2 + d0 * d1;

                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if (d3 < 0.0D) {
                    d3 = 0.0D;
                }

                if (d4 < 0.0D) {
                    d4 = 0.0D;
                }

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                if (d4 > 1.0D) {
                    d4 = 1.0D;
                }

                this.temperature[i1] = d3;
                this.rain[i1] = d4;
                into[i1++] = BiomeBase173.a(d3, d4);
            }
        }

        return into;
    }
}
