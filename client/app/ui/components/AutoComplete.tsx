// Autocomplete.tsx

import React, { useState, ChangeEvent, useEffect, useRef } from 'react';

interface AutocompleteProps<T> {
    items: T[];
    getLabel: (item: T) => string;
    getId?: (item: T) => string | number;
    isMultiSelect?: boolean;
    onChange?: (selectedItems: T[]) => void;
}

const Autocomplete = <T,>({
    items,
    getLabel,
    getId = (item: T) => (item as any).id,
    isMultiSelect = false,
    onChange,
}: AutocompleteProps<T>) => {
    const [query, setQuery] = useState<string>('');
    const [selectedItems, setSelectedItems] = useState<T[]>([]);
    const [filteredItems, setFilteredItems] = useState<T[]>(items);
    const [isDropdownOpen, setIsDropdownOpen] = useState<boolean>(false);
    const dropdownRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        if (isDropdownOpen) {
            document.addEventListener('click', handleClickOutside);
        } else {
            document.removeEventListener('click', handleClickOutside);
        }
        return () => {
            document.removeEventListener('click', handleClickOutside);
        };
    }, [isDropdownOpen]);

    const handleClickOutside = (event: MouseEvent) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
            setIsDropdownOpen(false);
        }
    };

    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const inputValue = e.target.value;
        setQuery(inputValue);

        const filtered = inputValue
            ? items.filter((item) =>
                getLabel(item).toLowerCase().includes(inputValue.toLowerCase())
            )
            : items;

        setFilteredItems(filtered);
        setIsDropdownOpen(true);
    };

    const handleItemClick = (item: T) => {
        if (isMultiSelect) {
            setSelectedItems((prevSelected) => [...prevSelected, item]);
            setQuery('');
            setFilteredItems((prevFiltered) =>
                prevFiltered.filter((filteredItem) => getId(filteredItem) !== getId(item))
            );
        } else {
            setQuery(getLabel(item));
            setFilteredItems([]);
            setSelectedItems([item]);
        }

        if (onChange) {
            onChange([...selectedItems, item]);
        }
    };

    const handleRemoveItem = (item: T) => {
        setSelectedItems((prevSelected) =>
            prevSelected.filter((selectedItem) => getId(selectedItem) !== getId(item))
        );
        setFilteredItems((prevFiltered) => [...prevFiltered, item]);

        if (onChange) {
            onChange(selectedItems.filter((selectedItem) => getId(selectedItem) !== getId(item)));
        }
    };

    useEffect(() => {
        setFilteredItems(items);
    }, [items]);

    return (
        <div className="relative" ref={dropdownRef}>
            <div className="flex flex-wrap">
                {selectedItems.map((selectedItem, index) => (
                    <div
                        key={getId(selectedItem) || index}
                        className="bg-blue-500 text-white px-2 py-1 m-1 rounded"
                    >
                        {getLabel(selectedItem)}
                        <span
                            className="ml-2 cursor-pointer"
                            onClick={() => handleRemoveItem(selectedItem)}
                        >
                            &#x2715;
                        </span>
                    </div>
                ))}
            </div>
            <input
                type="text"
                className="input"
                placeholder="Search..."
                value={query}
                onChange={handleInputChange}
                onFocus={() => {
                    setIsDropdownOpen(true);
                }}
            />
            {isDropdownOpen && (
                <ul className="absolute z-10 w-full mt-2 bg-white border border-gray-300 rounded-md shadow-md">
                    {filteredItems.map((item, index) => (
                        <li
                            key={getId(item) || index}
                            className="px-4 py-2 cursor-pointer hover:bg-gray-100"
                            onClick={() => handleItemClick(item)}
                        >
                            {getLabel(item)}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default Autocomplete;
