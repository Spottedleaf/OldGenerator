package ca.spottedleaf.oldgenerator.generator.b173.nether;

import ca.spottedleaf.oldgenerator.generator.b173.BiomeBase173;
import ca.spottedleaf.oldgenerator.generator.b173.WorldChunkManager173;
import java.util.Arrays;

public class WorldChunkManagerHell173 extends WorldChunkManager173 {

    private final BiomeBase173 hellBiome;
    private final double f;
    private final double g;

    public WorldChunkManagerHell173(BiomeBase173 biomebase, double d0, double d1) {
        this.hellBiome = biomebase;
        this.f = d0;
        this.g = d1;
    }

    @Override
    public BiomeBase173 getBiome(int blockX, int blockZ) {
        return this.hellBiome;
    }

    @Override
    public BiomeBase173[] getBiomeData(int i, int j, int k, int l) {
        this.d = this.getBiomeNoise(this.d, i, j, k, l);
        return this.d;
    }

    @Override
    public double[] createNoise(double[] into, int startX, int startZ, int sizeX, int sizeZ) {
        if (into == null || into.length < sizeX * sizeZ) {
            into = new double[sizeX * sizeZ];
        }

        Arrays.fill(into, 0, sizeX * sizeZ, this.f);
        return into;
    }

    @Override
    public BiomeBase173[] getBiomeNoise(BiomeBase173[] into, int startX, int startZ, int sizeX, int sizeZ) {
        if (into == null || into.length < sizeX * sizeZ) {
            into = new BiomeBase173[sizeX * sizeZ];
        }

        if (this.temperature == null || this.temperature.length < sizeX * sizeZ) {
            this.temperature = new double[sizeX * sizeZ];
            this.rain = new double[sizeX * sizeZ];
        }

        Arrays.fill(into, 0, sizeX * sizeZ, this.hellBiome);
        Arrays.fill(this.rain, 0, sizeX * sizeZ, this.g);
        Arrays.fill(this.temperature, 0, sizeX * sizeZ, this.f);
        return into;
    }

}
