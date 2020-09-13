package com.playernguyen.weaponist.runnable;

import com.playernguyen.weaponist.asset.throwItem.Throw;
import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.sound.SoundConfiguration;
import com.playernguyen.weaponist.util.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class ThrowableTriggerCountdownRunnable extends BukkitRunnable {
    private final Throw aThrow;
//    private double countdown;
    private final Item item;
    private final Shooter shooter;

    public ThrowableTriggerCountdownRunnable(Throw aThrow, Item item, Shooter shooter) {
        this.aThrow = aThrow;
        this.item = item;
//        this.countdown = aThrow.getExplodeTime();
        this.shooter = shooter;
    }

    @Override
    public void run() {
        Location itemLocation = item.getLocation();
        // Play smoke effect
        new ParticleBuilder(Particle.SPELL_INSTANT, 10)
                .extra(2).play(itemLocation);

        // Explode
        if (item.isOnGround()) {
            aThrow.onExplode(shooter, itemLocation);
            // Clear item
            new ParticleBuilder(Particle.ITEM_CRACK, 10)
                    .extra(2).play(itemLocation, item.getItemStack());
            // Play sound
            new SoundConfiguration(Sound.BLOCK_GLASS_BREAK, 15, 0.8f)
                    .play(itemLocation);
            item.remove();
            cancel();
        }
    }
}
