#!/bin/bash
echo "=== Schema Verification ==="
echo ""
echo "1. Table exists:"
sqlite3 stocksync.db "SELECT name FROM sqlite_master WHERE type='table' AND name='products';"
echo ""
echo "2. Column count (should be 8):"
sqlite3 stocksync.db "PRAGMA table_info(products);" | wc -l
echo ""
echo "3. Index count (should be 3):"
sqlite3 stocksync.db "SELECT name FROM sqlite_master WHERE type='index' AND tbl_name='products';" | wc -l
echo ""
echo "4. Trigger exists:"
sqlite3 stocksync.db "SELECT name FROM sqlite_master WHERE type='trigger' AND tbl_name='products';"
echo ""
echo "5. Row count (should be 5):"
sqlite3 stocksync.db "SELECT COUNT(*) FROM products;"
echo ""
echo "✅ If all checks pass, schema is correct!"
