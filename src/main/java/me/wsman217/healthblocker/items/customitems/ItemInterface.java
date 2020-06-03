package me.wsman217.healthblocker.items.customitems;

import me.wsman217.healthblocker.alters.AlterInterface;
import me.wsman217.healthblocker.alters.AlterRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public abstract class ItemInterface {

    private final Material mat;
    private ItemStack item;
    private final String name;
    private boolean isAlterRecipe = true;
    private AlterRecipe alterRecipe;
    private final String nameSpace;
    private final Permission permission;
    private final AlterInterface alter;

    public ItemInterface(Material mat, String name, String nameSpace, Permission permission, AlterInterface alter) {
        this.mat = mat;
        this.name = name;
        this.nameSpace = nameSpace;
        this.permission = permission;
        this.alter = alter;
    }

    public Material getMaterial() {
        return mat;
    }

    public String getName() {
        return name;
    }

    public boolean setAlterRecipe(boolean isAlterRecipe) {
        this.isAlterRecipe = isAlterRecipe;
        return this.isAlterRecipe;
    }

    public boolean isAlterRecipe() {
        return isAlterRecipe;
    }

    public void setAlterRecipe(AlterRecipe alterRecipe) {
        this.alterRecipe = alterRecipe;
    }

    public AlterRecipe getAlterRecipe() {
        return isAlterRecipe ? alterRecipe : null;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public Permission getPermission() {
        return permission;
    }

    public AlterInterface getAlter() {
        return alter;
    }
}
