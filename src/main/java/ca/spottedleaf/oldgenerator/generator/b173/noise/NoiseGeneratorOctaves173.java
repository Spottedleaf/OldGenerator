package ca.spottedleaf.oldgenerator.generator.b173.noise;

import java.util.Random;

public class NoiseGeneratorOctaves173 extends NoiseGenerator173 {

    private final NoiseGeneratorPerlin173[] noiseGenerators;
    private final int totalNoiseGenerators;

    public NoiseGeneratorOctaves173(Random var1, int var2) {
        this.totalNoiseGenerators = var2;
        this.noiseGenerators = new NoiseGeneratorPerlin173[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.noiseGenerators[var3] = new NoiseGeneratorPerlin173(var1);
        }

    }

    public double generateNoiseForCoordinate(double var1, double var3) {
        double var5 = 0.0D;
        double var7 = 1.0D;

        for(int var9 = 0; var9 < this.totalNoiseGenerators; ++var9) {
            var5 += this.noiseGenerators[var9].a(var1 * var7, var3 * var7) / var7;
            var7 /= 2.0D;
        }

        return var5;
    }

    public double[] generateNoise(double[] var1, double var2, double var4, double var6, int var8, int var9, int var10, double var11, double var13, double var15) {
        if (var1 == null) {
            var1 = new double[var8 * var9 * var10];
        } else {
            for(int var17 = 0; var17 < var1.length; ++var17) {
                var1[var17] = 0.0D;
            }
        }

        double var20 = 1.0D;

        for(int var19 = 0; var19 < this.totalNoiseGenerators; ++var19) {
            this.noiseGenerators[var19].a(var1, var2, var4, var6, var8, var9, var10, var11 * var20, var13 * var20, var15 * var20, var20);
            var20 /= 2.0D;
        }

        return var1;
    }

    public double[] generateNoise(double[] var1, int var2, int var3, int var4, int var5, double var6, double var8, double var10) {
        return this.generateNoise(var1, (double)var2, 10.0D, (double)var3, var4, 1, var5, var6, 1.0D, var8);
    }
}
