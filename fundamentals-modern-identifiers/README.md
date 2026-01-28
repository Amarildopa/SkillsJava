# Skill: Modern Identifiers

This project explores the performance implications of different unique identifier strategies for database-heavy applications.

## The Problem with UUIDv4
While `UUID.randomUUID()` is easy to use, it generates bits in a completely random order. Databases using B-tree indexes (like Postgres or MySQL) must reorder the index for every insert, leading to:
- **Index Fragmentation**: Many empty gaps in the index blocks.
- **Page Splits**: The database frequently splitting storage pages to fit new random IDs.
- **I/O Overhead**: Excessive disk reads/writes to maintain the index tree.

## The Solutions

### UUIDv7
UUIDv7 is a new standard that keeps the 128-bit UUID format but uses the first 48 bits for a Unix timestamp. This makes the IDs **time-ordered** (sequential), which the B-tree can handle efficiently similar to an auto-incrementing integer.

### ULID
ULID (Universally Unique Lexicographically Sortable Identifier) provides similar benefits to UUIDv7 but uses a 26-character Base32 representation. It's more compact and readable than the standard UUID string.

## Libraries Used
- **UUID Creator**: For generating UUID v7.
- **ULID Creator**: For generating monotonic ULIDs.

## How to Run
```bash
mvn compile exec:java
```
