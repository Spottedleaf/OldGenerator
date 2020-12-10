package ca.spottedleaf.oldgenerator.generator.b173.nether;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.generator.b173.MapGenBase173;
import ca.spottedleaf.oldgenerator.generator.b173.MathHelper173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import java.util.Random;

public class MapGenCavesHell173 extends MapGenBase173 {

    public MapGenCavesHell173() {}

    protected void a(int i, int j, ChunkGenerator.ChunkData chunkData, double d0, double d1, double d2) {
        this.a(i, j, chunkData, d0, d1, d2, 1.0F + this.random.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void a(int i, int j, ChunkGenerator.ChunkData chunkData, double d0, double d1, double d2, float f, float f1, float f2, int k, int l, double d3) {
        double d4 = (double) (i * 16 + 8);
        double d5 = (double) (j * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(this.random.nextLong());

        if (l <= 0) {
            int i1 = this.offset * 16 - 16;

            l = i1 - random.nextInt(i1 / 4);
        }

        boolean flag = false;

        if (k == -1) {
            k = l / 2;
            flag = true;
        }

        int j1 = random.nextInt(l / 2) + l / 4;

        for (boolean flag1 = random.nextInt(6) == 0; k < l; ++k) {
            double d6 = 1.5D + (double) (MathHelper173.sin((float) k * 3.1415927F / (float) l) * f * 1.0F);
            double d7 = d6 * d3;
            float f5 = MathHelper173.cos(f2);
            float f6 = MathHelper173.sin(f2);

            d0 += (double) (MathHelper173.cos(f1) * f5);
            d1 += (double) f6;
            d2 += (double) (MathHelper173.sin(f1) * f5);
            if (flag1) {
                f2 *= 0.92F;
            } else {
                f2 *= 0.7F;
            }

            f2 += f4 * 0.1F;
            f1 += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (!flag && k == j1 && f > 1.0F) {
                this.a(i, j, chunkData, d0, d1, d2, random.nextFloat() * 0.5F + 0.5F, f1 - 1.5707964F, f2 / 3.0F, k, l, 1.0D);
                this.a(i, j, chunkData, d0, d1, d2, random.nextFloat() * 0.5F + 0.5F, f1 + 1.5707964F, f2 / 3.0F, k, l, 1.0D);
                return;
            }

            if (flag || random.nextInt(4) != 0) {
                double d8 = d0 - d4;
                double d9 = d2 - d5;
                double d10 = (double) (l - k);
                double d11 = (double) (f + 2.0F + 16.0F);

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
                    return;
                }

                if (d0 >= d4 - 16.0D - d6 * 2.0D && d2 >= d5 - 16.0D - d6 * 2.0D && d0 <= d4 + 16.0D + d6 * 2.0D && d2 <= d5 + 16.0D + d6 * 2.0D) {
                    int k1 = MathHelper173.floor(d0 - d6) - i * 16 - 1;
                    int l1 = MathHelper173.floor(d0 + d6) - i * 16 + 1;
                    int i2 = MathHelper173.floor(d1 - d7) - 1;
                    int j2 = MathHelper173.floor(d1 + d7) + 1;
                    int k2 = MathHelper173.floor(d2 - d6) - j * 16 - 1;
                    int l2 = MathHelper173.floor(d2 + d6) - j * 16 + 1;

                    if (k1 < 0) {
                        k1 = 0;
                    }

                    if (l1 > 16) {
                        l1 = 16;
                    }

                    if (i2 < 1) {
                        i2 = 1;
                    }

                    if (j2 > 120) {
                        j2 = 120;
                    }

                    if (k2 < 0) {
                        k2 = 0;
                    }

                    if (l2 > 16) {
                        l2 = 16;
                    }

                    boolean flag2 = false;

                    int i3;
                    int j3;

                    for (j3 = k1; !flag2 && j3 < l1; ++j3) {
                        for (int k3 = k2; !flag2 && k3 < l2; ++k3) {
                            for (int l3 = j2 + 1; !flag2 && l3 >= i2 - 1; --l3) {
                                i3 = (j3 * 16 + k3) * 128 + l3;
                                if (l3 >= 0 && l3 < 128) {
                                    if (LegacyUtil173.getType(chunkData, i3) == Material.LAVA) {
                                        flag2 = true;
                                    }

                                    if (l3 != i2 - 1 && j3 != k1 && j3 != l1 - 1 && k3 != k2 && k3 != l2 - 1) {
                                        l3 = i2;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag2) {
                        for (j3 = k1; j3 < l1; ++j3) {
                            double d12 = ((double) (j3 + i * 16) + 0.5D - d0) / d6;

                            for (i3 = k2; i3 < l2; ++i3) {
                                double d13 = ((double) (i3 + j * 16) + 0.5D - d2) / d6;
                                int i4 = (j3 * 16 + i3) * 128 + j2;

                                for (int j4 = j2 - 1; j4 >= i2; --j4) {
                                    double d14 = ((double) j4 + 0.5D - d1) / d7;

                                    if (d14 > -0.7D && d12 * d12 + d14 * d14 + d13 * d13 < 1.0D) {
                                        Material b0 = LegacyUtil173.getType(chunkData, i4);

                                        if (b0 == Material.NETHERRACK || b0 == Material.DIRT || b0 == Material.GRASS_BLOCK) {
                                            LegacyUtil173.setBlockData(chunkData, i4, BlockConstants.AIR);
                                        }
                                    }

                                    --i4;
                                }
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void generate(World world, int i, int j, int k, int l, ChunkGenerator.ChunkData chunkData) {
        int i1 = this.random.nextInt(this.random.nextInt(this.random.nextInt(10) + 1) + 1);

        if (this.random.nextInt(5) != 0) {
            i1 = 0;
        }

        for (int j1 = 0; j1 < i1; ++j1) {
            double d0 = (double) (i * 16 + this.random.nextInt(16));
            double d1 = (double) this.random.nextInt(128);
            double d2 = (double) (j * 16 + this.random.nextInt(16));
            int k1 = 1;

            if (this.random.nextInt(4) == 0) {
                this.a(k, l, chunkData, d0, d1, d2);
                k1 += this.random.nextInt(4);
            }

            for (int l1 = 0; l1 < k1; ++l1) {
                float f = this.random.nextFloat() * 3.1415927F * 2.0F;
                float f1 = (this.random.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.random.nextFloat() * 2.0F + this.random.nextFloat();

                this.a(k, l, chunkData, d0, d1, d2, f2 * 2.0F, f, f1, 0, 0, 0.5D);
            }
        }
    }
}
