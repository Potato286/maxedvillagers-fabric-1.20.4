package com.ethan;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.Arrays;
import java.util.List;

public class LibrarianTrades {
    public static class EnchantBookFactory
            implements TradeOffers.Factory {
        private final int experience;
        private final List<Enchantment> possibleEnchantments;
        private final int minLevel;
        private final int maxLevel;

        public EnchantBookFactory(int experience) {
            this(experience, (Enchantment[]) Registries.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).toArray(Enchantment[]::new));
        }

        public EnchantBookFactory(int experience, Enchantment ... possibleEnchantments) {
            this(experience, 0, Integer.MAX_VALUE, possibleEnchantments);
        }

        public EnchantBookFactory(int experience, int minLevel, int maxLevel, Enchantment ... possibleEnchantments) {
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
            this.experience = experience;
            this.possibleEnchantments = Arrays.asList(possibleEnchantments);
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            Enchantment enchantment = this.possibleEnchantments.get(random.nextInt(this.possibleEnchantments.size()));
            System.out.println(enchantment);
            //int i = Math.max(enchantment.getMinLevel(), this.minLevel);
            int j = Math.min(enchantment.getMaxLevel(), this.maxLevel);
            System.out.println(enchantment.getMaxLevel());
            System.out.println(j);
            //int k = MathHelper.nextInt(random, i, j);
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, j));
            System.out.println(itemStack);
            int l = 2 + random.nextInt(5 + j * 10) + 3 * j;
            System.out.println(l);
            if (enchantment.isTreasure()) {
                l *= 2;
            }
            if (l > 64) {
                l = 64;
            }
            return new TradeOffer(new ItemStack(Items.EMERALD, l), new ItemStack(Items.BOOK), itemStack, 12, this.experience, 0.2f);
        }
    }


    public static void registerCustomTrades() {
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.remove(VillagerProfession.LIBRARIAN);
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1,
                factories -> {
                    factories.add(new TradeOffers.BuyItemFactory(Items.PAPER, 24, 16, 2));
                    factories.add(new TradeOffers.SellItemFactory(Blocks.BOOKSHELF, 9, 1, 12, 1));
                    factories.add(new EnchantBookFactory(1));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2,
                factories -> {
                    factories.add(new TradeOffers.BuyItemFactory(Items.BOOK, 4, 12, 10));
                    factories.add(new TradeOffers.SellItemFactory(Items.LANTERN, 1, 1, 12, 5));
                    factories.add(new EnchantBookFactory(5));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2,
                factories -> {
                    factories.add(new TradeOffers.BuyItemFactory(Items.BOOK, 4, 12, 10));
                    factories.add(new TradeOffers.SellItemFactory(Items.LANTERN, 1, 1, 5));
                    factories.add(new EnchantBookFactory(5));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 3,
                factories -> {
                    factories.add(new TradeOffers.BuyItemFactory(Items.INK_SAC, 5, 12, 20));
                    factories.add(new TradeOffers.SellItemFactory(Items.GLASS, 1, 4, 10));
                    factories.add(new EnchantBookFactory(10));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 4,
                factories -> {
                    factories.add(new TradeOffers.BuyItemFactory(Items.WRITABLE_BOOK, 2, 12, 30));
                    factories.add(new TradeOffers.SellItemFactory(Items.CLOCK, 5, 1, 15));
                    factories.add(new TradeOffers.SellItemFactory(Items.COMPASS, 4, 1, 15));
                    factories.add(new EnchantBookFactory(15));

                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5,
                factories -> {
                    factories.add(new TradeOffers.SellItemFactory(Items.NAME_TAG, 20, 1, 30));
                });
    }
}

