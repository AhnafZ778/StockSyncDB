-- ============================================================
-- StockSync Products Table Schema
-- Database: SQLite 3.45.1
-- Purpose: Store inventory product data with audit trail
-- ============================================================

-- Drop table if exists (for development/testing)
DROP TABLE IF EXISTS products;

-- Create products table
CREATE TABLE products (
    -- Primary Key (auto-incrementing ID)
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    
    -- Product Identification (required, unique)
    sku TEXT NOT NULL UNIQUE,
    
    -- Product Details (required)
    name TEXT NOT NULL,
    
    -- Inventory Tracking (required, non-negative)
    quantity INTEGER NOT NULL DEFAULT 0 CHECK(quantity >= 0),
    
    -- Pricing (required, non-negative)
    price REAL NOT NULL CHECK(price >= 0.0),
    
    -- Soft Delete Pattern (1 = active, 0 = deleted)
    active INTEGER NOT NULL DEFAULT 1 CHECK(active IN (0, 1)),
    
    -- Audit Timestamps (auto-populated)
    created_at TEXT NOT NULL DEFAULT (datetime('now')),
    updated_at TEXT NOT NULL DEFAULT (datetime('now'))
);

-- ============================================================
-- Indexes for Query Performance
-- ============================================================

-- Index on SKU (most common lookup field)
CREATE INDEX idx_products_sku ON products(sku);

-- Index on active flag (filter deleted products)
CREATE INDEX idx_products_active ON products(active);

-- Composite index for active products by SKU (common query pattern)
CREATE INDEX idx_products_sku_active ON products(sku, active);

-- ============================================================
-- Trigger: Auto-update updated_at timestamp
-- ============================================================

CREATE TRIGGER update_products_timestamp 
AFTER UPDATE ON products
FOR EACH ROW
BEGIN
    UPDATE products 
    SET updated_at = datetime('now') 
    WHERE id = NEW.id;
END;

-- ============================================================
-- Verification Queries
-- ============================================================

-- Show table structure
-- .schema products

-- Show all indexes
-- .indexes products

-- Show triggers
-- .triggers products
