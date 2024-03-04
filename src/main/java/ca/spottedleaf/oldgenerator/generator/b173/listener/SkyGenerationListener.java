package ca.spottedleaf.oldgenerator.generator.b173.listener;

import ca.spottedleaf.oldgenerator.OldGenerator;
import ca.spottedleaf.oldgenerator.generator.b173.Beta173ChunkGenerator;
import ca.spottedleaf.oldgenerator.util.FoliaSupport;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.ChunkGenerator;

public final class SkyGenerationListener implements Listener {

    private static boolean isSkyWorld(final World world) {
        final ChunkGenerator generator = world.getGenerator();
        return ((generator instanceof Beta173ChunkGenerator) && ((Beta173ChunkGenerator)generator).isSkyLands);
    }

    private final OldGenerator plugin;

    public SkyGenerationListener(final OldGenerator plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldInit(final WorldInitEvent event) {
        final World world = event.getWorld();

        if (!isSkyWorld(world)) {
            return;
        }

        FoliaSupport.runGlobalScheduler(this.plugin, () -> {
            world.setStorm(false);
            world.setThundering(false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, Boolean.FALSE);

            if (world.getTime() != (6 * 1000)) {
                world.setTime(6 * 1000);
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeatherChange(final WeatherChangeEvent event) {
        if (isSkyWorld(event.getWorld()) && event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeatherChange(final ThunderChangeEvent event) {
        if (isSkyWorld(event.getWorld()) && event.toThunderState()) {
            event.setCancelled(true);
        }
    }
}
