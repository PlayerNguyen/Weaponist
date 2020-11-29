package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.asset.AssetEnum;
import com.playernguyen.weaponist.asset.ItemType;
import com.playernguyen.weaponist.asset.ammunition.AmmunitionEnum;
import com.playernguyen.weaponist.sound.SoundConfiguration;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum GunEnum implements AssetEnum {

    BERETTA("beretta",
            "&cBeretta &7(%ammo%/%max_ammo%)",
            1.2d,
            5.5D,
            0.5D,
            1.2d,
            100,
            12,
            2,
            AmmunitionEnum.PISTOL,
            Material.WOOD_HOE,
            Arrays.asList(
                    "&cBarretta &7handgun is a light-weight,",
                    "&7mobility and simplex gun for who want ", "&7to have a little boy in hand"
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 1, 1.4f),
                    new SoundConfiguration(Sound.ENTITY_GENERIC_EXPLODE, 1, 1.4f)
            ),
            ShootType.SINGLE
    ),
    UZI(
            "uzi",
            "&cUzi &7(%ammo%/%max_ammo%)",
            1.4,
            5.2,
            0.01,
            0.8,
            100,
            25,
            2,
            AmmunitionEnum.SMG,
            Material.GOLD_PICKAXE,
            Arrays.asList(
                    "&cUzi &7- Killing machine with light-weight",
                    "&7guns. Kill kill and kill"
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_NOTE_BASEDRUM, 0.5f, 1),
                    new SoundConfiguration(Sound.BLOCK_NOTE_SNARE, 1f, 1f)
            ),
            ShootType.MULTIPLE
    ),
    AK_RIFLE(
            "ak_47",
            "&cAK-47 &7(%ammo%/%max_ammo%)",
            1.8,
            9.5,
            0.1,
            1,
            150,
            30,
            4,
            AmmunitionEnum.RIFLE,
            Material.DIAMOND_AXE,
            Arrays.asList(
                    "&cAK-47, &7the communist gun with ",
                    "&7huge damage."
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_NOTE_BASEDRUM, 0.5f, 1),
                    new SoundConfiguration(Sound.BLOCK_NOTE_SNARE, 1f, 1f),
                    new SoundConfiguration(Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 2f)
            ),
            ShootType.MULTIPLE
    ),
    SKS(
            "sks",
            "&cSKS &7(%ammo%/%max_ammo%)",
            3.0,
            14.2,
            1.2,
            25,
            300,
            10,
            7,
            AmmunitionEnum.SNIPER,
            Material.LEATHER,
            Arrays.asList(
                    "&cSKS &7is a gun for Soviet army",
                    "&7which make enemy cry."
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.ENTITY_BLAZE_DEATH, 0.5f, 1)
//                    new SoundConfiguration(Sound.BLOCK_NOTE_SNARE, 1f, 1f),
//                    new SoundConfiguration(Sound.ENTITY_GENERIC_EXPLODE, 1.3f, 2f)
            ),
            ShootType.SINGLE
    ),
    RPG(
            "rpg",
            "&6RPG &7(%ammo%/%max_ammo%)",
            6.0,
            40,
            0.01,
            0.2,
            150,
            1,
            1,
            AmmunitionEnum.ROCKET,
            Material.NAME_TAG,
            Arrays.asList(
                    "&6RPG &7a rocket with high damage",
                    "&7Explosion when hit object"
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_NOTE_BASEDRUM, 0.5f, 1),
                    new SoundConfiguration(Sound.BLOCK_NOTE_SNARE, 1f, 1f),
                    new SoundConfiguration(Sound.ENTITY_GENERIC_EXPLODE, 1.3f, 2f),
                    new SoundConfiguration(Sound.ENTITY_ENDERDRAGON_SHOOT, 1.3f, 1f)
            ),
            ShootType.SINGLE
    ),
    GLOCK(
            "glock",
            "&5Glock 18 &7(%ammo%/%max_ammo%)",
            0.6,
            5.0,
            0.01,
            6,
            100,
            30,
            2,
            AmmunitionEnum.PISTOL,
            Material.DIAMOND_HOE,
            Arrays.asList(
                    "&cGlock-18&7, &7the best gun for US Military",
                    "&7 with high fire rate."
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_NOTE_BASEDRUM, 0.5f, 1),
                    new SoundConfiguration(Sound.BLOCK_NOTE_SNARE, 1f, 1f)
            ),
            ShootType.MULTIPLE
    ),
    SPAS(
            "spas",
            "&cSpas 12 &7(%ammo%/%max_ammo%)",
            2.0,
            3.0,
            0.8,
            6,
            20,
            7,
            3,
            AmmunitionEnum.SHOTGUN,
            Material.WOOD_HOE,
            Arrays.asList(
                    "&cHigh damage &7pump-action gun",
                    "&7with great mobility"
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, .5f, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_ON, .5f,  .7f),
                    new SoundConfiguration(Sound.BLOCK_STONE_BUTTON_CLICK_OFF, .5f,  .7f)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_NOTE_BASEDRUM, 0.5f, 1),
                    new SoundConfiguration(Sound.BLOCK_NOTE_SNARE, 1f, 1f)
            ),
            ShootType.SINGLE
    )
    ;

    private final String id;
    private final String name;
    private final double delayPerShoot;
    private final double damage;
    private final double reloadTime;
    private final double fireAccuracy;
    private final int maxMagazine;
    private final int maxDistance;
    private final int maxPenetrate;

    private final AmmunitionEnum ammunitionType;
    private final Material material;
    private final List<String> description;
    private final List<String> reloadSound;
    private final List<String> shootSound;

    private final ShootType shootType;

    GunEnum(String id,
            String name,
            double reloadTime,
            double damage,
            double delayPerShoot,
            double fireAccuracy,
            int maxDistance,
            int maxMagazine,
            int maxPenetrate,
            AmmunitionEnum ammunitionType,
            Material material,
            List<String> description,
            List<SoundConfiguration> reloadSound,
            List<SoundConfiguration> shootSound,
            ShootType shootType
    ) {
        this.id = id;
        this.name = name;
        this.ammunitionType = ammunitionType;
        this.fireAccuracy = fireAccuracy;
        this.material = material;
        this.maxMagazine = maxMagazine;
        this.description = description;
        this.reloadTime = reloadTime;
        this.damage = damage;
        this.delayPerShoot = delayPerShoot;
        this.maxDistance = maxDistance;
        this.maxPenetrate = maxPenetrate;
        this.reloadSound = new ArrayList<>();
        for (SoundConfiguration soundConfiguration : reloadSound) {
            this.reloadSound.add(soundConfiguration.toString());
        }

        this.shootSound = new ArrayList<>();
        for (SoundConfiguration soundConfiguration : shootSound) {
            this.shootSound.add(soundConfiguration.toString());
        }

        this.shootType = shootType;
    }

    public List<String> getReloadSound() {
        return reloadSound;
    }

    public List<String> getShootSound() {
        return shootSound;
    }

    public double getDamage() {
        return damage;
    }

    public String getId() {
        return id;
    }

    /**
     * @deprecated change to {@link #getDisplay()}
     */
    @Deprecated
    public String getName() {
        return name;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.GUN;
    }

    @Override
    public String getDisplay() {
        return name;
    }

    public AmmunitionEnum getAmmunitionType() {
        return ammunitionType;
    }

    public Material getMaterial() {
        return material;
    }

    public int getMaxMagazine() {
        return maxMagazine;
    }

    public List<String> getDescription() {
        return description;
    }

    public String asFileName() {
        return id.concat(".yml");
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public double getDelayPerShoot() {
        return delayPerShoot;
    }

    @Deprecated
    public double getFireAccuracy() {
        return fireAccuracy;
    }

    public double getFireRate() {
        return fireAccuracy;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxPenetrate() {
        return maxPenetrate;
    }

    public ShootType getShootType() {
        return shootType;
    }
}
