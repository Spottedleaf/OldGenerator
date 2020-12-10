package ca.spottedleaf.oldgenerator.generator.b173.populator;

import ca.spottedleaf.oldgenerator.world.BlockAccess;
import java.util.Random;

public abstract class WorldGenerator173 {

    public WorldGenerator173() {}

    public abstract boolean populate(BlockAccess world, Random random, int centerX, int centerY, int centerZ);

    public void scale(double scaleX, double scaleY, double scaleZ) {}

}
