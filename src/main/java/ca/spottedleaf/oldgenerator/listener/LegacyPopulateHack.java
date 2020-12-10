package ca.spottedleaf.oldgenerator.listener;

import ca.spottedleaf.oldgenerator.OldGenerator;
import ca.spottedleaf.oldgenerator.generator.b173.Beta173ChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class LegacyPopulateHack implements Listener {

    private static final PersistentDataType<Byte, Boolean> BOOLEAN_TYPE = new PersistentDataType<Byte, Boolean>() {
        private final Byte ZERO = Byte.valueOf((byte)0);
        private final Byte ONE = Byte.valueOf((byte)1);

        @Override
        public Class<Byte> getPrimitiveType() {
            return Byte.class;
        }

        @Override
        public Class<Boolean> getComplexType() {
            return Boolean.class;
        }

        @Override
        public Byte toPrimitive(final Boolean complex, final PersistentDataAdapterContext context) {
            return complex.booleanValue() ? ONE : ZERO;
        }

        @Override
        public Boolean fromPrimitive(final Byte primitive, final PersistentDataAdapterContext context) {
            return primitive.byteValue() == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
    };

    private final NamespacedKey POPULATED_KEY = new NamespacedKey(JavaPlugin.getPlugin(OldGenerator.class), "populated");

    private void setHasPopulated(final Chunk chunk, final boolean value) {
        final PersistentDataContainer dataContainer = chunk.getPersistentDataContainer();
        dataContainer.set(this.POPULATED_KEY, BOOLEAN_TYPE, value ? Boolean.TRUE : Boolean.FALSE);
    }

    private boolean hasPopulated(final Chunk chunk) {
        final PersistentDataContainer dataContainer = chunk.getPersistentDataContainer();
        return dataContainer.get(this.POPULATED_KEY, BOOLEAN_TYPE) == Boolean.TRUE;
    }

    private boolean hasPopulatedKeySet(final Chunk chunk) {
        final PersistentDataContainer dataContainer = chunk.getPersistentDataContainer();
        return dataContainer.get(this.POPULATED_KEY, BOOLEAN_TYPE) != null;
    }

    // populators are now called just before a chunk loads, without any consideration to loaded neighbours.
    // so we need to fire them manually to get the order we expect.

    private static Chunk getChunkAtNoLoad(final World world, final int x, final int z) {
        if (!world.isChunkLoaded(x, z)) {
            return null;
        }
        return world.getChunkAt(x, z);
    }

    private void runPopulators(final World world, final Chunk chunk) {
        final Beta173ChunkGenerator generator = (Beta173ChunkGenerator)world.getGenerator();
        generator.runPopulators(world, chunk);
        this.setHasPopulated(chunk, true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChunkLoad(final ChunkLoadEvent event) {
        final Chunk center = event.getChunk();
        final int centerX = center.getX();
        final int centerZ = center.getZ();

        final World world = event.getWorld();
        final ChunkGenerator generator = world.getGenerator();

        if (!(generator instanceof Beta173ChunkGenerator)) {
            return; // not our generator
        }

        if (event.isNewChunk()) {
            this.setHasPopulated(center, false);
        } else if (!this.hasPopulatedKeySet(center)) {
            // if a chunk is loaded from disk but it doesn't have our key, it means it was probably generated
            // by another generator. So don't mess with it
            this.setHasPopulated(center, true);
        }

        final Chunk topRight = getChunkAtNoLoad(world, centerX + 1, centerZ + 1);
        final Chunk top = getChunkAtNoLoad(world, centerX, centerZ + 1);
        final Chunk right = getChunkAtNoLoad(world, centerX + 1, centerZ);
        final Chunk left = getChunkAtNoLoad(world, centerX - 1, centerZ);
        final Chunk topLeft = getChunkAtNoLoad(world, centerX - 1, centerZ + 1);
        final Chunk bottom = getChunkAtNoLoad(world, centerX, centerZ - 1);
        final Chunk bottomRight = getChunkAtNoLoad(world, centerX + 1, centerZ - 1);
        final Chunk bottomLeft = getChunkAtNoLoad(world, centerX - 1, centerZ - 1);

        // try populate us
        if (!this.hasPopulated(center) && topRight != null && top != null && right != null) {
            this.runPopulators(world, center);
        }

        // try populate left
        if (left != null && !this.hasPopulated(left) && topLeft != null && top != null) {
            this.runPopulators(world, left);
        }

        // try populate bottom
        if (bottom != null && !this.hasPopulated(bottom) && bottomRight != null && right != null) {
            this.runPopulators(world, bottom);
        }

        // try populate bottom left
        if (bottomLeft != null && !this.hasPopulated(bottomLeft) && bottom != null && left != null) {
            this.runPopulators(world, bottomLeft);
        }
    }
}