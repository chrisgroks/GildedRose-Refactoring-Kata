package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = getUpdaterForItem(item);
            updater.update(item);
        }
    }

    private ItemUpdater getUpdaterForItem(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            return new AgedBrieUpdater();
        } else if (item.name.equals(SULFURAS)) {
            return new SulfurasUpdater();
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            return new BackstagePassUpdater();
        } else if (item.name.startsWith(CONJURED)) {
            return new ConjuredItemUpdater();
        } else {
            return new NormalItemUpdater();
        }
    }
}
