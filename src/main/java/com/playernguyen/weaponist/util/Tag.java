package com.playernguyen.weaponist.util;

import com.playernguyen.weaponist.asset.ItemTagEnum;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Tag {

    public static class Builder {

        private final ItemStack itemStack;
        private final Map<String, Object> map;

        public Builder(ItemStack itemStack) {
            this.itemStack = itemStack;
            this.map = new LinkedHashMap<>();
        }

        public Builder setData(String key, Object object) {
            this.map.put(key, object);
            return this;
        }

        public Builder setData(ItemTagEnum keyEnum, Object data) {
            this.map.put(keyEnum.getKey(), data);
            return this;
        }

        public Builder initData(ItemTagEnum itemTagEnum) {
            this.map.put(itemTagEnum.getKey(), itemTagEnum.getInitial());
            return this;
        }

        public Builder clearAttribute() {
            this.map.put("AttributeModifiers", new ArrayList<>());
            return this;
        }

        public ItemStack build() {
            // Clone the new one
            net.minecraft.server.v1_12_R1.ItemStack buildStack =
                    CraftItemStack.asNMSCopy(itemStack);
            // Compound
            NBTTagCompound compound = (buildStack.hasTag()) ? buildStack.getTag() : new NBTTagCompound();
            if (compound == null) {
                throw new NullPointerException("NBTTagCompound is null...");
            }
            // Add the item from map to data -> flexible data
            for (String key : map.keySet()) {
                Object value = map.get(key);
                // String
                if (value instanceof String) {
                    compound.setString(key, (String) value);
                } else
                // Integer
                if (value instanceof Integer) {
                    compound.setInt(key, (Integer) value);
                } else
                // Float / double
                if (value instanceof Double) {
                    compound.setDouble(key, (Double) value);
                } else
                if (value instanceof Float) {
                    compound.setFloat(key, (Float) value);
                } else
                // Long
                if (value instanceof Long) {
                    compound.setLong(key, (Long) value);
                } else
                // Boolean
                if (value instanceof Boolean) {
                    compound.setBoolean(key, (Boolean) value);
                } else {
                    compound.setString(key, value.toString());
                }
            }
            // Put data into the buildStack
            buildStack.setTag(compound);
            return CraftItemStack.asBukkitCopy(buildStack);
        }
    }

    public static class Reader {
        private final ItemStack itemStack;

        public Reader(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        private NBTTagCompound compound() {
            // Clone the item stack
            net.minecraft.server.v1_12_R1.ItemStack cloneStack = CraftItemStack.asNMSCopy(itemStack);
            // Compound get
            if (!cloneStack.hasTag()) {
                return null;
            }

            // Filter the value
            return cloneStack.getTag();
        }

        public boolean hasCompound() {
            return compound() != null;
        }

        public boolean hasKey(String key) {
            return Objects.requireNonNull(compound()).hasKey(key);
        }

        public boolean hasKey(ItemTagEnum e) {
            return hasKey(e.getKey());
        }

        public String getString(String key) {
            return Objects.requireNonNull(compound()).getString(key);
        }

        public boolean getBoolean(String key) {
            return Objects.requireNonNull(compound()).getBoolean(key);
        }

        public int getInt(String key) {
            return Objects.requireNonNull(compound()).getInt(key);
        }

        public double getDouble(String key) {
            return Objects.requireNonNull(compound()).getDouble(key);
        }

        public float getFloat(String key) {
            return Objects.requireNonNull(compound()).getFloat(key);
        }

        public boolean getBoolean(ItemTagEnum keyEnum) {
            return getBoolean(keyEnum.getKey());
        }

        public String getString(ItemTagEnum e){
            return getString(e.getKey());
        }

        public int getInt(ItemTagEnum e){
            return getInt(e.getKey());
        }
    }

    public static boolean isAmmunition(ItemStack itemStack) {
        Reader reader = new Reader(itemStack);
        return reader.hasCompound()
                && reader.hasKey(ItemTagEnum.IS_AMMO)
                && (reader.getBoolean(ItemTagEnum.IS_AMMO));
    }

    public static String getAmmunitionType(ItemStack itemStack) {
        return getItemId(itemStack);
    }

    public static boolean isWeapon(ItemStack itemStack) {
        Reader reader = new Reader(itemStack);

        return reader.hasCompound()
                && reader.hasKey(ItemTagEnum.IS_WEAPON)
                && reader.getBoolean(ItemTagEnum.IS_WEAPON);
    }

    public static boolean isThrowable(ItemStack stack) {
        Reader reader = new Reader(stack);

        return reader.hasCompound()
                && reader.hasKey(ItemTagEnum.IS_THROWABLE)
                && reader.getBoolean(ItemTagEnum.IS_THROWABLE);
    }

    public static String getWeaponId(ItemStack stack) {
        return getItemId(stack);
    }

    public static ItemStack setData(ItemStack stack, ItemTagEnum itemTagEnum, Object value) {
        Builder builder = new Builder(stack);
        // Set the data
        builder.setData(itemTagEnum, value);

        // Rebuild
        return builder.build();
    }

    public static String getItemId(ItemStack itemStack) {
        Reader reader = new Reader(itemStack);
        if (!reader.hasCompound()) throw new NullPointerException("compound not found!");
        return reader.getString(ItemTagEnum.ITEM_ID);
    }

    public static int getGunAmmo(ItemStack stack) {
        Reader reader = new Reader(stack);
        if (!reader.hasCompound()) throw new NullPointerException("no compound found!");
        return reader.getInt(ItemTagEnum.GUN_AMMO);
    }


}
