#!/bin/bash

update_quality() {
    local IFS='|'

    while read -r name sell_in quality; do
        local is_conjured=false
        if [[ $name == Conjured* ]]; then
            is_conjured=true
        fi
        
        if [[ $name != "Aged Brie" && $name != "Backstage passes to a TAFKAL80ETC concert" ]]; then
            if ((quality > 0)); then
                if [[ $name != "Sulfuras, Hand of Ragnaros" ]]; then
                    quality=$((quality - 1))
                    if [[ $is_conjured == true && $quality > 0 ]]; then
                        quality=$((quality - 1))
                    fi
                fi
            fi
        else
            if ((quality < 50)); then
                quality=$((quality + 1))

                if [[ $name == "Backstage passes to a TAFKAL80ETC concert" ]]; then
                    if ((sell_in < 11)); then
                        if ((quality < 50)); then
                            quality=$((quality + 1))
                        fi
                    fi
                    if ((sell_in < 6)); then
                        if ((quality < 50)); then
                            quality=$((quality + 1))
                        fi
                    fi
                fi
            fi
        fi

        if [[ $name != "Sulfuras, Hand of Ragnaros" ]]; then
            sell_in=$((sell_in - 1))
        fi

        if ((sell_in < 0)); then
            if [[ $name != "Aged Brie" ]]; then
                if [[ $name != "Backstage passes to a TAFKAL80ETC concert" ]]; then
                    if ((quality > 0)); then
                        if [[ $name != "Sulfuras, Hand of Ragnaros" ]]; then
                            quality=$((quality - 1))
                            if [[ $is_conjured == true && $quality > 0 ]]; then
                                quality=$((quality - 1))
                            fi
                        fi
                    fi
                else
                    quality=$((quality - quality))
                fi
            else
                if ((quality < 50)); then
                    quality=$((quality + 1))
                fi
            fi
        fi

        echo "$name|$sell_in|$quality"
    done
}

update_quality
