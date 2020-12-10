package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.generator.b173.LegacyUtil173;
import ca.spottedleaf.oldgenerator.util.BlockConstants;
import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

public class WorldGenDungeons173 extends WorldGenerator173 {

    public WorldGenDungeons173() {}

    @Override
    public boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ) {
        byte b0 = 3;
        int l = random.nextInt(2) + 2;
        int i1 = random.nextInt(2) + 2;
        int j1 = 0;

        int k1;
        int l1;
        int i2;

        for (k1 = centerX - l - 1; k1 <= centerX + l + 1; ++k1) {
            for (l1 = centerY - 1; l1 <= centerY + b0 + 1; ++l1) {
                for (i2 = centerZ - i1 - 1; i2 <= centerZ + i1 + 1; ++i2) {
                    Material material = world.getType(k1, l1, i2);

                    if (l1 == centerY - 1 && !LegacyUtil173.Material_isBuildable(material)) {
                        return false;
                    }

                    if (l1 == centerY + b0 + 1 && !LegacyUtil173.Material_isBuildable(material)) {
                        return false;
                    }

                    if ((k1 == centerX - l - 1 || k1 == centerX + l + 1 || i2 == centerZ - i1 - 1 || i2 == centerZ + i1 + 1) && l1 == centerY && BlockConstants.isAir(world.getType(k1, l1, i2)) && BlockConstants.isAir(world.getType(k1, l1 + 1, i2))) {
                        ++j1;
                    }
                }
            }
        }

        if (j1 >= 1 && j1 <= 5) {
            for (k1 = centerX - l - 1; k1 <= centerX + l + 1; ++k1) {
                for (l1 = centerY + b0; l1 >= centerY - 1; --l1) {
                    for (i2 = centerZ - i1 - 1; i2 <= centerZ + i1 + 1; ++i2) {
                        if (k1 != centerX - l - 1 && l1 != centerY - 1 && i2 != centerZ - i1 - 1 && k1 != centerX + l + 1 && l1 != centerY + b0 + 1 && i2 != centerZ + i1 + 1) {
                            world.setBlockData(k1, l1, i2, BlockConstants.AIR, false);
                        } else if (l1 >= 0 && !LegacyUtil173.Material_isBuildable(world.getType(k1, l1 - 1, i2))) {
                            world.setBlockData(k1, l1, i2, BlockConstants.AIR, false);
                        } else if (LegacyUtil173.Material_isBuildable(world.getType(k1, l1, i2))) {
                            if (l1 == centerY - 1 && random.nextInt(4) != 0) {
                                world.setBlockData(k1, l1, i2, BlockConstants.MOSSY_COBBLESTONE, false);
                            } else {
                                world.setBlockData(k1, l1, i2, BlockConstants.COBBLESTONE, false);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2) {
                l1 = 0;

                while (true) {
                    if (l1 < 3) {
                        label204: {
                            i2 = centerX + random.nextInt(l * 2 + 1) - l;
                            int j2 = centerZ + random.nextInt(i1 * 2 + 1) - i1;

                            if (BlockConstants.isAir(world.getType(i2, centerY, j2))) {
                                int k2 = 0;

                                if (LegacyUtil173.Material_isBuildable(world.getType(i2 - 1, centerY, j2))) {
                                    ++k2;
                                }

                                if (LegacyUtil173.Material_isBuildable(world.getType(i2 + 1, centerY, j2))) {
                                    ++k2;
                                }

                                if (LegacyUtil173.Material_isBuildable(world.getType(i2, centerY, j2 - 1))) {
                                    ++k2;
                                }

                                if (LegacyUtil173.Material_isBuildable(world.getType(i2, centerY, j2 + 1))) {
                                    ++k2;
                                }

                                if (k2 == 1) {
                                    world.setBlockData(i2, centerY, j2, BlockConstants.CHEST, false);
                                    Chest tileentitychest = (Chest)world.getBlockState(i2, centerY, j2);

                                    for (int l2 = 0; l2 < 8; ++l2) {
                                        ItemStack itemstack = this.getRandomItem(random);

                                        if (itemstack != null) {
                                            tileentitychest.getBlockInventory().setItem(random.nextInt(tileentitychest.getBlockInventory().getSize()), itemstack);
                                        }
                                    }
                                    break label204;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }

            world.setBlockData(centerX, centerY, centerZ, BlockConstants.SPAWNER, false);
            CreatureSpawner tileentitymobspawner = (CreatureSpawner)world.getBlockState(centerX, centerY, centerZ);

            tileentitymobspawner.setSpawnedType(this.getRandomSpawnerType(random));
            tileentitymobspawner.update(true, false);
            return true;
        } else {
            return false;
        }
    }

    private ItemStack getRandomItem(Random random) {
        int i = random.nextInt(11);

        return i == 0 ? new ItemStack(Material.SADDLE) : (i == 1 ? new ItemStack(Material.IRON_INGOT, random.nextInt(4) + 1) : (i == 2 ? new ItemStack(Material.BREAD) : (i == 3 ? new ItemStack(Material.WHEAT, random.nextInt(4) + 1) : (i == 4 ? new ItemStack(Material.GUNPOWDER, random.nextInt(4) + 1) : (i == 5 ? new ItemStack(Material.STRING, random.nextInt(4) + 1) : (i == 6 ? new ItemStack(Material.BUCKET) : (i == 7 && random.nextInt(100) == 0 ? new ItemStack(Material.GOLDEN_APPLE) : (i == 8 && random.nextInt(2) == 0 ? new ItemStack(Material.REDSTONE, random.nextInt(4) + 1) : (i == 9 && random.nextInt(10) == 0 ? /*new ItemStack(Item.byId[Item.GOLD_RECORD.id + random.nextInt(2)])*/ new ItemStack(random.nextInt(2) == 0 ? Material.MUSIC_DISC_13 : Material.MUSIC_DISC_CAT) : (i == 10 ? new ItemStack(Material.COCOA_BEANS) : null))))))))));
    }

    private EntityType getRandomSpawnerType(Random random) {
        switch (random.nextInt(4)) {
            case 0:
                return EntityType.SKELETON;
            case 1:
            case 2:
                return EntityType.ZOMBIE;
            case 3:
                return EntityType.SPIDER;
            default:
                throw new IllegalStateException("Shouldn't happen");
        }
    }
}
