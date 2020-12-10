package ca.spottedleaf.oldgenerator.generator.b173.noise;

import java.util.Random;

public class NoiseGeneratorPerlin173 {
    private final int[] d;
    public double a;
    public double b;
    public double c;

    public NoiseGeneratorPerlin173() {
        this(new Random());
    }

    public NoiseGeneratorPerlin173(Random var1) {
        this.d = new int[512];
        this.a = var1.nextDouble() * 256.0D;
        this.b = var1.nextDouble() * 256.0D;
        this.c = var1.nextDouble() * 256.0D;

        int var2;
        for(var2 = 0; var2 < 256; this.d[var2] = var2++) {
        }

        for(var2 = 0; var2 < 256; ++var2) {
            int var3 = var1.nextInt(256 - var2) + var2;
            int var4 = this.d[var2];
            this.d[var2] = this.d[var3];
            this.d[var3] = var4;
            this.d[var2 + 256] = this.d[var2];
        }

    }

    public double a(double var1, double var3, double var5) {
        double var7 = var1 + this.a;
        double var9 = var3 + this.b;
        double var11 = var5 + this.c;
        int var13 = (int)var7;
        int var14 = (int)var9;
        int var15 = (int)var11;
        if (var7 < (double)var13) {
            --var13;
        }

        if (var9 < (double)var14) {
            --var14;
        }

        if (var11 < (double)var15) {
            --var15;
        }

        int var16 = var13 & 255;
        int var17 = var14 & 255;
        int var18 = var15 & 255;
        var7 -= (double)var13;
        var9 -= (double)var14;
        var11 -= (double)var15;
        double var19 = var7 * var7 * var7 * (var7 * (var7 * 6.0D - 15.0D) + 10.0D);
        double var21 = var9 * var9 * var9 * (var9 * (var9 * 6.0D - 15.0D) + 10.0D);
        double var23 = var11 * var11 * var11 * (var11 * (var11 * 6.0D - 15.0D) + 10.0D);
        int var25 = this.d[var16] + var17;
        int var26 = this.d[var25] + var18;
        int var27 = this.d[var25 + 1] + var18;
        int var28 = this.d[var16 + 1] + var17;
        int var29 = this.d[var28] + var18;
        int var30 = this.d[var28 + 1] + var18;
        return this.b(var23, this.b(var21, this.b(var19, this.a(this.d[var26], var7, var9, var11), this.a(this.d[var29], var7 - 1.0D, var9, var11)), this.b(var19, this.a(this.d[var27], var7, var9 - 1.0D, var11), this.a(this.d[var30], var7 - 1.0D, var9 - 1.0D, var11))), this.b(var21, this.b(var19, this.a(this.d[var26 + 1], var7, var9, var11 - 1.0D), this.a(this.d[var29 + 1], var7 - 1.0D, var9, var11 - 1.0D)), this.b(var19, this.a(this.d[var27 + 1], var7, var9 - 1.0D, var11 - 1.0D), this.a(this.d[var30 + 1], var7 - 1.0D, var9 - 1.0D, var11 - 1.0D))));
    }

    public final double b(double var1, double var3, double var5) {
        return var3 + var1 * (var5 - var3);
    }

    public final double a(int var1, double var2, double var4) {
        int var6 = var1 & 15;
        double var7 = (double)(1 - ((var6 & 8) >> 3)) * var2;
        double var9 = var6 < 4 ? 0.0D : (var6 != 12 && var6 != 14 ? var4 : var2);
        return ((var6 & 1) == 0 ? var7 : -var7) + ((var6 & 2) == 0 ? var9 : -var9);
    }

    public final double a(int var1, double var2, double var4, double var6) {
        int var8 = var1 & 15;
        double var9 = var8 < 8 ? var2 : var4;
        double var11 = var8 < 4 ? var4 : (var8 != 12 && var8 != 14 ? var6 : var2);
        return ((var8 & 1) == 0 ? var9 : -var9) + ((var8 & 2) == 0 ? var11 : -var11);
    }

    public double a(double var1, double var3) {
        return this.a(var1, var3, 0.0D);
    }

