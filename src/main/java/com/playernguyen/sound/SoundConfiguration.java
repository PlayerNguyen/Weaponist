package com.playernguyen.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class SoundConfiguration implements ConfigurationSerializable {

    private final Sound sound;
    private final float volume;
    private final float pitch;

    public SoundConfiguration(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return volume;
    }

    public Sound getSound() {
        return sound;
    }

    public void play(Location location) {
        location.getWorld().playSound(location, getSound(), getVolume(), getPitch());
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", getSound().toString(), getVolume(), getPitch());
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object>  m = new HashMap<>();
        m.put("value", this.toString());

        return m;
    }

    public static class Reader {

        private final SoundConfiguration soundConfiguration;

        public Reader(String raw) {

            String[] splitArray = raw.split(" ");
            if (splitArray.length < 3) {
                throw new IllegalArgumentException("Configuration: Sound array cannot be encode because empty data!");
            }

            String sound = splitArray[0];
            String volume = splitArray[1];
            String pitch = splitArray[2];
            // Sound check
            Sound s = Sound.valueOf(sound);
            // Return
            this.soundConfiguration =
                    new SoundConfiguration(s, Float.parseFloat(volume), Float.parseFloat(pitch));
        }

        public SoundConfiguration toSoundConfiguration() {
            return soundConfiguration;
        }
    }

}
