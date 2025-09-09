package ca.spottedleaf.oldgenerator.util;

import ca.spottedleaf.oldgenerator.world.BlockAccess;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public final class LeafDistanceCalculator {

    private static final int MAX_LEAF_DISTANCE = 7;

    private final ArrayDeque<QueuedCheck> updates = new ArrayDeque<>();
    private final Set<Pos> seen = new HashSet<>();
    private static final AxisDirection[] DIRECTIONS = AxisDirection.values();

    public void addLog(final int blockX, final int blockY, final int blockZ) {
        if (this.seen.add(new Pos(blockX, blockY, blockZ))) {
            this.updates.add(new QueuedCheck(blockX, blockY, blockZ, 0));
        }
    }

    public void reset() {
        this.updates.clear();
    }

    public void update(final BlockAccess blockAccess) {
        QueuedCheck check;
        while ((check = this.updates.pollFirst()) != null) {
            final int blockX = check.blockX;
            final int blockY = check.blockY;
            final int blockZ = check.blockZ;
            final int targetDistance = check.distance + 1;

            for (final AxisDirection direction : DIRECTIONS) {
                final int neighbourX = direction.x + blockX;
                final int neighbourY = direction.y + blockY;
                final int neighbourZ = direction.z + blockZ;

                if (!this.seen.add(new Pos(neighbourX, neighbourY, neighbourZ))) {
                    continue;
                }

                if (!blockAccess.isLoaded(neighbourX >> 4, neighbourZ >> 4)) {
                    continue;
                }

                final BlockData blockData = blockAccess.getBlockData(neighbourX, neighbourY, neighbourZ);

                if (!(blockData instanceof Leaves leaves)) {
                    continue;
                }

                final int leavesDistance = leaves.getDistance();

                if (leavesDistance == targetDistance) {
                    continue;
                }
                if (leavesDistance < targetDistance) {
                    // note: from another tree, so propagate it
                    this.updates.addLast(new QueuedCheck(neighbourX, neighbourY, neighbourZ, leavesDistance));
                    continue;
                }

                leaves.setDistance(targetDistance);

                blockAccess.setBlockData(neighbourX, neighbourY, neighbourZ, leaves, false);

                if (targetDistance < MAX_LEAF_DISTANCE) {
                    this.updates.addLast(new QueuedCheck(neighbourX, neighbourY, neighbourZ, targetDistance));
                }
            }
        }
    }

    private static record QueuedCheck(int blockX, int blockY, int blockZ, int distance) {}
    private static record Pos(int blockX, int blockY, int blockZ) {}

    private static enum AxisDirection {

        POSITIVE_X(1, 0, 0), NEGATIVE_X(-1, 0, 0),
        POSITIVE_Z(0, 0, 1), NEGATIVE_Z(0, 0, -1),
        POSITIVE_Y(0, 1, 0), NEGATIVE_Y(0, -1, 0);

        public final int x;
        public final int y;
        public final int z;

        AxisDirection(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