    public void a(double[] var1, double var2, double var4, double var6, int var8, int var9, int var10, double var11, double var13, double var15, double var17) {
        int var19;
        int var20;
        double var21;
        double var23;
        double var25;
        int var27;
        double var28;
        int var30;
        int var31;
        int var32;
        int var33;
        boolean var36;
        boolean var37;
        double var42;
        int var46;
        if (var9 == 1) {
            boolean var34 = false;
            boolean var35 = false;
            var36 = false;
            var37 = false;
            double var38 = 0.0D;
            double var40 = 0.0D;
            var33 = 0;
            var42 = 1.0D / var17;

            for(int var44 = 0; var44 < var8; ++var44) {
                var21 = (var2 + (double)var44) * var11 + this.a;
                int var45 = (int)var21;
                if (var21 < (double)var45) {
                    --var45;
                }

                var46 = var45 & 255;
                var21 -= (double)var45;
                var23 = var21 * var21 * var21 * (var21 * (var21 * 6.0D - 15.0D) + 10.0D);

                for(var27 = 0; var27 < var10; ++var27) {
                    var25 = (var6 + (double)var27) * var15 + this.c;
                    var30 = (int)var25;
                    if (var25 < (double)var30) {
                        --var30;
                    }

                    var31 = var30 & 255;
                    var25 -= (double)var30;
                    var28 = var25 * var25 * var25 * (var25 * (var25 * 6.0D - 15.0D) + 10.0D);
                    var19 = this.d[var46] + 0;
                    int var47 = this.d[var19] + var31;
                    int var48 = this.d[var46 + 1] + 0;
                    var20 = this.d[var48] + var31;
                    var38 = this.b(var23, this.a(this.d[var47], var21, var25), this.a(this.d[var20], var21 - 1.0D, 0.0D, var25));
                    var40 = this.b(var23, this.a(this.d[var47 + 1], var21, 0.0D, var25 - 1.0D), this.a(this.d[var20 + 1], var21 - 1.0D, 0.0D, var25 - 1.0D));
                    double var49 = this.b(var28, var38, var40);
                    var32 = var33++;
                    var1[var32] += var49 * var42;
                }
            }
        } else {
            var19 = 0;
            double var66 = 1.0D / var17;
            var20 = -1;
            var36 = false;
            var37 = false;
            boolean var67 = false;
            boolean var39 = false;
            boolean var68 = false;
            boolean var41 = false;
            var42 = 0.0D;
            var21 = 0.0D;
            double var69 = 0.0D;
            var23 = 0.0D;

            for(var27 = 0; var27 < var8; ++var27) {
                var25 = (var2 + (double)var27) * var11 + this.a;
                var30 = (int)var25;
                if (var25 < (double)var30) {
                    --var30;
                }

                var31 = var30 & 255;
                var25 -= (double)var30;
                var28 = var25 * var25 * var25 * (var25 * (var25 * 6.0D - 15.0D) + 10.0D);

                for(var46 = 0; var46 < var10; ++var46) {
                    double var70 = (var6 + (double)var46) * var15 + this.c;
                    int var71 = (int)var70;
                    if (var70 < (double)var71) {
                        --var71;
                    }

                    int var50 = var71 & 255;
                    var70 -= (double)var71;
                    double var51 = var70 * var70 * var70 * (var70 * (var70 * 6.0D - 15.0D) + 10.0D);

                    for(int var53 = 0; var53 < var9; ++var53) {
                        double var54 = (var4 + (double)var53) * var13 + this.b;
                        int var56 = (int)var54;
                        if (var54 < (double)var56) {
                            --var56;
                        }

                        int var57 = var56 & 255;
                        var54 -= (double)var56;
                        double var58 = var54 * var54 * var54 * (var54 * (var54 * 6.0D - 15.0D) + 10.0D);
                        if (var53 == 0 || var57 != var20) {
                            var20 = var57;
                            int var60 = this.d[var31] + var57;
                            int var61 = this.d[var60] + var50;
                            int var62 = this.d[var60 + 1] + var50;
                            int var63 = this.d[var31 + 1] + var57;
                            var33 = this.d[var63] + var50;
                            int var64 = this.d[var63 + 1] + var50;
                            var42 = this.b(var28, this.a(this.d[var61], var25, var54, var70), this.a(this.d[var33], var25 - 1.0D, var54, var70));
                            var21 = this.b(var28, this.a(this.d[var62], var25, var54 - 1.0D, var70), this.a(this.d[var64], var25 - 1.0D, var54 - 1.0D, var70));
                            var69 = this.b(var28, this.a(this.d[var61 + 1], var25, var54, var70 - 1.0D), this.a(this.d[var33 + 1], var25 - 1.0D, var54, var70 - 1.0D));
                            var23 = this.b(var28, this.a(this.d[var62 + 1], var25, var54 - 1.0D, var70 - 1.0D), this.a(this.d[var64 + 1], var25 - 1.0D, var54 - 1.0D, var70 - 1.0D));
                        }

                        double var72 = this.b(var58, var42, var21);
                        double var73 = this.b(var58, var69, var23);
                        double var74 = this.b(var51, var72, var73);
                        var32 = var19++;
                        var1[var32] += var74 * var66;
                    }
                }
            }
        }

    }
}
