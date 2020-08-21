package com.playernguyen.util;

import com.playernguyen.asset.ItemTagEnum;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

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

        public Builder appendData(String key, Object object) {
            this.map.put(key, object);
            return this;
        }

        public Builder appendData(ItemTagEnum keyEnum, Object data) {
            this.map.put(keyEnum.getKey(), data);
            return this;
        }

        public Builder appendInitialKeyEnum(ItemTagEnum itemTagEnum) {
            this.map.put(itemTagEnum.getKey(), itemTagEnum.getInitial());
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

    }
}
