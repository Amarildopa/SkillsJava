# Guardrail: Modern Identifiers (UUIDv4 vs UUIDv7 vs ULID)

## Context
Choosing the right identifier for database persistence is critical for performance and scalability. While `UUID.randomUUID()` (UUIDv4) is common, its complete randomness causes major performance issues in database indexes.

## Guardrails

### 1. Avoid UUIDv4 for Database Primary Keys
- **RULE**: NEVER use UUIDv4 (`UUID.randomUUID()`) as a primary key in B-tree indexed databases (SQL).
- **REASON**: UUIDv4 is non-sequential. This leads to B-tree fragmentation, frequent page splits, and high I/O overhead as the database grows. It also makes sharding/partitioning by range inefficient.

### 2. Avoid Pure Sequential IDs (Long/Integer) for Public APIs
- **RULE**: Avoid simple sequences like `1, 2, 3` for public-facing identifiers.
- **REASON**: They are guessable (security risk), leak business volume information (how many users you have), and create write contention in distributed systems due to centralized counter bottlenecks.

### 3. Prefer UUIDv7 for Persistence
- **RULE**: Use UUIDv7 when a standard 128-bit UUID format is required but performance matters.
- **PRACTICE**: Use a library like `uuid-creator` to generate time-ordered UUIDs.
- **REASON**: UUIDv7 is time-ordered (includes a timestamp in the most significant bits). It behaves like a sequence for the B-tree (improving locality) while remaining a unique 128-bit identifier. It is a direct swap for UUIDv4.

### 4. Consider ULID for Readability
- **RULE**: Use ULID (Universally Unique Lexicographically Sortable Identifier) if you want the benefits of UUIDv7 with better 26-character string readability (Crockford's Base32).
- **REASON**: ULIDs are monotonic, sortable, and don't include special characters like dashes, making them more URL-friendly.

---

## Reference Demo
See implementation in: `fundamentals-modern-identifiers/`
