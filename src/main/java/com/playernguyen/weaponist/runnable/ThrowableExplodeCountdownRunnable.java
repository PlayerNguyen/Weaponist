package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.asset.throwItem.Throw;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.util.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class ThrowableExplodeCountdownRunnable extends BukkitRunnable {

    private final Throw aThrow;
    private final Item item;
    private final Shooter shooter;
    private double countdown;

    public ThrowableExplodeCountdownRunnable(Throw aThrow, Item item, Shooter shooter) {
        this.aThrow = aThrow;
        this.item = item;
        this.countdown = aThrow.getExplodeTime();
        this.shooter = shooter;
    }

    @Override
    public void run() {
        countdown = countdown - (1.0 / 20.0);
        Location itemLocation = item.getLocation();
        // Play smoke effect
        new ParticleBuilder(Particle.SPELL_INSTANT, 10)
                .extra(2).play(itemLocation);

        // Explode
        if (countdown <= 0) {
            aThrow.onExplode(shooter, itemLocation);
            // Clear item
            new ParticleBuilder(Particle.ITEM_CRACK, 10)
                    .extra(2).play(itemLocation, item.getItemStack());
            item.remove();
            cancel();
        }
    }
}
