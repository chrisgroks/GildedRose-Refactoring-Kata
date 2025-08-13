#!/usr/bin/env python3
from python.gilded_rose import GildedRose, Item

def test_conjured_items():
    print("Testing Conjured Items Implementation")
    print("=" * 40)
    
    items = [Item('Conjured Mana Cake', 3, 6)]
    gilded_rose = GildedRose(items)
    
    print(f"Day 0: {items[0]}")
    gilded_rose.update_quality()
    print(f"Day 1: {items[0]} (should be quality 4)")
    gilded_rose.update_quality()
    print(f"Day 2: {items[0]} (should be quality 2)")
    gilded_rose.update_quality()
    print(f"Day 3: {items[0]} (should be quality 0)")
    gilded_rose.update_quality()
    print(f"Day 4: {items[0]} (should be quality 0, past sell date)")
    
    print("\nComparing with normal item:")
    normal_items = [Item('Normal Item', 3, 6)]
    normal_rose = GildedRose(normal_items)
    
    print(f"Day 0: {normal_items[0]}")
    normal_rose.update_quality()
    print(f"Day 1: {normal_items[0]} (should be quality 5)")
    normal_rose.update_quality()
    print(f"Day 2: {normal_items[0]} (should be quality 4)")
    normal_rose.update_quality()
    print(f"Day 3: {normal_items[0]} (should be quality 3)")
    normal_rose.update_quality()
    print(f"Day 4: {normal_items[0]} (should be quality 1, past sell date)")
    
    print("\nConjured items degrade twice as fast as expected!")

if __name__ == "__main__":
    test_conjured_items()
