package ca.spottedleaf.oldgenerator.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import java.lang.reflect.Method;

public final class FoliaSupport {

    private static final Compat COMPAT;
    static {
        Compat temp;
        try {
            Bukkit.class.getDeclaredMethod("getGlobalRegionScheduler");
            temp = new PaperCompat();
        } catch (final Exception ex) {
            temp = new BukkitCompat();
        }

        COMPAT = temp;
    }

    private static Class<?> getClass(final String name) {
        try {
            return Class.forName(name);
        } catch (final Exception ex) {
            return null;
        }
    }

    private static Method getMethod(final Class<?> clazz, final String name, final Class<?>... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (final Exception ex) {
            return null;
        }
    }

    public static void runGlobalScheduler(final Plugin plugin, final Runnable run) {
        COMPAT.runGlobalScheduler(plugin, run);
    }

    private static interface Compat {

        public void runGlobalScheduler(final Plugin plugin, final Runnable run);

    }

    private static final class BukkitCompat implements Compat {

        @Override
        public void runGlobalScheduler(final Plugin plugin, final Runnable run) {
            Bukkit.getScheduler().runTask(plugin, run);
        }
    }

    private static final class PaperCompat implements Compat {

        private static final Method GET_GLOBAL_REGION_SCHEDULER = getMethod(Bukkit.class, "getGlobalRegionScheduler");
        private static final Class<?> GLOBAL_REGION_SCHEDULER_CLASS = FoliaSupport.getClass("io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler");
        private static final Method GLOBAL_REGION_SCHEDULER_EXECUTE = getMethod(GLOBAL_REGION_SCHEDULER_CLASS, "execute", Plugin.class, Runnable.class);

        @Override
        public void runGlobalScheduler(final Plugin plugin, final Runnable run) {
            try {
                final Object scheduler = GET_GLOBAL_REGION_SCHEDULER.invoke(null);
                GLOBAL_REGION_SCHEDULER_EXECUTE.invoke(scheduler, plugin, run);
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
