package ca.spottedleaf.oldgenerator;

import ca.spottedleaf.oldgenerator.generator.b173.Beta173ChunkGenerator;
import ca.spottedleaf.oldgenerator.generator.b173.listener.SkyGenerationListener;
import ca.spottedleaf.oldgenerator.listener.LegacyPopulateHack;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class OldGenerator extends JavaPlugin {

    @Override
    public void onLoad() {}

    protected void setupMetrics() {
    	try {
    	    Class.forName("org.bstats.bukkit.Metrics");
    	    final Metrics metrics = new Metrics(this, 7761);
    	} catch (ClassNotFoundException e) {
    		System.err.println("bstats could not be found");
    	}
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new LegacyPopulateHack(), this);
        Bukkit.getPluginManager().registerEvents(new SkyGenerationListener(this), this);
        this.setupMetrics();
    }

    @Override
    public void onDisable() {}

    @Override
    public ChunkGenerator getDefaultWorldGenerator(final String worldName, final String id) {
        if (id == null || (!id.equalsIgnoreCase("b173") && !id.equalsIgnoreCase("sb173"))) {
            this.getLogger().warning("Id '" + id + "' is invalid, accepted ids: b173, sb173");
            return null;
        }
        // TODO configurable
        final boolean skylands = id.equalsIgnoreCase("sb173");
        this.getLogger().info("Registering world generator for world '" + worldName + "' with configuration: {skylands:" + skylands + "}");
        return new Beta173ChunkGenerator(skylands);
    }
}
