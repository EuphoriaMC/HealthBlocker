package me.wsman217.healthblocker.utils;

import org.bukkit.attribute.Attribute;

public enum AttributeModifiers {
    MAX_HEALTH(Attribute.GENERIC_MAX_HEALTH) {
        @Override
        public String getName() {
            return "generic.maxHealth";
        }
    },
    FOLLOW_RANGE(Attribute.GENERIC_FOLLOW_RANGE) {
        @Override
        public String getName() {
            return "generic.followRange";
        }
    },
    KNOCKBACK_RESISTANCE(Attribute.GENERIC_KNOCKBACK_RESISTANCE) {
        @Override
        public String getName() {
            return "generic.knockbackResistance";
        }
    },
    MOVEMENT_SPEED(Attribute.GENERIC_MOVEMENT_SPEED) {
        @Override
        public String getName() {
            return "generic.movementSpeed";
        }
    },
    ATTACK_DAMAGE(Attribute.GENERIC_ATTACK_DAMAGE) {
        @Override
        public String getName() {
            return "generic.attackDamage";
        }
    },
    ARMOR(Attribute.GENERIC_ARMOR) {
        @Override
        public String getName() {
            return "generic.armor";
        }
    },
    ARMOR_TOUGHNESS(Attribute.GENERIC_ARMOR_TOUGHNESS) {
        @Override
        public String getName() {
            return "generic.armorToughness";
        }
    },
    ATTACK_SPEED(Attribute.GENERIC_ATTACK_SPEED) {
        @Override
        public String getName() {
            return "generic.attackSpeed";
        }
    },
    LUCK(Attribute.GENERIC_LUCK) {
        @Override
        public String getName() {
            return "generic.luck";
        }
    },
    JUMP_STRENGTH(Attribute.HORSE_JUMP_STRENGTH) {
        @Override
        public String getName() {
            return "generic.jumpStrength";
        }
    },
    FLYING_SPEED(Attribute.GENERIC_FLYING_SPEED) {
        @Override
        public String getName() {
            return "generic.flyingSpeed";
        }
    },
    SPAWN_REINFORCEMENTS(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS) {
        @Override
        public String getName() {
            return "generic.spawnReinforcements";
        }
    },
    NONE(null);

    private final Attribute attribute;
    AttributeModifiers(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getName() {
        return "none";
    }

    public static AttributeModifiers getByName(String name) {
        for (AttributeModifiers modifier : AttributeModifiers.values()) {
            if (modifier.getName().equalsIgnoreCase(name))
                return modifier;
        }
        return NONE;
    }
}
