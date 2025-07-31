package com.gildedrose;

abstract class ItemUpdater {
    public abstract void update(Item item);
    
    protected void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }
    
    protected void increaseQuality(Item item, int amount) {
        item.quality = Math.min(50, item.quality + amount);
    }
    
    protected void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(0, item.quality - amount);
    }
}
